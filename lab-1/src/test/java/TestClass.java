import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import ru.blashchuk.banks.entities.ConcreteUser;
import ru.blashchuk.banks.interfaces.Account;
import ru.blashchuk.banks.interfaces.Bank;
import ru.blashchuk.banks.interfaces.User;
import ru.blashchuk.banks.models.AccountSubtype;
import ru.blashchuk.banks.models.DepositInterestPolicy;
import ru.blashchuk.banks.services.CentralBank;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {
    @Test
    public void testCreateNewBankBankAdded() {
        List<Double> points = new ArrayList<>();
        points.add(20000.0);
        points.add(50000.0);
        points.add(100000.0);
        List<Double> interests = new ArrayList<>();
        interests.add(1.0);
        interests.add(2.0);
        interests.add(3.0);
        interests.add(5.0);
        points.sort(null);
        interests.sort(null);
        DepositInterestPolicy depositInterestPolicy = new DepositInterestPolicy(points, interests);
        CentralBank centralBank = new CentralBank();
        Bank bank = centralBank.createBank("Tinkoff", 365, 10, depositInterestPolicy, 5, 100000.0);

        assertEquals(bank, centralBank.getBankById(bank.getId()));
    }

    @Test
    public void testCreateUserUserCreated() {
        List<Double> points = new ArrayList<>();
        points.add(20000.0);
        points.add(50000.0);
        points.add(100000.0);
        List<Double> interests = new ArrayList<>();
        interests.add(1.0);
        interests.add(2.0);
        interests.add(3.0);
        interests.add(5.0);
        points.sort(null);
        interests.sort(null);
        DepositInterestPolicy depositInterestPolicy = new DepositInterestPolicy(points, interests);
        CentralBank centralBank = new CentralBank();
        Bank bank = centralBank.createBank("Tinkoff", 365, 10, depositInterestPolicy, 5, 100000.0);
        ConcreteUser user = (ConcreteUser) ConcreteUser.builder
                .withName("Vasya")
                .withSurname("Zipkin")
                .withAddress("Jopova 1")
                .withPassportNumber(123456)
                .build();
        Account account = bank.createAccount(AccountSubtype.DebitAccountSubtype, user);
        account.addFunds(10);
        Map<String, Account> accounts = user.getAccounts();

        if (user.getAccounts() != null) assertEquals(user.getAccounts().get(account.getId()).getFunds(), bank.getAccounts().get(account.getId()).getFunds());
    }
}