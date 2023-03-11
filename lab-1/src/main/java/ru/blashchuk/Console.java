package ru.blashchuk;
import ru.blashchuk.banks.entities.ConcreteUser;
import ru.blashchuk.banks.entities.transactions.ATMAddFundsTransaction;
import ru.blashchuk.banks.entities.transactions.PaymentTransaction;
import ru.blashchuk.banks.entities.transactions.RefundTransaction;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.User;
import ru.blashchuk.banks.models.AccountSubtype;
import ru.blashchuk.banks.models.DepositInterestPolicy;
import ru.blashchuk.banks.services.CentralBank;

import java.util.*;

public class Console {
    public static void main(String[] args) {
        CentralBank centralBank = new CentralBank();
        Map<String, User> users = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String userInput = "0";
        while (!userInput.equals("x")) {
            System.out.println("u - create user profile");
            System.out.println("b - create bank");
            System.out.println("a - create account");
            System.out.println("m - money operation");
            System.out.println("x - exit");
            userInput = scanner.nextLine();
            switch (userInput) {
                case "b" -> {
                    System.out.println("input bank name");
                    String name = scanner.nextLine();
                    System.out.println("input account expiration time");
                    int daysToExpiration = Integer.parseInt(scanner.nextLine());
                    System.out.println("input limit for credit accounts");
                    double creditLimit = Double.parseDouble(scanner.nextLine());
                    System.out.println("input comission for credit accounts");
                    double creditComission = Double.parseDouble(scanner.nextLine());
                    System.out.println("input interest for debit accounts");
                    double debitInterest = Double.parseDouble(scanner.nextLine());
                    System.out.println("input points of interest raise for deposit accounts");
                    String pointString = scanner.nextLine();
                    String[] listOfStr = pointString.split(" ");
                    List<Double> listOfPoints = new ArrayList<>();
                    List<Double> listOfInterests = new ArrayList<>();
                    for (String str : listOfStr) {
                        listOfPoints.add(Double.parseDouble(str));
                    }
                    Collections.sort(listOfPoints);
                    System.out.println("input interests for each interval");
                    pointString = scanner.nextLine();
                    listOfStr = pointString.split(" ");
                    for (String str : listOfStr) {
                        listOfInterests.add(Double.parseDouble(str));
                    }
                    Collections.sort(listOfInterests);
                    DepositInterestPolicy policy = new DepositInterestPolicy(listOfPoints, listOfInterests);
                    centralBank.createBank(name, daysToExpiration, debitInterest, policy, creditComission, creditLimit);
                    System.out.println("bank created");
                    break;
                }
                case "u" -> {
                    System.out.println("input name");
                    String username = scanner.nextLine();
                    System.out.println("input surname");
                    String surname = scanner.nextLine();
                    ConcreteUser user = (ConcreteUser) ConcreteUser.builder
                            .withName(username)
                            .withSurname(surname)
                            .build();
                    users.put(username, user);
                    System.out.println("user created");
                    break;
                }
                case "a" -> {
                    System.out.println("input bank name");
                    String bankName = scanner.nextLine();
                    Bank bankForNewAccount = centralBank.getBankByName(bankName);
                    System.out.println("input user name");
                    String usernameForAccount = scanner.nextLine();
                    User userForNewAccount = users.get(usernameForAccount);
                    System.out.println("input account type: \ndeb/dep/cr");
                    AccountSubtype subtype;
                    Account account;
                    switch (scanner.nextLine()) {
                        case "deb" -> {
                            subtype = AccountSubtype.DebitAccountSubtype;
                            account = bankForNewAccount.createAccount(subtype, userForNewAccount);
                        }
                        case "dep" -> {
                            subtype = AccountSubtype.DepositAccountSubtype;
                            account = bankForNewAccount.createAccount(subtype, userForNewAccount);
                        }
                        case "cr" -> {
                            subtype = AccountSubtype.CreditAccountSubtype;
                            account = bankForNewAccount.createAccount(subtype, userForNewAccount);
                        }
                        default -> {
                            try {
                                throw new Exception();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    System.out.println("account created: " + account.getId());
                    break;
                }
                case "m" -> {
                    System.out.println("choose option");
                    System.out.println("s - send money");
                    System.out.println("a - add funds");
                    System.out.println("w - withdraw funds");
                    String option = scanner.nextLine();
                    switch (option) {
                        case "a" -> {
                            System.out.println("how much");
                            double funds = Double.parseDouble(scanner.nextLine());
                            System.out.println("what bank");
                            String bankToAddName = scanner.nextLine();
                            System.out.println("account id");
                            String accId = scanner.nextLine();
                            Bank bankToAdd = centralBank.getBankByName(bankToAddName);
                            Account accToAdd = bankToAdd.getAccountById(accId);
                            ATMAddFundsTransaction transaction = new ATMAddFundsTransaction(accToAdd, funds);
                            accToAdd.processTransaction(transaction);
                            System.out.println("funds added");
                        }
                        case "s" -> {
                            System.out.println("how much");
                            double funds = Double.parseDouble(scanner.nextLine());
                            System.out.println("bank from");
                            String bankNameFrom = scanner.nextLine();
                            System.out.println("account id from");
                            String accIdfrom = scanner.nextLine();
                            System.out.println("bank to");
                            String bankNameTo = scanner.nextLine();
                            System.out.println("account id to");
                            String accIdTo = scanner.nextLine();

                            Bank bankFrom = centralBank.getBankByName(bankNameFrom);
                            Account accFrom = bankFrom.getAccountById(accIdfrom);
                            Bank bankTo = centralBank.getBankByName(bankNameTo);
                            Account accTo = bankTo.getAccountById(accIdTo);

                            RefundTransaction transactionFrom = new RefundTransaction(accFrom, funds);
                            accFrom.processTransaction(transactionFrom);
                            PaymentTransaction transactionTo = new PaymentTransaction(accTo, funds);
                            accTo.processTransaction(transactionTo);

                            System.out.println("funds sent");
                        }
                        case "w" -> {
                            System.out.println("how much");
                            double funds = Double.parseDouble(scanner.nextLine());
                            System.out.println("bank from");
                            String bankNameFrom = scanner.nextLine();
                            System.out.println("account id from");
                            String accIdfrom = scanner.nextLine();

                            Bank bankFrom = centralBank.getBankByName(bankNameFrom);
                            Account accFrom = bankFrom.getAccountById(accIdfrom);

                            RefundTransaction transactionFrom = new RefundTransaction(accFrom, funds);
                            accFrom.processTransaction(transactionFrom);
                            System.out.println("funds withdrawn");
                        }
                    }
                    break;
                }
            }
        }
    }
}