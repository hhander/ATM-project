package com.atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AtmSys {

    public static void main(String[] args) {
        ArrayList<UserAccount> userArray = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("==========欢迎来此取钱，请输入您的操作===========");

            System.out.println("1.登录用户");
            System.out.println("2.注册用户");
            System.out.println("3.查看用户");
            System.out.println("0.退出程序");
            System.out.println("请选择你的选项");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice)
            {
                case 0:
                    flag = false;
                    System.out.println("退出成功");
                    break;
                case 1:
                    UserAccount loginUser = loginAccount(userArray, sc);
                    if(loginUser == null)
                    {
                        System.out.println("登陆失败");
                        break;
                    }
                    else
                    {
                        accountOperate(userArray,loginUser,sc);
                    }
                    break;
                case 2:
                    UserAccount u1 = register(userArray, sc);
                    if(u1==null)
                    {
                        System.out.println("注册失败");
                    }
                    else {
                        userArray.add(u1);
                    }
                    break;
                case 3:
                    showAccount(userArray);

                    break;
                default:
                    System.out.println("选择不在选项内");
                    break;
            }
        }


    }


    public static void showAccount(ArrayList<UserAccount> userArray) {
        for (int i = 0; i < userArray.size(); i++) {
            System.out.println(userArray.get(i).getUserName());
        }


    }


    public static UserAccount register(ArrayList<UserAccount> userArray, Scanner sc) {
        System.out.println("============欢迎来到开户============");
        System.out.println("请输入账户名称");
        String accountName = sc.next();
        Random r = new Random();

        System.out.println("请输入账户密码");
        String passwd1 = sc.next();
        int count = 3;
        double amount;
        int userCardNum;

        while (true) {

            System.out.println("请重新确认密码");
            String passwd2 = sc.next();
            count--;
            if (passwd1.equals(passwd2)) {
                System.out.println("密码确认成功");
                break;
            } else {
                System.out.println("密码确认失败，请重新确认");
            }
            if (count == 0) {
                System.out.println("尝试次数达到三次,已退出");
                return null;
            }
        }

        System.out.println("请输入取款额度");
        amount = sc.nextDouble();

        System.out.println("添加账户成功");

        String userNum;
        while(true)
        {
            userNum = GetCarId();
            if(findAccount(userArray,userNum) == null)
            {
                break;
            }
        }
        System.out.println("您的卡号为" + userNum);



        return new UserAccount(userNum,accountName, passwd1, amount, 0.0);


    }

    public static UserAccount loginAccount(ArrayList<UserAccount> userArray, Scanner sc) {
        boolean flag = true;
        int count = 3;
        if (userArray.size() == 0) {
            System.out.println("当前账户没有账户，请先注册");
        }
        else
        {
            System.out.println("请输入卡号");
            String cardId = sc.next();
            while(flag)
            {
                UserAccount u1 = findAccount(userArray, cardId);
                if (u1 == null)
                {
                    System.out.println("查无此号");
                    System.out.println("选择退出0 重新键入选1");
                    int choice = sc.nextInt();
                    switch (choice)
                    {
                        case 0:
                        flag = false;
                        System.out.println("退出成功");
                        break;
                        case 1:
                            System.out.println("请重新输入卡号");
                            cardId = sc.next();
                    }
                }
                else
                {

                    System.out.println("请输入密码");
                    String passwd = sc.next();
                    while(true)
                    {
                        if (passwd.equals(u1.getPasswd()))
                        {
                            System.out.println("登录成功");
                            return u1;
                        }
                        else
                        {
                            System.out.println("请重新输入密码");
                            passwd = sc.next();
                            count--;
                        }
                        if(count == 0)
                        {
                            System.out.println("密码输入错误3次，已经锁定");
                            return null;
                        }
                    }

                }
            }
        }
        return null;
    }

    public static String GetCarId()
    {
        Random r = new Random();
        String cardId = "";
        for (int i = 0; i < 8; i++)
        {
            cardId = cardId + r.nextInt(10);
        }
        return cardId;
    }

    public static UserAccount findAccount(ArrayList<UserAccount> userArray,String cardId)
    {
        for (int i = 0; i < userArray.size(); i++)
        {
            if(userArray.get(i).getUserNum().equals(cardId))
            {
                return userArray.get(i);
            }
        }
        return null;
    }


    public static void accountOperate(ArrayList<UserAccount> userArray,UserAccount loginUser,Scanner sc)
    {
        boolean flag = true;
        while (flag)
        {
            System.out.println("===========欢迎来到用户操作系统============");
            System.out.println("1.查询余额");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("0.退出账户");
            System.out.println("请选择");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 0:
                    flag = false;
                    System.out.println("退出成功");
                    break;
                case 1:
                    query(loginUser);
                    break;
                case 2:
                    System.out.println("请输入存钱的金额");
                    double money = sc.nextDouble();
                    saveMoney(loginUser,money);
                    break;
                case 3:
                    System.out.println("请输入取出的金额");
                    double money1 = sc.nextDouble();
                    withDrawMoney(loginUser,money1);
                    break;
                case 4:
                    double money2 = 0.0;
                    System.out.println("请输入需要转账的卡号");
                    String cardId = sc.next();
                    if(findAccount(userArray,cardId) == null)
                    {
                        System.out.println("没有找到该用户");
                    }
                    else
                    {
                        System.out.println("请核对对方信息");
                        UserAccount user3 = findAccount(userArray,cardId);
                        System.out.println(findAccount(userArray,cardId).getUserName());
                        System.out.println("请选择是否继续交易 0.退出 1.继续");
                        int choice1 = sc.nextInt();
                        switch (choice1)
                        {
                            case 0:
                                System.out.println("成功退出");
                                break;
                            case 1:
                                System.out.println("请输入转账金额");
                                money2 = sc.nextDouble();
                                transactionOperate(loginUser,user3,money2);
                                break;
                            default:
                                System.out.println("非法输入");
                                break;
                        }
                    }
                    break;
                case 5:
                    ModifyPaawd(loginUser,sc);
                    break;
                default:
                    break;
            }
        }


    }

    public static void query(UserAccount user)
    {
        System.out.println("卡号为" + user.getUserNum() + "的用户" + "您还有" + user.getBankSaving()+"元" + "存款");
    }

    public static void saveMoney(UserAccount user,double money)
    {
        user.setBankSaving(user.getBankSaving() + money);
        System.out.println("存钱成功");
    }

    public static void withDrawMoney(UserAccount loginUser,double money1)
    {
        if(loginUser.getBankSaving()<money1 || money1 > loginUser.getWithdrawMoneyAmount())
        {
            if(loginUser.getBankSaving()<money1)
            {
                System.out.println("余额不足");
            }
            else if(money1 > loginUser.getWithdrawMoneyAmount())
            {
                System.out.println("超出额度");
            }
            else
            {
                System.out.println("余额不足并且超出限额");
            }
        }
        else
        {
            loginUser.setBankSaving(loginUser.getBankSaving() - money1);
            System.out.println("取款成功，您的账户剩余" + loginUser.getBankSaving());
        }
    }

    public static void transactionOperate(UserAccount loginUser,UserAccount UserGetMoney,double money2)
    {
        if(loginUser.getBankSaving()>=money2&&loginUser.getWithdrawMoneyAmount()>=money2)
        {
            loginUser.setBankSaving(loginUser.getBankSaving()-money2);
            UserGetMoney.setBankSaving(UserGetMoney.getBankSaving() + money2);
            System.out.println("转账成功，您的余额为" + loginUser.getBankSaving());
        }
        else
        {
            System.out.println("请确认您的存款" + loginUser.getBankSaving() + "是否大于转账金额，并且在"+loginUser.getWithdrawMoneyAmount() + "的转账额度内");
        }
    }

    public static  void ModifyPaawd(UserAccount loginUser,Scanner sc)
    {
        System.out.println("请输入原密码");
        String passwd = sc.next();
        int count = 3;
        while(true)
        {
            if (passwd.equals(loginUser.getPasswd()))
            {
                System.out.println("原密码正确，请输入需要修改的密码");
                String passwd1 = sc.next();
                System.out.println("请确认需要修改的密码");
                String passwd2 = sc.next();
                while(true)
                {
                    if (passwd1.equals(passwd2))
                    {
                        loginUser.setPasswd(passwd1);
                        System.out.println("修改成功");
                        return;
                    }
                    else
                    {
                        System.out.println("密码确认失败，请重新输入");
                        passwd2 = sc.next();
                        count--;
                    }
                    if(count == 0)
                    {
                        break;
                    }
                }

            }
            else
            {
                System.out.println("密码错误,请重输入");
                passwd = sc.next();
                count--;
            }
            if(count == 0)
            {
                System.out.println("密码错误三次");
                return;
            }
        }

    }
}


