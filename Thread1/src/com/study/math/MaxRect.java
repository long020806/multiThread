package com.study.math;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author TODO
 * @date 2021/8/26 10:23
 **/
public class MaxRect {
    public static void main(String[] args) {

        MaxRect maxRect = new MaxRect();
//        maxRect.stackTest();
        int heights[] = new int[]{1,3,7,4};
        maxRect.Solution(heights);
    }
    /**
     * 柱状图最大矩形
     * 暴力解法||单调栈
     * [2,3,1,6,7,5]
     * [0,2,3,1,6,7,5,0]增加哨兵
     */
    public int Solution(int []heights){
        int len =heights.length;
        if(len==0){
            return 0;
        }
        if(len==1){
            return heights[0];
        }
        int area =0;
        int newheights[] = new int[len+2];
        for(int i=0;i<len;i++){
            newheights[i+1] = heights[i];
        }
        len+=2;
        heights = newheights;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addLast(0);
        for(int i=1;i<len;i++){
            while(heights[stack.peekLast()]>heights[i]){
                int front = stack.peekLast();
                stack.removeLast();
                int height = heights[front];
                int width = (i-front);
                area = Math.max(area,width*height);
            }
            stack.addLast(i);
        }
        System.out.println(area);
        return 0;
    }
    public void stackTest(){
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addLast(1);
        stack.addLast(2);
        System.out.println(stack.size());
//        int a =stack.pop();

        System.out.println(stack.getLast());
        System.out.println(stack.size());
    }
}
