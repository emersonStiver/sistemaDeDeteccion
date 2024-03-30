package Model.implementations;

import Model.contracts.AttractionType;

public class Fault {
    private int id;
    private AttractionType attractionType;
    private String message;
    private boolean isSolved;
    public int getId() {
        return id;
    }
    public AttractionType getAttractionType() {
        return attractionType;
    }
    public String getMessage() {
        return message;
    }
    public boolean getIsSolved(){
        return isSolved;
    }
    private Fault(int id, String message, AttractionType type){
        this.id = id;
        this.message = message;
        this.attractionType = type;
        this.isSolved= false;
    }
    public static Builder builder(){
        return new Fault.Builder();
    }

    public void setIsSolved(boolean b){
        this.isSolved = b;
    }
    public String toString(){
        return "Averia ( Id: " + id + ", Evento: "+ message + ", Atracción: " + attractionType + ", Status:  " + (isSolved ? "ARREGLADO" : "ABERIADO") + " ) ";
    }

    public static class Builder{
        private int idd;
        private String message;
        private AttractionType type;
        public Builder id(int idd){
            this.idd = idd;
            return this;
        }
        public Builder message(String message){
            this.message = message;
            return this;
        }
        public Builder attractionType(AttractionType type){
            this.type = type;
            return this;
        }
        public Fault build(){
            return new Fault(idd, message, type);
        }
    }
}
