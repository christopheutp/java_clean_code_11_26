package org.example.kata5;

import java.util.HashMap;
import java.util.Map;
import java.util.EnumMap;
import java.util.function.Consumer;

public class TransactionManager {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<TransactionType, Consumer<Transaction>> handlers = new EnumMap<>(TransactionType.class);

    public TransactionManager() {
        handlers.put(TransactionType.DEPOSIT, this::deposit);
        handlers.put(TransactionType.WITHDRAWAL, this::withdraw);
        handlers.put(TransactionType.TRANSFER, this::transfer);
    }

    public void processTransaction(Transaction transaction) {
        Consumer<Transaction> handler = handlers.get(transaction.getType());
        if (handler != null) handler.accept(transaction);
        saveTransaction(transaction);
    }

    public void addAccount(String id, Account account) {
        accounts.put(id, account);
    }

    private void deposit(Transaction transaction) {
        accounts.get(transaction.getAccountId()).credit(transaction.getAmount());
    }

    private void withdraw(Transaction transaction) {
        accounts.get(transaction.getAccountId()).debit(transaction.getAmount());
    }

    private void transfer(Transaction transaction) {
        Account source = accounts.get(transaction.getSourceAccountId());
        Account destination = accounts.get(transaction.getDestinationAccountId());
        source.debit(transaction.getAmount());
        destination.credit(transaction.getAmount());
    }

    private void saveTransaction(Transaction transaction) {
        // Enregistrement de la transaction.
    }
}
