import java.util.ArrayList;

public class linked_list implements Comparable<linked_list>{

    private node list;    // node for whole list, is equal to head
    private node head;    // node for head/start of list
    private node tail;    // node for tail/end of list
    private int length;   // length of list; amount of values stored in nodes
    
    public linked_list(int value){  // intialize node as list with head and tail, start length at 1
        this.list = new node(value);
        length = 1;
        head = this.list;
        tail = null;
    }

    public linked_list(int[] values){   // intialize the list with an array of values, numbers in values will be placed into nodes forming a linked list
        if (values.length == 0){    // create empty node if length of inputted list is nothing
            this.list = null;
            length = 0;
            return;
        }
        this.list = new node(values[0]);    // else create a new node with value at index 0 to start with
        length++;   // increment length
        head = this.list;
        tail = null;    // set head and tail
        for ( int i = 1; i < values.length; i++ ){  // index through all values and add them
            addItem(values[i]); // add item takes care of updating tail, length
        }
    }

    public linked_list(ArrayList<Integer> values){  // same as above, adapted for arraylist
        if (values.size() == 0){
            this.list = null;
            length = 0;
            return;
        }
        this.list = new node(values.get(0));
        length++;
        head = this.list;
        tail = null;
        for ( int i = 1; i < values.size(); i++ ){
            addItem(values.get(i));
        }
    }

    public void addItem(int value){ // adds new node of value to end of list, increments length
        if (this.list == null){ // if list is empty, create new list
            this.list = new node(value);
            head = this.list;
            tail = null;
            length++;
        }
        else if ( tail == null ){   // if tail points to null then there is only a head
            this.list.setPointer(new node(value));  // set pointer of start of list to a new node of value inputted
            tail = this.list.getPointer();  // set tail to what the start points to
            length++;   
        }
        else{   // there is a tail
            node a = new node(value);   //create new node of value
            tail.setPointer(a);         // add new node to list by having the tail point to it
            tail = a;                   // make tail the new node that is at the end
            length++;
        }
    }

    public void removeIndex(int index){ // corrects pointer at index around the given one effectively removing the node
        if ( index >= length || index < 0 ){ // Error check
            System.out.println("Index DNE");
            return;
        }
        node curr = head; // set curr to first node
        if ( index == 0 ){  // quick return if index = 0 = head
            head = curr.getPointer();
            list = head;
            return;
        }
        for ( int i = 0; i < index - 1; i++ ){ // index to node of just before index parameter number
            curr = curr.getPointer();
        }
        node after = curr.getPointer().getPointer(); // get node after node of removal 
        curr.setPointer(after); // pointer to node after removal, effectively removing node previous after it.
        if ( index == length - 1 ){ // update tail incase of removing final index node
            tail = curr; 
        }
        length--; // update length
    }

    public int valueAt(int index){  // give value of list at given index
        if (index >= length || index < 0){ // Error check
            return -1;
        }
        if (index == 0){    // quick escape if 0 or length - 1 as these are head and tail
            return head.getValue();
        }
        else if (index == length - 1){
            return tail.getValue();
        }
        node curr = head;
        for ( int i = 0; i < index; i++ ){ // find the node at given index
            curr = curr.getPointer();
        }
        return curr.getValue(); // return the value of node at index
    }
    // setters and getters
    public node getList() {
        return this.list;
    }

    public void setList(node a){
        this.list = a;
    }

    public node getHead(){
        return this.head;
    }

    public void setHead(node a){
        this.head = a;
    }
    
    public node getTail(){
        return this.tail;
    }

    public void setTail(node a){
        this.tail = a;
    }

    public int length() {
        return length;
    }

    protected void decLength(){
        length--;
    }

    protected void incLength(){
        length++;
    }

    @Override
    public String toString(){   // toString that helps visualize the data structure
        if (this.list == null){
            return "";
        }
        node point = this.list; // indexing pointer
        String result = "";
        while (point.getPointer() != null){ // wait until pointing to null, effectively end of list
            result = result + " " + point.getValue() + " ->";   // set string equal to previous result and pointing
            point = point.getPointer(); // set pointer for indexing to next point
        }
        return result + " " + point.getValue(); // return resulting string, note; loop stops at last node, not adding last value
    }
    
    @Override
    public int compareTo(linked_list o) {   // 1 = this > o, -1 = this < 0; a list is greater than if its value in lower index is higher; this.valueAt(1) > o.valueAt(1) -> this > o
        if (this.equals(o)){    // use equals, method already written
            return 0;   // 0 = this = o
        }
        int length;     // need smaller length for indexing 
        boolean length1;    // keep which length is greater for tie breaker
        if (this.length() > o.length()){    // find lower length
            length = o.length();    // o's length is lower so length = o.length()
            length1 = true;     // length of this is greater than o
        }
        else{
            length = this.length();
            length1 = false;    // length of this is less than o
        }
        for ( int i = 0; i < length; i++ ){
            if (this.valueAt(i) > o.valueAt(i)){    // test which value is higher, use objects method of value at index
                return 1;
            }
            else if (this.valueAt(i) < o.valueAt(i)){
                return -1;
            }
        } // use tie breaker if there is no return at the end of the loop
        if (length1){   // tie breaker; if lists are equal up to lower index, list with more elements is greater
            return 1;
        }
        else{
            return -1;
        }
    }
    
    @Override 
    public boolean equals(Object a){    // tests if this object is equals to object a. two linked lists are equal if have the same value and point to the same value for corresponding nodes
        if (!(a instanceof linked_list)){ // check if parameter object is linked list
            return false;
        }
        linked_list a1 = (linked_list)a;    // cast if so to linked list
        if (a1.length() != this.length()){  // if lengths are not equal, then lists are not equal
            return false;
        }
        for ( int i = 0; i < this.length(); i++){   // index through every node. It is know that the two length are equal at this stage
            if (this.valueAt(i) != a1.valueAt(i)){  // test value equality
                return false;   // return false if values are inequal once
            }
        }
        return true; // if not flagged for false yet, then this = other.
    }
}
