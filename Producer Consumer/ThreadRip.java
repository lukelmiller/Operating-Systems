//Dev: Luke Miller
//Dev ID: 010785453
//Dev Date: 4.16.2020
//Class: OS

import java.util.concurrent.*; 
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;



class ThreadRip extends Thread
{
	static class Buffer{
		static LinkedList<Integer> list = new LinkedList<>();
		static int producers = 0;
		static int consumers = 0;
	}
	
	static int Rando(int range){
		Random random = new Random();
		return (random.nextInt(range));
	}
	
    String threadName; 
    Semaphore empty;
    Semaphore full;
    ReentrantLock mutex;    
    public ThreadRip(String threadName, Semaphore empty, Semaphore full, ReentrantLock mutex)  
    { 
        super(threadName); 
        this.empty = empty;
        this.full = full;
        this.mutex = mutex;
    } 
  
    public void run() { 
    	while(Buffer.consumers < 100 || Buffer.producers < 100) {
	    	if(this.getName().equals("Producer") && Buffer.producers < 100) {
	            try 
	            { 
	                empty.acquire();
	                int rando = Rando(100000);
	                mutex.lock();
	                if(Buffer.producers < 100) {
		                Buffer.list.addLast(rando);
		                Buffer.producers += 1;
		                System.out.println("Producers produced: " + rando); 
	                }
	                mutex.unlock();
	                full.release();
	                Thread.sleep(Rando(500));
	            } catch (InterruptedException exc) { 
	                    System.out.println(exc); 
                }
	        } 
	        else if(this.getName().equals("Consumer") && Buffer.consumers < 100)
	        {
	            try 
	            {
	                full.acquire();
	                mutex.lock();
	                if(Buffer.consumers < 100){
		                int popper = Buffer.list.pop();
		                Buffer.consumers += 1;
		                System.out.println("\tConsumer consumed: " + popper); 
	                }
	                mutex.unlock();
	                empty.release();
	                Thread.sleep(Rando(500));
	                
	            } catch (InterruptedException exc) { 
	                    System.out.println(exc); 
                }
	        }
    	}
    	empty.release(5);
    	full.release(5);
    }
}