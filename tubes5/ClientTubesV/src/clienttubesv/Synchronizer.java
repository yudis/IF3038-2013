/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttubesv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chritianto Handojo
 */
public class Synchronizer implements Runnable, Serializable{
    private String username;
    private boolean needed;
    
    Synchronizer(String s) {
        username = s;
        needed = true;
    }
    
    @Override
    public void run() {
        while (true) {
            while (!needed) ;
            BufferedReader baca = null;
            try {
                baca = new BufferedReader(new FileReader("log_"+username+".txt"));
            } catch(IOException ex) {
                System.err.println("Failed to open log file");
            }
            
            needed = false;
        }
    }
    
    public void process() {
        needed = true;
    }
    
    public boolean busy() {
        return needed;
    }
}
