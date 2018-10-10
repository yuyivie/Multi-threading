package cn.itcast.heima2;
public class TraditionalThreadSynchronized {
 //1互斥的参考必须是同一个对象的。（门栓必须是同一个。）
 //2静态方法可以拿字节码文件做参考对象
 //3非静态方法拿当前对象做参考对象。

 //代码中output 和output3 是实现互斥的   他们和ouput2 不互斥 因为ouput2 使用的是 当前了类的对象  

 //如果要实现  output 和  output2 同步    output需改为 synchronized (this)  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
	}
	
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
				
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output2("lihuoming");
				}
				
			}
		}).start();
		
	}

	static class Outputer{
		
		public void output(String name){
			int len = name.length();
			synchronized (Outputer.class)  //字节码文件做锁
			{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		public synchronized void output2(String name){ //非静态方法 拿  当前对象做对象  等同于 synchronized（this）
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void output3(String name){ //静态方法 用字节码文件做锁
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}	
	}
}