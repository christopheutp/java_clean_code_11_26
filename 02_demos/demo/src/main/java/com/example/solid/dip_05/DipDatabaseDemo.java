package com.example.solid.dip_05;

public class DipDatabaseDemo {
    // CODE PROBLEMATIQUE
    static class Mysql {void connect(){System.out.println("MySQL");}}
    static class UserRepoAvant {
        private final Mysql db=new Mysql(); // dependance choisie par le code metier
        void load(){db.connect();}
    }

    // REFACTO
    interface Database {void connect();}
    static class MysqlDb implements Database {public void connect(){System.out.println("MySQL");}}
    static class PostgresDb implements Database {public void connect(){System.out.println("PostgreSQL");}}
    static class FakeDb implements Database {public void connect(){System.out.println("FAKE de test");}}
    static class UserRepoApres {
        private final Database db;
        UserRepoApres(Database db){this.db=db;}
        void load(){db.connect();}
    }

    public static void main(String[] args) {
        new UserRepoAvant().load();
        new UserRepoApres(new MysqlDb()).load();
        new UserRepoApres(new PostgresDb()).load();
        new UserRepoApres(new FakeDb()).load();
    }
}
