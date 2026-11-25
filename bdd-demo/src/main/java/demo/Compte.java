package demo;

public class Compte {
    private int solde;

    public Compte(int solde) {
        this.solde = solde;
    }

    public int getSolde() {
        return solde;
    }

    public int retirer(int montant) {
        if (montant <= 0 || montant > solde) {
            return 0;
        }
        solde -= montant;
        return montant;
    }

    public void deposer(int montant) {
        if (montant > 0) {
            solde += montant;
        }
    }
}
