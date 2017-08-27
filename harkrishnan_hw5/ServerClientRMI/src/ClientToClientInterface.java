import java.rmi.*;

public interface ClientToClientInterface extends Remote {
	public void show(String msg) throws RemoteException;
}