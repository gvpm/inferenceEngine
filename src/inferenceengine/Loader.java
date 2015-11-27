
package inferenceengine;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author gvpm
 */
public class Loader {

    Running r;
    ArrayList<Tuple> goals;
    WorkingMemory wm;
    ArrayList<Rule> rules;
    int ruleCount;

    public Loader(Running r) {
        this.r = r;
        this.goals = new ArrayList<>();
        this.wm = new WorkingMemory();
        this.rules = new ArrayList<>();
        this.ruleCount = 1;

    }

    public void run() throws IOException {

        FileReader f;
        try {
            f = new FileReader("run.txt");
            BufferedReader b;
            b = new BufferedReader(f);
            boolean eof = false;

            while (!eof) {

                String line = b.readLine();
                if (line == null) {
                    eof = true;
                } else if (line.startsWith("GOAL")) {//Load goals from file
                    String[] sv = line.split(":");
                    if (sv[1].contains(",")) {
                        String[] filteredGoals = sv[1].split(",");
                        for (int i = 0; i < filteredGoals.length; i++) {
                            Tuple filteredGoal = new Tuple();
                            filteredGoal.loadFromString(filteredGoals[i]);
                            goals.add(filteredGoal);

                        }

                    } else {
                        Tuple goal = new Tuple();
                        goal.loadFromString(sv[1]);
                        goals.add(goal);
                    }
                } else if (line.startsWith("WM")) {//Load WM from file
                    String[] sv = line.split(":");
                    if (sv[1].contains(",")) {
                        String[] filteredWm = sv[1].split(",");
                        for (int i = 0; i < filteredWm.length; i++) {
                            Tuple filteredWmTuple = new Tuple();
                            filteredWmTuple.loadFromString(filteredWm[i]);
                            wm.addTuple(filteredWmTuple);

                        }

                    } else {
                        Tuple wmTuple = new Tuple();
                        wmTuple.loadFromString(sv[1]);
                        wm.addTuple(wmTuple);

                    }
                } else if (line.startsWith("RULE")) {//Load RULE from file
                    
                    
                    String[] sv = line.split(":");
                    String[] parts = sv[1].split("-");
                    //int i =2;
                    Rule r = new Rule(ruleCount);
                    ruleCount++;
                    
                    //for to read the tuples from IF ADD and DELETE
                    for (int i = 0; i < parts.length; i++) {
                        //System.out.println(parts.length);                        
                        
                        
                        if (parts[i].contains(",")) {
                            
                            String[] filteredRulePart = parts[i].split(",");
                            for (String tuple : filteredRulePart) {
                                Tuple filteredRulePartTuple = new Tuple();
                                filteredRulePartTuple.loadFromString(tuple);
                                if (i == 0) {//case where tuple is from if
                                    r.addIfTuple(filteredRulePartTuple);
                                }
                                if (i == 1) {//case where tuple is from add
                                    r.addAddTuple(filteredRulePartTuple);
                                }
                                if (i == 2) {//case where tuple is from delete
                                    r.addDeleteTuple(filteredRulePartTuple);
                                }

                            }

                        } else if (parts[i].compareTo("x") != 0) {
                            
                            Tuple ruleTuple = new Tuple();
                            ruleTuple.loadFromString(parts[i]);
                            if (i == 0) {//case where tuple is from if
                                r.addIfTuple(ruleTuple);
                            }
                            if (i == 1) {//case where tuple is from add
                                r.addAddTuple(ruleTuple);
                            }
                            if (i == 2) {//case where tuple is from delete
                                r.addDeleteTuple(ruleTuple);
                            }

                        }
                        
                        
                    }
                    //add the rule to rules list
                    rules.add(r);
                }

            }
            b.close();
            r.setGoal(goals);
            r.setWm(wm);
            r.setRules(rules);
            

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File'rule.txt' not found.");

        }

    }

}
