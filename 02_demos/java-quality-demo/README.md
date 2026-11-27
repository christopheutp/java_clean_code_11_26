# Java Quality Demo

## 1. Checkstyle — Conventions de code

Contrôle les conventions d’écriture : nommage, imports, formatage et structure syntaxique.

```bash
mvn checkstyle:checkstyle
```

- Rapport : `target/site/checkstyle.html`

Configuration à ajouter dans la section `<build><plugins>` du `pom.xml` :

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.6.0</version>
    <configuration>
        <configLocation>${project.basedir}/checkstyle.xml</configLocation>
        <includeTestSourceDirectory>false</includeTestSourceDirectory>
        <consoleOutput>false</consoleOutput>
        <outputDirectory>${project.build.directory}/site</outputDirectory>
        <linkXRef>false</linkXRef>
    </configuration>
</plugin>
```

Le fichier `checkstyle.xml`, placé à la racine du projet, définit les règles à appliquer. Il permet d'ajouter, de supprimer ou de paramétrer les règles disponibles.

**Références :** [Site officiel](https://checkstyle.org/) · [Catalogue des règles](https://checkstyle.org/checks.html) · [Plugin Maven](https://maven.apache.org/plugins/maven-checkstyle-plugin/)

## 2. PMD — Mauvaises pratiques et complexité

Analyse le code source pour détecter notamment des constructions inutiles, une mauvaise gestion des exceptions ou une complexité excessive.

```bash
mvn compile pmd:pmd
```

- Rapport : `target/site/pmd.html`

Configuration à ajouter dans la section `<build><plugins>` du `pom.xml` :

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-pmd-plugin</artifactId>
    <version>3.26.0</version>
    <configuration>
        <rulesets>
            <ruleset>${project.basedir}/pmd-ruleset.xml</ruleset>
        </rulesets>
        <includeTests>false</includeTests>
        <outputDirectory>${project.build.directory}/site</outputDirectory>
        <linkXRef>false</linkXRef>
    </configuration>
</plugin>
```

Le fichier `pmd-ruleset.xml`, placé à la racine du projet, définit les règles d'analyse à appliquer. Il permet de sélectionner les règles et de personnaliser leurs paramètres.

**Références :** [Site officiel](https://pmd.github.io/) · [Catalogue des règles Java](https://docs.pmd-code.org/latest/pmd_rules_java.html) · [Plugin Maven](https://maven.apache.org/plugins/maven-pmd-plugin/)

## 3. SpotBugs — Bugs potentiels

Analyse le bytecode Java compilé pour détecter des erreurs potentielles et certaines inefficacités.

Rapport HTML :

```bash
mvn compile spotbugs:spotbugs
```

- Rapport : `target/site/spotbugs.html` ou `target/spotbugs.html` (chercher dans `target`).

Interface graphique :

```bash
mvn compile spotbugs:gui
```

**Références :** [Site officiel](https://spotbugs.github.io/) [Catalogue des anomalies](https://spotbugs.readthedocs.io/en/stable/bugDescriptions.html)

## 4. JaCoCo — Couverture des tests

Mesure les instructions, lignes et branches exécutées pendant les tests. Ne garantit ni la pertinence des assertions ni l’absence de bugs.

```bash
mvn verify
```

- Rapport : `target/site/jacoco/index.html`

**Références :** [Site officiel](https://www.jacoco.org/jacoco/)

## 5. IntelliJ IDEA — Inspections et corrections

Détecte les problèmes dans l’éditeur et propose certaines corrections automatiques.

- Analyse du projet : **Code → Inspect Code… → Whole project → Analyze** (ou rechercher `Inspect Code` avec `Ctrl + Shift + A`).
- Paramétrage : **Settings → Editor → Inspections**.

**Références :** [Inspections](https://www.jetbrains.com/help/idea/code-inspection.html) [Lancer une inspection](https://www.jetbrains.com/help/idea/running-inspections.html) [Profils d’inspection](https://www.jetbrains.com/help/idea/customizing-profiles.html)