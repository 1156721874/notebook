package com.ceaser.leetcode._009palindromenumber;
/**https://leetcode.com/problems/palindrome-number/
 * Determine whether an integer is a palindrome. Do this without extra space.
	
	click to show spoilers.
	
	Some hints:
	Could negative integers be palindromes? (ie, -1)
	
	If you are thinking of converting the integer to string, note the restriction of using extra space.
	
	You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
 * @Title:
 * @Description:
 * @Author:wangzequan
 * @Since:2017骞�1鏈�20鏃� 涓婂崍10:49:12
 * @Version:1.1.0
 * @Copyright:Copyright (c) 
 */
public class PalindromeNumber {
	
	public static void main(String[] args) {
		PalindromeNumber palindromenumber = new PalindromeNumber();
		System.out.println(palindromenumber.isPalindromeNumber(7654567));
	}
	
	public boolean isPalindromeNumber(int num){
		int original = num;
		int rnum = 0;
		int  i = 0;
		while(num/10!=0){
			int calcnum = num%10;
			rnum +=calcnum *  (int)Math.pow(10, i);
			num = num/10;
			i++;
		}
		
		if(num/10==0){
			num = num/1;
			rnum +=num * (int)Math.pow(10, i);
		}
		return rnum==original;
	}
	
}
