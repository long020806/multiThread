package com.study.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TODO
 * @date 2021/8/20 14:14
 **/
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //main do things......
        System.out.println(atomicInteger.compareAndSet(5, 6)+" \t current data : "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+" \t current data : "+atomicInteger.get());
    }
}
