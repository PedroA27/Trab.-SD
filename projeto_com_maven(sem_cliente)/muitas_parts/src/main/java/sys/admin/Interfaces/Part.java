package sys.admin.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

//Interface de Part	
public interface Part extends Remote{
	public String getName() throws RemoteException;
	public UUID getPartId() throws RemoteException;
	public String getDescription() throws RemoteException;
	public void setName(String name);
	public void setDescription(String description);

	//getSubParts map
	public Map<UUID, SubPartElement> getSubParts() throws RemoteException;

	//método utilizado pelos dois métodos acima, que serve para unificar e converter as duas coleções,
	// uma de Part's e outra de suas respectivas quantidades, em um Map de SubPartElement
	public void addSubParts(Collection<Part> subParts, Collection<Integer> quantities) throws RemoteException;
	
}