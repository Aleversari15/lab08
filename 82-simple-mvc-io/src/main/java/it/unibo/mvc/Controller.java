package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private String next;
    private String current;
    List<String> history = new ArrayList<>();

    public Controller(final String current) {
        this.current=current;
    }

    //a method for setting the next string to print. Null values 
    //are not acceptable and an exception should be produced
    public void nextStringSet(String next){
        try{
            this.current=this.next;
            this.next=next;
        }
        catch(IllegalArgumentException e){
            System.out.println("Null value are not accemptable");
        }
    }

    //a method for getting the next string to print
    public String nextStringGet(){
        return this.next;
    }

    //a method for getting the history of the printed strings (in form of a List of Strings)
    public void getHistory(final List<String> history){
        for(final String string: history){
            System.out.println(string);
        } 
    }

    //a method that prints the current string. if the current string is unset, an illegalstateexception should be trown
    public void currentStringPrint() throws IllegalStateException{
        System.out.println(this.current);
    }
}
