

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//核心是  将每个线程中的独立数据  放到一个map里面 （map的key 为当前线程）
//后面 取数据 通过  get（当前线程）来取   则  每个线程用的数据都是同一个。

//可用后面将要学到的 ThreadLocal 代替 更简单
public class ThreadScopeShareData {

	private static int data = 0;
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
	public static void main(String[] args) {
		for(int i=0;i<2;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() 
							+ " has put data :" + data);
					threadData.put(Thread.currentThread(), data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}
	}
	
	static class B{
		public void get(){
			int data = threadData.get(Thread.currentThread());			
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}		
	}
}
