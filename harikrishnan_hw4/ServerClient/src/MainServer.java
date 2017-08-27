import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer {
	private static ServerSocket server =null;
	private static Socket client = null;
	public static final SingleClient[] singleClient = new SingleClient[10];
	public static void main(String args[]){
		int port=Integer.parseInt(args[0]);
		try{
			server = new ServerSocket(port);
		}catch(IOException e){
			System.out.println("Server first");
		}
		for(;;){
			try{
				//Accepting clients
				client=server.accept();
				int i=0;
				
				//Strating the client threads
				for (i = 0; i<10 ; i++) {
					if (singleClient[i] == null) {
						(singleClient[i] = new SingleClient(client, singleClient)).start();
						break;
					}
				}
				if (i == 10) {
					PrintStream os = new PrintStream(client.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					client.close();
				}
			}catch(Exception e){
				System.out.println("Server second");
			}
		}	
		}
}


class SingleClient extends Thread{
	Socket client = null;
	int clientCount;
	private final SingleClient[] singleClient;
	static BufferedReader inputStream = null;
	PrintStream outputStream =null;
	public SingleClient(Socket client, SingleClient[] singleClient) {
		this.client = client;
		this.singleClient = singleClient;
		clientCount = singleClient.length;
	}
	
	public void run(){
		SingleClient[] singleClient = this.singleClient;
		try {
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
			outputStream = new PrintStream(client.getOutputStream());
			String data = inputStream.readLine();
			StringTokenizer values=new StringTokenizer(data,"#");
			String hostname = values.nextToken();
			String portnum = values.nextToken();
			String name = values.nextToken();
			String latitude = values.nextToken();
			String longitude = values.nextToken();
			String gender = values.nextToken();
			String age = values.nextToken();
			String ClientId = "jhjh ";
			
			Random rand = new Random();

			ClientId = Integer.toString(rand.nextInt(50) + 1234);
			outputStream.println("Your ID " + ClientId);
			Client cl = new Client(ClientId, hostname, portnum, name, latitude, longitude, gender, age);
			ClientList.clients.add(cl);
			for(;;){
				String read = inputStream.readLine().toLowerCase();
				
				//Quit input
				if(read.equals("quit")){
					break;
				}
				
				//list input
				if (read.contains("list")) {
					StringTokenizer stringtoken=new StringTokenizer(read," ");
					String list = stringtoken.nextToken();
					int dist = Integer.parseInt(stringtoken.nextToken());
					
					for (int i = 0; i < ClientList.clients.size(); i++) {

						try{
							//StringTokenizer latiToken2=new StringTokenizer(AllUserList.users.get(i).getUserpojo().getLati()," ");
							int x1 = Integer.parseInt(ClientList.clients.get(i).latitude);
							int y1 = Integer.parseInt(ClientList.clients.get(i).longitude);
							
							int x2 = Integer.parseInt(latitude);
							int y2 = Integer.parseInt(longitude);
							double finalDistance =Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));	
			
							if(finalDistance<=dist && finalDistance!=0){
								outputStream.println(ClientList.clients.get(i).getID() + "  " + ClientList.clients.get(i).getName() + "  " + ClientList.clients.get(i).getPortnum() + "  " + ClientList.clients.get(i).getLatitude() + "  " + ClientList.clients.get(i).getLongitude() + "  " + ClientList.clients.get(i).getGender() + "  " + ClientList.clients.get(i).getAge());
							}
							
						}
						catch(Exception e)
						{
							System.out.println("Server third");
						}
						
		
						}
				}
				
				//Get location input
				else if(read.contains("get location"))
				{
					outputStream.println("My Location:" + latitude + " " + longitude );
	
				}
				
				//Goto input
				else if(read.contains("go"))
				{
					
					StringTokenizer stringtoken=new StringTokenizer(read," ");
					String go = stringtoken.nextToken();
					int lat = Integer.parseInt(stringtoken.nextToken());
					int longi = Integer.parseInt(stringtoken.nextToken());
					System.out.println(lat + " " + longi);
					
					for(int i=0;i<ClientList.clients.size();i++)
					{
						System.out.println(ClientList.clients.get(i).getName()+ " "+ name);
						if(ClientList.clients.get(i).getName().equals(name))
						{
							try{
								int x = Integer.parseInt(ClientList.clients.get(i).getLatitude());
								int y = Integer.parseInt(ClientList.clients.get(i).getLongitude());
							
								int newX = x + lat;	
								int newY = y + longi;
								
								ClientList.clients.get(i).setLatitude(Integer.toString(newX));
								ClientList.clients.get(i).setLongitude(Integer.toString(newY));
								outputStream.println("New Location : " + newX + " " + newY);
							}
							catch(Exception e)
							{
								System.out.println("Server five");
							}
							
						}
					
					}
				}	
				
				//send messages
				else if(read.contains("send"))
				{
					String message=read.substring(4).trim();
					for(int i=0;i<ClientList.clients.size();i++)
					{
						
						String userid=ClientList.clients.get(i).getID();
						if(read.contains(userid))
						{
							{
								singleClient[i].outputStream.println(name + ":" + message);
							}
						}
					}
					
				}
			}
			
			outputStream.println("Bye");
			for(int i=0;i<ClientList.clients.size();i++)
			{
			if(	ClientList.clients.get(i).getName().equals(name))
			{
			
				ClientList.clients.remove(i);
			}
		}
		
		for (int i = 0; i < clientCount; i++) {
			if (singleClient[i] == this) {
				singleClient[i] = null;
			}
		}

		inputStream.close();
		outputStream.close();
		client.close();
		} catch (IOException e) {
			System.out.println("Server six");
		}
	}
}

