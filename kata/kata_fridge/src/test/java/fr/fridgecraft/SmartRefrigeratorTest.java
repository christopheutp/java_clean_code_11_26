package fr.fridgecraft;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class SmartRefrigeratorTest {
    private final Clock clock = Clock.fixed(Instant.parse("2026-11-25T08:00:00Z"), ZoneOffset.UTC);
    private final LocalDateTime now = LocalDateTime.now(clock);
    private SmartRefrigerator fridge = new SmartRefrigerator(clock);


    @BeforeEach
    void setUp() {
        fridge = new SmartRefrigerator(clock);
    }

    // Exigence 1 : suivre les entrées et sorties.
    @Test
    @DisplayName("Le réfrigérateur doit être vide à sa création")
    void shouldBeEmptyInitially() {
        assertTrue(fridge.isEmpty());
    }

    @Test
    @DisplayName("Les produits de même nom doivent pouvoir être ajoutés et retirés individuellement grâce à leur identifiant")
    void shouldAddAndRemoveOneArticleAmongIdenticalNames() {
        UUID first = fridge.addItem("Lait",now.plusDays(2),PackagingState.SEALED);
        UUID second = fridge.addItem("Lait",now.plusDays(3),PackagingState.SEALED);
        assertFalse(fridge.isEmpty());
        assertEquals(2,fridge.getItems().size());
        assertTrue(fridge.removeItem(first));
        assertEquals(second, fridge.getItems().get(0).id());
        assertFalse(fridge.removeItem(first));
        assertTrue(fridge.removeItem(second));
        assertTrue(fridge.isEmpty());
    }

    // Exigence 2 : capturer nom, expiration et horodatage.
    @Test
    @DisplayName("L'ajout d'un produit doit enregistrer son nom, sa date de péremption, sa date d'ajout et l'état de son emballage")
    void shouldCaptureArticleInformationAutomatically() {
        fridge.addItem("Lait",now.plusDays(3),PackagingState.SEALED);
        FoodItem item = fridge.getItems().get(0);
        assertEquals("Lait",item.name());
        assertEquals(now.plusDays(3),item.expirationDate());
        assertEquals(now,item.addedAt());
        assertEquals(PackagingState.SEALED,item.packagingState());
    }

    @Test
    @DisplayName("La liste des produits ne doit pas être modifiable depuis l'extérieur")
    void shouldProtectInventoryFromExternalModification() {
        assertThrows(UnsupportedOperationException.class, () -> fridge.getItems().add(null));
    }

    // Exigence 3 : dégrader de 1 h ou 5 h à chaque ouverture.
    @Test
    @DisplayName("Chaque ouverture doit réduire la conservation de 1 heure pour les produits scellés et de 5 heures pour les produits ouverts, sans modifier leur date d'ajout")
    void shouldApplyDifferentPenaltiesOnRepeatedOpenings() {
        fridge.addItem("Lait",now.plusDays(3),PackagingState.SEALED);
        fridge.addItem("Beurre",now.plusDays(3),PackagingState.OPENED);
        fridge.open();
        assertEquals(now.plusDays(3).minusHours(1),fridge.getItems().get(0).expirationDate());
        assertEquals(now.plusDays(3).minusHours(5),fridge.getItems().get(1).expirationDate());
        fridge.open();
        assertEquals(now.plusDays(3).minusHours(2),fridge.getItems().get(0).expirationDate());
        assertEquals(now.plusDays(3).minusHours(10),fridge.getItems().get(1).expirationDate());
    }

    @Test
    @DisplayName("Un produit ajouté après une ouverture ne doit pas subir les pénalités des ouvertures précédentes")
    void shouldNotPenalizeArticlesAddedAfterOpening() {
        fridge.open();
        fridge.addItem("Lait",now.plusDays(2),PackagingState.SEALED);
        assertEquals(now.plusDays(2),fridge.getItems().get(0).expirationDate());

    }

    // Exigence 4 : format affichage exact, articles expirés en premier.
    @Test
    @DisplayName("Les produits périmés doivent être affichés en premier, suivis des produits encore consommables avec leurs jours restants")
    void shouldDisplayExpiredArticlesBeforeRemainingArticles() {
        fridge.addItem("Lait",now.plusDays(2),PackagingState.SEALED);
        fridge.addItem("Yaourt",now.minusHours(1),PackagingState.OPENED);
        fridge.addItem("Fromage",now.plusDays(5),PackagingState.SEALED);
        fridge.addItem("Viande",now,PackagingState.OPENED);
        assertEquals("EXPIRÉ : Yaourt\nEXPIRÉ : Viande\nLait : 2 jour(s) restant(s)\nFromage : 5 jour(s) restant(s)",
                fridge.displayContents());
    }

    @Test
    @DisplayName("Un produit encore consommable pendant moins de 24 heures doit afficher 0 jour restant")
    void shouldDisplayZeroDaysForLessThan24Hours() {
        fridge.addItem("Lait",now.plusHours(23),PackagingState.SEALED);
        assertEquals("Lait : 0 jour(s) restant(s)",fridge.displayContents());
    }

    @Test
    @DisplayName("Un produit doit être considéré comme périmé si une ouverture ramène sa date de péremption à la date actuelle")
    void shouldBecomeExpiredAfterOpening() {
        fridge.addItem("Yaourt",now.plusHours(5),PackagingState.OPENED);
        fridge.open();
        assertEquals("EXPIRÉ : Yaourt",fridge.displayContents());
    }

    @Test
    @DisplayName("L'affichage du contenu d'un réfrigérateur vide doit retourner une chaîne vide")
    void shouldDisplayEmptyStringForEmptyFridge() {
        assertEquals("",fridge.displayContents());
    }
}
