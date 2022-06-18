package dsid.server.Server.Interfaces;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.UUID;

import org.javatuples.Pair;

public interface Part{
	public String getName() throws RemoteException;
	public UUID getPartId() throws RemoteException;
	public String getDescription() throws RemoteException;
	public void setName(String name);
	public void setDescription(String description);
	public LinkedList<Pair<Part, Integer>> getSubParts() throws RemoteException;
	//definir lista mais otimizada
	
}