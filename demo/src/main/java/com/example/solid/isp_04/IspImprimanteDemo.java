package com.example.solid.isp_04;

public class IspImprimanteDemo {
    //  CODE PROBLEMATIQUE
    interface MachineAvant {void print();void scan();void fax();}
    static class PrinterAvant implements MachineAvant {
        public void print(){System.out.println("impression");}
        public void scan(){throw new UnsupportedOperationException("pas de scanner");}
        public void fax(){throw new UnsupportedOperationException("pas de fax");}
    }

    //  REFACTO
    interface Printable {void print();}
    interface Scannable {void scan();}
    interface Faxable {void fax();}
    static class PrinterApres implements Printable {public void print(){System.out.println("impression");}}
    static class MultifunctionApres implements Printable,Scannable,Faxable {
        public void print(){System.out.println("impression");}
        public void scan(){System.out.println("scan");}
        public void fax(){System.out.println("fax");}
    }

    public static void main(String[] args) {
        MachineAvant ancienne=new PrinterAvant();
        ancienne.print();
        try {ancienne.scan();} catch(UnsupportedOperationException e){System.out.println("AVANT : "+e.getMessage());}
        Printable nouvelle=new PrinterApres();
        nouvelle.print();
        new MultifunctionApres().scan();
    }
}
