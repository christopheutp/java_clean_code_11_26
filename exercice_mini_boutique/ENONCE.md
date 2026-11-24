# TP — MiniBoutique : refactoring SOLID

**Contexte :** une petite boutique encaisse des commandes. Le programme est fonctionnel, mais `OrderService` effectue presque tout lui-même.


## Mission

Améliorer le projet sans changer ses résultats : une commande de 25,00 € passée par un client VIP conserve une remise de 10 %, soit **22,50 €**. L'affichage du paiement et du reçu reste disponible et une ligne est conservée dans l'historique.

1. Identifiez au moins **trois responsabilités** mélangées dans `OrderService` (**SRP**).
    - calcul du prix
    - calcul de la remise
    - paiement
    - historique

2. Extrayez le **calcul du total** dans une classe distincte.
 - creation pricing/PriceCalculator

3. Remplacez la logique de remise `VIP` / `REGULAR` par une abstraction permettant d'ajouter une nouvelle remise sans modifier le calculateur (**OCP**).

4. Isolez le paiement derrière une petite interface : le service de commande ne doit plus connaître les détails de paiement carte ou espèces (**DIP**).
5. Sortez l'affichage du reçu et l'enregistrement de l'historique de la classe de commande (**SRP / DIP**).
6. Vérifiez que l'application produit les mêmes résultats. Ajoutez, si vous le pouvez, une remise de **20 %** sans modifier le calculateur.

**Important :** inutile de fabriquer de l'héritage pour illustrer LSP ni de multiplier les interfaces pour ISP. Le but est d'améliorer ce projet, pas de cocher artificiellement les cinq lettres de SOLID.

