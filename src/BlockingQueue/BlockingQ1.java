package BlockingQueue;
/**
 * @author luyna
 * 2015��1��5��
 * 
 * һ���򵥵��������У��ٶ�linkedList���������Ƶĳ�������ֻ��Ҫ��һ����
 * ���Ҫִ��wait��notify��notifyAll������������ȡ�øö������
 * ִ��wait�����������ͷţ�������֮ǰ����Ҫ�Ȼ����
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
