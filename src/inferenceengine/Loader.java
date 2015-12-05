
package inferenceengine;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Class loader
 * Loads a .txt file into the main structure
 * @author gvpm
 */
public class Loader {

    Running r;
    ArrayList<Tuple> goals;
    WorkingMemory wm;
    ArrayList<Rule> rules;
    int ruleCount;

    /**
     * Constructor
     * @param r Structure to load into
     */
    public Loader(Running r) {
        this.r = r;
        this.goals = new ArrayList<>();
        this.wm = new WorkingMemory();
        this.rules = new ArrayList<>();
        this.ruleCount = 1;

    }

    /**
     * Runs the loader
     * @throws IOException
     */
    public void run() throws IOException {

        FileReader f;
        try {
            //File to read from
            f = new FileReader("run.txt");
            BufferedReader b;
            b = new BufferedReader(f);
            boolean eof = false;

            while (!eof) {
                //Reads the next line
                String line = b.readLine();
                if (line == null) {//Case where line is empty, end of file
                    eof = true;
                } else if (line.startsWith("GOAL")) {//Case when line is the Goal, Load goals from file
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
                } else if (line.startsWith("WM")) {// Case when line is WM, Load WM from file
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
                } else if (line.startsWith("RULE")) {//case when line is a rule, Load RULE from file
                    
                    
                    String[] sv = line.split(":");
                    String[] parts = sv[1].split("-");
                    
                    Rule r = new Rule(ruleCount);
                    ruleCount++;
                    
                    //for to read the tuples from IF ADD and DELETE
                    for (int i = 0; i < parts.length; i++) {                                               
                        
                        
                        if (parts[i].contains(",")) {//Case when its more than one tuple
                            
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

                        } else if (parts[i].compareTo("x") != 0) {//Case when its only one tuple
                            
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
            //After all is loaded from file, loads in the structure
            b.close();
            r.setGoal(goals);
            r.setWm(wm);
            r.setRules(rules);
            

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File'rule.txt' not found.");

        }

    }

}
