package com.ceaser.leetcode._004medianoftwosortedarrays;
import java.util.Arrays;
/*
4. Median of Two Sorted Arrays  QuestionEditorial Solution  My Submissions
Total Accepted: 117459
Total Submissions: 586359
Difficulty: Hard
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/


public class MedianofTwoSortedArrays {

	public static void main(String[] args) {
		int [] numsA = {1,10,20,30,40,50,60,70,80,90,100};
		int [] numsB = {5,15,25,35,45,55,65,75,85,95,105,115};
		int [] mergeAB = new int[numsA.length+numsB.length];
		int media = 0;
		int mergeABIdx = -1;
		int i;
		int j;
		for (i = 0,j=0; i < numsA.length && j<numsB.length; i++,j++) {
			if(numsA[i]==numsB[j]){
				mergeAB[++mergeABIdx]=numsA[i];
				mergeAB[++mergeABIdx]=numsB[j];
			}else if(numsA[i]<numsB[j]){
				mergeAB[++mergeABIdx] = numsA[i];
				mergeAB[++mergeABIdx] = numsB[j];
			}else if(numsA[i]>numsB[j]){
				mergeAB[++mergeABIdx] = numsB[j];
				mergeAB[++mergeABIdx] = numsA[i];
			}
		}
		if(numsA.length>numsB.length){
			for(int t=i;t<numsA.length;t++){
				mergeAB[++mergeABIdx] = numsA[t];
			}
		}else if(numsA.length<numsB.length){
			for(int t=j;t<numsB.length;t++){
				mergeAB[++mergeABIdx] = numsB[t];
			}
		}
		System.out.println(Arrays.toString(mergeAB).toString());
		
		if(mergeAB.length%2==0){
			media = (mergeAB[mergeAB.length/2]+mergeAB[(mergeAB.length/2)-1])/2;
		}else{
			media = mergeAB[mergeAB.length/2];
		}
		System.out.println("The midea is : "+media);
	}

}
