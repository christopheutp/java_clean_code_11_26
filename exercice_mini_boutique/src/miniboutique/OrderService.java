package miniboutique;

import java.util.ArrayList;
import java.util.List;

// CODE DE DEPART : il fonctionne, mais plusieurs responsabilités sont mélangées.
// Le but du TP est de le refactorer sans changer les résultats observables.
public class OrderService {
    private final List<String> history = new ArrayList<>();

    public int checkout(Order order, String customerType, String paymentMethod) {
        // Calcul métier.
        int subtotal = 0;
        for (Product product : order.products()) {
            subtotal += product.unitPriceCents();
        }

        // Une nouvelle catégorie de client nécessite de modifier cette méthode.
        int discountPercent;
        if (customerType.equals("VIP")) {
            discountPercent = 10;
        } else if (customerType.equals("REGULAR")) {
            discountPercent = 0;
        } else {
            throw new IllegalArgumentException("Type de client inconnu");
        }
        int total = subtotal * (100 - discountPercent) / 100;

        // Une nouvelle méthode de paiement nécessite aussi de modifier cette méthode.
        if (paymentMethod.equals("CARD")) {
            System.out.println("Paiement CARTE : " + total + " centimes");
        } else if (paymentMethod.equals("CASH")) {
            System.out.println("Paiement ESPECES : " + total + " centimes");
        } else {
            throw new IllegalArgumentException("Moyen de paiement inconnu");
        }

        // Persistance simulée, puis présentation du reçu.
        history.add(order.customerEmail() + ":" + total);
        System.out.println("Recu pour " + order.customerEmail() + " : " + total + " centimes");
        return total;
    }

    public List<String> history() {
        return List.copyOf(history);
    }
}
