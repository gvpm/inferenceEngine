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
public class Tuple {
    
    ArrayList<String> elements; 
    
   

    public Tuple() {
        elements = new ArrayList<>();
    }
    
    public Tuple(String s) {
        elements = new ArrayList<>();
    }
    
    public void addElement(String e){
        elements.add(e);
    }
    
    public void loadFromString(String s){
        //s= s.replaceAll("(", "");
        //s= s.replaceAll(")", "");
        //System.out.println(s);
        //System.out.println(s);
        String opened;
        opened = s.replace("(", "");
        opened = opened.replace(")", "");
        //System.out.println(opened);
        
        if(opened.contains(" ")){
             String[] sv = opened.split(" ");
             for (int i = 0; i < sv.length; i++) {
                 this.addElement(sv[i]);                
                 //System.out.println(sv[i]);
            }
            
        }else{
            this.addElement(opened);
        }
        
        
        
    }
    
    
   
    
    
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
    
    public String getElement(int i){
        
        return this.elements.get(i);
    }
    
    public String removeElement(int i){
        return this.elements.remove(i);
        
    }    
    
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
