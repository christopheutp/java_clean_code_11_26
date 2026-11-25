# language: fr

Fonctionnalité: Gestion des opérations bancaires

  Scénario: Effectuer des dépôts et des retraits
    Étant donné un compte avec un solde de 100 euros
    Quand le client effectue les opérations suivantes
      | operation | montant |
      | retrait   | 20      |
      | depot     | 50      |
      | retrait   | 10      |
    Alors le solde restant est de 120 euros