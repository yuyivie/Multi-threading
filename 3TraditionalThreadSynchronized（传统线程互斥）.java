package cn.itcast.heima2;
public class TraditionalThreadSynchronized {
 //1����Ĳο�������ͬһ������ġ�����˨������ͬһ������
 //2��̬�����������ֽ����ļ����ο�����
 //3�Ǿ�̬�����õ�ǰ�������ο�����

 //������output ��output3 ��ʵ�ֻ����   ���Ǻ�ouput2 ������ ��Ϊouput2 ʹ�õ��� ��ǰ����Ķ���  

 //���Ҫʵ��  output ��  output2 ͬ��    output���Ϊ synchronized (this)  
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
			synchronized (Outputer.class)  //�ֽ����ļ�����
			{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		public synchronized void output2(String name){ //�Ǿ�̬���� ��  ��ǰ����������  ��ͬ�� synchronized��this��
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void output3(String name){ //��̬���� ���ֽ����ļ�����
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}	
	}
}