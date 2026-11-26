# Jour 4 — Code Smells : correction

Projet Maven Java 17. Importez le fichier `pom.xml` dans votre IDE. Pour chaque kata, identifiez les problèmes de conception et refactorez le code en préservant les règles métier.

## Kata 1 : Gestion des Commandes

Package : `org.example.kata1`

Une classe `OrderManager` gère les commandes clients. Le code est difficile à maintenir en raison de sa complexité et de sa mauvaise organisation.

**Code Smells :**
- Long Method processOrder() mélange le contrôle du stock, le calcul du prix et la mise à jour du stock.
- Duplicate Code certaines opérations sont présentes à la fois dans processOrder() et dans des méthodes privées inutilisées.

## Kata 2 : Calculateur de Prix

Package : `org.example.kata2`

Une application calcule le prix de produits en fonction de leur type et applique des taxes et remises spécifiques.

**Code Smells :**
- Switch Statements — les règles tarifaires sont regroupées dans une succession de conditions dépendant du type de produit.
- Magic Numbers — les taux de taxes et de remises sont directement inscrits dans le code.


## Kata 3 : Gestion des Employés

Package : `org.example.kata3`

Le système calcule les salaires selon le rôle des employés, leurs heures travaillées et leurs éventuels bonus.

**Code Smells :**
- Switch Statements — le calcul du salaire dépend d’une succession de conditions sur le rôle.
- Duplicate Code —  les calculs du salaire de base sont répétés.
- Magic Numbers —  les tarifs, bonus et seuils sont inscrits directement dans le code.
- Primitive Obsession —  les rôles sont représentés par de simples chaînes de caractères.

## Kata 4 : Analyse des Données

Package : `org.example.kata4`

Une classe `DataAnalyzer` lit et traite des données provenant de différentes sources.

**Code Smells :**
- Switch Statements —  une succession de conditions sélectionne la source de données.
- Duplicate Code —  le traitement commun est répété conceptuellement dans chaque branche.
- Divergent Change —  les comportements de lecture de différentes sources sont regroupés dans la même classe.


Design pattern :Strategy, sous forme fonctionnelle avec des lambdas. 

## Kata 5 : Gestion des Transactions Bancaires

Package : `org.example.kata5`

Le système bancaire gère les dépôts, retraits et virements entre comptes.

**Code Smells :**
- Switch Statements — les types de transactions sont traités dans une succession de conditions.
- Feature Envy — TransactionManager manipule directement le solde des comptes à leur place.

## Kata 6 : Système de Réservation

Package : `org.example.kata6`

Un système de réservation de salles gère différents types d’événements.

**Code Smells :**
- Switch Statements —  les opérations de réservation sont sélectionnées par une succession de conditions.
- Divergent Change —  les règles propres aux conférences, réunions et mariages sont regroupées dans la même méthode.


## Kata 7 : Gestion des Utilisateurs

Package : `org.example.kata7`

Le système gère la création des utilisateurs et l’attribution des permissions selon leur rôle.

**Code Smells :**
- Long Method — la création d’un utilisateur mélange plusieurs responsabilités.
- Switch Statements — les permissions sont attribuées par une succession de conditions sur le rôle.


## Lien Code Smell

- [Switch Statements](https://refactoring.guru/smells/switch-statements)
- [Divergent Change](https://refactoring.guru/smells/divergent-change)
- [Long Method](https://refactoring.guru/smells/long-method)
- [Duplicate Code](https://refactoring.guru/smells/duplicate-code)
- [Primitive Obsession](https://refactoring.guru/smells/primitive-obsession)
- [Feature Envy](https://refactoring.guru/smells/feature-envy)


