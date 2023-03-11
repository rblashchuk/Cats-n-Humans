package ru.blashchuk.banks.entities.transactions;

import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Transaction;
import ru.blashchuk.banks.models.TransactionType;

import java.util.UUID;

public class ATMWithdrawFundsTransaction implements Transaction {
    private String id;
    private Account account;
    private double funds;
    private boolean cancelled;
    private TransactionType type;
    public ATMWithdrawFundsTransaction(Account account, double funds) {
        this.id = UUID.randomUUID().toString();
        this.account = account;
        this.funds = funds;
        this.cancelled = false;
        this.type = TransactionType.ATMWithdrawFundsType;
    }

    public TransactionType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}