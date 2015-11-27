
package inferenceengine;

import java.io.IOException;

/**
 *
 * @author gvpm
 */
public class InferenceEngine {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Running r = new Running();
        Loader l = new Loader (r);
        l.Run();
        System.out.println("Goals: "+r.goals);
        System.out.println(r.wm.toString());
        r.firstBack();
       
        System.out.println(r.wm.toString());
        System.out.println("");
        //System.out.println(r.toString());
        //System.out.println("");
        
        /*
        r.rules.get(0).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        
        r.rules.get(5).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        r.rules.get(2).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        r.rules.get(4).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        r.rules.get(1).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        r.rules.get(3).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        r.rules.get(0).applyOn(r.wm);
        System.out.println(r.wm.toString());
        System.out.println(r.checkGoal());
        
        
        
        
       */ 
        
    }
    
}
