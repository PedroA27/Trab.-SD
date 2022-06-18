package dsid_client.Interfaces;

import java.util.Collection;
import java.util.UUID;

import org.javatuples.Pair;

import java.rmi.RemoteException;

public interface PartRepository {
	Part getPart(UUID id) throws RemoteException;
	String getName() throws RemoteException;
	int getPartsQuantity() throws RemoteException;
	Collection<Part> listParts() throws RemoteException;
	Part addPart(String nome, String descricao, Collection<Pair<Part, Integer>> subParts) throws RemoteException;
}
