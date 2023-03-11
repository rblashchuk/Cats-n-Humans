package ru.blashchuk.banks.models;

import ru.blashchuk.banks.entities.accounts.DepositAccount;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.User;

public class DepositAccountCreator extends AccountCreator {
    @Override
    public Account FactoryMethod(Bank bank, User user) {
        int daysToExpiration = bank.getAccountValidTime();
        double depositInterest = bank.getDepositInterestPolicy().evaluateInterestValue((double) 0);

        DepositAccount account = new DepositAccount(user, daysToExpiration, depositInterest);
        user.addAccount(account);
        return account;
    }
}
