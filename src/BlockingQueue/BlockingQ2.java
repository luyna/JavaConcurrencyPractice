package BlockingQueue;
import java.util.*;


/**
 * @author luyna
 * 2015年1月5日
 * 一个简单的阻塞队列，队列长度有限制，此时需要两个锁
 * 使用synchronized加锁
 * 
 */
public class BlockingQ2 {
	private Object notEmpty=new Object();
	private Object notFull=new Object();
	private Queue<Object> linkedList=new LinkedList<Object>();
	private int maxLength=10;
	
	public Object take() throws InterruptedException{
		synchronized(notEmpty){
			if(linkedList.size()==0){
				notEmpty.wait();
			}
			synchronized(notFull){//notify之前必须先锁定
				if(linkedList.size()==maxLength){  
					//如果此时长度达到最大，说明可能有多个生产者线程处于阻塞状态需要唤醒
					notFull.notifyAll();  //唤醒所有线程，notify只唤醒一个线程
				}
				return linkedList.poll();
			}	
		}
	}
	
	public void offer(Object obj) throws InterruptedException{
		synchronized(notFull){
			if(linkedList.size()==maxLength){
				notFull.wait();
			}
			synchronized(notEmpty){
				if(linkedList.size()==0){
					notEmpty.notifyAll();
				}
				linkedList.add(obj);
			}
		}
	}
}
