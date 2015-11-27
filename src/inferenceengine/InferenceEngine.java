
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
        
        
        //Creates the main structure
        Running r = new Running();
        //Loads the .txt file into the main structure
        Loader l = new Loader (r);
        //Runs the loader
        l.run();
        //System.out.println("Goal: "+r.goals);
        //System.out.println(r.wm.toString());
        System.out.println(r);
        
        r.run();
       
       
        
        
    }
    
}
