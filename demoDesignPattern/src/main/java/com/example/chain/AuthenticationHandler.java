package com.example.chain;



public class AuthenticationHandler extends Handler {
    @Override protected boolean check(AccessRequest request) {
        if (!request.authenticated()) { System.out.println("Refus : non connecté"); return false; }
        return true;
    }
}
