package process;
import java.util.*;
// Dena Bensinger 
// Assignment 3 
public class SimProcess {
	private SimProcess  currentProc;

	private int pid;
	private String procName;
	private int totalInstructions;
	private int currInstruction;
	
	private int reg1;
	private int reg2;
	private int reg3;
	private int reg4;
	
	Random random= new Random();

	public SimProcess(int pid, String procName, int totalInstructions, int currInstruction) {
		this.pid= pid;
		this.procName= procName;
		this.totalInstructions= totalInstructions;
		this.currInstruction= currInstruction;
	}

	public int getPID() {
		return pid;
	}
	public String getProcName() {
		return procName;
	}
	public int getCurrInstruction() {
		return currInstruction;
	}
	public int addCurrInstruction() {
		return currInstruction++;
	}


	public void setPID(int pid) {
		this.pid=pid;
	}

	public SimProcess getCurrentProc() {
		return currentProc;
	}
	public void setTotalInstructions (int totalInstructions) {
		this.totalInstructions= totalInstructions;
	}
	public int getTotalInstructions() {
		return totalInstructions;
	}
	public void setRegisters(int reg1, int reg2, int reg3, int reg4) {
		this.reg1= random.nextInt();
		this.reg2= random.nextInt();
		this.reg3= random.nextInt();
		this.reg4= random.nextInt();
	}
	public int getReg1Value() {
		return reg1;
	}
	public int getReg2Value() {
		return reg2;
	}
	public int getReg3Value() {
		return reg3;
	}
	public int getReg4Value() {
		return reg4;
	}


	public ProcessState execute(int currInstruction) {
		//System.out.println("\nPID: "+ pid +"\nProcess name: "+ procName+"\nExecuting instruction: "+ currInstruction);
		if (currInstruction >= totalInstructions) {
			return ProcessState.FINISHED;
		}
		else if(currInstruction <= .15 * totalInstructions){
		return ProcessState.BLOCKED;
		}

		return ProcessState.READY;
	}
	
	public ProcessState executeNextInstruction(int currInstruction) {
		ProcessState result = currentProc.execute(currInstruction);
		currInstruction++;
		
		setRegisters(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
		return result;	
	}
	
}