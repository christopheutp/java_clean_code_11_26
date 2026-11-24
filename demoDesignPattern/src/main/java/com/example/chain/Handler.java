package com.example.chain;



public abstract class Handler {
    private Handler next;
    public Handler then(Handler next) { this.next = next; return next; }
    public final void handle(AccessRequest request) {
        if (check(request) && next != null) next.handle(request);
    }
    protected abstract boolean check(AccessRequest request);
}
