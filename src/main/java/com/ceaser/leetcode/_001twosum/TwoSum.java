package com.ceaser.leetcode._001twosum;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.
Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 * @author Administrator
 *
 */
public class TwoSum {
    public static void getIndexOfTarget(){
        int [] originalArr = new int[]{2, 7, 11, 15,18,6,9,15,4,10,11,13};
        TwoSum.twoSum(originalArr,28);
    }
    
    public static void main(String [] args){
    	TwoSum.getIndexOfTarget();
    }
    
    public static void twoSum(int[] nums, int target) {
        for(int i=0; i<nums.length-1;i++){
            for(int j=i+i; j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    System.out.println("nums["+i+"] + "+ "nums["+j+"] = "+target);
                }
            }
        }
    }
	
}
