package sys.admin.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import sys.admin.server.Server.Interfaces.Part;
import sys.admin.server.Server.Interfaces.PartRepository;
import sys.admin.server.Server.Interfaces.SubPartElement;


public class ClientSimulator {
    public static void main(String[]args) throws MalformedURLException, RemoteException, NotBoundException{
        PartRepository repository1 = (PartRepository) Naming.lookup("rmi://localhost:1099/serv3");
        PartRepository repository2 = (PartRepository) Naming.lookup("rmi://localhost:1098/serv2");
        PartRepository repository3 = (PartRepository) Naming.lookup("rmi://localhost:1097/serv1");
        UUID id = repository1.addPart("p1", "p1 serv1", null, null).getPartId();
        Collection<Integer> list1 =  new LinkedList<>();
        Collection<Part> list2 =  new LinkedList<>();
        list1.add(3);
        list2.add(repository1.getPart(id));
        UUID id2 = repository2.addPart("p2", "p2 serv2", list2, list1).getPartId();
        Part p3 = repository3.addPart("p3", "p3 serv 3", list2, list1);
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
        list1.add(20);
        list2.add(p3);
        UUID idp12 = repository1.addPart("p12", "p12 serv 1", list2, list1).getPartId();
        Part p234 = repository3.getPart(p3.getPartId());
        for(SubPartElement s : p234.getSubParts().values()){
            System.out.println(s.getSubPart().getName());
            System.out.println(s.getSubPart().getDescription());
            System.out.println(s.getSubPartQuantity());
        }
        for(SubPartElement s : repository1.getPart(idp12).getSubParts().values()){
            System.out.println(s.getSubPart().getName());
            System.out.println(s.getSubPart().getDescription());
            System.out.println(s.getSubPartQuantity());
        }
        // list1.
        // repository2.addSubPartsToPart(id2, subParts, subPartsQuantity);
        // repository2.

    }
}
