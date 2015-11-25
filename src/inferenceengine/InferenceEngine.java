
package inferenceengine;

import java.io.IOException;

/**
 *
 * @author gvpm
 */
public class InferenceEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Running r = new Running();
        Loader l = new Loader (r);
        l.Run();
        
        System.out.println(r.toString());
        System.out.println("");
        
        Rule r1 = new Rule(1);
        Tuple t1 = new Tuple();
        Tuple t2 = new Tuple();
        Tuple t3 = new Tuple();
        WorkingMemory wm = new WorkingMemory();
        
        t1.addElement("a");
        t1.addElement("b");
        t2.addElement("c");
        t2.addElement("d");
        t3.addElement("e");
        
        r1.addIfTuple(t1);
        r1.addAddTuple(t2);
        r1.addDeleteTuple(t3);
        
        wm.addTuple(t1);
        
        System.out.println(wm);
        
        
        System.out.println(r1.isApplicableOn(wm));
        System.out.println(r1);
        
        System.out.println("");
        
        r1.applyOn(wm);
        
        System.out.println(wm);
        
    }
    
}
