package ru.blashchuk.banks.entities;

import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.models.DepositInterestPolicy;

import java.util.List;
import java.util.stream.Collectors;

public class BankReport {
    private String bankName;
    private double debitInterest;
    private DepositInterestPolicy depositInterestPolicy;
    private double creditComission;
    public BankReport(Bank bank) {
        this.bankName = bank.getName();
        this.creditComission = bank.getCreditComission();
        this.debitInterest = bank.getDebitInterest();
        this.depositInterestPolicy = bank.getDepositInterestPolicy();
    }

    public String getBankName() {
        return bankName;
    }

    public double getDebitInterest() {
        return debitInterest;
    }

    public DepositInterestPolicy getDepositInterestPolicy() {
        return depositInterestPolicy;
    }

    public void setDepositInterestPolicy(DepositInterestPolicy depositInterestPolicy) {
        this.depositInterestPolicy = depositInterestPolicy;
    }

    public double getCreditComission() {
        return creditComission;
    }

    @Override
    public String toString() {
        String ans = "Bank: " + this.bankName + "\n";
        ans += "Debit Interest: " + this.debitInterest + "\n";
        ans += "Deposit Interest:" + "\n";
        List<Double> points = this.depositInterestPolicy.getPointsOfInterestRaise();
        List<Double> values = this.depositInterestPolicy.getInterestValues();
        for (int i = 0; i < points.size(); i++) {
            ans += String.format("  - funds less than %f: %f%%\n", points.get(i), values.get(i));
        }
        double lastPoint = points.get(points.size() - 1);
        double lastValue = values.get(values.size() - 1);
        ans += String.format("  - funds more than %f: %f%%\n", lastPoint, lastValue);
        ans += "Credit Comission: $" + this.creditComission + "\n";
        return ans;
    }

    public String message() {
        return "Bank " + this.bankName + " has updated its terms. Here is the actual information. \n" + this.toString();
    }
}
