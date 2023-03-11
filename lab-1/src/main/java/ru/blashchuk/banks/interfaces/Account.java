package ru.blashchuk.banks.interfaces;

import ru.blashchuk.banks.models.AccountSubtype;

import java.util.List;

public interface Account {
    String getId();
    AccountSubtype getSubtype();
    int getDaysToExpiration();
    void setDaysToExpiration(int days);
    User getUser();
    double getFunds();
    void setFunds(double funds);
    List<Transaction> getTransactionLog();
    void processTransaction(Transaction transaction);
    void addFunds(double funds);
    void withdrawFunds(double funds) throws Exception;

    void processTimeCalculations(int days);
}