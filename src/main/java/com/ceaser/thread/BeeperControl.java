package com.ceaser.thread;


import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * ��ʱ���߳�demo
 * @author wangzequan
 *
 */
public class BeeperControl {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		
		final Runnable beeper1 = new Runnable() {
			public void run() {
				System.out.println("beep1");
			}
		};
		
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
		final ScheduledFuture<?> beeperHandle1 = scheduler.scheduleAtFixedRate(beeper1, 6, 6, SECONDS);
		
		
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
				beeperHandle1.cancel(true);
			}
		}, 60 * 60, SECONDS);
	}

	public static void main(String[] args) {
		BeeperControl bs = new BeeperControl();
		bs.beepForAnHour();
	}

}
