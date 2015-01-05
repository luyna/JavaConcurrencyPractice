package BlockingQueue;
import java.util.*;
import java.util.concurrent.locks.*;

/**
 * @author luyna
 * 2015��1��5��
 * һ���򵥵��������У����г��������ƣ���ʱ��Ҫ������
 * ʹ��Lock����,Condition��Ӧ����await��Signal��SignalAll
 */
public class BlockingQ3 {
	private Lock lock=new ReentrantLock();
	private Condition notEmpty=lock.newCondition();
	private Condition notFull=lock.newCondition();
	private int maxLength=10;
	private Queue<Object> linkedList=new LinkedList<Object>();
	
	public Object take() throws InterruptedException{
		lock.lock();
		try{
			if(linkedList.size()==0){
				notEmpty.await();
			}
			if(linkedList.size()==maxLength){
				notFull.signalAll();
			}
			return linkedList.poll();
		}finally{
			lock.unlock();
		}		
	}
	
	public void offer(Object obj) throws InterruptedException{
		lock.lock();
		try{
			if(linkedList.size()==maxLength){
				notFull.await();
			}
			if(linkedList.size()==0){
				notEmpty.signalAll();
			}
			linkedList.add(obj);
		}finally{
			lock.unlock();
		}
	}
}
