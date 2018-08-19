package com.ceaser.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
/**
 * ʹ��Fork/Join�������Ҫ���ǵ�������ηָ��������ϣ��ÿ�����������ִ������
������ӣ���ô�������÷ָ����ֵ��2��������4��������ӣ�����Fork/Join��ܻ�������
��fork������������������һ�������1+2����������������3+4��Ȼ����join����������
�Ľ������Ϊ���н�����������Ա���̳�RecursiveTask��

ForkJoinPool��ForkJoinTask�����ForkJoinWorkerThread������ɣ�ForkJoinTask���鸺��
����ų����ύ��ForkJoinPool�����񣬶�ForkJoinWorkerThread���鸺��ִ����Щ����
��1��ForkJoinTask��fork����ʵ��ԭ��
�����ǵ���ForkJoinTask��fork����ʱ����������ForkJoinWorkerThread��pushTask����
�첽��ִ���������Ȼ���������ؽ�����������¡�
public final ForkJoinTask<V> fork() {
((ForkJoinWorkerThread) Thread.currentThread())
.pushTask(this);
return this;
}
pushTask�����ѵ�ǰ��������ForkJoinTask��������Ȼ���ٵ���ForkJoinPool��
signalWork()�������ѻ򴴽�һ�������߳���ִ�����񡣴������¡�
final void pushTask(ForkJoinTask<> t) {
ForkJoinTask<>[] q; int s, m;
if ((q = queue) != null) {��������// ignore if queue removed
long u = (((s = queueTop) & (m = q.length - 1)) << ASHIFT) + ABASE;
UNSAFE.putOrderedObject(q, u, t);
queueTop = s + 1;������������// or use putOrderedInt
if ((s -= queueBase) <= 2)
pool.signalWork();
else if (s == m)
growQueue();
}
}
��2��ForkJoinTask��join����ʵ��ԭ��
Join��������Ҫ������������ǰ�̲߳��ȴ���ȡ�����������һ�𿴿�ForkJoinTask��join
������ʵ�֣��������¡�
public final V join() {
if (doJoin() != NORMAL)
return reportResult();
else
return getRawResult();
} private V
reportResult() {
int s; Throwable ex;
if ((s = status) == CANCELLED)
throw new CancellationException();
if (s == EXCEPTIONAL && (ex = getThrowableException()) != null)
UNSAFE.throwException(ex);
return getRawResult();
}
���ȣ���������doJoin()������ͨ��doJoin()�����õ���ǰ�����״̬���жϷ���ʲô��
��������״̬��4�֣�����ɣ�NORMAL������ȡ����CANCELLED�����źţ�SIGNAL���ͳ����쳣
��EXCEPTIONAL����
���������״̬������ɣ���ֱ�ӷ�����������
���������״̬�Ǳ�ȡ������ֱ���׳�CancellationException��
���������״̬���׳��쳣����ֱ���׳���Ӧ���쳣��
��������������һ��doJoin()������ʵ�ִ��롣
private int doJoin() {
Thread t; ForkJoinWorkerThread w; int s; boolean completed;
if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
if ((s = status) < 0)
return s;
if ((w = (ForkJoinWorkerThread)t).unpushTask(this)) {
try {
completed = exec();
} catch (Throwable rex) {
return setExceptionalCompletion(rex);
}
if (completed)
return setCompletion(NORMAL);
}
return w.joinTask(this);
}
else
return externalAwaitDone();
}
��doJoin()���������ͨ���鿴�����״̬���������Ƿ��Ѿ�ִ����ɣ����ִ����ɣ�
��ֱ�ӷ�������״̬�����û��ִ���꣬�������������ȡ������ִ�С��������˳��ִ��
��ɣ�����������״̬ΪNORMAL����������쳣�����¼�쳣����������״̬����Ϊ
EXCEPTIONAL��

 * @author wangzequan
 *
 */
public class CountTask extends RecursiveTask<Integer> {
private static final int THRESHOLD = 2; // ��ֵ
	private int start;
	private int end;

	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// ��������㹻С�ͼ�������
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// ������������ֵ���ͷ��ѳ��������������
			int middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			// ִ��������
			leftTask.fork();
			rightTask.fork();
			// �ȴ�������ִ���꣬���õ�����
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			// �ϲ�������
			sum = leftResult + rightResult;
		}
		return sum;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// ����һ���������񣬸������1+2+3+4
		CountTask task = new CountTask(1, 4);
		// ִ��һ������
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
	}
}