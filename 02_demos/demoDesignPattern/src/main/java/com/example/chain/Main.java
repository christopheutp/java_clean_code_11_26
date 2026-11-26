package com.example.chain;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : créer le premier maillon.
        Handler chain = new AuthenticationHandler();

        // ÉTAPE B : relier les contrôles dans leur ordre d'exécution.
        chain.then(new AuthorizationHandler()).then(new OpenDoorHandler());

        // ÉTAPE C : une demande autorisée traverse tous les maillons.
        chain.handle(new AccessRequest("Alice", true, true));

        // ÉTAPE D : une demande sans droit s'arrête avant l'ouverture.
        chain.handle(new AccessRequest("Bob", true, false));


    }
}
