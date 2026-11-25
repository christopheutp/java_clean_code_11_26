# language: fr
Fonctionnalité: Retrait bancaire

  Scénario: Retrait avec un solde suffisant
    Étant donné un compte avec un solde de 100 euros
    Quand le client retire 50 euros
    Alors le montant distribué est de 50 euros
    Et le solde restant est de 50 euros

  Scénario: Retrait avec un solde insuffisant
    Étant donné un compte avec un solde de 100 euros
    Quand le client retire 150 euros
    Alors le montant distribué est de 0 euros
    Et le solde restant est de 100 euros


  Scénario: Plusieurs retraits sur un même compte
    Étant donné un compte avec un solde de 100 euros
    Quand le client effectue les retraits suivants
      | montant |
      | 20      |
      | 30      |
      | 10      |
    Alors le solde restant est de 40 euros
