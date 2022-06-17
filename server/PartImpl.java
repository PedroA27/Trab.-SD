package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.UUID;

public class PartImpl extends UnicastRemoteObject implements Part{

	private static final long serialVersionUID = 1L;//seria o id?
	private UUID id;
	private String name;
	private String description;
	private LinkedList<SubPartElement> subParts;

	protected PartImpl() throws RemoteException {
		super();
	}
	public PartImpl(String name, String description) throws RemoteException{
		super();
		this.name = name;
		this.description = description;
		this.id = UUID.randomUUID();
		this.subParts = new LinkedList<>();
	}

	@Override
	public LinkedList<SubPartElement> getSubParts() throws RemoteException{
		return this.subParts;
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
		return id;
	}

	public int hashCode() {
        return id.hashCode();
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartImpl other = (PartImpl) obj;
		return other.id != this.id;
	}
		
}
