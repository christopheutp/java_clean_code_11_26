# Code Smells

Pour chaque kata, identifiez les problèmes de conception et refactorez le code en préservant les règles métier.

## Kata 1 : Gestion des Commandes

Package : `org.example.kata1`

Vous disposez d'une classe `OrderManager` qui gère les commandes clients. Le code actuel est difficile à maintenir en
raison de sa complexité et de sa mauvaise organisation.

## Kata 2 : Calculateur de Prix

Package : `org.example.kata2`

Une application calcule le prix de produits en fonction de leur type et applique des remises spécifiques. Le code actuel
utilise de nombreux `if` et `switch` et est difficile à maintenir.

Version polymorphique : `org.example.kata2.polymorphisme`.

## Kata 3 : Gestion des Employés

Package : `org.example.kata3`

Le système de gestion des employés doit calculer les salaires en fonction du type d'employé et de leurs heures
travaillées. Le code est complexe et contient des duplications.

Version polymorphique : `org.example.kata3.polymorphisme`.

## Kata 4 : Analyse des Données

Package : `org.example.kata4`

Une classe `DataAnalyzer` lit des données à partir de différentes sources et les traite. Le code est rempli de
duplications et ne suit pas les principes de conception.

## Kata 5 : Gestion des Transactions Bancaires

Package : `org.example.kata5`

Le système bancaire gère les transactions entre comptes. Le code actuel a des problèmes de couplage et de complexité, ce
qui rend difficile l'ajout de nouveaux types de transactions.

## Kata 6 : Système de Réservation

Package : `org.example.kata6`

Un système de réservation de salles doit gérer les réservations pour différents types d'événements. Le code contient des
structures conditionnelles complexes et est difficile à maintenir.

## Kata 7 : Gestion des Utilisateurs

Package : `org.example.kata7`

Le système gère les utilisateurs avec différents rôles et permissions. Le code actuel est mal organisé et mélange les
responsabilités.
