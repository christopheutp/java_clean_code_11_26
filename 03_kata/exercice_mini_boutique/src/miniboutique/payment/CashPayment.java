package miniboutique.payment;

public class CashPayment implements PaymentMethod {
    @Override
    public void pay(int amountCents) {
        System.out.println("Paiement ESPECES : " + amountCents + " centimes");
    }
}
