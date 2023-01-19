package atmSystem;

public class Account {
    private String name;                //账户名
    private String password;            //密码
    private String cardId;              //卡号
    private double money;               //余额
    private double limitWithdrawals;    //提现上限

    public Account(String name, String password, String cardId, double money, double limitWithdrawals) {
        this.name = name;
        this.password = password;
        this.cardId = cardId;
        this.money = money;
        this.limitWithdrawals = limitWithdrawals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getLimitWithdrawals() {
        return limitWithdrawals;
    }

    public void setLimitWithdrawals(double limitWithdrawals) {
        this.limitWithdrawals = limitWithdrawals;
    }
}
