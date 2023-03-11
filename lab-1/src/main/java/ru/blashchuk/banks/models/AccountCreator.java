package ru.blashchuk.banks.models;

import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.User;

public abstract class AccountCreator {
    public abstract Account FactoryMethod(Bank bank, User user);
}
