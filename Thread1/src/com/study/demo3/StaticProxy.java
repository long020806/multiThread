package com.study.demo3;

public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();

    }
}

interface Marry{
    void HappyMarry();
}
class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("you marry");
    }
}
class WeddingCompany implements Marry{
    private Marry target;
    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("after marry");
    }

    private void before() {
        System.out.println("before marry");
    }

}
