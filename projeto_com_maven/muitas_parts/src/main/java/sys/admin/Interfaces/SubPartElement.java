package sys.admin.Interfaces;

import java.rmi.Remote;

//interface de SubPartElement, para possibilitar a visualização dos dados das SubPart's pelo cliente
public interface SubPartElement extends Remote{
    public Part getSubPart();
    public int getSubPartQuantity();
}
