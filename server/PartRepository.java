package server;

import java.util.Collection;
import java.util.UUID;
import java.rmi.RemoteException;

public interface PartRepository {
	Part getPart(UUID id) throws RemoteException;
	String getName() throws RemoteException;
	int getPartsQuantity() throws RemoteException;
	Collection<Part> listParts() throws RemoteException;
	void addPart(Part part) throws RemoteException;
}
