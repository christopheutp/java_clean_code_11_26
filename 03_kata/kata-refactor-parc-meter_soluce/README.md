## Correction du kata ParcMeter

### 1. Primitive Obsession

Remplacement des types primitifs et génériques (`String`, `double`, `long`) utilisés pour représenter des concepts métier par des types spécialisés.

Introduction de `VehicleType`, `ParkingDuration`, `Price` et `PromoCode`.

### 2. Extraction des Value Objects

Encapsulation des données et de leurs règles métier dans des types dédiés :

- `ParkingDuration.java` : gestion de la durée, de la gratuité et des heures facturables.
- `Price.java` : gestion des montants monétaires, des opérations et des arrondis.
- `PromoCode.java` : reconnaissance du code promotionnel et application de sa remise.
- `VehicleType.java` : énumération des types de véhicules et de leurs tarifs associés.

### 3. Remplacement des Magic Numbers

Introduction de constantes explicites et centralisation des valeurs métier :

- `ParkingDuration` : gratuité et conversion des minutes en heures.
- `ParkingSession` : plafond journalier.
- `VehicleType` : tarifs horaires.
- `WeekendDiscount` et `PromoCode` : taux de remise.

### 4. Strategy Pattern pour les remises

Création de trois fichiers permettant d'isoler les comportements de remise :

- `DiscountCalculator.java` : interface commune des stratégies.
- `WeekendDiscount.java` : application de la remise week-end.
- `NoDiscount.java` : comportement sans remise.

`ParkingSession` sélectionne la stratégie appropriée et lui délègue le calcul.

### 5. Single Responsibility Principle (SRP)

Répartition des responsabilités entre les différentes classes :

- `ParkingSession.java` : nouvelle classe orchestrant le calcul tarifaire.
- `ParkingService.java` : adaptation des données et délégation du calcul.
- `InvoicePrinter.java` : présentation de la facture.
- `Session.java` : conservation du modèle historique pour assurer la compatibilité.

Les règles spécifiques sont déléguées aux Value Objects et aux stratégies précédemment introduits.

### 6. Code mort

- Suppression de la méthode inutilisée `legacy()`.
- Suppression de la condition sans effet concernant la zone.
- Suppression de `PriceUtils.java` et centralisation des arrondis dans `Price`.

### 7. Amélioration du flux de calcul

La méthode `calculateCost()` de `ParkingSession` présente désormais les étapes du calcul dans un ordre explicite : durée, gratuité, tarif de base, remises et plafond journalier.

Chaque traitement est délégué à une méthode ou à une classe spécialisée.

---

### Améliorations complémentaires

Les éléments suivants ont été ajoutés en complément des demandes initiales :

- `ParkingException.java` : exception métier commune.
- `InvalidTimeRangeException.java` : gestion des dates invalides.
- `InvalidSessionException.java` : exception prévue pour les sessions invalides, actuellement inutilisée.
- `Logger.java` : abstraction de la journalisation.
- `ConsoleLogger.java` : implémentation de la journalisation.
- `ParkingSessionTest.java` : tests du nouveau modèle métier.

Les six cas d'acceptation initiaux restent la référence pour vérifier l'absence de régression avec `mvn test`.