
public class Processor {
	
	private int completedTasks;
	
	//default constructor
	public Processor () {
		completedTasks = 0;
	}
	
	//checks if processor is full
	public boolean isNotFull(Task[] array){
		for(int i=0; i<array.length; i++)
			if(array[i]==null)
				return true;
		
		return false;
	}
	
	//returns last index empty in processor
	public int lastEmptyInArray(Task[] array){
		for(int i=0; i<array.length; i++)
			if(array[i]==null)
				return i;
		
		return 0;
	}
	
	//returns number of tasks in process
	public int inProcess (Task[] array){
		int counter = 0;
		for(int i=0; i<array.length; i++)
			if(array[i]!=null && array[i].getTime()!=0)
				counter++;

		return counter;
	}
	
	//Formats task printing
	public String printTask (Task task){
		return ("\tTASK #" + task.getNum() + "  (time = " 
				+ task.getTime() + ")  " + task.getAction() );
	}
	
	//adjusts time if a task exists
	public void adjustTime (Task[] array){
		for(int i=0; i<array.length; i++){
			if(array[i]!=null){
				array[i].adjustTime();
			}
		}
	}
	
	//changes actions if they were moved/completed
	public void adjustActions (Task[] array){
		for(int i=0; i<array.length; i++){
			if(array[i]!=null){
				//sets tasks time 0 to complete
				if(array[i].getTime()==0){
					array[i].setAction(3);
					completedTasks++;
				}
				//changes action to "processing"
				else if(array[i].getAction().equals("moved to processor")){
					if(array[i].getQueued())	
						array[i].setQueued(false);
					else
						array[i].setAction(2);
				}
			}
		}
	}
	
	//removes completed tasks after they have been counted
	public void removeCompleted(Task[] array){
		for(int i=0; i<array.length; i++)
			if(array[i]!=null)
				if(array[i].getAction().equals("completed"))
					array[i] = null;
	}
	
	//accessor method
	public int getNumTasksCompleted(){ return completedTasks; }
}
