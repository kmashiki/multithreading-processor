
public class Task {

	//declare vairables
	private int time, action, taskNumber;
	private boolean queued = true;

	//overloaded constructor
	public Task (int num){
		time = ((int)(Math.random() * 30))+1;
		taskNumber = num;
	}
	
	//array of possible actions
	String [] possibleActions = {"put into queue", 
			"moved to processor", "processing", "completed"};
	
	//mutators
	public void adjustTime() { time--; }
	
	public void setAction(int num) { action = num; }
	
	public void setQueued(boolean bool) { queued = bool; }
	
	public void setTime(int num) { time = num; }
	
	//accessors 
	public int getTime() { return time; }
	
	public int getNum() { return taskNumber; }
	
	public String getAction() { return possibleActions[action]; }
	
	public boolean getQueued() { return queued; }
}


