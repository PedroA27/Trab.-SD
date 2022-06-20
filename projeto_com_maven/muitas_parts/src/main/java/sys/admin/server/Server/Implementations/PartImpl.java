package sys.admin.server.Server.Implementations;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import sys.admin.Interfaces.Part;
import sys.admin.Interfaces.SubPartElement;

//Implementação da interface Part
public class PartImpl implements Part, Serializable{

	private static final long serialVersionUID = 1L;//seria o id?
	private UUID id;
	private String name;
	private String description;
	private Map<UUID, SubPartElement> subParts;

	protected PartImpl() throws RemoteException {
		super();
	}
	//Construtor, recebendo nome, descrição e uma coleção de subparts, atribuindo-os ao objeto Par,
	//e criando um id único com a classe UUID
	public PartImpl(String name, String description, Collection<Part> subParts, Collection<Integer> quantitites) throws RemoteException{
		super();
		this.name = name;
		this.description = description;
		this.id = UUID.randomUUID();
		this.subParts = new HashMap<>();
		this.addSubParts(subParts, quantitites);		
	}
	//método para retornar as subParts de um Part
	@Override
	public List<SubPartElement> getSubParts() throws RemoteException{
		return new LinkedList<>(this.subParts.values());
	}
	//retorna o nome da Part
	@Override
	public String getName() throws RemoteException {
		return this.name;
	}


	//retorna a descrição da Part
	@Override
	public String getDescription() {
		return description;
	}

	//retorna o id da Part
	@Override
	public UUID getPartId() {
		return id;
	}

	@Override
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

	//setter for changing Part name
	@Override
	public void setName(String name) {
		this.name = name;
		
	}
	//setter for changing Part description
	@Override
	public void setDescription(String description) {
		this.description = description;		
	}
	//método para retornar se a peça é primitiva ou não, com base no tamanho da sua lista de subParts
	@Override
	public boolean isPrimitive() throws RemoteException {
		return this.subParts.isEmpty();
	}
	//adiciona subParts ao Map, com base em duas coleções: uma com as subParts, e a outra com as quantidades de cada subPart
	protected void addSubParts(Collection<Part> subParts, Collection<Integer> quantitites) throws RemoteException {
		if(subParts != null && quantitites != null){			
			int length = subParts.size() < quantitites.size() ? subParts.size() : quantitites.size();
			Iterator<Part> parts = subParts.iterator();
			Iterator<Integer> quantities = quantitites.iterator();
			for(int i = 0; i < length; i++){
				Part part = parts.next();
				this.subParts.putIfAbsent(part.getPartId(), new SubElementImpl(part, quantities.next()));	
			}		
		}		
	}
		
}
