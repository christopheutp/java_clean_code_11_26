package miniboutique.payment;

public class CardPayment implements PaymentMethod {
    @Override
    public void pay(int amountCents) {
        System.out.println("Paiement CARTE : " + amountCents + " centimes");
    }
}
