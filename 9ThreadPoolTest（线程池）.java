package cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(3);//固定线程池 （线程池中的线程的数量始终是XX个）
		//ExecutorService threadPool = Executors.newCachedThreadPool();//缓存线程池（线程池中的线程的数量随着任务多少的变化而变化）  
		// newScheduledThreadPool   创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下
		ExecutorService threadPool = Executors.newSingleThreadExecutor();//线程池中只有一个线程 当池子中的线程死了  则创建一个新的线程来替代当前线程,保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable(){
				@Override
				public void run() {
					for(int j=1;j<=10;j++){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
					}
				}
			});
		}
		System.out.println("all of 10 tasks have committed! ");
		//threadPool.shutdownNow();//杀掉线程池中的所有线程
		
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
				new Runnable(){
					@Override
				public void run() {
					System.out.println("bombing!");
					
				}},
				6,
				2,
				TimeUnit.SECONDS);
	}

}
