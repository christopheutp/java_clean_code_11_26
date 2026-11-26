# TP Clean Code — Refactoring par AOP / Interceptors


## Contexte
ShopFlow est une API de gestion de commandes, de produits, de stocks et de paiements. Elle fonctionne mais les services mélangent métier, contrôle des droits, audit, mesure de durée et relance des appels externes. Votre mission : refactoriser sans modifier le contrat REST ni les règles métier.

## Démarrage
Ouvrir le dossier choisi dans IntelliJ en tant que projet Maven. Exécuter `mvn test` avant toute modification. Démarrer avec `mvn spring-boot:run`. API sur http://localhost:8080. Les données sont en mémoire et réinitialisées au redémarrage. Le fichier `requests.http` permet de tester les routes.

Le header `X-User` sélectionne un utilisateur de démonstration : `admin` (tous les droits), `seller` (commandes), `warehouse` (stocks), `finance` (paiements), `guest` (lecture uniquement). Ce mécanisme est volontairement simulé : il ne constitue pas une authentification réelle.

## Organisation du code
Les packages `controller`, `model`, `service`, `repository`, `security`, `audit`, `monitoring`, `payment` et `exception` sont déjà fournis. Le stockage est encapsulé dans `Store`. Créez les packages `annotation` et `aspect` (Spring).

## Travail demandé
1. Analyse : repérer les responsabilités transversales répétées, les dépendances techniques et les règles métier qui doivent rester explicites. Identifier les risques de régression.
2. Permissions : centraliser les vérifications via une annotation paramétrable. Un refus doit produire HTTP 403 avant toute modification et ne doit créer ni audit ni mesure.
3. Audit : externaliser l'enregistrement des opérations sensibles. Conserver le nom d'opération, utilisateur, horodatage et succès/échec. Une exception métier doit rester propagée. Une opération refusée ne produit pas d'audit. Un paiement avec plusieurs tentatives ne doit produire qu'un seul audit final.
4. Mesure : centraliser le chronométrage, y compris en cas d'échec, avec seuil configurable en millisecondes. Conserver les mesures consultables. La durée couvre toutes les tentatives éventuelles.
5. Retry : centraliser les tentatives pour les appels au prestataire de paiement. Seules les exceptions temporaires sont rejouées, maximum trois tentatives au total. Les erreurs définitives ne sont jamais rejouées. Le prestataire simulé échoue avant tout effet et mémorise les clés d'idempotence. Ne pas rejouer la totalité d'une opération métier contenant des mutations.
6. Vérification et débrief : exécuter les tests existants, ajouter au moins deux cas, expliquer l'ordre des aspects/interceptors, comparer la lisibilité et les dépendances avant/après.

## Règles métier et contrat à préserver
- Produit initial P1, prix 20 EUR (en centimes : 2000), stock 10. Client C1. Une commande est créée au statut NEW, quantité positive, produit et client existants. La validation réserve le stock et passe à CONFIRMED. Annuler une commande confirmée restitue le stock ; une commande payée ne peut pas être annulée. Seule une commande confirmée peut être payée, seule une commande payée peut être remboursée. Les prix sont figés à la création. Le remboursement ne remet pas le stock en vente.
- Les erreurs métier donnent 400, absence de droit 403, panne temporaire épuisée 503, refus définitif du prestataire 422.


## Contraintes
Utiliser des annotations personnalisées et Spring AOP ou CDI Interceptors. Ne pas simplement déplacer la duplication dans chaque service. Ne pas utiliser une annotation pour masquer une règle métier (stock, statut, prix). Préserver les routes et les tests. Les aspects doivent s'appliquer aux appels via les beans gérés par le framework. Ne pas ajouter d'infrastructure inutile.

## Livrables
Projet refactorisé, tests au vert, courte justification des préoccupations extraites et de leur ordre. Bonus : tester un nouvel appel annoté, ou expliquer les limites d'un appel interne à un bean selon le framework choisi.
