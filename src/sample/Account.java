package sample;

public class Account {
    private static final String DEFAULT_CURRENCY = "EUR";
    private static final double DEFAULT_AMOUNT = 0.0;

    private String iban;
    private AccountType type;
    private int daysOverdrawn;
    private MoneyWithCurrency balance;
    private Customer customer;

    public Account(AccountType type, int daysOverdrawn) {
        this(type, daysOverdrawn, new MoneyWithCurrency(DEFAULT_AMOUNT, DEFAULT_CURRENCY));
    }

    public Account(AccountType type, int daysOverdrawn, MoneyWithCurrency balance) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
        this.balance = balance;
    }
    
    public double bankcharge() {
        double result = 4.5;
        result += overdraftCharge();
        return result;
    }

    private double overdraftCharge() {
        if (type.isPremium()) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (type.isPremium()) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money) {
        balance.setAmount(money);
    }

    public double getMoney() {
        return balance.getAmount();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public String printCustomer() {
        return customer.getFullName() + " " + customer.getEmail();
    }

    public String getCurrency() {
        return balance.getCurrency();
    }

    public void setCurrency(String currency) {
        balance.setCurrency(currency);
    }

    public String getAccountSummary() {
        return "IBAN: " + iban +
                ", Money: " + balance.getAmount() +
                " " + balance.getCurrency() +
                ", Days Overdrawn: " + daysOverdrawn;
    }
}