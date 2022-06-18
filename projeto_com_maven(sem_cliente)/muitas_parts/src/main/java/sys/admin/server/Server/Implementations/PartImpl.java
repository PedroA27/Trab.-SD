package sys.admin.server.Server.Implementations;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import sys.admin.server.Server.Interfaces.Part;
import sys.admin.server.Server.Interfaces.SubPartElement;

//Implementação da interface Part
public class PartImpl implements Part, Serializable{

	private static final long serialVersionUID = 1L;//seria o id?
	private UUID id;
	private String name;
	private String description;
	private LinkedList<SubPartElement> subParts;

	protected PartImpl() throws RemoteException {
		super();
	}
	//Construtor, recebendo nome, descrição e uma coleção de subparts, atribuindo-os ao objeto Par,
	//e criando um id único com a classe UUID
	public PartImpl(String name, String description, Collection<SubPartElement> subParts) throws RemoteException{
		super();
		this.name = name;
		this.description = description;
		this.id = UUID.randomUUID();
		this.subParts = new LinkedList<>();
		this.subParts.addAll(subParts);
	}
	//método para retornar as subParts de um Part
	@Override
	public LinkedList<SubPartElement> getSubParts() throws RemoteException{
		return this.subParts;
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
		
}
