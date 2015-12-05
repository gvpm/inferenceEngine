
package inferenceengine;

import java.util.ArrayList;

/**
 * Simple structure for the Working memory 
 * Basically an array list
 * @author gvpm
 */
public class WorkingMemory {
    
    ArrayList<Tuple> wm;

    /**
     * Constructor
     */
    public WorkingMemory() {
        this.wm =new ArrayList<>();
    }
    
    /**
     * Adds one tuple to the Working Memory
     * @param t the tuple to add
     */
    public void addTuple(Tuple t){
        wm.add(t);
        
    }
    
    /**
     *Removes one tuple from the Working memory
     * @param i index of the tuple to remove
     * @return the tuple removed
     */
    public Tuple removeTuple(int i){
        
        return this.wm.remove(i);
    }
    
    /**
     * Search the given tuple in the working memory and returns its index
     * @param t tuple to search
     * @return -1 if tuple not found, else, return the index
     */
    public int searchTuple(Tuple t){
        if(this.isEmpty())
            return -1;
        
        for (int i = 0; i < this.getSize(); i++) {
            if(this.getTuple(i).compareTuple(t))
                return i;
            
        }
        
        return -1;
    }
    
    /**
     * Returns a tuple from its index
     * @param i  index of the tuple
     * @return the tuple
     */
    public Tuple getTuple(int i){
        
        return this.wm.get(i);
    }
    
    /**
     * Returns the size of the Working Memory 
     * @return size
     */
    public int getSize(){
        
        return this.wm.size();
    }
    
    /**
     * Returns if the working memory is empty
     * @return true = if empty false = if not empty
     */
    public boolean isEmpty(){
        return this.wm.isEmpty();
        
    }

    @Override
    public String toString() {
        return "WorkingMemory: "+ wm;
    }
    
    
    
    
    
}
