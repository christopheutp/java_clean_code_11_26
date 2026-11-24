package com.example.solid.ocp_02;

public class OcpLivraisonComparaison {
    // CODE PROBLEMATIQUE
    static class Avant {
        int calculer(String mode, int poids) {
            return switch(mode) {
                case "STANDARD" -> poids*2 + 5;
                case "EXPRESS" -> poids*2 + 12;
                default -> throw new IllegalArgumentException("mode inconnu");
            }; // Ajouter un mode oblige a MODIFIER cette methode centrale.
        }
    }

    // REFACTO
    interface TarifLivraison { int calculer(int poids); }
    static class Standard implements TarifLivraison {public int calculer(int p){return p*2+5;}}
    static class Express implements TarifLivraison {public int calculer(int p){return p*2+12;}}
    static class PointRelais implements TarifLivraison {public int calculer(int p){return p*2+3;}}
    static class Apres {
        int calculer(TarifLivraison tarif,int poids){return tarif.calculer(poids);}
        // Ajouter une classe PointRelais ne modifie pas cette methode.
    }

    public static void main(String[] args) {
        int avant=new Avant().calculer("EXPRESS",2);
        int apres=new Apres().calculer(new Express(),2);
        System.out.println("Avant="+avant+" / Apres="+apres);
        if(avant!=apres)throw new AssertionError("comportement modifie");
        System.out.println("NOUVEAU sans modifier Apres="+new Apres().calculer(new PointRelais(),2));
    }
}
