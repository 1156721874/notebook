package com.ceaser.leetcode._005LongestPalindromicSubstring;

public class LongestPalindromicSubstring {

	/**
	 * 椹媺杞︾畻娉�
	 * 5. Longest Palindromic Substring
	 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
	 * @param args
	 * @Description:
	 * @author wangzequan 2016 涓嬪崍4:03:29
	 * reference : https://github.com/SwiftClub/SwiftAlgorithms/wiki/%E9%A9%AC%E6%8B%89%E8%BD%A6%E7%AE%97%E6%B3%95
	 */
	public static void main(String[] args) {
		String str  = "asda123456789987654321lsjdfsbfskdbf";
		System.out.println(longestPalindrome(str));
	}

	 public static String longestPalindrome(String s) {  
	        int[] p = new int[2048];  
	        StringBuilder t = new StringBuilder("$");  
	        for (int i = 0; i < s.length(); ++i) {  
	            t.append('#');  
	            t.append(s.charAt(i));  
	        }  
	        t.append("#_");  
	        // mx涓哄凡鍒ゆ柇鍥炴枃涓叉渶鍙宠竟浣嶇疆锛宨d涓轰腑闂翠綅缃紝mmax璁板綍p鏁扮粍涓渶澶у��  
	        int mx = 0, id = 0, mmax = 0;  
	        int right = 0;  
	        for (int i = 1; i < t.length() - 1; i++) {  
	            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;  
	            while (t.charAt(i + p[i]) == t.charAt(i - p[i]))  
	                p[i]++;  
	            if (i + p[i] > mx) {  
	                mx = i + p[i];  
	                id = i;  
	            }  
	            if (mmax < p[i]) {  
	                mmax = p[i];  
	                right = i;  
	            }  
	        }  
	        // 鏈�闀夸负mmax - 1  
	        return s.substring(right/2 - mmax/2, right/2 - mmax/2 + mmax-1);  
	    }  

	
}
