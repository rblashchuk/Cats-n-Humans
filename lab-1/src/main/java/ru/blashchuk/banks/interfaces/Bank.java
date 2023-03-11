package ru.blashchuk.banks.interfaces;

import ru.blashchuk.banks.models.AccountSubtype;
import ru.blashchuk.banks.models.DepositInterestPolicy;

import java.math.BigDecimal;
import java.util.*;
public interface Bank extends Observable {

    public String getId();
    public String getName();

    public int getAccountValidTime();
    public Map<String, Account> getAccounts();
    public Map<String, Observer> getUsers();
    public void setAccounts(Map<String, Account> accounts);
    public void setUsers(Map<String, Observer> users);
    public void setDebitInterest(double debitInterest);
    public DepositInterestPolicy getDepositInterestPolicy();
    public void setDepositInterestPolicy(DepositInterestPolicy depositInterestPolicy);
    public void setCreditComission(double creditComission);
    public void setCreditLimit(double creditLimit);
    public Account createAccount(AccountSubtype subtype, User user);

    public Account getAccountById(String id);
    public void skipTime(int days);

    public double getCreditComission();

    public double getDebitInterest();

    public double getCreditCommission();

    public double getCreditLimit();
}
