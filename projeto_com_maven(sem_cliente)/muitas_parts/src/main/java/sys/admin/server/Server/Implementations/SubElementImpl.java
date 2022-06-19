package sys.admin.server.Server.Implementations;

import java.io.Serializable;

import sys.admin.server.Server.Interfaces.Part;
import sys.admin.server.Server.Interfaces.SubPartElement;

//classe SubElementImpl, que implementa SubPartElement (classe que retorna um subPart, ou seja, representando
//uma subPart de uma Part, e quantidade que a Part pai possui dessa subPart) e Serializable (para que
//seja possível retorno no liente)
public class SubElementImpl implements SubPartElement, Serializable{
	private static final long serialVersionUID = 31;
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
    
    @Override
	public void addSubPartQuantity(int qtd) {
		// TODO Auto-generated method stub
		this.quantity = quantity + qtd;
	}
    
}
