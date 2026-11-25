package demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Quand;

import java.util.Map;

public class RetraitSteps {
    private Compte compte;
    private int montantDistribue;

    @Given("un compte avec un solde de {int} euros")
    public void initialiserCompte(int solde) {
        compte = new Compte(solde);
    }

    @When("le client retire {int} euros")
    public void retirer(int montant) {
        montantDistribue = compte.retirer(montant);
    }

    @Then("le montant distribué est de {int} euros")
    public void verifierMontant(int montant) {
        assertEquals(montant, montantDistribue);
    }

    @Then("le solde restant est de {int} euros")
    public void verifierSolde(int solde) {
        assertEquals(solde, compte.getSolde());
    }

    @When("le client effectue les retraits suivants")
    public void effectuerRetraits(DataTable table) {

        for (Map<String, String> ligne : table.asMaps()) {
            int montant = Integer.parseInt(ligne.get("montant"));
            compte.retirer(montant);
        }
    }

    @When("le client effectue les opérations suivantes")
    public void effectuerOperations(DataTable table) {

        for (Map<String, String> ligne : table.asMaps()) {

            String operation = ligne.get("operation");
            int montant = Integer.parseInt(ligne.get("montant"));

            switch (operation) {
                case "retrait" -> compte.retirer(montant);
                case "depot" -> compte.deposer(montant);
                default -> throw new IllegalArgumentException(
                        "Opération inconnue : " + operation);
            }
        }
    }


    @Quand("le client effectue {int} retraits de {int} euros")
    public void leClientEffectueRetraitsDeEuros(int nombre, int montant) {
        for (int i = 0; i < nombre; i++) {
            compte.retirer(montant);
        }
    }

}
