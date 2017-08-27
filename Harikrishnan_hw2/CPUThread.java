
public class CPUThread extends Thread{
	int time = 0;
	
	CPUThread(int time){
		this.time = time;
	}
	
	//CPU Utilization
	public void run(){
		try{Thread.sleep(time);}catch(InterruptedException e){System.out.println(e);} 
	}
}
