package com.ceaser.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolInvoke {
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

		CountDownLatch cdl = new CountDownLatch(15);
		
		for (int i = 0; i < 15; i++) {
			MyTask myTask = new MyTask(i,cdl);
			executor.execute(myTask);
			//System.out.println("�̳߳����߳���Ŀ��" + executor.getPoolSize() + "�������еȴ�ִ�е�������Ŀ��" + executor.getQueue().size()+ "����ִ������������Ŀ��" + executor.getCompletedTaskCount());
		}
		
		cdl.await();
		
		/*executor.shutdown();
		boolean flag = true;
		while(!executor.isTerminated()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		executor.shutdown();
		System.out.println("end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}

class MyTask implements Runnable {
	private int taskNum;
	CountDownLatch cdl;
	public MyTask(int num,CountDownLatch cdl) {
		this.taskNum = num;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		System.out.println("����ִ��task " + taskNum);
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task " + taskNum + "ִ�����");
		this.cdl.countDown();
	}
}