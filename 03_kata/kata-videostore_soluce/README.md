# Kata VideoStore — Correction

L'objectif de ce kata est d'améliorer un code legacy en appliquant les principes du Clean Code, tout en conservant les règles de tarification et de calcul des points de fidélité.

## 1. Amélioration des tests unitaires

**Travail effectué dans `VideoStoreTest.java` :**
- Séparation des tests de calcul et des tests de formatage du relevé.
- Vérification directe des montants et des points de fidélité.
- Centralisation des données de test dans `setUp()`.
- Utilisation de noms de films génériques et de noms de méthodes plus explicites.

**Objectif :** rendre les tests plus lisibles et moins dépendants du format d'affichage.

## 2. Nommage et lisibilité

- Renommage de `Customer.java` en `Statement.java`, car cette classe génère un relevé de locations et ne représente pas réellement un client.
- Renommage de `statement()` en `generate()`.
- Utilisation de noms de variables plus explicites.
- Déplacement des attributs en haut des classes.

**Objectif :** utiliser des noms et une organisation qui révèlent clairement les intentions du code.

## 3. Extraction de méthodes (Extract Method)

La méthode `generate()` de `Statement` a été décomposée en plusieurs méthodes spécialisées :

- `clearTotals()` : réinitialisation des totaux.
- `createHeader()` : création de l'en-tête.
- `createRentalLines()` : génération des lignes de location.
- `createRentalLine()` : traitement d'une location.
- `formatRentalLine()` : formatage d'une ligne.
- `createFooter()` : création du pied de relevé.

Les méthodes sont organisées selon la règle du Step Down et le formatage utilise `String.format()`.

**Objectif :** améliorer la lisibilité et respecter les niveaux d'abstraction.

## 4. Respect de la loi de Déméter

Remplacement de `rental.getMovie().getTitle()` par `rental.getTitle()`.

La classe `Rental.java` expose directement les informations nécessaires sans obliger `Statement` à connaître sa structure interne.

**Objectif :** réduire le couplage entre les classes.

## 5. Cohésion et Single Responsibility Principle (SRP)

Déplacement des méthodes `determineAmount()` et `determineFrequentRenterPoints()` de `Statement` vers `Rental`, puis délégation des règles de calcul à `Movie`.

Les responsabilités sont désormais réparties ainsi :

- `Statement.java` : génération du relevé et consolidation des totaux.
- `Rental.java` : représentation d'une location et délégation des calculs.
- `Movie.java` : définition du contrat de calcul des tarifs et des points de fidélité.

**Objectif :** placer chaque comportement dans la classe la plus légitime pour le gérer.

## 6. Remplacement des conditions par du polymorphisme

La classe `Movie` devient abstraite et les codes numériques représentant les catégories de films sont supprimés.

Trois nouvelles classes sont introduites :

- `RegularMovie.java` : tarification des films classiques.
- `NewReleaseMovie.java` : tarification des nouveautés et gestion du bonus de fidélité.
- `ChildrenMovie.java` : tarification des films pour enfants.

Chaque classe implémente ses propres méthodes de calcul du montant et des points de fidélité.

**Objectif :** supprimer le `switch`, exploiter le polymorphisme et faciliter l'ajout de nouvelles catégories de films conformément au principe OCP.

## 7. Suppression du code inutile

- Suppression des codes numériques des catégories de films.
- Suppression des getters et setters devenus inutiles.
- Suppression des paramètres et variables redondants.
- Simplification des constructeurs.

**Objectif :** réduire la complexité et ne conserver que le code nécessaire.

## 8. Ajout complémentaire — Design pattern Observer

Deux interfaces supplémentaires ont été introduites :

- `Observer.java` : contrat des objets recevant les notifications.
- `Subject.java` : contrat de gestion et de notification des observateurs.

`Statement` implémente `Subject` afin de permettre la notification d'autres composants.

**Objectif :** permettre à des composants externes de réagir à la génération d'un relevé sans créer de dépendances directes.

Cet ajout n'était pas demandé dans le sujet original et reste inachevé : l'appel à `notify()` dans `generate()` est actuellement commenté.

## Vérification

Les quatre scénarios de calcul initiaux sont conservés et un cinquième test vérifie le format du relevé.

```bash
mvn test
```

Le refactoring améliore la lisibilité, la cohésion, le découplage et l'évolutivité du programme tout en cherchant à préserver son comportement fonctionnel.