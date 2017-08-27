
import java.rmi.*;
import java.util.*;

public class ClientList {
	LinkedList<Clients> client=new LinkedList<Clients>();
	public  synchronized LinkedList<Clients> getClients()throws RemoteException {
		return client;
	}
	public synchronized void setClientInfoList(LinkedList<Clients> list) throws RemoteException{
		client = list;
	}
    
	
}