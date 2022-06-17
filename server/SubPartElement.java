package server;

public class SubPartElement {
    private Part SubPart;
    private int Quantity;
    public SubPartElement(Part subPart, int quantity){
        this.Quantity = quantity;
        this.SubPart = subPart;
    }
    public Part GetSubPart(){
        return this.SubPart;
    }
    public int GetQuantity(){
        return this.Quantity;
    }
}
