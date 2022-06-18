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

//Classe Server, que implementa PartRepository, e é filha de UnicastRemoteObject, para que seja 
//possível, além de serializá-la, devolver um stub dela para o cliente, que realiza chamadas 
//remotas para o Server
public class Server extends UnicastRemoteObject implements PartRepository{
	
	private static final long serialVersionUID = 1;//seria o id?
	//objeto que lida com as configurações do servidor: PORT, NAME e URL(no caso, localhost)
	public ServerConfiguration serverConfiguration;
	//estrutura de dados para armazenar as Parts contidas neste Server
	private HashMap<UUID, Part> partRepository; 

	//inicializa a estrutura de dados, bem como as configurações do servidor com base em
	//um array de String, que são os parâmetros passados por linha de comando
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
	//aqui, o cliente passa os parâmetros para criação de uma nova Part nese repositório, bem como
	//passa como parâmetros duas coleções, uma de Part's e outra de Integer's, que serão convertidos
	//em uma única lista de SubPart's
	@Override
	public Part addPart(String nome, String descricao, Collection<Part> subParts, Collection<Integer> subPartsQuantity) throws RemoteException {			
		LinkedList<SubPartElement> subPartsList = convertToSubElementList(subParts, subPartsQuantity);
		Part part = new PartImpl(nome, descricao, subPartsList);
		partRepository.put(part.getPartId(), part);
		return part;
	}
	//procura uma Part pelo seu id, e se ela existir, unifica e converte as duas coleções em uma de SubPart's,
	//e adiciona na lista de subParts da Part
	@Override
	public void addSubPartsToPart(UUID partID, Collection<Part> subParts, Collection<Integer> subPartsQuantity)
			throws RemoteException {
		Part part = partRepository.get(partID);
		if (part != null) {
			LinkedList<SubPartElement> subPartsList = convertToSubElementList(subParts, subPartsQuantity);
			part.getSubParts().addAll(subPartsList);
		}
		
	}
	//método utilizado pelos dois métodos acima, que serve para unificar e converter as duas coleções,
	// uma de Part's e outra de suas respectivas quantidades, em uma lista de SubPartElement
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