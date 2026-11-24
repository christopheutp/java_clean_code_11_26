package com.example.solid.srp_01;

public class SrpRapportDemo {

    // Avant => code problematique
    static class Avant {
        void genererRapport(int ventes) {
            int total = ventes * 10;                  // règle métier
            System.out.println("DB: " + total);      // persistance mêlée
            System.out.println("PDF: " + total);     // présentation mêlée
        }
    }

    // refactoriser
    interface Stockage {
        void enregistrer(int total);
    }
    interface Export { void produire(int total); }

    static class ConsoleStockage implements Stockage {
        @Override
        public void enregistrer(int total) {
            System.out.println("DB: " + total);
        }
    }
    static class ConsoleExport implements Export {
        public void produire(int total) { System.out.println("PDF: " + total); }
    }

    static class Apres {
        private final Stockage stockage;
        private final Export export;
        Apres(Stockage s, Export e) {stockage=s; export=e;}
        void genererRapport(int ventes) {
            int total = ventes * 10;                  // reste une seule règle métier
            stockage.enregistrer(total);              // délégation
            export.produire(total);
        }

    }


    public static void main(String[] args) {
        System.out.println("--- AVANT ---"); new Avant().genererRapport(3);
        System.out.println("--- APRES ---"); new Apres(new ConsoleStockage(),new ConsoleExport()).genererRapport(3);
    }
}
