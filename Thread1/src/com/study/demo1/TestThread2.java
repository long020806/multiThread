package com.study.demo1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * 实现同步下载
 */
public class TestThread2 extends Thread{
    private String url;//保存图片地址
    private String name;//保存图片名字
    TestThread2(String url,String name){
        this.url=url;
        this.name=name;
    }
    @Override
    public void run() {
        WebDownload webDownload = new WebDownload();
        webDownload.downloader(url,name);
        System.out.println("下载了文件名:"+name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdocs.ebdoor.com%2Fimage%2Fcompany%2F283%2F2836590_intro1.jpg&refer=http%3A%2F%2Fdocs.ebdoor.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631429877&t=490a122c4fcda49d12a8f8242ff16532","1.jpg");
        TestThread2 t2 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdocs.ebdoor.com%2Fimage%2Fcompany%2F283%2F2836590_intro1.jpg&refer=http%3A%2F%2Fdocs.ebdoor.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631429877&t=490a122c4fcda49d12a8f8242ff16532","2.jpg");
        TestThread2 t3 = new TestThread2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdocs.ebdoor.com%2Fimage%2Fcompany%2F283%2F2836590_intro1.jpg&refer=http%3A%2F%2Fdocs.ebdoor.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631429877&t=490a122c4fcda49d12a8f8242ff16532","3.jpg");
        t1.start();
        t2.start();
        t3.start();
    }
}
class WebDownload{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
