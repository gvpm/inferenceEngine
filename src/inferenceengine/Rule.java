
package inferenceengine;

import java.util.ArrayList;

/**
 * Structure that represents a Rule
 * Contains a list of If tuples, Add tuples and Delete tuples
 * @author gvpm
 */
public class Rule {
    
    ArrayList<Tuple> ifTuples;
    ArrayList<Tuple> addTuples;
    ArrayList<Tuple> deleteTuples;
    int ruleNumber;

    /**
     * Constructor
     * @param n number of the rule
     */
    public Rule(int n) {
        this.ifTuples = new ArrayList<>();
        this.addTuples = new ArrayList<>();
        this.deleteTuples = new ArrayList<>();
        this.ruleNumber=n;
        
    }

    /**
     * Returns the rule number
     * @return rule number
     */
    public int getRuleNumber() {
        return ruleNumber;
    }
    
    /**
     * Checks if rule is applicable in a given working memory
     * @param wm working memory to check
     * @return true= applicable false= not applicable
     */
    public boolean isApplicableOn(WorkingMemory wm){
        
        for (int i = 0; i < getIfTuplesSize(); i++) {
            if(wm.searchTuple(getIfTuple(i))==-1)
                
                return false;            
        }
        
        return true;        
    }
    
   

    /**
     *Check if the rule can provide the goal
     * @param g tuple
     * @return true if provides, false if not
     */
        public boolean providesGoal(Tuple g){
        
        for (int i = 0; i < addTuples.size(); i++) {
            if(this.getAddTuple(i).compareTuple(g)){
                return true;
            }
            
        }
        
        return false;
    }
    
    /**
     * Apply the rule to a given working memory 
     * @param wm working memory to apply on
     */
    public void applyOn(WorkingMemory wm){
        
        if(this.isApplicableOn(wm)){
        System.out.println(this.toString());
        
        
        for (int i = 0; i < this.getDeleteTuplesSize(); i++) {
            int toRemove = wm.searchTuple(this.getDeleteTuple(i));
            if(toRemove!=-1){
                wm.removeTuple(toRemove);
            }
            
        }
        
        for (int i = 0; i < this.getAddTuplesSize(); i++) {
           if(wm.searchTuple(this.getAddTuple(i))==-1){
               wm.addTuple(this.getAddTuple(i));
           }
            
        }
        System.out.println("Updated "+wm.toString()+"\n");
        }else{
            System.out.println("RULE"+ruleNumber+ " Failed");
        }      
    }
    
    /**
     * Adds a If tuple
     * @param t Tuple to add
     */
    public void addIfTuple(Tuple t){
        this.ifTuples.add(t);
        
    }

    /**
     *  Adds ADD tuple
     * @param t Tuple to add
     */
    public void addAddTuple(Tuple t){
        this.addTuples.add(t);
        
    }

    /**
     * Adds Delete tuple
     * @param t Tuple to add
     */
    public void addDeleteTuple(Tuple t){
        this.deleteTuples.add(t);
        
    }
     
    /**
     * Returns the size of the IF tuples list
     * @return size
     */
    public int getIfTuplesSize(){
        
        return this.ifTuples.size();
    } 

    /**
     * Returns the size of the Add tuples list
     * @return size
     */
    public int getAddTuplesSize(){
        
        return this.addTuples.size();
    } 

    /**
     * Returns the size of the Delete tuples list
     * @return size
     */
    public int getDeleteTuplesSize(){
        
        return this.deleteTuples.size();
    } 
    
    /**
     * Returns one if tuple from the list
     * @param i index of the tuple
     * @return the tuple
     */
    public Tuple getIfTuple(int i){
        return this.ifTuples.get(i);
    }

   /**
     * Returns one Add tuple from the list
     * @param i index of the tuple
     * @return the tuple
     */
    public Tuple getAddTuple(int i){
        return this.addTuples.get(i);
    }

    /**
     * Returns one Delete tuple from the list
     * @param i index of the tuple
     * @return the tuple
     */
    public Tuple getDeleteTuple(int i){
        return this.deleteTuples.get(i);
    }
    
    @Override
    public String toString() {
        return "RULE"+ruleNumber+": \n"+ "IF: " + ifTuples + "\nTHEN: " + 
                "Add: " + addTuples+ " Delete: " +deleteTuples;
    }
    
    
    
            
    
    
}
