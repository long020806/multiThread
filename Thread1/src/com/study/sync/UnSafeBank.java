package com.study.sync;

public class UnSafeBank {

    public static void main(String[] args) {
        Account account =new Account("--------",100);
        Bank bank = new Bank(account,100,"1");
        Bank bank1 = new Bank(account,100,"2");
        bank.start();
        bank1.start();

    }

}


class Account{
    public String name;
    public int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}
class Bank extends Thread {
    Account account;
    int drawingMoney;
    int nowMoney;
    public Bank(Account account,int drawingMoney,String name){
        super(name);
        this.account =account;
        this.drawingMoney = drawingMoney;
    }
    @Override
    public void run() {
        synchronized (account){

            if(account.money-drawingMoney<0){
                System.out.println(Thread.currentThread().getName()+"钱不够，取不了");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money  = account.money - drawingMoney;
            nowMoney+=drawingMoney;
            System.out.println(account.name + "余额为:"+ account.money);
            System.out.println("手里有"+nowMoney);
        }
    }
}