package cn.itcast.heima2;
/**
����̷߳��ʹ����������ݵķ�ʽ��
���ÿ���߳�ִ�еĴ�����ͬ�������ʹ��ͬһ��Runnable�������Runnable���������Ǹ���������
���磬��Ʊϵͳ�Ϳ�����ô�������Ǽ���棩

���ÿ���߳�ִ�еĴ��벻ͬ����ʱ����Ҫ�ò�ͬ��Runnable�������������ַ�ʽʵ����ЩRunnable����֮������ݹ���
   1.���������ݷ�װ������һ�������У�Ȼ�����������һ���ݸ�����Runnable����ÿ���̶߳Թ������ݵĲ�������Ҳ���䵽�Ǹ���������ɣ�
   ��������ʵ����Ը����ݽ��еĸ��������Ļ����ͨ��
   2.����ЩRunnable������Ϊĳһ�����е��ڲ��࣬����������Ϊ����ⲿ���еĳ�Ա������ÿ���̶߳Թ������ݵĲ�������Ҳ������ⲿ��,
   �Ա�ʵ�ֶԹ������ݽ��еĸ��������Ļ����ͨ�ţ���Ϊ�ڲ���ĸ���Runnable��������ⲿ�����Щ����

   �ܽ᣺Ҫͬ������ļ��δ�������Ƿֱ���ڼ��������ķ����У���Щ�����ٷ���ͬһ�����У������Ƚ�����ʵ������֮���ͬ�������ͨ��
*/
public class MultiThreadShareData {

	private static ShareData1 data1 = new ShareData1();
	
	public static void main(String[] args) {
		ShareData1 data2 = new ShareData1();
		new Thread(new MyRunnable1(data2)).start(); //MyRunnable1  �� MyRunnable2 ͬʱ���� data2 ������
		new Thread(new MyRunnable2(data2)).start();
		
		final ShareData1 data1 = new ShareData1();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.decrement();
				
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.increment();
				
			}
		}).start();

	}

}
	
	class MyRunnable1 implements Runnable{
		private ShareData1 data1;
		public MyRunnable1(ShareData1 data1){
			this.data1 = data1;
		}
		public void run() {
			data1.decrement();
			
		}
	}
	
	class MyRunnable2 implements Runnable{
		private ShareData1 data1;
		public MyRunnable2(ShareData1 data1){
			this.data1 = data1;
		}
		public void run() {
			data1.increment();
		}
	}

	class ShareData1 /*implements Runnable*/{
/*		private int count = 100;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				count--;
			}
		}*/
		
		
		private int j = 0;
		public synchronized void increment(){
			j++;
		}
		
		public synchronized void decrement(){
			j--;
		}
	}