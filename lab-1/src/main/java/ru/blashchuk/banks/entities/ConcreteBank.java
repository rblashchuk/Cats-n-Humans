package ru.blashchuk.banks.entities;
import ru.blashchuk.banks.entities.accounts.CreditAccount;
import ru.blashchuk.banks.entities.accounts.DebitAccount;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.Observer;
import ru.blashchuk.banks.interfaces.User;
import ru.blashchuk.banks.models.*;

import java.util.*;
import java.util.stream.*;

public class ConcreteBank implements Bank {
    private String Id;
    private String Name;
    private int AccountValidTime;
    private Map<String, Account> Accounts;
    private Map<String, Observer> Users;
    private double DebitInterest;
    private DepositInterestPolicy DepositInterestPolicy;
    private double CreditComission;
    private double CreditLimit;
    public ConcreteBank(String name, int accountValidTime, double debitInterest, DepositInterestPolicy depositInterestPolicy, double creditComission, double creditLimit) {
        this.Id = UUID.randomUUID().toString();
        this.Name = name;
        this.AccountValidTime = accountValidTime;
        this.DebitInterest = debitInterest;
        this.DepositInterestPolicy = depositInterestPolicy;
        this.CreditComission = creditComission;
        this.CreditLimit = creditLimit;
        this.Accounts = new HashMap<String, Account>();
        this.Users = new HashMap<String, Observer>();
    }

    public String getId() { return this.Id; }
    public String getName() { return this.Name; }
    public int getAccountValidTime() { return this.AccountValidTime; }
    public Map<String, Account> getAccounts() { return this.Accounts; }
    public Map<String, Observer> getUsers() { return this.Users; }

    @Override
    public void setAccounts(Map<String, Account> accounts) {
        this.Accounts = accounts;
    }

    @Override
    public void setUsers(Map<String, Observer> users) {
        this.Users = users;
    }

    @Override
    public void setDebitInterest(double debitInterest) {
        this.DebitInterest = debitInterest;
    }

    public double getDebitInterest() { return this.DebitInterest; }

    @Override
    public double getCreditCommission() {
        return this.CreditComission;
    }

    public DepositInterestPolicy getDepositInterestPolicy() { return this.DepositInterestPolicy; }

    @Override
    public void setDepositInterestPolicy(ru.blashchuk.banks.models.DepositInterestPolicy depositInterestPolicy) {
        this.DepositInterestPolicy = depositInterestPolicy;
    }

    @Override
    public void setCreditComission(double creditComission) {
        this.CreditComission = creditComission;
    }

    @Override
    public void setCreditLimit(double creditLimit) {
        this.CreditLimit = creditLimit;
    }
    public double getCreditLimit() { return this.CreditLimit; }

    public Account createAccount(AccountSubtype subtype, User user) {
        if (!this.Users.containsValue(user)) {
            addObserver(user);
        }

        AccountCreator creator = new CreditAccountCreator();
        switch (subtype) {
            case CreditAccountSubtype:
                creator = new CreditAccountCreator();
                break;
            case DebitAccountSubtype:
                creator = new DebitAccountCreator();
                break;
            case DepositAccountSubtype:
                creator = new DepositAccountCreator();
                break;
            default:
                throw new IllegalArgumentException();
        }

        Account account = creator.FactoryMethod(this, user);
        this.Accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account getAccountById(String id) {
        return this.Accounts.get(id);
    }

    public void skipTime(int days) {
        for (Account account : this.Accounts.values()) {
            account.processTimeCalculations(days);
        }
    }

    @Override
    public double getCreditComission() {
        return this.CreditComission;
    }

    public void updateDebitInterest(double debitInterest) {
        this.DebitInterest = debitInterest;
        List<DebitAccount> debitAccounts = this.Accounts.values().stream().filter(x -> x instanceof DebitAccount).map(x -> (DebitAccount)x).collect(Collectors.toList());
        for (DebitAccount account : debitAccounts) {
            account.setInterest(this.DebitInterest);
        }

        this.notifyObservers();
    }

    public void updateCreditComission(double creditComission) {
        this.CreditComission = creditComission;
        List<CreditAccount> creditAccounts = this.Accounts.values().stream().filter(x -> x instanceof CreditAccount).map(x -> (CreditAccount)x).collect(Collectors.toList());
        for (CreditAccount account : creditAccounts) {
            account.setComission(this.CreditComission);
        }

        this.notifyObservers();
    }

    public void addObserver(Observer observer) {
        Map<String, Observer> observerDict = new HashMap<>(this.Users);
        if (observer.getId() != null) {
            observerDict.put(observer.getId(), observer);
        }
        this.Users = observerDict;
    }
    public void removeObserver(Observer observer) {
        Map<String, Observer> observerDict = new HashMap<>(this.Users);
        if (!observerDict.containsValue(observer)) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (observer.getId() != null) {
            observerDict.remove(observer.getId());
        }
        this.Users = observerDict;
    }

    public void notifyObservers() {
        BankReport report = new BankReport(this);
        for (Map.Entry<String, Observer> entry : Users.entrySet()) {
            entry.getValue().update(report.toString());
        }
    }
}
