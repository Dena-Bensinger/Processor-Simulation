package process;

import java.util.*;

// Dena Bensinger 
// Assignment 3 

public class SimProcessor{
	private SimProcess currentProc;
	private int reg1;
	private int reg2;
	private int reg3;
	private int reg4;
	private int currInstruction;
	Random random = new Random();
	

	public SimProcessor( int currInstruction, SimProcess currentProc) {
		this.currentProc= currentProc;
		this.currInstruction = currInstruction;
	}
	public SimProcess getCurrentProccess() {
		return currentProc;
	}
	public void setCurrentProccess(SimProcess currentProc) {
		this.currentProc=currentProc;
	}
	public void setcurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}

	public void setRegisters(int reg1, int reg2, int reg3, int reg4) {
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
	public void setCurrInstruction(int currInstruction) {
		this.currInstruction= currInstruction;
	}
	public int getCurrInstruction() {
		return currInstruction;
	}
	public ProcessState executeNextInstruction(int currInstruction) {
		ProcessState result = currentProc.execute(currInstruction);
		currInstruction++;
		
		setRegisters(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
		return result;	
	}
	}