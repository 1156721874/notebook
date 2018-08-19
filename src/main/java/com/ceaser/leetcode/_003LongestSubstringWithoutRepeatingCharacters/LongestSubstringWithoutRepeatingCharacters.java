package com.ceaser.leetcode._003LongestSubstringWithoutRepeatingCharacters;

/**
 * Given a string, find the length of the longest substring without repeating characters.
	Examples:
	Given "abcabcbb", the answer is "abc", which the length is 3.
	Given "bbbbb", the answer is "b", with the length of 1.
	Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * @author Administrator
 reference : http://www.cnblogs.com/dollarzhaole/p/3155712.html
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		String str = "abcdefkkghzizja";
		int idx = -1;
		int max= 0;
		int [] loca = new int[256];
		char [] strArr = str.toCharArray();
		for (int i = 0; i < strArr.length; i++) {
			if(loca[strArr[i]]>idx){
				idx = loca[strArr[i]];
			}
			if(i-idx>max){
				max= i-idx;
			}
			loca[strArr[i]] = i;
		}
		System.out.println(max);
		for (int i = 0; i < loca.length; i++) {
			System.out.print(loca[i]);
		}
	}

}
