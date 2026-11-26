package com.example.chain;



public class OpenDoorHandler extends Handler {
    @Override protected boolean check(AccessRequest request) {
        System.out.println("Porte ouverte pour " + request.user());
        return true;
    }
}
