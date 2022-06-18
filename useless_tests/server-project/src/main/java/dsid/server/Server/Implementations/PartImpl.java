package dsid.server.Server.Implementations;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import org.javatuples.Pair;

import dsid.server.Server.Interfaces.Part;

public class PartImpl implements Part, Serializable{

	private static final long serialVersionUID = 1L;//seria o id?
	private UUID id;
	private String name;
	private String description;
	private LinkedList<Pair<Part, Integer>> subParts;

	protected PartImpl() throws RemoteException {
		super();
	}
	public PartImpl(String name, String description, Collection<Pair<Part, Integer>> subParts) throws RemoteException{
		super();
		this.name = name;
		this.description = description;
		this.id = UUID.randomUUID();
		this.subParts = new LinkedList<>();
	}

	@Override
	public LinkedList<Pair<Part, Integer>> getSubParts() throws RemoteException{
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
	@Override
	public void setName(String name) {
		this.name = name;
		
	}
	@Override
	public void setDescription(String description) {
		this.description = description;		
	}
		
}
