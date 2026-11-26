# language: fr

Fonctionnalité: Vérification des règles de retrait

  Plan du scénario: Retirer de l'argent
    Étant donné un compte avec un solde de <solde> euros
    Quand le client retire <montant> euros
    Alors le montant distribué est de <distribution> euros
    Et le solde restant est de <reste> euros

    Exemples:
      | solde | montant | distribution | reste |
      | 100   | 50      | 50           | 50    |
      | 100   | 150     | 0            | 100   |
      | 100   | 100     | 100          | 0     |
      | 0     | 50      | 0            | 0     |