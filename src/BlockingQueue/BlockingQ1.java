package BlockingQueue;
/**
 * @author luyna
 * 2015年1月5日
 * 
 * 一个简单的阻塞队列，假定linkedList可以无限制的长，所以只需要加一个锁
 * 如果要执行wait、notify、notifyAll操作，必须先取得该对象的锁
 * 执行wait操作后，锁会释放；被唤醒之前，需要先获得锁
 * 
 */
import java.util.*;

public class BlockingQ1 {
	private Object notEmpty=new Object();
	private Queue<Object> linkedList=new LinkedList<Object>();
	
	public Object take() throws InterruptedException{
		synchronized(notEmpty){   
			if(linkedList.size()==0){
				notEmpty.wait();
			}
			return linkedList.poll();
		}
	}
	public void offer(Object obj){
		synchronized(notEmpty){
			if(linkedList.size()==0){
				notEmpty.notifyAll();
			}
			linkedList.add(obj);  
		}
	}
}
