package sys.admin.server.Server.Interfaces;

import java.rmi.Remote;

public interface SubPartElement extends Remote{
    public Part getSubPart();
    public int getSubPartQuantity();
}
