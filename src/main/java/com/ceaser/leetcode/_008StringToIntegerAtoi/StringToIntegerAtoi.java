package com.ceaser.leetcode._008StringToIntegerAtoi;
/*
8. String to Integer (atoi)   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 136522
Total Submissions: 991177
Difficulty: Easy
Contributors: Admin
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
*/

public class StringToIntegerAtoi {

	public static void main(String[] args) {
		String input ="123456";
		StringToIntegerAtoi sti = new StringToIntegerAtoi();
		System.out.println(sti.parseStrToInt(input));
		
	}
	
	public int parseStrToInt(String input){
		if(null==input || "".equals(input)){
			return -1;
		}
		if(input.indexOf(".")>0){
			return -1;
		}
		
		int returnnum  = -1;
		char[] charArr = input.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			if(Character.isDigit(charArr[i])){
				returnnum+=Character.getNumericValue(charArr[i])*(Math.pow(10, charArr.length-i-1));
			}else{
				return returnnum;
			}
		}
		return returnnum;
		
	}
}
