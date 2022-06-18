package sys.admin.server.Server.Implementations;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;


import sys.admin.server.Server.Interfaces.Part;
import sys.admin.server.Server.Interfaces.PartRepository;
import sys.admin.server.Server.Interfaces.SubPartElement;
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
	public Part addPart(String nome, String descricao, Collection<Part> subParts, Collection<Integer> subPartsQuantity) throws RemoteException {			
		LinkedList<SubPartElement> subPartsList = convertToSubElementList(subParts, subPartsQuantity);
		Part part = new PartImpl(nome, descricao, subPartsList);
		partRepository.put(part.getPartId(), part);
		return part;
	}

	@Override
	public void addSubPartsToPart(UUID partID, Collection<Part> subParts, Collection<Integer> subPartsQuantity)
			throws RemoteException {
		LinkedList<SubPartElement> subPartsList = convertToSubElementList(subParts, subPartsQuantity);
		Part part = partRepository.get(partID);
		part.getSubParts().addAll(subPartsList);
	}

	private LinkedList<SubPartElement> convertToSubElementList(Collection<Part> subParts, Collection<Integer> subPartsQuantity){
		LinkedList<SubPartElement> subPartsList = new LinkedList<>();
		if(subParts != null && subPartsQuantity != null){			
			int length = subParts.size() < subPartsQuantity.size() ? subParts.size() : subPartsQuantity.size();
			Iterator<Part> parts = subParts.iterator();
			Iterator<Integer> quantities = subPartsQuantity.iterator();
			for(int i = 0; i < length; i++)
				subPartsList.add(new SubElementImpl(parts.next(), quantities.next()));			
		}
		return subPartsList;
	}
	
	
}
