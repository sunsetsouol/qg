package atmSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        HERE :
        while (true)
        {
            System.out.println("欢迎来到ATM系统");
            System.out.println("你可以选择如下操作");
            System.out.println("1.注册账户\n2.登录账户\n3.退出系统");
            String tem;
            int option=0;

            do {
                try {
                    tem=in.next();
                    option=Integer.valueOf(tem);
                    if (option<=0 || option>3){
                        System.out.println("你输入的操作不存在，请重新输入");
                        System.out.println("1.注册账户\n2.登录账户\n3.退出系统");
                    }
                } catch (Exception e) {
                    System.out.println("你输入的操作不存在，请重新输入");
                    System.out.println("1.注册账户\n2.登录账户\n3.退出系统");
                }
            }while(option>3 || option <=0);
            switch (option)
            {
                case 1:
                    register(accounts,in);
                    break;
                case 2:
                    if(accounts.size()==0)
                    {
                        System.out.println("现在还没有用户注册，请先注册后再登录");
                        break;
                    }
                    logIn(accounts,in);
                    break;
                case 3:
                    break HERE;
            }
        }
    }
    public static void register(ArrayList<Account> accounts,Scanner in)
    {
        String name,password,ensurePassword,cardId;
        System.out.println("请输入用户名");
        name = in.next();
        do {
            System.out.println("请输入密码");
            password = in.next();
            System.out.println("请确认密码");
            ensurePassword = in.next();
            if(!password.equals(ensurePassword))
            {
                System.out.println("对不起，你两次输入密码不一致，请重新输入");
            }
        }while(!password.equals(ensurePassword));
        System.out.println("请输入单次提现最大金额");
        double limit=0.0;
        String tem;
        do {
            try {
                tem=in.next();
                limit=Double.valueOf(tem);
                if(limit<=0){
                    System.out.println("请输入正确的金额");
                }
            } catch (Exception e) {
                System.out.println("请输入正确的金额");
            }
        }while (limit<=0);
        Random r = new Random();
        boolean flag;
        do {
            flag=true;
            cardId="";
            for (int i = 0; i < 19; i++)
            {
                cardId+=r.nextInt(10);
            }
            if(accounts.size()>0)
            {
                for (Account o:accounts)
                {
                    if(cardId.equals(o.getCardId()))
                    {
                        flag=false;
                        break;
                    }
                }
            }
        }while (!flag);
        Account account = new Account(name,password,cardId,0,limit);
        accounts.add(account);
        System.out.println("注册成功，你的卡号为"+cardId);
    }
    public static void logIn(ArrayList<Account> accounts,Scanner in)
    {
        Account account=searchById(accounts,in);
        System.out.println("请输入密码");
        String password;
        int cnt=5;
        do {
            password=in.next();
            if(!password.equals(account.getPassword()))
            {
                cnt--;
                System.out.println("密码错误，你还剩"+ cnt +"次机会");
                continue;
            }
            break;
        }while (cnt>0);
        if(cnt>0)
        {
            System.out.println("登录成功");
            userCommand(accounts,account,in);
        }else {
            System.out.println("全部机会用完，登录失败");
        }
    }

    private static Account searchById(ArrayList<Account> accounts, Scanner in) {
        Account account;
        String cardId;
        System.out.println("请输入卡号");
        OUT:
        while (true)
        {
            cardId = in.next();                     //判断是否存在用户
            for (Account o:accounts)
            {
                if(cardId.equals(o.getCardId()))
                {
                    account=o;
                    break OUT;
                }
            }
            System.out.println("卡号不存在，请重新输入");
        }
        return account;
    }

    private static void userCommand(ArrayList<Account>accounts,Account account,Scanner in) {
        OUT:
        while (true){
            System.out.println("欢迎来到操作界面");
            System.out.println("1.查询\n2.存款\n3.取款\n4.转账\n5.修改密码\n6.注销账户\n7.退出");
            System.out.println("请输入操作命令");
            int option=0;
            String tem;
            do {
                try {
                    tem=in.next();
                    option=Integer.valueOf(tem);
                    if (option<=0 || option>7)
                    {
                        System.out.println("你输入的操作不存在，请重新输入");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("你输入的操作不存在，请重新输入");
                }
            }while (option<=0 || option>7);
            switch (option)
            {
                case 1:
                    System.out.println("当前账户卡号为"+account.getCardId());
                    System.out.println("姓名为"+account.getName());
                    System.out.println("余额为"+account.getMoney());
                    System.out.println("提现上限为"+account.getLimitWithdrawals());
                    break;
                case 2:
                    System.out.println("请输入存款金额");
                    double add=0;
                    do {
                        try {
                            tem=in.next();
                            add=Double.valueOf(tem);
                            if(add<=0){
                                System.out.println("请输入正确金额");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("请输入正确金额");
                        }
                    }while (add<=0);
                    account.setMoney(account.getMoney()+add);
                    System.out.println("存款成功，当前余额"+account.getMoney());
                    break;
                case 3:
                    if(account.getMoney()==0)
                    {
                        System.out.println("当前账户余额为0.0元，无法取款");
                        break;
                    }
                    System.out.println("请输入取款金额");
                    double minus=0;
                    do {
                        try {
                            tem=in.next();
                            minus=Double.valueOf(tem);
                            if(minus<=0){
                                System.out.println("请输入正确金额");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("请输入正确金额");
                        }
                    }while (minus<=0);
                    if(minus>account.getMoney())
                    {
                        System.out.println("余额不足，当前余额为"+account.getMoney());
                    }
                    else
                    {
                        if (minus>account.getLimitWithdrawals())
                        {
                            System.out.println("取款金额大于设定提现上限" + account.getLimitWithdrawals() +"元");
                        }
                        else
                        {
                            account.setMoney(account.getMoney() - minus);
                            System.out.println("取款成功，当前余额为" + account.getMoney());
                        }
                    }
                    break;
                case 4:
                    Account account1=searchById(accounts,in);
                    if (account1==account)
                        System.out.println("对不起，不能给自己转账");
                    String userName = "*" + account1.getName().substring(1);
                    System.out.println("请你输入" + userName + "的姓氏");
                    String surName;
                    int cnt;
                    for (cnt= 5; cnt >0; cnt--) {
                        surName=in.next();
                        if (account1.getName().startsWith(surName))
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("回答错误，你还剩" + cnt +"次机会");
                        }
                    }
                    if(cnt==0)
                    {
                        System.out.println("对不起，信息验证失败，你不能进行转账");
                    }
                    else
                    {
                        System.out.println("验证成功，请输入转账金额");
                        double transfer;
                        transfer=in.nextDouble();
                        if(transfer>account.getMoney())
                        {
                            System.out.println("转账失败，你的余额仅剩" + account.getMoney() + "元");
                        }
                        else
                        {
                            if (transfer>account.getLimitWithdrawals())
                            {
                                System.out.println("转账失败，转账额度大于提现上限"+account.getLimitWithdrawals()+"元");
                            }
                            else
                            {
                                account.setMoney(account.getMoney() - transfer);
                                account1.setMoney(account1.getMoney() + transfer);
                                System.out.println("转账成功");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("请输入当前密码");
                    String password;
                    do {
                        password=in.next();
                        if (!password.equals(account.getPassword()))
                            System.out.println("密码错误，请重新输入");
                    }while (!password.equals(account.getPassword()));
                    String ensurePassword;
                    do {
                        System.out.println("请输入新密码");
                        password=in.next();
                        System.out.println("请确认密码");
                        ensurePassword=in.next();
                        if (!password.equals(ensurePassword))
                            System.out.println("对不起，你两次密码输入不一致，请重新输入");
                    }while(!password.equals(ensurePassword));
                    account.setPassword(password);
                    System.out.println("密码修改成功");
                    break;
                case 6:
                    accounts.remove(account);
                    System.out.println("销户成功");
                case 7:
                    break OUT;
            }
        }
    }
}