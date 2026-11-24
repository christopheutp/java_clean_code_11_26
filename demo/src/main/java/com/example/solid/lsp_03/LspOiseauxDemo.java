package com.example.solid.lsp_03;

public class LspOiseauxDemo {
    // CODE PROBLEMATIQUE
    static class BirdAvant { void fly(){System.out.println("vole");} }
    static class SparrowAvant extends BirdAvant { }
    static class OstrichAvant extends BirdAvant {
        @Override void fly(){throw new UnsupportedOperationException("autruche ne vole pas");}
    }
    static void demanderVol(BirdAvant b){b.fly();}

    // REFACTO
    interface BirdApres {String move();}
    static class SparrowApres implements BirdApres {public String move(){return "vole";}}
    static class OstrichApres implements BirdApres {public String move(){return "court";}}
    static void observer(BirdApres b){System.out.println(b.move());}
    // Attention : un client ayant reellement besoin de voler doit demander une interface Flyable.

    public static void main(String[] args) {
        demanderVol(new SparrowAvant());
        try {demanderVol(new OstrichAvant());} catch (UnsupportedOperationException e) {System.out.println("AVANT : violation du contrat : "+e.getMessage());}
        observer(new SparrowApres()); observer(new OstrichApres());
    }
}
