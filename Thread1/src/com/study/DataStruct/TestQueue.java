package com.study.DataStruct;

import java.util.*;

public class TestQueue {
    public static void main(String[] args) {
        TestQueue testQueue = new TestQueue();
        Tree first = testQueue.CreateATree();
        testQueue.bfs(first);
//        testQueue.stackTest();
    }
    public Tree CreateATree(){
        List<Integer> list = Arrays.asList(5,1,2,3,5,6,1,2,5,2);
//        System.out.println(list.size());
        List<Tree> list1 = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            list1.add(new Tree(null,null,list.get(j)));
        }
        for (int j = 1; j <= list.size(); j++) {
            if((2*j)<=list.size()){
                list1.get(j-1).left = list1.get(2*j-1);
//                System.out.println(list1.get(2 * j - 1));
            }
            if(2*j<=list.size()-1){
//                System.out.println(list1.get(2*j));
                list1.get(j-1).right = list1.get(2*j);
            }
        }
        return list1.get(0);
    }

    public void bfs(Tree firsttree){
        Queue<Tree> trees = new LinkedList<>();
        trees.offer(firsttree);
        while(trees.size()!=0){
            Tree tree = trees.poll();
            System.out.println(tree.data);
            if(tree.left!=null){
                trees.offer(tree.left);
            }
            if(tree.right!=null){
                trees.offer(tree.right);
            }
        }
    }
    public void dfs(Tree tree){
        if(tree ==null) return;
        System.out.println(tree.data);
        dfs(tree.left);
        dfs(tree.right);
    }
    public void stackTest(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(2);
        Integer a = stack.peek();
        System.out.println(a);
        System.out.println(stack.size());
        System.out.println(stack.empty());
        Integer b = stack.pop();
        System.out.println(b);
        System.out.println(stack.size());
        System.out.println(stack.empty());
        stack.pop();
        System.out.println(stack.size());
        System.out.println(stack.empty());
        stack.pop();
        System.out.println(stack.size());
        System.out.println(stack.empty());
    }
}
class Tree{
    public Tree left;
    public Tree right;
    public Integer data;


    public Tree(Tree left, Tree right, Integer data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "left=" + left +
                ", right=" + right +
                ", data=" + data +
                '}';
    }
}