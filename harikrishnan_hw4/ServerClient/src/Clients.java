import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("deprecation")
public class Clients implements Runnable {
	static Socket client = null;
	static String hostname = "";
	static String portnum = "";
	static String name = "";
	static String latitude = "";
	static String longitude = "";
	static String gender = "";
	static String age = "";
	static String values = " ";
	
	BufferedReader input = null;
	PrintStream outputStream = null;
	static DataInputStream inputStream = null;
	static boolean clear = false;
	
	public static void main(String[] args) {
		// Getting client information from the user
		hostname = args[1];
		portnum = args[3];
		name = args[5];
		latitude = args[7];
		longitude = args[8];
		gender = args[10];
		age = args[12];
		
		values = hostname + "#" + portnum + "#" + name + "#" + latitude + "#" + longitude + "#" + gender + "#" + age;
		
		Clients clientObj = new Clients();
		clientObj.sendServer(values);
		Clients client = new Clients();
		client.sendServer(values);
	}
	
	public void sendServer(String text) 
	{
		//Establishing a server

		try {
			client = new Socket("localhost", Integer.parseInt(portnum));
			input = new BufferedReader(new InputStreamReader(System.in));
			outputStream = new PrintStream(client.getOutputStream());
			inputStream = new DataInputStream(client.getInputStream());
			outputStream.println(values);
		} catch (UnknownHostException e) {
			System.out.println("First");
		} catch (IOException e) {
			System.out.println("Secondd");
		}

		if (client != null && outputStream != null && inputStream != null) {
			try {
				new Thread(new Clients()).start();
				while (!clear) {
					outputStream.println(input.readLine().trim());
				}
				outputStream.close();
				inputStream.close();
				client.close();
			} catch (Exception e) 
			{
				System.out.println("third");
			}
		}
	}
	
	
	public void run() 
	{
		String respond;
		//Read inputs from server
		try 
		{
			while ((respond = inputStream.readLine()) != null) {
				System.out.println(respond);
				if (respond.indexOf("Bye") != -1)
					break;
			}
			clear = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

