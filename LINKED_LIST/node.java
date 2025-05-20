public class node{

    private int value;  // value of current node
    private node pointer;   // pointer to next
    private node prev;  // pointer to previous 


    public node(int value){ // intialize node with value and no pointers
        this.value = value;
        this.pointer = null;
        this.prev = null;
    }

    //setters and getters
    public int getValue() { 
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public node getPointer() {
        return pointer;
    }

    public void setPointer(node pointer){
        this.pointer = pointer;
    }

    public node getPrev() {
        return prev;
    }

    public void setPrev(node prev) {
        this.prev = prev;
    }

    @Override 
    public boolean equals(Object a){    /* 
        tests node equality for a single node (has a value, next and previous pointer that both have values)
        a node is equal if it has the same value as another and its pointer nodes also contain the same value
        */
        if (!(a instanceof node)){
            return false;
        }
        node b = (node)a;
        if (this.value != b.value){
            return false;
        }
        if ((this.getPointer() != null && b.getPointer() != null) && this.getPointer().getValue() != b.getPointer().getValue()){
            return false;
        }
        if ((this.getPrev() != null && b.getPrev() != null) && this.getPrev().getValue() != b.getPrev().getValue()){
            return false;
        }
        return true;
    }
}