import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.UUID;

public class PartImpl extends UnicastRemoteObject implements Part{
	
	
	

	private static final long serialVersionUID = 1L;//seria o id?
	private String name;
	private String description;

	protected PartImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}



	@Override
	public String getDescription() {
		return description;
	}

	

	@Override
	public UUID getPartId() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
