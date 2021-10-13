package com.study.sync;

public class UnSafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station,"1").start();
        new Thread(station,"2").start();
        new Thread(station,"3").start();
        new Thread(station,"4").start();

    }
}

class BuyTicket implements Runnable{
    private int tickNums =10;
    private boolean flag =true;
    @Override
    public void run() {
        //买票
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private synchronized void buy() throws InterruptedException {
        //判断是否有票
        if(tickNums<=0){
            flag=false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+"----拿到"+tickNums--);

    }
}
