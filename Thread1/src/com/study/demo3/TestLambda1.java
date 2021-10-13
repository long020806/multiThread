package com.study.demo3;

public class TestLambda1 {
    static class Like1 implements ILike{

        @Override
        public void lambda() {
            System.out.println("lambda2");

        }
    }
    //
    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();
        ILike like1 = new Like1();
        like1.lambda();



        class Like2 implements ILike{

            @Override
            public void lambda() {
                System.out.println("lambda3");

            }
        }
        ILike like2 = new Like2();
        like2.lambda();
        ILike like3 = new ILike() {
            @Override
            public void lambda() {
                System.out.println("lambda4");

            }
        };
        like3.lambda();
        ILike like4 = ()->{
            System.out.println("lambda5");
        };
        like4.lambda();
    }
}
interface ILike{
    void lambda();
}
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("lambda");
    }
}
