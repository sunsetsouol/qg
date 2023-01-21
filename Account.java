package atmSystem;

public class Account {
    //账户名
    private String name;
    //密码
    private String password;
    //卡号
    private String cardId;
    //余额
    private double money;
    //提现上限
    private double limitWithdrawals;
    //判断是否被封
    private boolean signFlag;
    //判断转账是否被封
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

    public boolean isSignFlag() {
        return signFlag;
    }

    public void setSignFlag(boolean flag) {
        this.signFlag = flag;
    }

    public boolean isTransferFlag() {
        return transferFlag;
    }

    public void setTransferFlag(boolean transferFlag) {
        this.transferFlag = transferFlag;
    }
}
