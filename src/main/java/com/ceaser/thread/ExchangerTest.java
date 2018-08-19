package com.ceaser.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Exchanger���������Ŵ��㷨���Ŵ��㷨����Ҫѡ����������Ϊ���������ʱ��ύ��
	���˵����ݣ���ʹ�ý������ó�2����������ExchangerҲ��������У�Թ���������������
	Ҫ��ֽ��������ˮͨ���˹��ķ�ʽ¼��ɵ���������ˮ��Ϊ�˱�����󣬲���AB�����˽���
	¼�룬¼�뵽Excel֮��ϵͳ��Ҫ����������Excel����������Excel���ݽ���У�ԣ������Ƿ�
	¼��һ��
 * @author wangzequan
 *
 */
public class ExchangerTest {
	private static final Exchanger<String> exgr = new Exchanger<String>();

	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String A = "������ˮA"; // A¼��������ˮ����
					exgr.exchange(A);
				} catch (InterruptedException e) {
				}
			}
		});
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String B = "������ˮB";// B¼��������ˮ����
					String A = exgr.exchange("B");
					System.out.println("A��B�����Ƿ�һ�£�" + A.equals(B) + "��A¼����ǣ�" + A + "��B¼���ǣ�" + B);
				} catch (InterruptedException e) {
				}
			}
		});
		threadPool.shutdown();
	}
}