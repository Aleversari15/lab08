package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    /**
     * 
     * @param s string to set as next string 
     */
    void setNextString( String s);

    /**
     * 
     * @return the next string to print
     */
    String getNextString();

    /**
     * 
     * @return a List of the printed strings
     */
    List<String> getHistory();

    /**
     * prints the current string
     */
    void printString();

}
