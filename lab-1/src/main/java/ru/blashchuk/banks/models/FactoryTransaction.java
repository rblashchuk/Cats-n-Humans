package ru.blashchuk.banks.models;

import ru.blashchuk.banks.entities.transactions.ATMAddFundsTransaction;
import ru.blashchuk.banks.entities.transactions.ATMWithdrawFundsTransaction;
import ru.blashchuk.banks.entities.transactions.PaymentTransaction;
import ru.blashchuk.banks.entities.transactions.RefundTransaction;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Transaction;

class ATMAddFundsTransactionCreator extends TransactionCreator {
    @Override
    public Transaction FactoryMethod(Account account, double funds) {
        ATMAddFundsTransaction transaction = new ATMAddFundsTransaction(account, funds);
        return transaction;
    }
}

class ATMWithdrawFundsTransactionCreator extends TransactionCreator {
    @Override
    public Transaction FactoryMethod(Account account, double funds) {
        ATMWithdrawFundsTransaction transaction = new ATMWithdrawFundsTransaction(account, funds);
        return transaction;
    }
}

class PaymentTransactionCreator extends TransactionCreator {
    @Override
    public Transaction FactoryMethod(Account account, double funds) {
        PaymentTransaction transaction = new PaymentTransaction(account, funds);
        return transaction;
    }
}

class RefundTransactionCreator extends TransactionCreator {
    @Override
    public Transaction FactoryMethod(Account account, double funds) {
        RefundTransaction transaction = new RefundTransaction(account, funds);
        return transaction;
    }
}