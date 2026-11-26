package org.example.kata5;

public class TransactionManager {
    public void processTransaction(Transaction transaction) {
        if (transaction.getType() == TransactionType.DEPOSIT) {
            Account account = getAccount(transaction.getAccountId());
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else if (transaction.getType() == TransactionType.WITHDRAWAL) {
            Account account = getAccount(transaction.getAccountId());
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if (transaction.getType() == TransactionType.TRANSFER) {
            Account sourceAccount = getAccount(transaction.getSourceAccountId());
            Account destinationAccount = getAccount(transaction.getDestinationAccountId());
            sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
        }
        // Enregistrement de la transaction
        saveTransaction(transaction);
    }

    private final java.util.Map<String, Account> accounts = new java.util.HashMap<>();

    public void addAccount(String id, Account account) {
        accounts.put(id, account);
    }

    private Account getAccount(String id) {
        return accounts.get(id);
    }

    private void saveTransaction(Transaction transaction) {
    }
}
