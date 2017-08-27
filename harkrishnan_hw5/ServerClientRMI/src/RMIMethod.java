import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

 public class RMIMethod extends UnicastRemoteObject 
		 implements Methods {

	protected RMIMethod() throws RemoteException {
		super();
	}
	 private int ClientId=0;
	 private int i=0;
	 ClientList ClientList=new ClientList();
	 
	 //List all clients
	 public void showAll() throws RemoteException{
		  i++;
	 }
	 
	 
	 // update the location
	 public Boolean newLocation(int ID,int NewX,int NewY) throws RemoteException{
		 LinkedList <Clients> list = new LinkedList<Clients>();
		 list= ClientList.getClients();
		 int j;
		 for(j=0;j<list.size();j++){
			 if(list.get(j).getID()== ID){
				 int UpdatedX=list.get(j).getlatitude()+NewX;
				 int UpdatedY = list.get(j).getlongitude()+NewY;
				 list.get(j).setlatitude(UpdatedX);
				 list.get(j).setlongitude(UpdatedY);
				 ClientList.setClientInfoList(list);
				 break;
			 }
		 }
		 if( j==list.size()){
			 return false;
		 }
		 return true;
	}
	 
	//Send messages to clients
		 public void CallBack(int id,ClientToClientInterface obj) throws RemoteException{
			 LinkedList <Clients> list = new LinkedList<Clients>();
			 list=ClientList.getClients();
			 int j;
			 for(j=0;j<list.size();j++){
				 if(list.get(j).getID()== id){
					list.get(j).setCallBackObj(obj);
					 break;
				 }
			 }
			 
		 }
		 
	 //update new X coordinate
	 public int newlatitude(int Id) throws RemoteException{
		 LinkedList <Clients> list = new LinkedList<Clients>();
		 list= ClientList.getClients();
		 int j,X=0;
		 for(j=0;j<list.size();j++){
			 if(list.get(j).getID()== Id){
				 X=list.get(j).getlatitude();
				 break;
			 }
		 }
		 if( j==list.size()){
			 return 0;
		 }
		 return X;
	 }
	 
	 
	 //update new Y coordinate
    public int newlongitude(int ID) throws RemoteException{
    	LinkedList <Clients> list = new LinkedList<Clients>();
		 list= ClientList.getClients();
		 int j,Y=0;
		 for(j=0;j<list.size();j++){
			 if(list.get(j).getID()== ID){
				 Y=list.get(j).getlongitude();
				 break;
			 }
		 }
		 if( j==list.size()){
			return 0;
		 }
		 return Y;
		 
	 }
    
  //add client to the list
  	 public int addClient(Clients ClientInfo) throws RemoteException{
  		 
  		 LinkedList <Clients> list = new LinkedList<Clients>();
  		 ClientId++;
  		 ClientInfo.setID(ClientId);
  		 list= ClientList.getClients();
  		 list.add(ClientInfo);
  		 ClientList.setClientInfoList(list);
  		 return ClientId;
  	 }
    
    //get the list of all clients
	 public LinkedList <Clients> getList(int ID, int range) throws RemoteException{
		 LinkedList <Clients> list30=new LinkedList<Clients>();
		 LinkedList <Clients> list = new LinkedList<Clients>();
		 list= ClientList.getClients();
		 int j,X=0,Y=0;
		 boolean flag=false;
		 for(j=0;j<list.size();j++){
			 if(list.get(j).getID()== ID){
				 X=list.get(j).getlatitude();
				 Y=list.get(j).getlongitude();
				 flag=true;
				 break;
			 }
		}
	
		 if( flag==false){
			 return list30;
		 }
		 for(j=0;j<list.size();j++){
			 if((Math.pow((list.get(j).getlatitude() - X),2) + Math.pow((list.get(j).getlongitude() - Y),2)) <= Math.pow(range, 2)){
				 list30.add(list.get(j));
			 }
		 }
		 return list30;
	 }
	 
	 //Exit from the system
	 public void exit(int ID) throws RemoteException{
		 LinkedList <Clients> list = new LinkedList<Clients>();
		 list= ClientList.getClients();
		 int j;
		 for(j=0;j<list.size();j++){
			 if(list.get(j).getID()== ID){
				list.remove(j);
				 break;
			 }
		 }
		 if( j==list.size()){
		 }
		 
	 }

	
	
}
 