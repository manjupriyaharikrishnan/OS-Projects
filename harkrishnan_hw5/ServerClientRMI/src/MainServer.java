
import java.rmi.Naming;
public class MainServer{

  public static void main(String args[]){

    try {
    // java.rmi.registry.LocateRegistry.createRegistry(1099);
    	// binding method
	 Naming.rebind("RMIMethod", 
			new RMIMethod() );

	 System.out.println ("Server established");
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}