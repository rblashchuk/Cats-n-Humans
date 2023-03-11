package ru.blashchuk.banks.interfaces;

import ru.blashchuk.banks.models.TransactionType;

import java.math.BigDecimal;

public interface Transaction {
    TransactionType getType();
    String getId();
    Account getAccount();
    void setFunds(double funds);
    double getFunds();
    boolean isCancelled();
    void setCancelled(boolean cancelled);
}