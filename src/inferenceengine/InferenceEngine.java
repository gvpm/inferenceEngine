package inferenceengine;

import java.io.IOException;

public class InferenceEngine {

    /**
     * Main 
     * 
     * 
     * @param args
     * @throws java.io.IOException Problem in reading the file
     */
    public static void main(String[] args) throws IOException {

        //Creates the main structure
        Running r = new Running();
        //Loads the .txt file into the main structure
        Loader l = new Loader(r);
        //Runs the loader
        l.run();

        System.out.println(r);

        r.run();

    }

}
