package sys.admin.server.Server.Implementations;

import java.io.Serializable;

import sys.admin.server.Server.Interfaces.Part;
import sys.admin.server.Server.Interfaces.SubPartElement;

public class SubElementImpl implements SubPartElement, Serializable{
	private static final long serialVersionUID = 31;//seria o id?
    private Part subPart;
    private int quantity;
    public SubElementImpl(Part subPart, int quantity){
        this.subPart = subPart;
        this.quantity = quantity;
    }
    @Override
    public Part getSubPart() {
        return this.subPart;
    }

    @Override
    public int getSubPartQuantity() {
        return this.quantity;
    }
    
}
