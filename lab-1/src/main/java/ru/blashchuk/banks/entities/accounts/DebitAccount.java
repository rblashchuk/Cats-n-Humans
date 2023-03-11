package ru.blashchuk.banks.entities.accounts;

import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Transaction;
import ru.blashchuk.banks.interfaces.User;
import ru.blashchuk.banks.models.AccountSubtype;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DebitAccount implements Account {
    private String id;
    private AccountSubtype subtype;
    private User user;
    private int daysToExpiration;
    private double interest;
    private double funds;
    private List<Transaction> transactionLog;

    public DebitAccount(User user, int daysToExpiration, double interest) {
        this.id = UUID.randomUUID().toString();
        this.subtype = AccountSubtype.DebitAccountSubtype;
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

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
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
                try {
                    withdrawFunds(transaction.getFunds());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case ATMAddFundsType:
                addFunds(transaction.getFunds());
                break;
            case ATMWithdrawFundsType:
                try {
                    withdrawFunds(transaction.getFunds());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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

    public void cancelTransaction(Transaction transaction) throws Exception {
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
                throw new Exception();
        }

        transaction.setCancelled(true);
    }

    public void addFunds(double funds) {
        this.funds += funds;
    }

    public void addInterestValue() {
        this.funds += (this.funds * (this.interest/3000));
    }

    public void withdrawFunds(double funds) throws Exception {
        if (this.funds < funds) {
            throw new Exception();
        }

        this.funds = this.funds - funds;
    }
    public void processTimeCalculations(int days) {
        for (int i = 0; i < days; i++) {
            addInterestValue();
        }
    }

}