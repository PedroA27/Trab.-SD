import java.util.LinkedList;
import java.rmi.RemoteException;

public interface PartRepository {
	LinkedList<Part> getConj() throws RemoteException;

}
