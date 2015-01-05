package BlockingQueue;
import java.util.*;


/**
 * @author luyna
 * 2015��1��5��
 * һ���򵥵��������У����г��������ƣ���ʱ��Ҫ������
 * ʹ��synchronized����
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
			synchronized(notFull){//notify֮ǰ����������
				if(linkedList.size()==maxLength){  
					//�����ʱ���ȴﵽ���˵�������ж���������̴߳�������״̬��Ҫ����
					notFull.notifyAll();  //���������̣߳�notifyֻ����һ���߳�
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
