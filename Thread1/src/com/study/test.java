package com.study;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TODO
 * @date 2021/8/25 16:27
 **/

/**
 *输入：[2, 3, 1, 4, 0]
 * 输出：3
 * 解释：
 * 下面列出了每个 K 的得分：                    2  3  1  4  0  差分
 * K = 0,  A = [2,3,1,4,0],    score 2      0  0  1  0  1   0   0   0   -1  0      -1
 * K = 1,  A = [3,1,4,0,2],    score 3      1  0  1  0  1   1   0   0   0   0       1
 * K = 2,  A = [1,4,0,2,3],    score 3      1  1  0  0  1   0   1   -1  0   0       0
 * K = 3,  A = [4,0,2,3,1],    score 4      1  1  1  0  1   0   0   1   0   0       1
 * K = 4,  A = [0,2,3,1,4],    score 3      0  0  1  1  1   -1  -1  0   1   0       -1
 * 所以我们应当选择K = 3，得分最高。
 */

/**         leetcode 798 . 差分法 只计算变化的位置得到每一次变化影响最终根据影响增加和减少确认最大。
 *     int bestRotation(vector<int>& nums) {
 *         int n = nums.size();
 *         int diff[n];
 *         memset(diff, 0, sizeof(diff));
 *         for (int i = 0; i < n; ++i)
 *         {
 *             ++diff[(i+1)%n];
 *             --diff[(n+i+1-nums[i])%n];
 *         }
 *
 *         int res = 0;
 *         int currScore = 0;
 *         int maxScore = INT_MIN;
 *         for (int i = 0; i < n; ++i)
 *         {
 *             currScore += diff[i];
 *             if (currScore > maxScore)
 *             {
 *                 maxScore = currScore;
 *                 res = i;
 *             }
 *         }
 *
 *         return res;
 *     }
 *
 */
public class test {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(2);
        nums.add(3);
        nums.add(1);
        nums.add(4);
        nums.add(0);
        int min=0;
        int minval=0;
        Integer dp[] = new Integer[20];
        for(int j=0;j<nums.size();j++){
            minval += nums.get(j)<=j?1:0;
        }
        dp[0]=minval;
        for(int i=1;i<nums.size();i++){
            int tempval=0;
            for(int j=0;j<nums.size();j++){
                tempval += nums.get(j)<=((j-i+nums.size())%nums.size())?1:0;
            }
            if(tempval>minval){
                min=i;
                minval=tempval;
            }
        }
        System.out.println("min: "+min+"\t"+"minval: "+minval);

    }
}
