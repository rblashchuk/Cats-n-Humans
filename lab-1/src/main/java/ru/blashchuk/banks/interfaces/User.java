package ru.blashchuk.banks.interfaces;

import ru.blashchuk.banks.entities.ConcreteUser;
import ru.blashchuk.banks.entities.LastnameBuilder;
import ru.blashchuk.banks.entities.NameBuilder;
import ru.blashchuk.banks.entities.UserBuilder;

import java.util.ArrayList;
import java.util.Map;

public interface User extends Observer {

    String getName();

    void setName(String name);

    String getLastname();

    void setLastname(String lastname);

    Map<String, Account> getAccounts();

    void setAccounts(Map<String, Account> accounts);

    void addAccount(Account account);

    void setPassportNumber(int passportNumber);

    void setMessageLog(ArrayList<Object> objects);

    void setAddress(String address);
}
