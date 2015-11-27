
package inferenceengine;

import java.util.ArrayList;

/**
 *
 * @author gvpm
 */
public class WorkingMemory {
    
    ArrayList<Tuple> wm;

    public WorkingMemory() {
        this.wm =new ArrayList<>();
    }
    
    public void addTuple(Tuple t){
        wm.add(t);
        
    }
    
    public Tuple removeTuple(int i){
        
        return this.wm.remove(i);
    }
    
    public int searchTuple(Tuple t){
        if(this.isEmpty())
            return -1;
        
        for (int i = 0; i < this.getSize(); i++) {
            if(this.getTuple(i).compareTuple(t))
                return i;
            
        }
        
        return -1;
    }
    
    
    
    public Tuple getTuple(int i){
        
        return this.wm.get(i);
    }
    
    public int getSize(){
        
        return this.wm.size();
    }
    
    public boolean isEmpty(){
        return this.wm.isEmpty();
        
    }

    @Override
    public String toString() {
        return "WorkingMemory: "+ wm;
    }
    
    
    
    
    
}
