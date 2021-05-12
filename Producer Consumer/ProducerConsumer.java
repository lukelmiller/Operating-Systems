//Dev: Luke Miller
//Dev ID: 010785453
//Dev Date: 4.16.2020
//Class: OS

import java.util.concurrent.*; 
import java.util.concurrent.locks.ReentrantLock;

class ProducerConsumer {
	
	static void caller(String a0, String a1, String a2) throws java.lang.IllegalThreadStateException, InterruptedException{
		
		Semaphore empty = new Semaphore(5); 
		Semaphore full = new Semaphore(5);
		full.drainPermits();
		ReentrantLock mutex = new ReentrantLock();
		
		System.out.println("Using arguments from the command line");
		System.out.println("Sleep time = " + a0);
		System.out.println("Producer threads = " + a1);
		System.out.println("Consumer threads = " + a2 + "\n");
	
		int sleepTime = Integer.parseInt(a0);
		int pThreads = Integer.parseInt(a1);
		int cThreads = Integer.parseInt(a2);
		
		ThreadRip pt = new ThreadRip("Producer", empty, full, mutex);
		ThreadRip ct = new ThreadRip("Consumer", empty, full, mutex);
		
		for(int i = 0; i < pThreads || i < cThreads; i++) {
			if(i < pThreads) {
				pt = new ThreadRip("Producer", empty, full, mutex);
				pt.start();
			}
			if(i < cThreads) {
				ct = new ThreadRip("Consumer", empty, full, mutex);
				ct.start();
			}
		}
		int count = 0;
		while(count <= sleepTime && ct.isAlive()) {
			ThreadRip.sleep(1000);
			count+=1;
		}
		
		//System.out.println("Done!");
		System.exit(0);
	}
	

	
	public static void main(String[] args) throws java.lang.IllegalThreadStateException, InterruptedException
	{
		caller(args[0], args[1], args[2]);
//		caller("20","1","1");
	}

}

