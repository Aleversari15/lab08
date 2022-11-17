package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private String next;
    private List<String> history = new ArrayList<>();

    public SimpleController(){
        this.next=null;
    }

    @Override
    public void setNextString(String s) {
        this.next=s;
    }

    @Override
    public String getNextString() {
        return this.next;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void printString() {
        if(this.next == null ){
            throw new IllegalStateException("No String to print. Please set the next string to print");
        }
        System.out.println(this.next);
        history.add(this.next);
    }

}
