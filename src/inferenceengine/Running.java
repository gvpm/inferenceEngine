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
public class Running {
    
    
    ArrayList<Tuple> goals;
    WorkingMemory wm;
    ArrayList<Rule> rules;

    public Running() {
        
    }
    
    

    public Running(ArrayList<Tuple> goals, WorkingMemory wm, ArrayList<Rule> rules) {
        this.goals = goals;
        this.wm = wm;
        this.rules = rules;
    }
    
    public boolean checkGoal(){
        boolean r = true;
        
        for (Tuple goal : this.goals) {
            if (wm.searchTuple(goal) == -1) {
                r = false;
            }
        }
      
        
        return r;
    }

    public void setGoal(ArrayList<Tuple> goals) {
        this.goals = goals;
    }

    public void setWm(WorkingMemory wm) {
        this.wm = wm;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Running{" + "goals=" + goals + ", wm=" + wm + ", rules=" + rules + '}';
    }

   
    
    
    
    
    
    
    
    
}
