package cn.itcast.heima2;

import java.util.concurrent.atomic.AtomicInteger;
// 经验：要用到共同数据（包括同步锁）或共同算法的若干个方法应该归而在同一个类身上，这种设计正好体现了高内聚和程序的健壮性
public class TraditionalThreadCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Business business = new Business();
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
					
						for(int i=1;i<=50;i++){
							business.sub(i);
						}
						
					}
				}
		).start();
		
		for(int i=1;i<=50;i++){
			business.main(i);
		}
		
	}

}
  class Business {
	  private boolean bShouldSub = true;
	  public synchronized void sub(int i){
		  while(!bShouldSub){
			  try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
			for(int j=1;j<=10;j++){
				System.out.println("sub thread sequence of " + j + ",loop of " + i);
			}
		  bShouldSub = false;
		  this.notify();
	  }
	  
	  public synchronized void main(int i){
		  	while(bShouldSub){
		  		try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  	}
			for(int j=1;j<=100;j++){
				System.out.println("main thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = true;
			this.notify();
	  }
  }



