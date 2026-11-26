Feature: Calculer le meilleur prix d'un panier Harry Potter

  Background:
    Given un panier vide


  Scenario: Un livre coûte 8 euros
    When j'ajoute 1 exemplaire du tome 1
    And je calcule le prix du panier
    Then le prix total est de 8.00 euros


  Scenario: Deux tomes différents donnent 5 % de remise
    When j'ajoute 1 exemplaire du tome 1
    And j'ajoute 1 exemplaire du tome 2
    And je calcule le prix du panier
    Then le prix total est de 15.20 euros


  Scenario: Trois tomes différents donnent 10 % de remise
    When j'ajoute 1 exemplaire du tome 1
    And j'ajoute 1 exemplaire du tome 2
    And j'ajoute 1 exemplaire du tome 3
    And je calcule le prix du panier
    Then le prix total est de 21.60 euros


  Scenario: Quatre tomes différents donnent 20 % de remise
    When j'ajoute 1 exemplaire du tome 1
    And j'ajoute 1 exemplaire du tome 2
    And j'ajoute 1 exemplaire du tome 3
    And j'ajoute 1 exemplaire du tome 4
    And je calcule le prix du panier
    Then le prix total est de 25.60 euros


  Scenario: Cinq tomes différents donnent 25 % de remise
    When j'ajoute 1 exemplaire du tome 1
    And j'ajoute 1 exemplaire du tome 2
    And j'ajoute 1 exemplaire du tome 3
    And j'ajoute 1 exemplaire du tome 4
    And j'ajoute 1 exemplaire du tome 5
    And je calcule le prix du panier
    Then le prix total est de 30.00 euros


  Scenario: Deux exemplaires du même tome ne donnent aucune remise
    When j'ajoute 2 exemplaires du tome 1
    And je calcule le prix du panier
    Then le prix total est de 16.00 euros


  Scenario: Un doublon ne bénéficie pas de la remise du groupe
    When j'ajoute 2 exemplaires du tome 1
    And j'ajoute 1 exemplaire du tome 2
    And je calcule le prix du panier
    Then le prix total est de 23.20 euros


  Scenario: Deux groupes de quatre sont moins chers qu'un groupe de cinq et un groupe de trois
    When j'ajoute 2 exemplaires du tome 1
    And j'ajoute 2 exemplaires du tome 2
    And j'ajoute 2 exemplaires du tome 3
    And j'ajoute 1 exemplaire du tome 4
    And j'ajoute 1 exemplaire du tome 5
    And je calcule le prix du panier
    Then le prix total est de 51.20 euros


  Scenario: Un panier vide coûte zéro euro
    When je calcule le prix du panier
    Then le prix total est de 0.00 euros


  Scenario: Deux collections complètes bénéficient chacune de 25 % de remise
    When j'ajoute 2 exemplaires du tome 1
    And j'ajoute 2 exemplaires du tome 2
    And j'ajoute 2 exemplaires du tome 3
    And j'ajoute 2 exemplaires du tome 4
    And j'ajoute 2 exemplaires du tome 5
    And je calcule le prix du panier
    Then le prix total est de 60.00 euros
