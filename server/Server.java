package server;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import server.ServerConfiguration.ServerConfiguration;
import server.ServerConfiguration.ServerMissingParamsException;

public class Server implements PartRepository{

	private ServerConfiguration serverConfiguration;
	private HashMap<UUID, Part> partRepository; 
	private Server(String[] args){
		try {
			serverConfiguration = new ServerConfiguration(args);
		} catch (ServerMissingParamsException e1) {
			e1.printStackTrace();
			return;
		}		
	}
	private void Start(){
		try {
			
			Registry registry = LocateRegistry.createRegistry(serverConfiguration.GetPort());
			Naming.rebind(serverConfiguration.GetFullUrl(),new PartImpl());
			partRepository = new HashMap<>();		
			System.out.println("Servidor levantou");

		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Server server = new Server(args);
		server.Start();
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
	public void addPart(Part part) throws RemoteException {
		partRepository.put(part.getPartId(), part);
		
	}
}
