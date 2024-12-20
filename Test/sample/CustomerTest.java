package sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void testWithdrawPersonWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(-22.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(-22.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10);
        Customer customer = getCompanyCustomer(account);
        customer.withdraw(10, "EUR");
        assertThat(account.getMoney(), is(-20.5));
    }

    @Test
    public void testPrintCustomerDaysOverdrawn() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerDaysOverdrawn(), is("danix dan, IBAN: RO023INGB434321431241, Money: 34.0 EUR, Days Overdrawn: 9"));
    }

    @Test
    public void testPrintCustomerMoney() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerMoney(), is("danix dan Account: IBAN: RO023INGB434321431241, Money: 34.0"));
    }

    @Test
    public void testPrintCustomerAccountNormal() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerAccount(), is("Account: IBAN: RO023INGB434321431241, Money: 34.0, Account type: normal"));
    }

    @Test
    public void testPrintCustomerAccountPremium() throws Exception {
        Customer customer = getPersonWithAccount(true);
        assertThat(customer.printCustomerAccount(), is("Account: IBAN: RO023INGB434321431241, Money: 34.0, Account type: premium"));
    }

    private Customer getPersonWithAccount(boolean premium) {
        AccountType accountType = premium ? AccountType.PREMIUM : AccountType.NORMAL;

        MoneyWithCurrency balance = new MoneyWithCurrency(34.0, "EUR");

        Account account = new Account(accountType, 9, balance);

        Customer customer = getPersonCustomer(account);
        account.setIban("RO023INGB434321431241");
        return customer;
    }

    private Account getAccountByTypeAndMoney(boolean premium, double money) {
        AccountType accountType = premium ? AccountType.PREMIUM : AccountType.NORMAL;

        MoneyWithCurrency balance = new MoneyWithCurrency(money, "EUR");

        Account account = new Account(accountType, 9, balance);
        account.setIban("RO023INGB434321431241");

        return account;
    }

    private Customer getPersonCustomer(Account account) {
        Customer customer = new Customer("danix", "dan", "dan@mail.com", CustomerType.PERSON, account);
        account.setCustomer(customer);
        return customer;
    }

    private Customer getCompanyCustomer(Account account) {
        Customer customer = new Customer("company", "company@mail.com", account, 0.50);
        account.setCustomer(customer);
        return customer;
    }
}