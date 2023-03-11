package ru.blashchuk.banks.entities.accounts;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Transaction;
import ru.blashchuk.banks.interfaces.User;
import ru.blashchuk.banks.models.AccountSubtype;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class DepositAccount implements Account {
    private String id;
    private AccountSubtype subtype;
    private int daysToExpiration;
    private User user;
    private double interest;
    private double funds;
    private List<Transaction> transactionLog;

    public DepositAccount(User user, int daysToExpiration, double interest) {
        this.id = UUID.randomUUID().toString();
        this.subtype = AccountSubtype.DepositAccountSubtype;
        this.user = user;
        this.daysToExpiration = daysToExpiration;
        this.interest = interest;
        this.funds = 0;
        this.transactionLog = new ArrayList<Transaction>();
    }

    public String getId() {
        return id;
    }

    public AccountSubtype getSubtype() {
        return subtype;
    }

    public int getDaysToExpiration() {
        return daysToExpiration;
    }

    public void setDaysToExpiration(int daysToExpiration) {
        this.daysToExpiration = daysToExpiration;
    }

    public User getUser() {
        return user;
    }

    public double getInterest() {
        return interest;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public List<Transaction> getTransactionLog() {
        return transactionLog;
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
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }

        transaction.setCancelled(true);
    }

    public void addInterestValue() {
        this.funds = this.funds + (this.funds * this.interest / 100);
    }

    public void addFunds(double funds) {
        this.funds = this.funds + funds;
    }

    public void withdrawFunds(double funds) {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void processTimeCalculations(int days) {
        for (int i = 0; i < days; i++) {
            addInterestValue();
        }
    }
}