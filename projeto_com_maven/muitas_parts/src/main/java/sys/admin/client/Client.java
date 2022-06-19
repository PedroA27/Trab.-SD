package sys.admin.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;

import sys.admin.Interfaces.PartRepository;
import sys.admin.Interfaces.Part;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Client {
	private PartRepository repository;
	private Part currentPart;
	private String currentPartRepositoryName;
	private Map<Part, Integer> subParts;

	

	public Client(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);
		this.currentPart = null;
		this.subParts = new HashMap<>();
	}

	public void bind(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);

	}
	public Map<Part, Integer> getSubParts(){
		return this.subParts;
	}
	public List<String> getrepprop() throws RemoteException{
		return Arrays.asList(
			"Nome do repositório: "+this.repository.getName(),
			"Número de peças: "+this.repository.getPartsQuantity()
		);
	}


	public List<Part> listp() {
		try {
			List<Part> list = repository.listParts();
			return list;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public Part getp(UUID partId) {
		try {
			return repository.getPart(partId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public void setPart(Part newPart) throws RemoteException {

		this.currentPart = newPart;
		currentPartRepositoryName = this.repository.getName();
	}

	public Part getPart() {
		return this.currentPart;
	}
	public String getPartRepo(){
		return this.currentPartRepositoryName;
	}

	public void clearlist() {
		this.subParts.clear();
	}

	public void addsubpart(int qtd) {
		if (this.currentPart == null) {
			System.out.println("Peça não pode ser adicionada");
			return;
		}
		if (this.subParts.containsKey(currentPart)) {
			int oldQuantity = this.subParts.get(currentPart);
			this.subParts.replace(currentPart, oldQuantity + qtd);
		} else
			this.subParts.put(currentPart, qtd);
	}

	public void addp(String name, String description) {
		try {
			LinkedList<Part> subP = new LinkedList<>();
			LinkedList<Integer> subQtd = new LinkedList<>();

			this.subParts.forEach((subPart,qtd) -> {
				subP.add(subPart);
				subQtd.add(qtd);
			});

			Part newPart = this.repository.addPart(name, description, subP, subQtd);
			this.currentPart = newPart;
			currentPartRepositoryName = this.repository.getName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
