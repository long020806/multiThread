package com.study.demo2;

import com.study.demo1.TestThread2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.concurrent.*;

public class TestCallable implements Callable<Boolean> {

    private String url;//保存图片地址
    private String name;//保存图片名字
    TestCallable(String url,String name){
        this.url=url;
        this.name=name;
    }
    @Override
    public Boolean call() throws Exception {
        WebDownload webDownload = new WebDownload();
        webDownload.downloader(url,name);
        System.out.println("下载了文件名:"+name);

        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdocs.ebdoor.com%2Fimage%2Fcompany%2F283%2F2836590_intro1.jpg&refer=http%3A%2F%2Fdocs.ebdoor.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631429877&t=490a122c4fcda49d12a8f8242ff16532","1.jpg");
        TestCallable t2 = new TestCallable("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdocs.ebdoor.com%2Fimage%2Fcompany%2F283%2F2836590_intro1.jpg&refer=http%3A%2F%2Fdocs.ebdoor.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631429877&t=490a122c4fcda49d12a8f8242ff16532","2.jpg");
        TestCallable t3 = new TestCallable("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdocs.ebdoor.com%2Fimage%2Fcompany%2F283%2F2836590_intro1.jpg&refer=http%3A%2F%2Fdocs.ebdoor.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631429877&t=490a122c4fcda49d12a8f8242ff16532","3.jpg");
        //创建执行服务
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> future1 = executorService.submit(t1);
        Future<Boolean> future2 = executorService.submit(t2);
        Future<Boolean> future3 = executorService.submit(t3);
        //获取结果
        boolean rs1 = future1.get();
        boolean rs2 = future2.get();
        boolean rs3 = future3.get();
        //关闭服务
        executorService.shutdown();
    }

}
class WebDownload {
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}