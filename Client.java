import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;
import java.util.Collection;

public class Client {
	PartRepository repository;
	private Part part;
	
	public Client(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);
		this.part = null;
	}
	
	
	public void bind(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);
	
	}
	
	public Collection<Part> listp() {
		try {
			Collection<Part> list = repository.listParts();
			return list;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Part getp(UUID partId) {
		try {
			return repository.getPart(partId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	 public void setPart(Part newPart) {

	        this.part = newPart;
    }
	 public Part getPart() {
			return this.part;
		}

}
