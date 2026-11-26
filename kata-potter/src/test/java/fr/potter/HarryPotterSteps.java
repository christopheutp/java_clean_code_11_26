package fr.potter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class HarryPotterSteps {
    private BookBasket basket;
    private int total;

    @Given("un panier vide")
    public void emptyBasket() {
        basket = new BookBasket();
    }

    @When("j'ajoute {int} exemplaire(s) du tome {int}")
    public void addBooks(int quantity, int volume) {
        basket.addBook(volume, quantity);
    }

    @When("je calcule le prix du panier")
    public void calculatePrice() {
        total = new PriceCalculator().calculatePrice(basket);
    }

    @Then("le prix total est de {bigdecimal} euros")
    public void checkPrice(BigDecimal expected) {
        assertEquals(expected.movePointRight(2).intValueExact(), total);
    }
}
