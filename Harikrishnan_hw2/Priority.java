import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Priority {
	List<List<String>> processList = new ArrayList<List<String>>();
	List<HashMap<String,Float>> mapList = new ArrayList<HashMap<String,Float>>();
	public static HashMap<String,Float> final_list = new HashMap<String,Float>();
	int process_number=0;
	public static float CPU_time = 0;
	public static float IO_time = 0;
	public static float Wait_time = 0;
	public static float turn_around_time = 0;
	public static float CPU_time_total =0;
	public static float IO_time_total = 0;
	
	Priority(List<List<String>> processList){
		this.processList = processList;
	}
	
	public void Calculate(){
		int[] queue = new int[processList.size()];
		List<List<String>> processListNew = new ArrayList<List<String>>();
		for(int i=0;i<processList.size();i++){
			queue[i] = Integer.parseInt(processList.get(i).get(1)); 
		}
		int temp;
		for(int i=1; i<queue.length; i++) {
			for(int j=i; j>0; j--) {
				if(queue[j] > queue[j-1]) {
					temp=queue[j];
					queue[j]=queue[j-1];
					queue[j-1]=temp;
				}
			}
		}
		
	/*	for(int i=0;i<queue.length;i++){
			System.out.println(queue[i]);
		}	*/
		
		int k=0;
		//Assign the queues based on priority
		while(k<queue.length){
			for(int i=0;i<processList.size();i++){
			//for(int j=0;j<processList.size();j++){
				if(queue[k]==Integer.parseInt(processList.get(i).get(1))){
					processListNew.add(processList.get(i));
					processList.remove(processList.get(i));
				}
			//}
			}
			k++;
		}
		
		/*for(int i=0;i<processListNew.size();i++){
			for(int j=0;j<processListNew.get(i).size();j++){
				System.out.print(processListNew.get(i).get(j) + " ");
			}
			System.out.println();
		}*/
		
		
		while(process_number < processListNew.size()){
			List<String> process = processListNew.get(process_number); 
			//System.out.println(CPU_time_total + " " + IO_time_total);
			//Wait_time = CPU_time_total + IO_time_total ; 
			//System.out.println(Wait_time);
			for(int i=3;i<process.size();i++){
				if(i % 2 == 0){
					IO_time += Integer.parseInt(process.get(i));
					IO_time_total += IO_time;
					IOThread io = new IOThread(Integer.parseInt(process.get(i)));
					io.start();
				}
				else{
					CPU_time += Integer.parseInt(process.get(i));
					CPU_time_total += CPU_time;
					CPUThread cpu = new CPUThread(Integer.parseInt(process.get(i)));
					cpu.start();
				}
			}
			
			// Formulas to calculate performance measures
			HashMap<String,Float> hp = new HashMap<String,Float>();
			hp.put("CPU_time", CPU_time);
			hp.put("IO_time", IO_time);
			Wait_time = CPU_time + IO_time;
			hp.put("Wait_time", Wait_time );
			turn_around_time = IO_time + CPU_time + Wait_time;
			hp.put("Turn_around_time",turn_around_time);
			mapList.add(hp);
			process_number++;
			CPU_time = 0;
			IO_time = 0;
		}
		float wait_time_total = 0, turn_around_time_total = 0;
		for(int i=0;i<mapList.size();i++){
			//System.out.print("Process " + i + " ");
			//System.out.print( "CPU time " + mapList.get(i).get("CPU_time") + " ");
			//System.out.print( "IO time " + mapList.get(i).get("IO_time") + " ");
			//System.out.print( "Wait time " + mapList.get(i).get("Wait_time") + " ");
			wait_time_total += mapList.get(i).get("Wait_time");
			//System.out.println("Turn around time" + mapList.get(i).get("Turn_around_time") + " ");
			turn_around_time_total += mapList.get(i).get("Turn_around_time");
		}
		
		//Assigning of output Paramenters
		final_list.put("CPU_utilization", (CPU_time_total/(IO_time_total+CPU_time_total) * 100));
		final_list.put("throughput", mapList.size()/((IO_time_total+CPU_time_total)));
		final_list.put("turnaround_time", (turn_around_time_total / mapList.size()) );
		final_list.put("waiting_time", (wait_time_total / mapList.size()));
		final_list.put("response_time", (wait_time_total / mapList.size()));
	}
}

