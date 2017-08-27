import java.rmi.*;
import java.util.*;

public interface Methods extends Remote {
    public int addClient(Clients ClientInfo) throws RemoteException;
	public void CallBack(int id,ClientToClientInterface obj) throws RemoteException;
	 public void showAll() throws RemoteException;
	 public Boolean newLocation(int ID,int NewX,int NewY)throws RemoteException;
	 public int newlatitude(int ID)throws RemoteException;
	 public int newlongitude(int ID)throws RemoteException;
	 public LinkedList <Clients> getList(int ID,int range)throws RemoteException;	 
	 public void exit(int ID) throws RemoteException;
	 }