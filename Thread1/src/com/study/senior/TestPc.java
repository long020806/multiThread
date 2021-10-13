package com.study.senior;


public class TestPc {
    public static void main(String[] args) {
        SyncContainer container =new SyncContainer();
        Productor productor = new Productor(container);
        productor.setName("生产者0");
        productor.start();
//        Productor productor1 = new Productor(container);
//        productor1.setName("生产者1");
//        productor1.start();
//        Productor productor2 = new Productor(container);
//        productor2.setName("生产者2");
//        productor2.start();
        Consumer consumer = new Consumer(container);
        consumer.setName("消费者0");
        consumer.start();
//        Consumer consumer1 = new Consumer(container);
//        consumer1.setName("消费者1");
//        consumer1.start();
//        Consumer consumer2 = new Consumer(container);
//        consumer2.setName("消费者2");
//        consumer2.start();
    }
}

class Productor extends Thread{

    SyncContainer container;
    Productor(SyncContainer container){
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            container.push(new Chicken(i));
            System.out.println(Thread.currentThread().getName()+"生产了"+i+"只产品");
        }
    }
}

class Consumer extends Thread{
    SyncContainer container;
    Consumer(SyncContainer container){
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            Chicken chicken = container.pop();
            System.out.println(Thread.currentThread().getName()+"消费了第"+chicken.id+"只产品");
        }
    }
}

class Chicken{
    int id;

    Chicken(int id) {
        this.id = id;
    }
}

class SyncContainer{
    //缓冲池大小
    Chicken chickens[] = new Chicken[10];
    int count =0;
    //生产者生产

    public synchronized void push(Chicken chicken) {
        //缓冲池已满，等待消费者
        while(count == chickens.length){
            //进入自旋等待，防止被唤醒后未争抢过
            //通知消费者消费，自我等待
            try {
//                System.out.println(Thread.currentThread().getName()+"进入等待"+"--count: "+count);
                this.wait();
//                System.out.println(Thread.currentThread().getName()+"被唤醒"+"--count: "+count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有将产品放入缓冲池
//        System.out.println(Thread.currentThread().getName()+"正常运行"+"--count: "+count);
        chickens[count] = chicken;
        count++;
//        System.out.println(Thread.currentThread().getName()+"运行完毕"+"--count: "+count);
        //通知消费者消费（缓冲池不再为空）
        this.notifyAll();
    }
    public synchronized Chicken pop(){
        //缓冲池为空，等待生产者
        while(count==0){
            //进入自旋等待，防止被唤醒后未争抢过
            //通知生产者生产,自我等待
            try {
//                System.out.println(Thread.currentThread().getName()+"进入等待"+"--count: "+count);
                this.wait();
//                System.out.println(Thread.currentThread().getName()+"被唤醒"+"--count: "+count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(Thread.currentThread().getName()+"正常运行"+"--count: "+count);
        count--;
        Chicken chicken = chickens[count];
//        System.out.println(Thread.currentThread().getName()+"运行完毕"+"--count: "+count);
        //消费完毕，可以继续生产(缓冲池不在为满)
        this.notifyAll();
        return chicken;
    }

}