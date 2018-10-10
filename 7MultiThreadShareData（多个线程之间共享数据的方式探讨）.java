package cn.itcast.heima2;
/**
多个线程访问共享对象和数据的方式：
如果每个线程执行的代码相同，则可以使用同一个Runnable对象，这个Runnable对象中有那个共享数据
例如，买票系统就可以这么做（都是减库存）

如果每个线程执行的代码不同，这时候需要用不同的Runnable对象，有如下两种方式实现这些Runnable对象之间的数据共享：
   1.将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个Runnable对象。每个线程对共享数据的操作方法也分配到那个对象中完成，
   这样容易实现针对该数据进行的各个操作的互斥和通信
   2.将这些Runnable对象作为某一个类中的内部类，共享数据作为这个外部类中的成员变量，每个线程对共享数据的操作方法也分配给外部类,
   以便实现对共享数据进行的各个操作的互斥和通信，作为内部类的各个Runnable对象调用外部类的这些方法

   总结：要同步互斥的几段代码最好是分别放在几个独立的方法中，这些方法再放在同一个类中，这样比较容易实现他们之间的同步互斥和通信
*/
public class MultiThreadShareData {

	private static ShareData1 data1 = new ShareData1();
	
	public static void main(String[] args) {
		ShareData1 data2 = new ShareData1();
		new Thread(new MyRunnable1(data2)).start(); //MyRunnable1  和 MyRunnable2 同时持有 data2 的引用
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