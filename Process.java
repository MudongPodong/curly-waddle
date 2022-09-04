
public class Process {
	private int processID;
	private int arrivetime;
	private int bursttime;
	private int priority;
	private int startP;
	private int finishtime;
	private int waitingtime;
	private int preemcheck=0;
	private int responsecheck=0;
	
	public Process() {}
	void setProcessID(int processID) {
		this.processID=processID;
	}
	int getProcessID() {
		return processID;
	}
	
	void setArrivetime(int Arrivetime) {
		this.arrivetime=Arrivetime;
	}
	int getArrivetime() {
		return arrivetime;
	}
	
	void setBursttime(int bursttime) {
		this.bursttime=bursttime;
	}
	int getBursttime() {
		return bursttime;
	}
	
	void setPriority(int priority) {
		this.priority=priority;
	}
	int getPriority() {
		return priority;
	}
	void setStartP(int startP) {
		this.startP=startP;
	}
	int getStartP() {
		return startP;
	}
	void setFinishTime(int finishtime) {
		this.finishtime=finishtime;
	}
	int getFinishTime() {
		return finishtime;
	}
	void setWaitingtime(int waitingtime) {
		this.waitingtime=waitingtime;
	}
	int getWaitingtime() {
		return waitingtime;
	}
	
	void setPreemcheck(int preemcheck) {
		this.preemcheck=preemcheck;
	}
	int getPreemcheck() {
		return preemcheck;
	}
	void setResponsecheck(int responsecheck) {
		this.responsecheck=responsecheck;
	}
	int getResponsecheck() {
		return responsecheck;
	}
}

class Result{
	private int processID;
	private int arrivetime;
	private int startP;
	private int bursttime;
	private int waitingtime;
	private int turnaroundtime;
	private int responsetime;
	private int finishtime;
	
	
	public Result() {}
   
	
	void setProcessID(int processID) {
		this.processID=processID;
	}
	int getProcessID() {
		return processID;
	}
	
	void setArriveTime(int arrivetime) {
		this.arrivetime=arrivetime;
	}
	int getArriveTime() {
		return arrivetime;
	}
	
	void setStartP(int startP) {
		this.startP=startP;
	}
	int getStartP() {
		return startP;
	}
	void setFinishTime(int finishtime) {
		this.finishtime=finishtime;
	}
	int getFinishTime() {
		return finishtime;
	}
	
	void setBursttime(int bursttime) {
		this.bursttime=bursttime;
	}
	int getBursttime() {
		return bursttime;
	}
	
	void setWaitingtime(int waitingtime) {
		this.waitingtime=waitingtime;
	}
	int getWaitingtime() {
		return waitingtime;
	}
	void setTurnaroundtime(int turnaroundtime) {
		this.turnaroundtime=turnaroundtime;
	}
	int getTurnaroundtime() {
		return turnaroundtime;
	}
	void setResponsetime(int responsetime) {
		this.responsetime=responsetime;
	}
	int getResponsetime() {
		return responsetime;
	}
}