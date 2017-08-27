public class IOThread  extends Thread {
	int time =0;
	
	IOThread(int time){
		this.time = time;
	}
	
	//IO Utilization
	public void run(){
			try{Thread.sleep(time);}catch(InterruptedException e){System.out.println(e);} 
		}
}
