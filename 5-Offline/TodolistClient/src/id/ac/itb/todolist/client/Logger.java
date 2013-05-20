package id.ac.itb.todolist.client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import id.ac.itb.todolist.client.Controller;
import id.ac.itb.todolist.model.UpdateStatus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author kevin
 */
public class Logger {
    
    public Logger(){
        
    }
    
    public void log(HashMap<Integer, UpdateStatus> logUpdate) throws IOException { 
            try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"), true)) {
                    Iterator<Map.Entry<Integer, UpdateStatus>> it = logUpdate.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Integer, UpdateStatus> pairs = it.next();
                        int flagBool = 0;
                        String time = Long.toString(pairs.getValue().getTimestamp().getTime());
                        out.write(Integer.toString(pairs.getValue().getIdTugas()));
                        out.write(" ");
                        if (pairs.getValue().isStatus())
                        {
                            flagBool = 1;
                        }
                        out.write(Integer.toString(flagBool));
                        out.write(" ");
                        out.write(time);
                        out.write("\n");
                    }
            }
    }
}
