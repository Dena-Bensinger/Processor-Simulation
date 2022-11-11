package process;
import java.util.*;
// Dena Bensinger 
// Assignment 3

public class Main {
	
public static void main(String[]args) {
	Random rand = new Random();
	final int QUANTUM = 5;
	
	ArrayList<SimProcess> processes = new ArrayList<>();
	
	// Add 10 processes to the list. 
	processes.add(new SimProcess(1, "Process 1", 108, rand.nextInt(400)+1));
	processes.add(new SimProcess(2, "Process 2", 323, rand.nextInt(400)+1));
	processes.add(new SimProcess(3, "Process 3", 399, rand.nextInt(400)+1));
	processes.add(new SimProcess(4, "Process 4", 200, rand.nextInt(400)+1));
	processes.add(new SimProcess(5, "Process 5", 213, rand.nextInt(400)+1));
	processes.add(new SimProcess(6, "Process 6", 167, rand.nextInt(400)+1));
	processes.add(new SimProcess(7, "Process 7", 400, rand.nextInt(400)+1));
	processes.add(new SimProcess(8, "Process 8", 229, rand.nextInt(400)+1));
	processes.add(new SimProcess(9, "Process 9", 98, rand.nextInt(400)+1));
	processes.add(new SimProcess(10, "Process 10", 107, rand.nextInt(400)+1));
	
	for(int i =0; i< processes.size(); i++) {
		processes.get(i);
	}
	
	
	
	ArrayList<ProcessControlBlock> ready = new ArrayList<ProcessControlBlock>();
	ArrayList<ProcessControlBlock> blocked = new ArrayList<ProcessControlBlock>();
	
	
	ArrayList<ProcessControlBlock> pcb = new ArrayList<>();
	for(int i =0; i< processes.size(); i++) {
		pcb.add(new ProcessControlBlock(processes.get(i)));
		ready.add(pcb.get(i));
	}
	ProcessControlBlock  currentpcb = ready.remove(0);
	ProcessState currentState = null;
	
	for (int i = 0; i < processes.size(); i++) {
		ProcessState pState = processes.get(i).execute(processes.get(i).getCurrInstruction());
		if (pState == ProcessState.READY) {
			ready.add(currentpcb);
		} else if (pState == ProcessState.BLOCKED) {
			blocked.add(currentpcb);
		}

	}
	

int instructionCompleted = 0;


for (int i = 0; i < processes.size(); i++) {
	// Loop through 3000 times.
	for (int j = 0; j < 3000; j++) {

	SimProcessor simProcessor = new SimProcessor(processes.get(i).getCurrInstruction(), processes.get(i));


	for (int x = 0; x < QUANTUM; x++) {
	if(processes.get(i).execute(processes.get(i).getCurrInstruction()) == ProcessState.BLOCKED) {
		instructionCompleted ++;


		System.out.println("Step: " + instructionCompleted +" PID: " + processes.get(i).getPID() + " Process Name: "+ processes.get(i).getProcName() + " Executing Instruction #: " + processes.get(i).getCurrInstruction());

		
		currentState = ProcessState.BLOCKED;
		blocked.add(currentpcb);
		System.out.println("\n*** Process Blocked ***");
		instructionCompleted ++;
		
		System.out.println("Step: " + instructionCompleted + " Saving Process: " + processes.get(i).getPID());

		if (blocked.size() > 1) {
		    unblockLoop(ready, blocked, processes);
		}

		ready.remove(currentpcb);
		
		if (!ready.isEmpty()) {
		
			System.out.println("Resuming Process " + processes.get(i).getPID());

		}
		processes.get(i).addCurrInstruction();
		simProcessor.executeNextInstruction(processes.get(i).getCurrInstruction());
		System.out.println("Resuming Process " + processes.get(i).getPID());
		System.out.println("Instruction: " + processes.get(i).getCurrInstruction() + " R1: " + simProcessor.getRegister1Value()
                             + ", R2: "  +  simProcessor.getRegister2Value() + ", R3: " 
                             + simProcessor.getRegister3Value()+ ", R4: " + simProcessor.getRegister4Value());
		 
	}
	
		else if(processes.get(i).execute(processes.get(i).getCurrInstruction()) == ProcessState.READY) {
			instructionCompleted ++;
	
			System.out.println("Step: " + instructionCompleted +" PID: " + processes.get(i).getPID() + " Processor Name: "+ processes.get(i).getProcName() + " Executing Instruction #: " + processes.get(i).getCurrInstruction());
			currentState = ProcessState.READY;
			processes.get(i).addCurrInstruction();
			 unblockLoop(ready, blocked, processes);
			 simProcessor.executeNextInstruction(processes.get(i).getCurrInstruction());
	}
		else if(processes.get(i).execute(processes.get(i).getCurrInstruction()) == ProcessState.FINISHED) {
			instructionCompleted ++;
			
			System.out.println("Step: " + instructionCompleted +" PID: " + processes.get(i).getPID() + " Processor Name: "+ processes.get(i).getProcName() + " Executing Instruction #: " + processes.get(i).getCurrInstruction());
			currentState = ProcessState.FINISHED;
			System.out.println("\n*** Process Completed ***");
			instructionCompleted ++;
			unblockLoop(ready, blocked, processes);
			
	
			 simProcessor.setRegisters(simProcessor.getRegister1Value(),simProcessor.getRegister2Value(), simProcessor.getRegister3Value(), simProcessor.getRegister4Value() );

			 // context switch
			 System.out.println("Step: " + instructionCompleted + " Context Switch: " + "Saving Process: " + processes.get(i).getPID());
			 System.out.println("Instruction: " + processes.get(i).getCurrInstruction() + " R1: " + simProcessor.getRegister1Value()
                                      + ", R2: "  +  simProcessor.getRegister2Value() + ", R3: " 
	                                  + simProcessor.getRegister3Value()+ ", R4: " + simProcessor.getRegister4Value());
				processes.get(i).addCurrInstruction();
				
			    ready.remove(currentpcb);
				ready.add(currentpcb);
				
				if (!ready.isEmpty()) {
					
					System.out.println("Resuming Process " + processes.get(i).getPID());
					simProcessor.executeNextInstruction(processes.get(i).getCurrInstruction());
				} 
				else {
					System.out.println("The processor is idling...");
					
					processes.get(i++);
					unblockLoop(ready, blocked, processes);
					
				}
		}
	}
	System.out.println("\n*** Quantum Expired ***");
	instructionCompleted ++;
	System.out.println("Step: " + instructionCompleted + " Saving Process: " + processes.get(i).getPID());
	unblockLoop(ready, blocked, processes);

	ready.remove(currentpcb);
	ready.add(currentpcb);
	 System.out.println("Instruction: " + processes.get(i).getCurrInstruction() + " R1: " + simProcessor.getRegister1Value()
                       + ", R2: "  +  simProcessor.getRegister2Value() + ", R3: " 
                       + simProcessor.getRegister3Value()+ ", R4: " + simProcessor.getRegister4Value());
	 if (!ready.isEmpty()) {
			
			if (j == 3000) {
				System.exit(0);
			} else {
				System.out.println("Resuming Process " + processes.get(i).getPID());
			}
		}

	}

}
}
private static void unblockLoop(ArrayList<ProcessControlBlock> ready,	
		                        ArrayList<ProcessControlBlock> blocked, 
		                        ArrayList<SimProcess> processes) {
for (int i = 0; i < blocked.size(); i++) {
	if(processes.get(i).getCurrInstruction() <= .30 * processes.get(i).getTotalInstructions()) {
		ready.add(blocked.get(i));
		blocked.remove(i);
		}
	}
	}
}
			 



