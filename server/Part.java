import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.UUID;

public interface Part{
	public String getName() throws RemoteException;
	public UUID getPartId() throws RemoteException;
	public String getDescription() throws RemoteException;
	public LinkedList<SubPartElement> getSubParts() throws RemoteException;
	//definir lista mais otimizada
	
}