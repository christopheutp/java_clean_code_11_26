# Démo BDD — Java 17 / Cucumber
Ouvrir le pom.xml dans IntelliJ comme projet Maven, puis lancer RunCucumberTest ou `mvn test` (Maven et accès aux dépendances nécessaires).
Le projet est livré en version GREEN. Pour la démo RED, remplacer uniquement le corps de Compte.retirer par `return 0;` : le scénario accepté échoue. Restaurer le corps fourni : les deux scénarios doivent réussir.
Les annotations Java anglaises fonctionnent avec les scénarios français : Cucumber associe le texte des étapes, indépendamment de la langue des mots-clés.
Les montants sont des euros entiers pour simplifier la démo.
