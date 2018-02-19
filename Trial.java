import chn.util.FileOutput;

public class Trial {

	public Trial(){}	//default constructor
	
	int numTimesProcFull = 0, numTimesQEmpty = 0;
	
	FileOutput output = new FileOutput ("./src/output.txt");
	
	//For Cedrone's computer
	//FileOutput outputKM = new FileOutput ("C:\\temp\\QKM.txt");
	
	Processor obj = new Processor();
	
	int counter = 0;
	
	int timeStep = 1, queueSize = 0, tasksInProcess = 0, probability = 0, taskNumber = 1;
	Task[] processor = new Task[10];
	Queue queue = new ListQueue();
	String toOutput = "";
	boolean queued = false;
	
	public void trial(){
		for(int i=1; i<=1000; i++){			
			probability = ((int)(Math.random () * 100))+1;
			
			output.println(probability);
			
			//New task probability
			if(probability>=1 && probability<=63){
				//new task created and put into queue if right probability
				Task task = new Task(taskNumber);
				queue.enqueue(task);
				task.setAction(0);
				toOutput = obj.printTask(task);	//outputs "put to queue"
				
				//moves new task to processor if it isn't full
				if(obj.isNotFull(processor)){
					//temporary task
					Task task2 = (Task)queue.dequeue();
					
					//finds empty row in processor
					int last = obj.lastEmptyInArray(processor);
					processor[last] = task2;
					
					//sets time and action- makes up for off by 1
					task2.setTime(task2.getTime()+1);
					task2.setAction(1);
				}
				
				//increment task # counter
				taskNumber++;	
			}
			
			//Movement of task from queue to processor
			while(obj.isNotFull(processor) && !queue.isEmpty()){
				Task task = (Task)queue.dequeue();
				processor[obj.lastEmptyInArray(processor)] = task;
				task.setAction(1);
				task.setTime(task.getTime()+1);
			}
			
			//changes time (minus one second)
			obj.adjustTime(processor);
			
			//checks if actions change
			obj.adjustActions(processor);
			
			//Records data for final report
			queueSize = queue.size();
			tasksInProcess = obj.inProcess(processor);
			
			if(queueSize==0)
				numTimesQEmpty++;
			if(tasksInProcess==10)
				numTimesProcFull++;
			
			//Adjusts outputs if it is first task 
			if(!toOutput.equals(""))
				output.println("TIME STEP = " + timeStep + "\tQUEUE SIZE = " + 
						queueSize + "\tTASKS IN PROCESS = " + tasksInProcess + 
						"\n" + toOutput);
			else
				output.println("TIME STEP = " + timeStep + "\tQUEUE SIZE = " + 
						queueSize + "\tTASKS IN PROCESS = " + tasksInProcess);
				
			//two for loops correct order of output
			for(int m=0; m<processor.length; m++)
				if(processor[m]!=null)
					if(processor[m].getAction().equals("moved to processor"))
						output.println(obj.printTask(processor[m]));
			
			for(int m=0; m<processor.length; m++)
				if(processor[m]!=null)
					if(!(processor[m].getAction().equals("moved to processor")))
						output.println(obj.printTask(processor[m]));
			
			//reset variables
			toOutput = "";
			timeStep++;
			
			//remove completed tasks after they are counted as tasks in process
			obj.removeCompleted(processor);
		}
	}
	
	//final report output
	public void trialAOutput(){
		output.println("\n=============\nFINAL REPORT\n=============\n");
		output.println("Time steps elapsed = " + 1000);
		output.println("Total number of tasks processed = " + obj.getNumTasksCompleted());
		output.println("Number of time steps processor was full = " + numTimesProcFull);
		output.println("Number of time steps queue was empty = " + numTimesQEmpty);
		output.close();
	}
	
	//accessor methods
	public int getTimesFull(){
		return numTimesProcFull;
	}
	
	public int getNumTasksQueued(){
		return queue.size();
	}
}
