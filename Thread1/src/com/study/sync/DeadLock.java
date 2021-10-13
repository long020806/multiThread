package com.study.sync;

public class DeadLock {
    public static void main(String[] args) {
        MakeUp m1 = new MakeUp(0,"1");
        MakeUp m2 = new MakeUp(1,"1");
        m1.start();
        m2.start();
    }
}

class LipStick{

}

class Mirror{

}
class MakeUp extends Thread{
    static LipStick lipStick = new LipStick();
    static Mirror mirror = new Mirror();
    int choice;
    String girlName;
    public MakeUp(int choice,String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }
    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void makeup() throws InterruptedException {
        if(choice==0){
            synchronized (lipStick){
                System.out.println(this.getName()+"获得口红的锁");
                Thread.sleep(1000);

            }
            synchronized (mirror){
                System.out.println(this.getName()+"获得镜子的锁");
            }
        }else{
            synchronized (mirror){
                System.out.println(this.getName()+"获得镜子的锁");
                Thread.sleep(2000);

            }
            synchronized (lipStick){
                System.out.println(this.getName()+"获得口红的锁");
            }
        }
    }
}
