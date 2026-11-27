# TP ShopFlow — Correction Spring AOP

L'objectif du refactoring est de séparer les préoccupations transversales de la logique métier en utilisant la programmation orientée aspect (AOP) avec Spring Boot.

## 1. Analyse du code existant

Quatre traitements transversaux étaient répétés dans les services métier :

- La vérification des permissions.
- L'audit des opérations.
- La mesure des performances.
- La gestion des nouvelles tentatives d'appel au prestataire de paiement (retry).

Ces traitements ont été externalisés afin de respecter les principes SRP, DRY et Separation of Concerns.

## 2. Centralisation des permissions

**Fichiers créés :**
- `RequiresPermission.java` : annotation permettant de déclarer la permission nécessaire.
- `RequiresPermissionAspect.java` : aspect interceptant la méthode et déléguant la vérification à `PermissionService`.

Les appels répétitifs à `PermissionService` sont remplacés par une annotation :

```java
@RequiresPermission("ORDER")
```

Si l'utilisateur ne possède pas la permission nécessaire, la méthode métier n'est pas exécutée.

## 3. Centralisation de l'audit

**Fichiers créés :**
- `Auditable.java` : annotation permettant d'indiquer l'opération à enregistrer.
- `AuditableAspect.java` : aspect interceptant l'exécution et déléguant l'enregistrement à `AuditService`.

```java
@Auditable("CREATE_ORDER")
```

L'aspect gère automatiquement l'enregistrement des succès et des échecs. Les services métier n'ont plus à gérer directement l'audit ni les blocs `try/catch` associés.

Les exceptions restent propagées afin de conserver le comportement initial.

## 4. Centralisation de la mesure des performances

**Fichiers créés :**
- `MonitorPerformance.java` : annotation permettant de définir l'opération surveillée et son seuil de durée.
- `MonitorPerformanceAspect.java` : aspect mesurant le temps d'exécution et transmettant le résultat à `MetricsService`.

```java
@MonitorPerformance(operation = "CREATE_ORDER", thresholdMs = 500)
```

L'utilisation de `System.nanoTime()` et d'un bloc `finally` permet de mesurer la durée d'exécution, même en cas d'exception.

Le chronométrage est ainsi supprimé des méthodes métier.

## 5. Centralisation du mécanisme de Retry

**Fichiers créés :**
- `Retryable.java` : annotation définissant le nombre maximal de tentatives.
- `RetryableAspect.java` : aspect réexécutant la méthode en cas de `TemporaryPaymentException`.

**Fichier modifié :**
- `PaymentGateway.java` : remplacement de la boucle de retry par l'annotation.

```java
@Retryable(maxAttempts = 3)
```

Seules les erreurs temporaires déclenchent une nouvelle tentative.

Le mécanisme est appliqué uniquement aux appels au prestataire externe afin d'éviter de répéter les modifications métier concernant les commandes ou les stocks.

## 6. Ordre d'exécution des aspects

L'annotation Spring `@Order` permet de définir la priorité des aspects.

| Priorité | Aspect | Justification |
|---|---|---|
| 10 | `RequiresPermissionAspect` | Vérifier les droits avant toute opération. |
| 20 | `AuditableAspect` | Enregistrer le résultat final de l'opération. |
| 30 | `MonitorPerformanceAspect` | Mesurer la durée complète du traitement. |
| 40 | `RetryableAspect` | Gérer les nouvelles tentatives lorsque nécessaire. |

## 7. Simplification des services métier

Les classes suivantes ont été refactorisées :

- `OrderService.java`
- `ProductService.java`
- `StockService.java`
- `PaymentService.java`

Les traitements transversaux et les dépendances techniques associées sont remplacés par des annotations.

```java
@RequiresPermission("PAYMENT")
@Auditable("PAY")
@MonitorPerformance(operation = "PAY", thresholdMs = 800)
```

Les véritables règles métier, telles que la vérification du statut d'une commande, restent dans les services.

## 8. Organisation des nouveaux fichiers

Huit fichiers Java ont été introduits, répartis dans deux packages :

```text
shopflow/
├── annotation/
│   ├── RequiresPermission.java
│   ├── Auditable.java
│   ├── MonitorPerformance.java
│   └── Retryable.java
└── aspect/
    ├── RequiresPermissionAspect.java
    ├── AuditableAspect.java
    ├── MonitorPerformanceAspect.java
    └── RetryableAspect.java
```

Les annotations déclarent les comportements attendus, tandis que les aspects en assurent l'exécution.

## 9. Tests de non-régression

Les sept scénarios de `ShopFlowTest.java` sont conservés afin de vérifier le comportement fonctionnel après le refactoring.

```bash
mvn test
```

## Bilan

| Principe | Application dans la correction |
|---|---|
| SRP | Séparation des responsabilités métier et techniques. |
| DRY | Suppression des traitements transversaux dupliqués. |
| OCP | Application des aspects à de nouvelles méthodes par annotation. |
| SoC | Séparation des préoccupations transversales. |
| Lisibilité | Services recentrés sur leurs responsabilités métier. |

L'utilisation de Spring AOP permet de centraliser les traitements techniques sans encombrer les méthodes métier ni modifier volontairement leur comportement fonctionnel.