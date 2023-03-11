package ru.blashchuk.banks.models;

import ru.blashchuk.banks.entities.accounts.DebitAccount;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.User;

public class DebitAccountCreator extends AccountCreator {
    @Override
    public Account FactoryMethod(Bank bank, User user) {
        int daysToExpiration = bank.getAccountValidTime();
        double debitInterest = bank.getDebitInterest();

        DebitAccount account = new DebitAccount(user, daysToExpiration, debitInterest);
        user.addAccount(account);
        return account;
    }
}
