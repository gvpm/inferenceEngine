
package inferenceengine;

import java.util.ArrayList;

/**
 * Structure that represents a Tuple
 * Contains a list of strings that are the tuple elements
 * @author gvpm
 */
public class Tuple {
    
    ArrayList<String> elements; 
    
    /**
     * Constructor
     */
    public Tuple() {
        elements = new ArrayList<>();
    }
    

    
    /**
     * Adds an element to the tuple
     * @param e String to add
     */
    public void addElement(String e){
        elements.add(e);
    }
    
    /**
     * Loads a tuple from a String from txt template
     * Example (a b) will will create a tuple with strings "a" and "b"
     * @param  s String to transform in 
     */
    public void loadFromString(String s){
      
        String opened;
        opened = s.replace("(", "");
        opened = opened.replace(")", "");
      
        
        if(opened.contains(" ")){
             String[] sv = opened.split(" ");
             for (int i = 0; i < sv.length; i++) {
                 this.addElement(sv[i]);                
                
            }
            
        }else{
            this.addElement(opened);
        }
        
        
        
    }
    
    /**
     * Compares one tuple to another
     * @param t tuple to compare 
     * @return true= if the tuples are equal false= if tuples are not equal
     */
    public boolean compareTuple(Tuple t){
        boolean r = true;
        int comparison = 0;
        
        if(this.getSize()!=t.getSize()){
            r=false;
            return r;
        }
        
        for (int i = 0; i < elements.size(); i++) {
            comparison = this.getElement(i).compareTo(t.getElement(i));
            if(comparison !=0){
                r=false;
            }
            
        }        
        
        return r;
    }
    
    /**
     * Returns an element from the tuple
     * @param i index for the element
     * @return the element
     */
    public String getElement(int i){
        
        return this.elements.get(i);
    }
    
    /**
     * Removes one element from tuple
     * @param i index of the element
     * @return the element removed
     */
    public String removeElement(int i){
        return this.elements.remove(i);
        
    }    
    
    /**
     * Return the amount of elements in the tuple
     * @return the number of elements
     */
    public int getSize(){
        
        return this.elements.size();
    }

    @Override
    public String toString() {
        if(this.getSize()==0)
            return null;
        
        String r = "(";
        
        
        
        for (int i = 0; i < this.getSize(); i++) {
           r=r.concat(this.getElement(i));
           if(i!=this.getSize()-1)
               r=r.concat(" ");
            
        }
        r= r.concat(")");
        
        
        return r;
    }
    
    
    
    
    
    
    
}
