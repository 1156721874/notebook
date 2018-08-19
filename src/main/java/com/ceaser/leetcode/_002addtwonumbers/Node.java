package com.ceaser.leetcode._002addtwonumbers;

public class Node {
	public Node() {
		super();
	}
	public int data;//�ڵ�����
	public int carry; //��λ
	public Node next;
	public Node(int data, int carry) {
		super();
		this.data = data;
		this.carry = carry;
	}
}
