import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;
import java.util.Collection;
import java.util.LinkedList;

import Server.Part;
import Server.PartRepository;
import Server.SubPartElement;


public class Client {
	PartRepository repository;
	private Part part;
	LinkedList<SubPartElement> subParts;
	
	public Client(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);
		this.part = null;
		this.subParts = new LinkedList<>();
	}
	
	
	public void bind(String server) throws MalformedURLException, RemoteException, NotBoundException {
		this.repository = (PartRepository) Naming.lookup(server);
	
	}
	
	public Collection<Part> listp() {
		try {
			Collection<Part> list = repository.listParts();
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
	
	 public void setPart(Part newPart) {

	        this.part = newPart;
    }
	 public Part getPart() {
			return this.part;
	}
	 public void clearlist() {
		 this.subParts.clear();
	 }
	 public void addsubpart(int qtd) {
		 if(this.part == null) {
			 System.out.println("Peça não pode ser adicionada");
		 }
		 try {
			 UUID id = this.part.getPartId();
			 for (int i = 0; i < subParts.size(); i++) {
				 if(subParts.get(i).getSubPart().getPartId().equals(id)){
					 subParts.get(i).addSubPartQuantity(qtd);
					 i = subParts.size();
					 return;
				 }
			 }
			 
			 //criar no server?
			 SubPartElement newSub = null;
			 subParts.add(newSub);
			 
		 } catch (RemoteException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		
	 }
	 
	 public void addp(String name, String description) {
		 try {
			 LinkedList<Part> subP = new LinkedList<>();
			 LinkedList<Integer> subQtd = new LinkedList<>();
			 for (int i = 0; i < subParts.size(); i++) {
				 subP.add(subParts.get(i).getSubPart());
				 subQtd.add(subParts.get(i).getSubPartQuantity());
	         }
			 this.repository.addPart(name, description, subP, subQtd);
		 }catch (RemoteException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }

}
