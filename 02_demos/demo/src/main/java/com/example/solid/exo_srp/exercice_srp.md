# Exercice B : service bancaire (SRP)
1. Ouvrir `SrpBanqueExercice.java`. Identifier la responsabilité de stockage des montants et celle de présentation du relevé.
2. Extraire un composant `StatementPrinter` : le service bancaire doit le recevoir plutôt que contenir le format de sortie.
3. Conserver l'ordre des opérations et la sortie `Solde: 15` pour le scénario de test.
4. Facultatif : ajouter un test avec un imprimeur factice. 