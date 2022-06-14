import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server implements PartRepository{
	public static void main(String[] args) {
		try {
			Registry registry=LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost//1099/nomez",new PartImpl());
			
			
			System.out.println("Servidor levantou");
		} catch (RemoteException |MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
