package sys.admin.server.Server.Interfaces;

import java.rmi.Remote;

//interface de SubPartElement, para possibilitar a visualização dos dados das SubPart's pelo cliente
public interface SubPartElement extends Remote{
    public Part getSubPart();
    public int getSubPartQuantity();
    public void addSubPartQuantity(int quantity);
}
