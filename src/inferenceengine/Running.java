package inferenceengine;

import java.util.ArrayList;

/**
 * Class Running
 * Main structures are inside
 * Where the algorithm lives and runs
 * @author gvpm
 */
public class Running {

    ArrayList<Tuple> goals;
    WorkingMemory wm;
    ArrayList<Rule> rules;

    /**
     * Constructor
     */
    public Running() {

    }

    /**
     * Constructor
     * @param goals Goals   
     * @param wm Working memory
     * @param rules Rules
     */
    public Running(ArrayList<Tuple> goals, WorkingMemory wm, ArrayList<Rule> rules) {
        this.goals = goals;
        this.wm = wm;
        this.rules = rules;
    }

    /**
     * Check if all the main goals were achieved
     * @return true = achieved, false= not achieved
     */
    public boolean checkFinalGoal() {
        boolean r = true;

        for (Tuple goal : this.goals) {
            if (wm.searchTuple(goal) == -1) {
                r = false;
            }
        }

        return r;
    }

    /**
     * Check if one single goal is in the WM
     * @param g goal to be checked
     * @return true = goal in the WM false= goal not in the WM
     */
    public boolean checkGoal(Tuple g) {
        boolean r = true;

        if (wm.searchTuple(g) == -1) {
            r = false;
        }

        return r;
    }

    /**
     * Runs the inference algorithm
     */
    public void run() {//Begining of the algorithm
        System.out.println("START: ");
        if (checkFinalGoal()) {
            System.out.println("\nGOAL IN THE WM");
        } else {
            int r = 1;
            for (Tuple goal : this.goals) {//Calls the backtracking function for each goal
                r = r * back(goal);//Accumulative result for the results of all the goals
            }
            if (r == 1) {//case when all the goals were achieved
                System.out.println("\nGOAL INFERED");
                System.out.println("Goal: "+this.goals);
                System.out.println("Final "+this.wm);
            } else {//case when one or more goals returned 0, could not be achieved
                System.out.println("\nGOAL UNREACHEBLE");
            }

        }

    }

    /**
     * Recursive function, where the backtrack lives.
     * @param subGoal goal to be proven
     * @return 1 = subgoal could be proven, 0 = rule could not be proven
     */
    public int back(Tuple subGoal) {
        ArrayList<Rule> providingRules = providingRules(subGoal);
        //case when goal is in the wm
        if (checkGoal(subGoal)) {
            System.out.println("Subgoal "+subGoal+" already in the WM");
            return 1;
        }
        //case when there is at least one rule  that provides the goal
        if (!providingRules.isEmpty()) {
            int r = 1;
            //will check all the rules that can provide the goal in order
            for (int j = 0; j < providingRules.size(); j++) {
                System.out.println("\nTry to apply RULE"+providingRules.get(j).getRuleNumber()+" to prove "+subGoal);
                //r is the accumulative return of the recursive call to all new subgoals
                for (int i = 0; i < providingRules.get(j).ifTuples.size(); i++) {
                    System.out.println("New Sobgoal:" +providingRules.get(j).getIfTuple(i)+" to satisfy RULE"+providingRules.get(j).getRuleNumber());
                    r = r * back(providingRules.get(j).getIfTuple(i));

                }//case when all the subgoals where met, nothing was 0
                if (r == 1) {
                    System.out.print("\nFIRED ");
                    providingRules.get(j).applyOn(wm);
                    return 1;
                }
                //set  r to 1 again for next rule to be checked
                r = 1;
            }
            //case when all the possible rules could not find the goal in the end
            System.out.println("Can't reach "+subGoal);
            return 0;

        }
        //case where no rules could provide goal
        System.out.println("No rules can provide "+subGoal);
        return 0;
    }

    /**
     * Sets the goal list
     * @param goals list of goals
     */
    public void setGoal(ArrayList<Tuple> goals) {
        this.goals = goals;
    }

    /**
     *  Sets the Working memory
     * @param wm list of tuples in the WM
     */
    public void setWm(WorkingMemory wm) {
        this.wm = wm;
    }

    /**
     * Sets the rules list  
     * @param rules list of rules
     */
    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    /**
     * Search for rules that can provide the given subgoal
     * @param subGoal goal to achieve
     * @return list of rules that can provide the given goal
     */
    public ArrayList<Rule> providingRules(Tuple subGoal) {
        ArrayList<Rule> r = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).providesGoal(subGoal)) {
                r.add(rules.get(i));
            }

        }

        return r;
    }
    
    @Override
    public String toString() {
        String r = "---------------------------------------------------------";
        r = r.concat("\nLOADED CONFIGURATION\n");
        r = r.concat("\nGoal: " + goals + "\n" + wm);
        r = r.concat("\n\nRules: \n");
        for (int i = 0; i < rules.size(); i++) {
            r = r.concat("\n"+rules.get(i).toString()+"\n");
            
        }
        r = r.concat("---------------------------------------------------------");
        return r;
    }

   

 

}
