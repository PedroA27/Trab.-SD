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

	
	//instancia Client, atribuindo o repositório de acordo com a String passada como parâmetro
	public Client(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);
		this.currentPart = null;
		this.subParts = new HashMap<>();
	}
	//muda a referência de PartRepository com base em outro servidor
	public void bind(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);

	}
	//retorna o Map de SubParts corrente
	public Map<Part, Integer> getSubParts(){
		return this.subParts;
	}
	//retorna uma lista, com as informações formatadas do repositório corrente
	public List<String> getrepprop() throws RemoteException{
		return Arrays.asList(
			"Nome do repositório: "+this.repository.getName(),
			"Número de peças: "+this.repository.getPartsQuantity()
		);
	}

	//retorna uma lista com as peças contidas no repositório corrente
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
	//obtém uma peça do repositório corrente com base em seu id
	public Part getp(UUID partId) {
		try {
			return repository.getPart(partId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	//muda a referência da peça corrente, e o nome do repositório referente à peça corrente
	public void setPart(Part newPart) throws RemoteException {
		this.currentPart = newPart;
		currentPartRepositoryName = this.repository.getName();
	}
	//retorna a peça corrente
	public Part getPart() {
		return this.currentPart;
	}
	//retorna o nome do repositório que contém a peça corrente
	public String getPartRepo(){
		return this.currentPartRepositoryName;
	}
	//limpa o Map de sub partes corrente
	public void clearlist() {
		this.subParts.clear();
	}
	//adiciona a peça corrente ao Map de sub peças corrente, e se ela já estiver no Map, incrementa
	//a quantidade dela com base na quantidade passada por parâmetro
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
	//adiciona uma nova peça no repositório corrente, tendo essa nova peça as sub peças referenciadas
	//pelo Map de sub peças corrente
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
