public interface Queue
{  
	boolean isEmpty();

	void enqueue(Task x);

	Object dequeue();

	Object peekFront();
	
	int size();
}




