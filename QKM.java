import javax.swing.JFrame;
import javax.swing.JOptionPane;

import chn.util.*;

public class QKM {

	public static void main(String[] args) {
		String choice = JOptionPane.showInputDialog
				(null, "Option A: One trial for 1000 milliseconds" +
				"\nOption B: 25 trials and graphs\nQuit\nInput A, B, or Q");
		
		while(!choice.equals("Q")){		//continue running program
			
			//run one trial
			if(choice.equals("A")){
				Trial trialA = new Trial();
				trialA.trial();
				trialA.trialAOutput();
			}
			
			//run 25 trials
			else if(choice.equals("B")){
				int[] timesFull = new int[25];
				int[] tasksLeft = new int[25];
				Trial t;
				for(int i=0; i<25; i++){
					 t = new Trial();
					 t.trial();
					 t.trialAOutput();
					 timesFull[i] = t.getTimesFull();
					 tasksLeft[i] = t.getNumTasksQueued();
			}
				
			//Average number of times processor was full
			int sum = 0;
			for(int i=0; i<25; i++){
				sum += timesFull[i];
			}
			System.out.println("AVG = " + sum/25);
			
			//Output graph #1
			Graph tool = new Graph(timesFull);
			tool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tool.setTitle("Bar Graph #1");
			tool.pack();
			tool.show();
	
			//Output graph #2
			Graph2 tool2 = new Graph2(tasksLeft);
			tool2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tool2.setTitle("Bar Graph #2");
			tool2.pack();
			tool2.show();
		
		}
		
		//Rerun option
		choice = JOptionPane.showInputDialog
			(null, "Option A: One trial for 1000 milliseconds" +
			"\nOption B: 25 trials and graphs\nQuit\nInput A, B, or Q");
		}
	}
}

