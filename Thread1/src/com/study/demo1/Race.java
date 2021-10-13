package com.study.demo1;

public class Race implements Runnable{

    private static String winner;
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(Thread.currentThread().getName().equals("兔子")&&i%20==19){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(gameover(i)){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"-----跑了"+i+"米");
        }
    }

    private boolean gameover(int steps){
        if(winner!=null){
            return true;
        }
        if(steps>=100){
            winner = Thread.currentThread().getName();
            System.out.println("winner是-------"+winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race =new Race();
        new Thread(race,"乌龟").start();
        new Thread(race,"兔子").start();

    }
}
