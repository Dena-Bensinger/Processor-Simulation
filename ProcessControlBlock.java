package process;
import java.util.*;

// Dena Bensinger
// Assignment 3

public class ProcessControlBlock {
	private SimProcess process;
	private int currentInstruction;
	private int reg1;
	private int reg2;
	private int reg3;
	private int reg4;
	Random random = new Random();
	
	private SimProcess currentProcess;

	public ProcessControlBlock(SimProcess process) {
		this.process= process;
	}
	public SimProcess getProcess() {
		return process;
	}
	
	public void setCurrentProcess(SimProcess currentProcess) {
		this.currentProcess = currentProcess;
	}
	public SimProcess getCurrentProcess() {
		return currentProcess;
	}


	public void setCurrentInstruction(int currentInstruction) {
		this.currentInstruction= process.getCurrInstruction();
	}
	public int getCurrentInstruction() {
		return process.getCurrInstruction();
	}
	public void setRegisterValue(int reg1, int reg2, int reg3, int reg4) {
		this.reg1= random.nextInt();
		this.reg2= random.nextInt();
		this.reg3= random.nextInt();
		this.reg4= random.nextInt();
	}
	public int getRegister1Value() {
		return reg1;
	}
	public int getRegister2Value() {
		return reg2;
	}
	public int getRegister3Value() {
		return reg3;
	}
	public int getRegister4Value() {
		return reg4;
	}
}