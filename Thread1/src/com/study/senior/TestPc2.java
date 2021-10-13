package com.study.senior;

public class TestPc2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

class Player extends Thread{
    private TV tv;
    Player(TV tv){
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                tv.play("正在播放1号表演");
            }else{
                tv.play("正在播放2号表演");
            }
        }

    }
}

class Watcher extends  Thread{
    private TV tv;
    Watcher(TV tv){
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }

    }
}

class TV{
    //
    String voice;
    boolean flag = true;

    //表演
    public synchronized void play(String voice){
        while(!this.flag){
            //正在表演，等待结束
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演了"+voice);
        //开始表演，通知观看
        this.notify();
        this.voice = voice;
        this.flag = !this.flag;
    }

    public synchronized void watch(){
        while(this.flag){
            //正在观看，等待结束
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了"+voice);
        //观看完毕，通知再次表演
        this.notifyAll();
        this.flag=!this.flag;
    }
}


