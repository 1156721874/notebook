package com.ceaser.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * TwinsLockʵ����Lock�ӿڣ��ṩ������ʹ���ߵĽӿڣ�ʹ���ߵ���lock()
������ȡ����������unlock()�����ͷ�������ͬһʱ��ֻ���������߳�ͬʱ��ȡ������
TwinsLockͬʱ������һ���Զ���ͬ����Sync������ͬ���������̷߳��ʺ�ͬ��״̬���ơ���
����ʽ��ȡͬ��״̬Ϊ����ͬ�������ȼ������ȡ���ͬ��״̬��Ȼ��ͨ��CASȷ��״̬����
ȷ���ã���tryAcquireShared(int reduceCount)��������ֵ���ڵ���0ʱ����ǰ�̲߳Ż�ȡͬ��״
̬�������ϲ��TwinsLock���ԣ����ʾ��ǰ�̻߳��������
 * @author wangzequan
 *
 */
public class TwinsLock implements Lock {
	private final Sync sync = new Sync(2);

	private static final class Sync extends AbstractQueuedSynchronizer {
		Sync(int count) {
			if (count <= 0) {
				throw new IllegalArgumentException("count must large than zero.");
			}
			setState(count);
		}
		
		public int tryAcquireShared(int reduceCount) {
			for (;;) {
				int current = getState();
				int newCount = current - reduceCount;
				System.out.println(">>>newCount : "+newCount);
				if (newCount < 0 || compareAndSetState(current, newCount)) {
					System.out.println(">>>return newCount : "+ newCount);
					return newCount;
				}
			}
		}
		
		public boolean tryReleaseShared(int returnCount) {
			for (;;) {
				int current = getState();
				int newCount = current + returnCount;
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
	}

	public void lock() {
		sync.acquireShared(1);
	}

	public void unlock() {
		sync.releaseShared(1);
	}
	// �����ӿڷ�����

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
}