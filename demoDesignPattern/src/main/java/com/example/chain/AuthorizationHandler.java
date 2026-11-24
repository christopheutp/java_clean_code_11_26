package com.example.chain;



public class AuthorizationHandler extends Handler {
    @Override protected boolean check(AccessRequest request) {
        if (!request.authorized()) { System.out.println("Refus : droit insuffisant"); return false; }
        return true;
    }
}
