package ru.blashchuk.banks.models;

import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Transaction;

public abstract class TransactionCreator {
    public abstract Transaction FactoryMethod(Account account, double funds);
}
