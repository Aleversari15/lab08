package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String HOME = System.getProperty("home user");
    private static final String DEFAULT_FILE = "output.txt";
    private File f = new File(HOME + System.getProperty("file.separator") + DEFAULT_FILE);

    public  void setFile(final File newFile){
        this.f=newFile;
    }

    public  File getFile(){
        return this.f;
    }

    public String getPath(final File f){
        return f.getPath();
    }

    public void addStringToFile(final String s) throws IOException{
       try(PrintStream output = new PrintStream(f, StandardCharsets.UTF_8)){
            output.print(s);
       }
    } 

}
