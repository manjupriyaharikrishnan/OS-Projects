import java.rmi.*;
import java.util.*;


public class Client{
  public static void main (String[] args) {
    try {
    	Scanner scanner=new Scanner(System.in);
    	Clients values=new Clients();
    	ListAll list=new ListAll();	
    	// Getting client information from the user
    	values.setname(args[3]);
    	values.setlatitude(Integer.parseInt(args[5]));
    	values.setlongitude(Integer.parseInt(args[6]));
    	values.setsex(args[8]);
    	values.setAge(Integer.parseInt(args[10]));
    	
    	//RMI binding method
    	Methods methods = (Methods) Naming.lookup("//localhost/RMIMethod"); 
    	int ID= methods.addClient(values);
    	values.setID(ID);
    	ClientToClient cl=new ClientToClient();
    	methods.CallBack(ID,(ClientToClientInterface) cl);
    	methods.showAll();
    	while(true){
    		System.out.println("Enter your command");
    		String input = scanner.nextLine();
    		String[] inputs=input.split(" ");
    		
    		//get location command
    		if(inputs[0].equals("get")){
    			int changedlatitude = methods.newlatitude(values.getID());
    			int changedlongitude= methods.newlongitude(values.getID());
    		    System.out.println("New Location:" + changedlatitude +"," + changedlongitude);
    		}
    		
    		//list 30 command
    		else if(input.contains("list")){
			    LinkedList<Clients> clients=new LinkedList<Clients>();
			    list.setClients(methods.getList(values.getID(),Integer.parseInt(inputs[1])));
				 clients = list.getneighborInfoList();
				System.out.println("Id   Name   Sex   Age");
				for(Clients client : clients){
					System.out.println(client.getID() + "   " + client.getname() + "   " + client.getsex() + "   " + client.getAge());
    			}
    		}
    		
    		//go to new location
    		else if(inputs[0].equals("go")){
    			
    			if(methods.newLocation(values.getID(),Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]))){
    				System.out.println("Updated");
    		}
    		}
    		
    		//send messages to other clients
    		else if(inputs[0].equals("send")){
    			LinkedList<Clients> clients=new LinkedList<Clients>();
    			clients = list.getneighborInfoList();
    			String[] sendmessage=input.split(" ");
    			String message="";
    			for(int i=2;i< sendmessage.length;i++){
    				message=message+" "+sendmessage[i];
    			}
    	
    			for(Clients client : clients){
    				if(client.getID()==Integer.parseInt(sendmessage[1])){
    					client.getCallBackObj().show(message);
    				}
    			}
    			
    		}
    		
    		//Exit from the system
    		else if(inputs[0].equals("quit")){
    		    methods.exit(values.getID());
    		    System.exit(0);
    		}
    			
    	}
    } catch (Exception e) {
    	e.printStackTrace();
    }  
 }
}