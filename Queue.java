
public class Queue 
{
	Node front;
	Node rear;
	public Queue()
	{
		front = rear = null;
	}
	public void enQueue (int k)
	{
		Node t = new Node();
		t.datum = k;
		t.next = null;
		
		if (front == null)
		{	
			front = rear = t;
		}
		else
		{
			rear.next = t;
			rear = t;
		}
	}
	public int deQueue()
	{
		if (front == null)
			return -1;
		else
		{
			int k = front.datum;
			front = front.next;
			if (front == null)
				rear = null;
			return k;
		}
	}
	public boolean isEmpty()
	{
		if (front == null)
			return true;
		else
			return false;
	}
}