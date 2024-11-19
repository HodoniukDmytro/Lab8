package sample;

public enum AccountType {
    NORMAL(false),
    PREMIUM(true);

    private final boolean isPremium;

    AccountType(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public boolean isPremium() {
        return isPremium;
    }

    @Override
    public String toString() {
        return isPremium ? "premium" : "normal";
    }
}