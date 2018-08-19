package com.ceaser.thread;

import java.util.concurrent.locks.Lock;

/**
 * ���Կ����߳����Ƴɶ������Ҳ������ͬһʱ��ֻ�������߳��ܹ���
	ȡ�����������TwinsLock���԰���Ԥ����ȷ������
 * @author wangzequan
 *
 */
public class TwinsLockTest {
	public void test() throws InterruptedException {
		final Lock lock = new TwinsLock();
		class Worker extends Thread {
			public void run() {
				while (true) {
					lock.lock();
					try {
						Thread.sleep(100);
						System.out.println(Thread.currentThread().getName());
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}
				}
			}
		}
		// ����10���߳�
		for (int i = 0; i < 10; i++) {
			Worker w = new Worker();
			System.out.println("name : "+w.getName());
			w.setDaemon(true);
			w.start();
		}
		// ÿ��1�뻻��
		for (int i = 0; i < 10; i++) {
			Thread.sleep(100);
			System.out.println();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		TwinsLockTest tt = new TwinsLockTest();
		tt.test();
	}
}