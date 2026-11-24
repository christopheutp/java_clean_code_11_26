package com.example.chain;



public record AccessRequest(String user, boolean authenticated, boolean authorized) {}
