package atmSystem;

public class Account {
    private String name;
    private String password;
    private String cardId;
    private double money;
    private double limitWithdrawals;
    private boolean signFlag;
    private boolean transferFlag;

    public Account(String name, String password, String cardId, double money, double limitWithdrawals) {
        this.name = name;
        this.password = password;
        this.cardId = cardId;
        this.money = money;
        this.limitWithdrawals = limitWithdrawals;
    }

    public Account() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getLimitWithdrawals() {
        return this.limitWithdrawals;
    }

    public void setLimitWithdrawals(double limitWithdrawals) {
        this.limitWithdrawals = limitWithdrawals;
    }

    public boolean isSignFlag() {
        return this.signFlag;
    }

    public void setSignFlag(boolean flag) {
        this.signFlag = flag;
    }

    public boolean isTransferFlag() {
        return this.transferFlag;
    }

    public void setTransferFlag(boolean transferFlag) {
        this.transferFlag = transferFlag;
    }
}
