# Kata Clean Code — VideoStore

## Objectif

Vous disposez d'une application de location de films. Elle génère un relevé contenant les films loués, leur coût, le montant total et les points de fidélité du client.

Votre objectif est de refactoriser cette application pour améliorer sa lisibilité, sa cohésion et son extensibilité, sans modifier les règles métier ni le format du relevé.

## 1. Améliorer les tests et la lisibilité

- Exécutez les tests existants avant toute modification.
- Placez les attributs en haut des classes et découpez les chaînes de caractères trop longues dans les tests.
- Séparez les tests des montants et des points de fidélité du test de présentation du relevé. Rendez les totaux accessibles après la génération du relevé et utilisez une tolérance pour les comparaisons de montants.
- Conservez un test vérifiant le texte complet du relevé. Nommez les tests de calcul avec le suffixe `Totals` et le test de présentation avec le suffixe `Format`.
- Initialisez les films dans des attributs réutilisables, avec des titres génériques, et utilisez `Customer` comme nom de client dans les tests. Adaptez le texte attendu en conséquence.

## 2. Clarifier la responsabilité du relevé

- Examinez le rôle réel de `Customer`. Renommez cette classe, ses attributs et sa méthode `statement()` pour exprimer clairement la génération d'un relevé.
- Transformez la méthode de génération en une succession d'étapes lisibles : réinitialisation des totaux, en-tête, lignes de location et pied de page.
- Extrayez également le traitement d'une location individuelle dans une méthode dédiée.
- Utilisez des noms explicites, `String.format` pour la présentation et préservez le format décimal attendu par les tests.
- Organisez les méthodes selon leur ordre d'appel (règle de lecture descendante, ou *Step Down Rule*). Supprimez les méthodes et variables devenues inutiles.

## 3. Isoler les calculs et le formatage

- Isolez le calcul du prix d'une location et celui des points de fidélité dans des méthodes distinctes retournant leurs résultats, sans modifier directement les totaux.
- Donnez un nom explicite à la condition d'attribution du point de fidélité supplémentaire.
- Regroupez les calculs avant le formatage d'une ligne de location. Isolez ce formatage dans une méthode dédiée et simplifiez les variables intermédiaires et paramètres inutiles.

## 4. Améliorer la cohésion et respecter la loi de Déméter

- Identifiez les accès chaînés aux objets, notamment `rental.getMovie().getTitle()`, et supprimez cette dépendance du relevé à la structure interne de `Rental`.
- Examinez les données utilisées par les méthodes de calcul. Déplacez les calculs du relevé vers `Rental`, puis vers `Movie` lorsque les règles dépendent du film.
- Faites en sorte que `Rental` transmette la durée de location au film et expose les résultats nécessaires au relevé. Simplifiez les accès aux attributs et les variables intermédiaires.

## 5. Remplacer les conditions par du polymorphisme

- Remplacez le `switch` de calcul du prix et les conditions liées au type de film par des comportements propres aux trois catégories de films : standard, enfant et nouveauté.
- Définissez les opérations communes dans une abstraction et répartissez les implémentations dans les sous-classes appropriées.
- Adaptez les instanciations des films dans les tests et supprimez les codes de type, les paramètres de constructeur et le code devenus inutiles. Utilisez la couverture des tests pour guider ce nettoyage.

## Résultats attendus

Les tests doivent rester valides après chaque étape. Les quatre scénarios de calcul existants doivent conserver les résultats suivants :

| Scénario | Montant total | Points |
|---|---:|---:|
| Une nouveauté louée 3 jours | 9.0 | 2 |
| Deux nouveautés louées 3 jours chacune | 18.0 | 4 |
| Un film enfant loué 3 jours | 1.5 | 1 |
| Trois films standard loués 1, 2 et 3 jours | 7.5 | 3 |

Le test de présentation doit également vérifier l'en-tête, les lignes de location et le pied de page du relevé, avec les données de test renommées. Conservez les règles tarifaires et de fidélité existantes, y compris pour les durées non couvertes par ces quatre scénarios.
