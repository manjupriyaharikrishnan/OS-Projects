public class Main{
	public static void main(String[] args) {
		int arg_count=0;
		String algo= " ",file_name= " ";
		int quantam = 0,cpu_utilization =0, throughput = 0, turnaround_time = 0, waiting_time = 0, response_time = 0;
		//Getting arguments
		while(args.length > arg_count){
			switch(args[arg_count]){		
			case "-algo":
				algo = args[++arg_count];
				break;
			case "-quantam":
				quantam = Integer.parseInt(args[++arg_count]);
				break;
			case "-input":
				file_name = args[++arg_count];
				break;
			}
			arg_count++;
		}
		
		//Read content from file
		//System.out.println(algo + " " + quantam + " " + file_name);
		FileReadThread file = new FileReadThread(file_name);
		file.start();
		try {
			file.join();
		} catch (InterruptedException e) {}
		
		/*for(int i=0;i<FileReadThread.processList.size();i++){
			for(int j=0;j<FileReadThread.processList.get(i).size();j++){
				System.out.print(FileReadThread.processList.get(i).get(j) + " ");
			}
			System.out.println();
		}*/
		
		//Switch to select the scheduling Algorithm
		switch(algo){
			case "FIFO":
				FIFO p = new FIFO(FileReadThread.processList);
				p.Calculate();
				System.out.println("Input File Name		:  " + file_name);
				System.out.println("CPU Scheduling Alg	:  " + algo);
				System.out.println("CPU Utilization		:  " + FIFO.final_list.get("CPU_utilization") + "%");
				System.out.println("Throughput		:  " + FIFO.final_list.get("throughput"));
				System.out.println("Turnaround time		:  " + FIFO.final_list.get("turnaround_time"));
				System.out.println("Waiting time		:  " + FIFO.final_list.get("waiting_time"));
				System.out.println("Response time		:  " + FIFO.final_list.get("response_time"));
				break;
			case "SJF":
				SJF s = new SJF(FileReadThread.processList);
				s.Calculate();
				System.out.println("Input File Name		:  " + file_name);
				System.out.println("CPU Scheduling Alg	:  " + algo);
				System.out.println("CPU Utilization		:  " + SJF.final_list.get("CPU_utilization") + "%");
				System.out.println("Throughput		:  " + SJF.final_list.get("throughput"));
				System.out.println("Turnaround time		:  " + SJF.final_list.get("turnaround_time"));
				System.out.println("Waiting time		:  " + SJF.final_list.get("waiting_time"));
				System.out.println("Response time		:  " + SJF.final_list.get("response_time"));
				break;
			case "PR":
				Priority pr = new Priority(FileReadThread.processList);
				pr.Calculate();
				System.out.println("Input File Name		:  " + file_name);
				System.out.println("CPU Scheduling Alg	:  " + algo);
				System.out.println("CPU Utilization		:  " + Priority.final_list.get("CPU_utilization") + "%");
				System.out.println("Throughput		:  " + Priority.final_list.get("throughput"));
				System.out.println("Turnaround time		:  " + Priority.final_list.get("turnaround_time"));
				System.out.println("Waiting time		:  " + Priority.final_list.get("waiting_time"));
				System.out.println("Response time		:  " + Priority.final_list.get("response_time"));
				break;
			case "RR":
				RoundRobin rr = new RoundRobin(FileReadThread.processList);
				rr.Calculate();
				break;
		}
		
		
		//IOThread io= new IOThread();
		//io.start();
		//CPUThread cpu= new CPUThread(); 
		//cpu.start();		
		/*try {
			//thread1.join();
			//thread2.join();
		} catch (InterruptedException e) {}*/
		
		
	}
}