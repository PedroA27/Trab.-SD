package sys.admin.server.Server.Interfaces;

import java.util.Collection;
import java.util.UUID;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interface de PartRepository
public interface PartRepository extends Remote{
	Part getPart(UUID id) throws RemoteException;
	String getName() throws RemoteException;
	int getPartsQuantity() throws RemoteException;
	Collection<Part> listParts() throws RemoteException;
	Part addPart(String nome, String descricao, Collection<Part> subParts, Collection<Integer> subPartsQuantity) throws RemoteException;
}
