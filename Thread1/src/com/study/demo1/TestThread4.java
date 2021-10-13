package com.study.demo1;

public class TestThread4 implements Runnable{

    private int tickNums= 10;
    @Override
    public void run() {
        while (true){
            if(tickNums<=0) break;
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"------拿到了第"+ tickNums--+"张票");
        }
    }

    public static void main(String[] args) {
        TestThread4 ticket = new TestThread4();
        new Thread(ticket,"小明").start();
        new Thread(ticket,"小红").start();
        new Thread(ticket,"黄牛").start();

    }
}
