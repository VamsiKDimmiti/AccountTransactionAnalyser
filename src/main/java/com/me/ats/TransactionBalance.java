package com.me.ats;

import javax.persistence.Entity;

public class TransactionBalance {
    public TransactionBalance(String balance, int transactionCount) {
        this.balance = balance;
        this.transactionCount = transactionCount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String balance;

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public int transactionCount;
}
