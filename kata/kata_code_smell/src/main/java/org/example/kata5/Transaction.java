package org.example.kata5;

public record Transaction(TransactionType type, String accountId, String sourceAccountId, String destinationAccountId,
                          double amount) {
    public TransactionType getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public double getAmount() {
        return amount;
    }
}
