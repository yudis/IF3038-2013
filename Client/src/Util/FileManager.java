package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileManager {

    String rootPath = "./build/log/";

    public void createFile(String fileName) {
        //Create a new Path
        Path newDirectory = Paths.get(rootPath);
        Path newFile = Paths.get(rootPath + fileName);
        try {
            if (!Files.exists(newDirectory)) {
                Files.createDirectories(newDirectory);
            }
            Files.deleteIfExists(newFile);
            Files.createFile(newFile);
        } catch (IOException ex) {
            System.out.println("Error creating file");
        }
    }

    public void writeToFile(String fileName, String text) {
        Path path = Paths.get(rootPath + fileName);
        //Writing to file
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())) {
            writer.append(text);
            writer.flush();
        } catch (IOException exception) {
            System.out.println("Error writing to file");
        }
    }

    public void readFile(String fileName) {
        Path path = Paths.get(rootPath + fileName);

        //Reading from file
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String lineFromFile;
            System.out.println("The contents of file are: ");
            while ((lineFromFile = reader.readLine()) != null) {
                System.out.println(lineFromFile);
            }
        } catch (IOException exception) {
            System.out.println("Error while reading file");
        }
    }

    public HashMap<Integer, Log> parseFile(String fileName) {
        HashMap<Integer, Log> map = new HashMap();
        Path path = Paths.get(rootPath + fileName);

        //Reading from file
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                String[] component = lineFromFile.split("[\t]");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(component[0]);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                Log a = new Log(timestamp, Boolean.parseBoolean(component[2]));
                map.put(Integer.parseInt(component[1]), a);
            }

        } catch (IOException exception) {
            System.out.println("Error while reading file");
        } finally {
            return map;
        }
    }

    public void appendFile(String fileName, String timestamp, String idTask, String value) {
        // Path path = Paths.get(filePath);
        File path = new File(rootPath + fileName);
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(timestamp + "\t" + idTask + "\t" + value + "\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileManager fm = new FileManager();

        //fm.createFile("aku");

//        Timestamp t = new Timestamp(new java.util.Date().getTime());
//        fm.appendFile("aku", t.toString(), "1", "true");

        /*Cek Parsing*/
        HashMap<Integer, Log> a = fm.parseFile("aku");
        Iterator it = a.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
