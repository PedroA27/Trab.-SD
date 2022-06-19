package sys.admin.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

//Interface de Part	
public interface Part extends Remote{
	public String getName() throws RemoteException;
	public UUID getPartId() throws RemoteException;
	public String getDescription() throws RemoteException;
	public void setName(String name);
	public void setDescription(String description);

	//getSubParts map
	public List<SubPartElement> getSubParts() throws RemoteException;
	public boolean isPrimitive() throws RemoteException;

	
}