import java.util.ArrayList;

public class double_linked_list extends linked_list{
        
    public double_linked_list(int value){ // same as linked list but previous = null for head of list
        super(value);
        super.getHead().setPrev(null);
    }

    public double_linked_list(int[] values){
        super(values);  // create single then go back and set previous pointers which are null in single linked list
        node curr = getHead();
        node prev = getHead();
        for ( int i = 0; i < length(); i++ ){ // set previous pointers;
            if (curr != getHead()){  // head should not get a previous pointer, make sure current node is not head.
                curr.setPrev(prev); 
                prev = curr;
            }   // link previous and update
            curr = curr.getPointer(); // next node
        }
    }

    public double_linked_list(ArrayList<Integer> values){   // same logic as above
        super(values);
        node curr = getHead();
        node prev = getHead();
        for ( int i = 0; i < length(); i++ ){
            if ( curr != getHead() ){
                curr.setPrev(prev);
                prev = curr;
            }
            curr = curr.getPointer();
        }
    }

    @Override
    public void addItem(int value){ // need changes so that previous pointers remain correct
        super.addItem(value);   // will add the value but leave previous pointer as null, must set previous pointer
        node target = getHead(); // intialize target to start of list
        while (target.getPointer() != getTail()){ // stop at node before tail; target
            target = target.getPointer(); // update target one node
        }
        getTail().setPrev(target);   // set tails previous node
        
    }

    @Override //NOT FINISHED
    public void removeIndex(int index){ // needs overwriting so previous pointer remain consistant; see CORRECTION
        if ( index >= length() || index < 0 ){ // Error check
            System.out.println("Index DNE");
            return;
        }
        node curr = getHead(); // set curr to first node
        if ( index == 0 ){  // quick return if index = 0 = head
            setHead(curr.getPointer());
            setList(getHead());
            return;
        }
        for ( int i = 0; i < index - 1; i++ ){ // index to node of just before index parameter number
            curr = curr.getPointer();
        }
        node after = curr.getPointer().getPointer(); // get node after node of removal 
        curr.setPointer(after); // pointer to node after removal, effectively removing node previous after it.
        after.setPrev(curr); // CORRECTION: Set previous pointer to node before removal index
        if ( index == length() - 1 ){ // reset tail incase of index index removal
            setTail(curr); 
        }
        decLength(); // update length
    }

    @Override  
    public String toString(){
        String final_result = super.toString();    // print single linked list. then print tail -> head to prove double linkage
        if (getTail() == null){
            return final_result;
        }
        String result = " ;";   // add ';' to display backwards link afterwards
        node curr = getTail(); 
        while(curr.getPrev() != null){  // same logic as parent class
            result = result + " " + curr.getValue() + " ->";
            curr = curr.getPrev();
        }
        return final_result + result + " " + curr.getValue(); // prints opposite order, but does so by tail -> head linkage proving double linkage
    } 

    // inherits linked_list list methods, equals and compare has no relation to the linkage of nodes, whether being single or double.
    @Override
    public boolean equals(Object a){
        if (!(a instanceof double_linked_list)){
            return false;
        }
        return super.equals(a);
    } 

    public int compareTo(double_linked_list o){
        return super.compareTo(o);
    }
}
