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

    public boolean checkFinalGoal() {
        boolean r = true;

        for (Tuple goal : this.goals) {
            if (wm.searchTuple(goal) == -1) {
                r = false;
            }
        }

        return r;
    }

    public boolean checkGoal(Tuple g) {
        boolean r = true;

        if (wm.searchTuple(g) == -1) {
            r = false;
        }

        return r;
    }

    public void firstBack() {
        if (checkFinalGoal()) {
            System.out.println("GOAL IN THE WM");
        } else {
            int r = 1;
            for (int i = 0; i < this.goals.size(); i++) {
                r = r * back(this.goals.get(i));

            }
            if (r == 1) {
                System.out.println("GOAL INFERED");
            } else {
                System.out.println("GOAL UNREACHEBLE");
            }

        }

    }

    public int back(Tuple subGoal) {
        ArrayList<Rule> providingRules = providingRules(subGoal);
        //case when goal is in the wm
        if (checkGoal(subGoal)) {
            return 1;
        }
        //case when there is at least one rule  that provides the goal
        if (!providingRules.isEmpty()) {
            int r = 1;
            //will check all the rules that can provide the goal in order
            for (int j = 0; j < providingRules.size(); j++) {
                //r is the accumulative return of the recursive call to all new subgoals
                for (int i = 0; i < providingRules.get(j).ifTuples.size(); i++) {
                    r = r * back(providingRules.get(j).getIfTuple(i));

                }//case when all the subgoals where met, nothing was 0
                if (r == 1) {
                    providingRules.get(j).applyOn(wm);
                    return 1;
                }
                //set  r to 1 againfor next rule to be checked
                r=1;
            }
            //case when all the possible rules could not find the goal in the end
            return 0;

        }
        //case where no rules could provide goal
        return 0;
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

    private ArrayList<Rule> providingRules(Tuple subGoal) {
        ArrayList<Rule> r = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).providesGoal(subGoal)) {
                r.add(rules.get(i));
            }

        }

        return r;
    }

}
