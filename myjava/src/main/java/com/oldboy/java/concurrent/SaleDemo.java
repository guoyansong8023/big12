package com.oldboy.java.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/9/9.
 */
public class SaleDemo {

	public static void main(String[] args) {
		new Sales("s1").start();
		new Sales("s2").start();
	}


	//票池
	static class TicketPool{
		//票数
		private int tickets = 100 ;

		//重入锁
		private static ReentrantLock lock = new ReentrantLock() ;

		//
		private static TicketPool instance ;

		//
		private TicketPool(){
		}

		public static TicketPool getInstance(){
			if(instance != null){
				return instance ;
			}
			lock.lock();
			if(instance == null){
				instance = new TicketPool() ;
			}
			lock.unlock();
			return instance ;
		}

		/**
		 * 取票
		 */
		public int getTicket(){
			boolean b = lock.tryLock();
			if(!b)
				return 0 ;
			int tmp = tickets ;
			if(tmp == 0){
				lock.unlock();
				return -1 ;
			}
			else{
				tickets -- ;
				lock.unlock();
				return tmp ;
			}
		}
	}

	/**
	 * 售票员
	 */
	static class Sales extends Thread{
		private String ssname ;
		private Sales(String ssname){
			this.ssname = ssname ;
		}

		public void run() {
			TicketPool pool = TicketPool.getInstance();
			for(;;){
				int n = pool.getTicket();
				if(n == 0){
					continue;
				}
				else if(n == -1){
					break ;
				}
				else{
					System.out.printf("%s : %d\r\n" , ssname , n);
				}
			}
		}
	}
}
