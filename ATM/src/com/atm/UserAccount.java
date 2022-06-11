package com.atm;

public class UserAccount {
    private String userNum;

    public UserAccount(String userNum, String userName, String passwd, double withdrawMoneyAmount, double bankSaving) {
        this.userNum = userNum;
        this.userName = userName;
        Passwd = passwd;
        this.withdrawMoneyAmount = withdrawMoneyAmount;
        this.bankSaving = bankSaving;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    private String userName;
    private String Passwd;
    private double withdrawMoneyAmount;
    private double bankSaving;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }

    public double getWithdrawMoneyAmount() {
        return withdrawMoneyAmount;
    }

    public void setWithdrawMoneyAmount(double withdrawMoneyAmount) {
        this.withdrawMoneyAmount = withdrawMoneyAmount;
    }

    public double getBankSaving() {
        return bankSaving;
    }

    public void setBankSaving(double bankSaving) {
        this.bankSaving = bankSaving;
    }

    public UserAccount(String userName, String passwd, double withdrawMoneyAmount, double bankSaving) {
        this.userName = userName;
        Passwd = passwd;
        this.withdrawMoneyAmount = withdrawMoneyAmount;
        this.bankSaving = bankSaving;
    }

    public UserAccount() {
    }
}
