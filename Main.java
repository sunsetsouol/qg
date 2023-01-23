package atmSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        // 主系统
        while (true) {
            System.out.println("欢迎来到ATM系统");
            System.out.println("你可以选择如下操作");
            System.out.println("1.注册账户\n2.登录账户\n3.退出系统");
            //输入操作数，字符串输入操作数防止系统崩溃
            String tem;
            int option = 0;
            do {
                //输入操作数到String上，并尝试转换为整形数字，如果失败则重新输入
                try {
                    tem = in.next();
                    option = Integer.parseInt(tem);
                    //如果成功转换为整形，判断操作数是否合法,如果不合法，则继续输入
                    if (option <= 0 || option > 3) {
                        System.out.println("你输入的操作不存在，请重新输入");
                        System.out.println("1.注册账户\n2.登录账户\n3.退出系统");
                    }
                } catch (Exception e) {
                    System.out.println("你输入的操作不存在，请重新输入");
                    System.out.println("1.注册账户\n2.登录账户\n3.退出系统");
                }
            } while (option > 3 || option <= 0);
            //根据操作数选择不同操作
            switch (option) {
                case 1:
                    //注册账户，并将账户人数加一
                    register(accounts, in);
                    break;
                case 2:
                    //判断系统是否有账户，如果没有账户，应先注册账户再登录
                    if (accounts.size() == 0) {
                        System.out.println("现在还没有用户注册，请先注册后再登录");
                        break;
                    }
                    //登录
                    logIn(accounts, in);
                    break;
                case 3:
                default:
                    //退出系统，中止循环
                    System.exit(0);
            }
        }
    }

    //注册用户函数
    public static void register(ArrayList<Account> accounts, Scanner in) {
        String name, password, ensurePassword, cardId;
        //注册用户名和密码
        System.out.println("请输入用户名");
        name = in.next();
        do {
            System.out.println("请输入密码");
            password = in.next();
            System.out.println("请确认密码");
            ensurePassword = in.next();
            //判断两次密码是否一样，防止误输入
            if (!password.equals(ensurePassword)) {
                System.out.println("对不起，你两次输入密码不一致，请重新输入");
            }
        } while (!password.equals(ensurePassword));
        //输入最大提现额度
        System.out.println("请输入单次提现最大金额");
        //一样用String转成Double防止系统崩溃
        double limit = 0.0;
        String tem;
        do {
            try {
                tem = in.next();
                limit = Double.parseDouble(tem);
                if (limit <= 0) {
                    System.out.println("请输入正确的金额");
                }
            } catch (Exception e) {
                System.out.println("请输入正确的金额");
            }
        } while (limit <= 0);
        Random r = new Random();
        //判断生成的卡号是否重复
        boolean flag;
        StringBuilder add = new StringBuilder();
        do {
            flag = true;
            add.delete(0,add.length());
            //为账户随机生成卡号
            for (int i = 0; i < Constant.ACCOUNT_LENGTH; i++) {
                add.append(r.nextInt(10));
            }
            cardId = add.toString();
            //如果账户不止一个，就判断随机出来的卡号是否与之前的卡号重复
            if (accounts.size() > 0) {
                for (Account o : accounts) {
                    //如果有相同的卡号，flag改为false表示卡号已存在，这是再重写生成一个卡号
                    if (cardId.equals(o.getCardId())) {
                        flag = false;
                        break;
                    }
                }
            }
        } while (!flag);
        //生成一个账号系统并添加到用户列表中
        Account account = new Account(name, password, cardId, 0, limit);
        accounts.add(account);
        System.out.println("注册成功，你的卡号为" + cardId);
    }

    public static void logIn(ArrayList<Account> accounts, Scanner in) {
        //根据卡号搜索到对应用户
        Account account = searchById(accounts, in);
        //判断账号之前是否5次登录机会全部用完
        if (account.isSignFlag()) {
            System.out.println("密码输入错误超过5次，请稍后重试");
            return;
        }
        System.out.println("请输入密码");
        String password;
        int cnt = 5;
        //判断密码是否正确
        do {
            password = in.next();
            if (!password.equals(account.getPassword())) {
                cnt--;
                System.out.println("密码错误，你还剩" + cnt + "次机会");
                continue;
            }
            break;
        } while (cnt > 0);
        //如果机会没用光，则代表密码正确，登录成功，进入用户操作界面
        if (cnt > 0) {
            System.out.println("登录成功");
            userCommand(accounts, account, in);
        } else {
            //机会耗尽，1分钟后才可以登录
            System.out.println("全部机会用完，登录失败");
            account.setSignFlag(true);
            //设置计时器，10s后解封
            ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(100, Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
            pool.schedule(new TimerTask() {
                @Override
                public void run() {
                    account.setSignFlag(false);
                }
            }, 10, TimeUnit.SECONDS);
        }
    }

    //根据卡号搜索账户
    private static Account searchById(ArrayList<Account> accounts, Scanner in) {
        Account account;
        String cardId;
        System.out.println("请输入卡号");

        //输入卡号并遍历查找，如果不存在要重新输入
        OUT:
        while (true) {
            //判断是否存在用户
            cardId = in.next();
            for (Account o : accounts) {
                //如果存在则跳出循环
                if (cardId.equals(o.getCardId())) {
                    account = o;
                    break OUT;
                }
            }
            System.out.println("卡号不存在，请重新输入");
        }
        return account;
    }

    private static void userCommand(ArrayList<Account> accounts, Account account, Scanner in) {
        OUT:
        while (true) {
            System.out.println("欢迎来到操作界面");
            System.out.println("1.查询\n2.存款\n3.取款\n4.转账\n5.修改密码\n6.注销账户\n7.退出");
            System.out.println("请输入操作命令");
            //用String读取操作数并转换为整形防止系统崩溃
            int option = 0;
            String tem;
            do {
                try {
                    tem = in.next();
                    option = Integer.parseInt(tem);
                    if (option <= 0 || option > 7) {
                        System.out.println("你输入的操作不存在，请重新输入");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("你输入的操作不存在，请重新输入");
                }
            } while (option <= 0 || option > 7);
            switch (option) {
                case 1:
                    //查询信息，直接打印相关信息
                    print(account);
                    break;
                case 2:
                    deposit(account,in);
                    break;
                case 3:
                    withdraw(account,in);
                    break;
                case 4:
                    transfer(account,accounts,in);
                    break;
                case 5:
                    changePassword(account,in);
                    break;
                case 6:
                    //销户
                    accounts.remove(account);
                    System.out.println("销户成功");
                case 7:
                default:
                    //退出
                    break OUT;
            }
        }
    }

    public static void print(Account account){
        System.out.println("当前账户卡号为" + account.getCardId());
        System.out.println("姓名为" + account.getName());
        System.out.println("余额为" + account.getMoney());
        System.out.println("提现上限为" + account.getLimitWithdrawals());
    }

    public static void deposit(Account account, Scanner in){
        //存款，用String读取存款金额，防止系统崩溃
        System.out.println("请输入存款金额");
        String tem;
        double add = 0;
        do {
            try {
                tem = in.next();
                add = Double.parseDouble(tem);
                if (add <= 0) {
                    System.out.println("请输入正确金额");
                }
            } catch (NumberFormatException e) {
                System.out.println("请输入正确金额");
            }
        } while (add <= 0);
        account.setMoney(account.getMoney() + add);
        System.out.println("存款成功，当前余额" + account.getMoney());
    }

    public static void withdraw(Account account,Scanner in){
        //取款，判断账户是否有钱
        if (account.getMoney() == 0) {
            System.out.println("当前账户余额为0.0元，无法取款");
            return;
        }
        System.out.println("请输入取款金额");
        //用String读取金额，防止系统崩溃
        double minus = 0;
        String tem;
        do {
            try {
                tem = in.next();
                minus = Double.parseDouble(tem);
                //判断金额合法性
                if (minus <= 0) {
                    System.out.println("请输入正确金额");
                }
            } catch (NumberFormatException e) {
                System.out.println("请输入正确金额");
            }
        } while (minus <= 0);
        //判断金额是否小于余额和取款上限
        if (minus > account.getMoney()) {
            System.out.println("余额不足，当前余额为" + account.getMoney());
        } else {
            if (minus > account.getLimitWithdrawals()) {
                System.out.println("取款金额大于设定提现上限" + account.getLimitWithdrawals() + "元");
            } else {
                account.setMoney(account.getMoney() - minus);
                System.out.println("取款成功，当前余额为" + account.getMoney());
            }
        }
    }

    public static void transfer(Account account,ArrayList<Account> accounts,Scanner in){
        //转账，先判断是否有另外的账户
        if (accounts.size() == 1) {
            System.out.println("对不起，当前系统只有你一个账户，不能进行转账");
            return;
        }
        //判断是否因为多次验证信息失败而被冻结转账功能
        if (account.isTransferFlag()) {
            System.out.println("对不起，你多次转账信息验证失败，请稍后重试");
            return;
        }
        //根据卡号查找转账者
        Account account1 = searchById(accounts, in);
        //判断转账人是否是自己
        if (account1 == account) {
            System.out.println("对不起，不能给自己转账");
            return;
        }
        //验证身份
        String userName = "*" + account1.getName().substring(1);
        System.out.println("请你输入" + userName + "的姓氏");
        String surName;
        String tem;
        int cnt;
        for (cnt = Constant.OPPORTUNITIES-1; cnt >= 0; cnt--) {
            surName = in.next();
            if (account1.getName().startsWith(surName)) {
                break;
            } else {
                System.out.println("回答错误，你还剩" + cnt + "次机会");
            }
        }
        //验证失败，转账功能冻结1分钟
        if (cnt == -1) {
            System.out.println("对不起，信息验证失败，你不能进行转账");
            account.setTransferFlag(true);
            ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(100,
                    Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            pool.schedule(new TimerTask() {
                @Override
                public void run() {
                    account.setTransferFlag(false);
                }
            }, 10, TimeUnit.SECONDS);
        } else {
            //验证成功，可以转账了
            System.out.println("验证成功，请输入转账金额");
            //合法性判断
            double transfer = 0;
            do {
                try {
                    tem = in.next();
                    transfer = Double.parseDouble(tem);
                } catch (Exception e) {
                    System.out.println("请输入正确金额");
                }
            } while (transfer <= 0);
            //判断转账金额是否大于余额
            if (transfer > account.getMoney()) {
                System.out.println("转账失败，你的余额仅剩" + account.getMoney() + "元");
            } else {
                //判断转账额度是否大于取款上限
                if (transfer > account.getLimitWithdrawals()) {
                    System.out.println("转账失败，转账额度大于提现上限" + account.getLimitWithdrawals() + "元");
                } else {
                    account.setMoney(account.getMoney() - transfer);
                    account1.setMoney(account1.getMoney() + transfer);
                    System.out.println("转账成功");
                }
            }
        }
    }

    public static void changePassword(Account account,Scanner in){
        //修改密码
        System.out.println("请输入当前密码");
        String password;
        //安全检验，先输入当前密码
        do {
            password = in.next();
            if (!password.equals(account.getPassword())) {
                System.out.println("密码错误，请重新输入");
            }
        } while (!password.equals(account.getPassword()));
        //防止误输入，保证两次输入密码相同
        String ensurePassword;
        do {
            System.out.println("请输入新密码");
            password = in.next();
            System.out.println("请确认密码");
            ensurePassword = in.next();
            if (!password.equals(ensurePassword)) {
                System.out.println("对不起，你两次密码输入不一致");
            }
        } while (!password.equals(ensurePassword));
        //如果与原来密码相同则不修改
        if (password.equals(account.getPassword())) {
            System.out.println("你不能修改与原来相同的密码");
            return;
        }
        account.setPassword(password);
        System.out.println("密码修改成功");
    }
}

