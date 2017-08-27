import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileReadThread extends Thread {
	String file_name;
	public static List<List<String>> processList = new ArrayList<List<String>>();
	
	FileReadThread(String file_name){
		this.file_name = file_name;
	}
	
	//Read and assign the parameters from file to the data structure
	public void run(){
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader("C:/Users/ADMIN/workspace/SchedulingAlgos/src/" + file_name);
			br = new BufferedReader(fr);
			String line;
			String inst = " ";
			int process_number=0;
			while ((line = br.readLine()) != null) {
				String[] list = line.split(" ");
				inst = list[0];	
				switch(inst){
					case "proc":
						int burst_time=2;
						List<String> process = new ArrayList<String>();
						process.add("P"+ ++process_number);
						process.add(list[burst_time]);
						while(burst_time < list.length){
							process.add(list[burst_time]);
							burst_time++;
						}
						processList.add(process);
						break;
					case "sleep":
						int time = Integer.parseInt(line.split(" ")[1]);
						try{Thread.sleep(time);}catch(InterruptedException e){System.out.println(e);} 
						break;
					case "stop":
						break;
				}
			}
			
			/*for(int i=0;i<processList.size();i++){
				for(int j=0;j<processList.get(i).size();j++){
					System.out.print(processList.get(i).get(j) + " ");
				}
				System.out.println("\n");
			}*/

		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("File read Thread");
	}
	
}
