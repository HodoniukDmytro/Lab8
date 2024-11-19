package sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

    @Test
    public void testBankchargePremiumLessThanAWeek() {
        Account account = getPremiumAccount(5);
        assertThat(account.bankcharge(), is(14.5));
    }

    @Test
    public void testBankchargePremiumMoreThanAWeek() {
        Account account = getPremiumAccount(9);
        assertThat(account.bankcharge(), is(16.5));
    }

    @Test
    public void testOverdraftFeePremium() {
        Account account = getPremiumAccount(9);
        assertThat(account.overdraftFee(), is(0.10));
    }

    @Test
    public void testOverdraftFeeNotPremium() {
        Account account = getNormalAccount();
        assertThat(account.overdraftFee(), is(0.20));
    }

    @Test
    public void testPrintCustomer() {
        Account account = getNormalAccount();
        Customer customer = new Customer("xxx", "xxx", "xxx@mail.com", CustomerType.PERSON, account);
        account.setCustomer(customer);
        assertThat(account.printCustomer(), is("xxx xxx@mail.com"));
    }

    private Account getNormalAccount() {
        AccountType normal = AccountType.NORMAL;
        MoneyWithCurrency balance = new MoneyWithCurrency(0.0, "EUR");

        return new Account(normal, 9, balance);
    }

    private Account getPremiumAccount(int daysOverdrawn) {
        AccountType premium = AccountType.PREMIUM;
        MoneyWithCurrency balance = new MoneyWithCurrency(0.0, "EUR");

        return new Account(premium, daysOverdrawn, balance);
    }
}