/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Loader(Running r) {
        this.r = r;
        this.goals = new ArrayList<>();
        this.wm = new WorkingMemory();
        this.rules = new ArrayList<>();
        
        
    }

    public void Run() throws IOException {

        
        
        
        
        
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
                } else if (line.startsWith("GOAL")) {
                    String[] sv = line.split(":");
                    if(sv[1].contains(",")){
                        String[] filteredGoals = sv[1].split(",");
                        for (int i = 0; i < filteredGoals.length; i++) {
                            Tuple filteredGoal = new Tuple();
                            filteredGoal.loadFromString(filteredGoals[i]);
                            goals.add(filteredGoal);                            
                            
                        }                            
            
                    }else{
                    Tuple goal = new Tuple();
                    goal.loadFromString(sv[1]);
                    goals.add(goal);
                    }
                } 

            }
            b.close();
            r.setGoal(goals);
            r.setWm(wm);
            r.setRules(rules);

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo 'config.txt' não encontrado.");
            

        }
        
        

    }

}
