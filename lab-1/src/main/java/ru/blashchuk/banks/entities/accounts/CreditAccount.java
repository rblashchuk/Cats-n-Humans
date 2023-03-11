package ru.blashchuk.banks.entities.accounts;

import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Transaction;
import ru.blashchuk.banks.interfaces.User;
import ru.blashchuk.banks.models.AccountSubtype;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditAccount implements Account {
    private String id;
    private AccountSubtype subtype;
    private User user;
    private int daysToExpiration;
    private double limit;
    private double comission;
    private double funds;
    private List<Transaction> transactionLog;

    public CreditAccount(User user, int daysToExpiration, double limit, double comission) {
        this.id = UUID.randomUUID().toString();
        this.subtype = AccountSubtype.CreditAccountSubtype;
        this.user = user;
        this.daysToExpiration = daysToExpiration;
        this.limit = limit;
        this.comission = comission;
        this.funds = 0;
        this.transactionLog = new ArrayList<Transaction>();
    }

    public String getId() {
        return this.id;
    }

    public AccountSubtype getSubtype() {
        return this.subtype;
    }

    public User getUser() {
        return this.user;
    }

    public int getDaysToExpiration() {
        return this.daysToExpiration;
    }

    public void setDaysToExpiration(int daysToExpiration) {
        this.daysToExpiration = daysToExpiration;
    }

    public double getLimit() {
        return this.limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getComission() {
        return this.comission;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    public double getFunds() {
        return this.funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public List<Transaction> getTransactionLog() {
        return this.transactionLog;
    }

    public void setTransactionLog(List<Transaction> transactionLog) {
        this.transactionLog = transactionLog;
    }

    public void processTransaction(Transaction transaction) {
        switch (transaction.getType()) {
            case RefundType:
                addFunds(transaction.getFunds());
                break;
            case WithdrawType:
                withdrawFunds(transaction.getFunds());
                break;
            case ATMAddFundsType:
                addFunds(transaction.getFunds());
                break;
            case ATMWithdrawFundsType:
                withdrawFunds(transaction.getFunds());
                break;
            default:
                throw new RuntimeException();
        }

        List<Transaction> newLog = new ArrayList<Transaction>(this.transactionLog);
        newLog.add(transaction);
        this.transactionLog = newLog;
    }

    public void cancelTransaction(Transaction transaction) {
        switch (transaction.getType()) {
            case RefundType:
                withdrawFunds(transaction.getFunds());
                break;
            case WithdrawType:
                addFunds(transaction.getFunds());
                break;
            case ATMAddFundsType:
                withdrawFunds(transaction.getFunds());
                break;
            case ATMWithdrawFundsType:
                addFunds(transaction.getFunds());
                break;
            default:
                throw new RuntimeException();
        }

        transaction.setCancelled(true);
    }

    public void addFunds(double funds) {
        this.funds += funds;
    }

    public void withdrawFunds(double funds) {
        if (this.funds + this.limit < funds) {
            throw new RuntimeException();
        }
    }

    public void processTimeCalculations(int days) {
        for (int i = 0; i < days; i++) {
            subtractCommission();
        }
    }

    public void subtractCommission() {
        if (this.funds < 0) {
            this.funds = this.funds - (this.funds * this.comission / 3000);
        }
    }
}