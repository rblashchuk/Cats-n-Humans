package ru.blashchuk.banks.entities;

import java.util.*;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.User;

public class ConcreteUser implements User {
    public static NameBuilder builder = new ConcreteUserBuilder();
    private String id;
    private String name;
    private String lastname;
    private String address;
    private Integer passportNumber;
    private Map<String, Account> accounts;
    private List<String> messageLog;

    public void AddAccount(Account account) {
        if (accounts == null) {
            accounts = new HashMap<>();
        }
        accounts.put(account.getId(), account);
    }

    public void Update(String bankReport) {
        if (messageLog == null) {
            messageLog = new ArrayList<>();
        }
        messageLog.add(bankReport);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void update(String bankReport) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.put(account.getId(), account);
    }

    @Override
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public void setMessageLog(ArrayList<Object> objects) {
        this.messageLog = messageLog;
    }

    public List<String> getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(List<String> messageLog) {

        this.messageLog = messageLog;
    }

    private static class ConcreteUserBuilder implements NameBuilder, LastnameBuilder, UserBuilder {
        private final ConcreteUser user = new ConcreteUser();

        public LastnameBuilder withName(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder withSurname(String lastname) {
            user.setLastname(lastname);
            return this;
        }

        public UserBuilder withAddress(String address) {
            user.setAddress(address);
            return this;
        }

        public UserBuilder withPassportNumber(int passportNumber) {
            if (passportNumber < 100000 || passportNumber > 999999) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            user.setPassportNumber(passportNumber);
            return this;
        }

        public ConcreteUser build() {
            user.setAccounts(new HashMap<>());
            user.setMessageLog((ArrayList<Object>) new ArrayList<>());
            user.setId(UUID.randomUUID().toString());
            return user;
        }
    }
}