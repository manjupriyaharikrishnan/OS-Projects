
import java.rmi.*;
import java.util.*;

public class ListAll {
	LinkedList<Clients> Info=new LinkedList<Clients>();
	
	
	public  synchronized LinkedList<Clients> getneighborInfoList()throws RemoteException {
		return Info;
	}
	public synchronized void setClients(LinkedList<Clients> list) throws RemoteException{
		Info = list;
	}
    
	
}
