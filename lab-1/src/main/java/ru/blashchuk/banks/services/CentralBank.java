package ru.blashchuk.banks.services;

import ru.blashchuk.banks.entities.ConcreteBank;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.models.DepositInterestPolicy;

import java.util.*;


public class CentralBank {

    private Map<String, Bank> Banks;

    public CentralBank() {
        Banks = new HashMap<String, Bank>();
    }

    public Bank createBank(String name, int accountValidTime, double debitInterest, DepositInterestPolicy depositInterestPolicy, double creditComission, double creditLimit) {
        Bank bank = new ConcreteBank(name, accountValidTime, debitInterest, depositInterestPolicy, creditComission, creditLimit);
        this.Banks.put(bank.getId(), bank);
        return bank;
    }

    public void withdrawFunds(Account account, double funds) throws Exception {
        account.withdrawFunds(funds);
    }

    public void addFunds(Account account, double funds) {
        account.addFunds(funds);
    }

    public Bank getBankById(String id) {
        if (!this.Banks.containsKey(id)) {
            throw new RuntimeException();
        }

        return this.Banks.get(id);
    }

    public Bank getBankByName(String name) {
        for (Map.Entry<String, Bank> entry : this.Banks.entrySet()) {
            System.out.println(entry);
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }

        throw new RuntimeException();
    }

    public void skipTime(int days) {
        for (Map.Entry<String, Bank> entry : this.Banks.entrySet()) {
            entry.getValue().skipTime(days);
        }
    }
}