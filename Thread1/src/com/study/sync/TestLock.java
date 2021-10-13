package com.study.sync;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }


}
class TestLock2 extends Thread{
    int tickNums =10;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try{
                reentrantLock.lock();
                if(tickNums>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickNums--);
                }else{
                    break;
                }

            }finally {
                reentrantLock.unlock();
            }

        }

    }
}
