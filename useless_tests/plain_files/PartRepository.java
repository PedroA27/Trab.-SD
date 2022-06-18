package sys.admin.server.Server.Interfaces;

import java.util.Collection;
import java.util.UUID;

import org.javatuples.Pair;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote{
	Part getPart(UUID id) throws RemoteException;
	String getName() throws RemoteException;
	int getPartsQuantity() throws RemoteException;
	Collection<Part> listParts() throws RemoteException;
	Part addPart(String nome, String descricao, Collection<Pair<Part, Integer>> subParts) throws RemoteException;
}
