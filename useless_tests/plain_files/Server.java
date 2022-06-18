package sys.admin.server.Server.Implementations;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import org.javatuples.Pair;

import sys.admin.server.Server.Interfaces.Part;
import sys.admin.server.Server.Interfaces.PartRepository;
import sys.admin.server.Server.ServerConfiguration.ServerConfiguration;
import sys.admin.server.Server.ServerConfiguration.ServerMissingParamsException;


public class Server extends UnicastRemoteObject implements PartRepository{
	
	private static final long serialVersionUID = 1;//seria o id?
	public ServerConfiguration serverConfiguration;
	private HashMap<UUID, Part> partRepository; 

	public Server(String[] args) throws ServerMissingParamsException, Exception{
		partRepository = new HashMap<>();
		serverConfiguration = new ServerConfiguration(args);	
	}

	@Override
	public Part getPart(UUID id) throws RemoteException {
		return partRepository.get(id);
	}

	@Override
	public String getName() throws RemoteException {
		return serverConfiguration.GetName();
	}
	@Override
	public int getPartsQuantity() throws RemoteException{
		return partRepository.size();
	}
	@Override
	public Collection<Part> listParts() throws RemoteException {
		return partRepository.values();
	}

	@Override
	public Part addPart(String nome, String descricao, Collection<Pair<Part, Integer>> subParts) throws RemoteException {			
		LinkedList<Pair<Part, Integer>> subPartsList = new LinkedList<>();
		if(subParts != null)
			subPartsList.addAll(subParts);
		Part part = new PartImpl(nome, descricao, subParts);
		partRepository.put(part.getPartId(), part);
		return part;
	}
	
	
}
