import java.io.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
public class ClientToClient extends UnicastRemoteObject 
implements ClientToClientInterface,Serializable {

	 ClientToClient() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void show(String m) throws RemoteException{
	   System.out.println(m);
	  	
	}

}