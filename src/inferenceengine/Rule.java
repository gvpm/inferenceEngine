/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inferenceengine;

import java.util.ArrayList;

/**
 *
 * @author gvpm
 */
public class Rule {
    
    ArrayList<Tuple> ifTuples;
    ArrayList<Tuple> addTuples;
    ArrayList<Tuple> deleteTuples;
    int ruleNumber;

    public Rule(int n) {
        this.ifTuples = new ArrayList<>();
        this.addTuples = new ArrayList<>();
        this.deleteTuples = new ArrayList<>();
        this.ruleNumber=n;
        
    }
    
    public boolean isApplicableOn(WorkingMemory wm){
        
        for (int i = 0; i < getIfTuplesSize(); i++) {
            if(wm.searchTuple(getIfTuple(i))==-1)
                return false;            
        }
        
        return true;        
    }
    
    public void applyOn(WorkingMemory wm){
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
               
    }
    
    public void addIfTuple(Tuple t){
        this.ifTuples.add(t);
        
    }
    public void addAddTuple(Tuple t){
        this.addTuples.add(t);
        
    }
    public void addDeleteTuple(Tuple t){
        this.deleteTuples.add(t);
        
    }
     
    public int getIfTuplesSize(){
        
        return this.ifTuples.size();
    } 
    public int getAddTuplesSize(){
        
        return this.addTuples.size();
    } 
    public int getDeleteTuplesSize(){
        
        return this.deleteTuples.size();
    } 
    
    public Tuple getIfTuple(int i){
        return this.ifTuples.get(i);
    }
    public Tuple getAddTuple(int i){
        return this.addTuples.get(i);
    }
    public Tuple getDeleteTuple(int i){
        return this.deleteTuples.get(i);
    }
    
    @Override
    public String toString() {
        return "RULE"+ruleNumber+": \n"+ "IF: " + ifTuples + "\nTHEN: \n" + 
                "Add: " + addTuples+ "\nDelete: " +deleteTuples;
    }
    
    
    
            
    
    
}