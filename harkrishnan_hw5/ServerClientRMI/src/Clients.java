import java.rmi.*;
import java.io.Serializable;
public class Clients implements Serializable{
      
	//private static final long serialVersionUID = 1L;
	private int latitude;
      private int longitude;
      private int age;
      private String name;
      private int id;
      private String sex;
      ClientToClientInterface cb;
      
    Clients()throws RemoteException{
    	latitude=0;
        longitude=0;
        age=0;
        name=null;
        id=0;
        cb =null;
    }
	public int getlatitude()throws RemoteException {
		return latitude;
	}
	public void setlatitude(int latitude) throws RemoteException{
		this.latitude = latitude;
	}
	public int getlongitude()throws RemoteException {
		return longitude;
	}
	public void setlongitude(int longitude)throws RemoteException {
		this.longitude = longitude;
	}
	public int getAge()throws RemoteException {
		return age;
	}
	public void setAge(int age)throws RemoteException {
		this.age = age;
	}
	public String getname()throws RemoteException {
		return name;
	}
	public void setname(String name) throws RemoteException{
		this.name = name;
	}
	
	public String getsex()throws RemoteException {
		return sex;
	}
	public void setsex(String sex) throws RemoteException{
		this.sex = sex;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	public void setCallBackObj(ClientToClientInterface Obj){
		cb=Obj;
	}
	public ClientToClientInterface getCallBackObj(){
		return cb;
	}
}


