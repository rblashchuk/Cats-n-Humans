package ru.blashchuk.banks.models;

import ru.blashchuk.banks.entities.accounts.CreditAccount;
import ru.blashchuk.banks.entities.accounts.DebitAccount;
import ru.blashchuk.banks.entities.accounts.DepositAccount;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.User;

public class CreditAccountCreator extends AccountCreator {
    @Override
    public Account FactoryMethod(Bank bank, User user) {
        int daysToExpiration = bank.getAccountValidTime();
        double creditCommission = bank.getCreditCommission();
        double creditLimit = bank.getCreditLimit();

        CreditAccount account = new CreditAccount(user, daysToExpiration, creditLimit, creditCommission);
        user.addAccount(account);
        return account;
    }
}