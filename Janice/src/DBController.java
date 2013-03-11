package SPACE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Class that regulates all actions concerning databases.
 * @author Jeremy Joseph Hanniel | 13510026
 */
public class DBController {
    /**
     * A HashMap that maps students IDs to their respective file which contains their data.
     */
    public static LinkedHashMap<String, String> studentDBFiles;
    
    /**
     * A HashMap that maps teacher IDs to their respective file which contains their data.
     */
    public static LinkedHashMap<String, String> teacherDBFiles;
    
    /**
     * A HashMap that maps lessons IDs to their respective file which contains their data.
     */
    public static LinkedHashMap<String, String> lessonDBFiles;
    
    /**
     * A database containing all students' data.
     */
    public static ArrayList<Student> studentDB = new ArrayList<>();
    /**
     * A database containing all teachers' data.
     */
    public static ArrayList<Teacher> teacherDB = new ArrayList<>();
    /**
     * A database containing all lessons' data.
     */
    public static ArrayList<Lesson> lessonDB = new ArrayList<>();
    
    /**
     * Default constructor for initialization. Clears all data-to-file-reference HashMaps and then populate them, and also clears all databases.
     */
    public DBController() {
//        studentDBFiles.clear();
//        teacherDBFiles.clear();
//        lessonDBFiles.clear();
//        
//        studentDB.clear();
//        teacherDB.clear();
//        lessonDB.clear();
        
        for (int i = 0; i < 3; i++) {
            ParseDatabaseDir(i);
            PopulateDatabase(i);
        }
    }
    
    /**
     * Parses the text file which contains filenames that constructs a specific database.
     * @param dbType type of database : 0 for student database, 1 for teacher, and 2 for lesson
     */
    private void ParseDatabaseDir (int dbType) {
        File file = null;
        switch (dbType) {
            case 0: {
                file = new File("Database\\StudentsDB.txt");
                studentDBFiles = new LinkedHashMap<>();
                break;
            }
            case 1 : {
                file = new File("Database\\TeachersDB.txt");
                teacherDBFiles = new LinkedHashMap<>();
                break;
            }
            case 2 : {
                file = new File("Database\\LessonsDB.txt");
                lessonDBFiles = new LinkedHashMap<>();
                break;
            }
            default : {
                // TO DO : Error
                System.err.println("dbType must range from 0 to 2!");
                break;
            }
        }
        
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (!file.setWritable(false)) {
                    System.err.println("Could not set unwritable file " + file.toString() + ".");
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        } else {
            try (BufferedReader dbReader = new BufferedReader(new FileReader(file))) {
                String curLine = null;
                String[] splittedLine = null;
                while(dbReader.ready()) {
                    curLine = dbReader.readLine();
                    splittedLine = curLine.split(" ");

                    switch (dbType) {
                        case 0: {
                            studentDBFiles.put(splittedLine[0], splittedLine[1]);
                            break;
                        }
                        case 1 : {
                            teacherDBFiles.put(splittedLine[0], splittedLine[1]);
                            break;
                        }
                        case 2 : {
                            lessonDBFiles.put(splittedLine[0], splittedLine[1]);
                            break;
                        }
                    }
                }

                dbReader.close();
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
        }
    }
    
    /**
     * Populate a specific database by reading and parsing necessary data from all files in its corresponding directory.
     * @param dbType database to populate. 0 is for studentDB, 1 is for teacherDB, and 2 is for lessonDB.
     */
    private void PopulateDatabase (int dbType) {
        LinkedHashMap<String, String> databaseDir = null;
        
        if (dbType == 0) {
            databaseDir = new LinkedHashMap(studentDBFiles);
        } else if (dbType == 1) {
            databaseDir = new LinkedHashMap(teacherDBFiles);
        } else if (dbType == 2) {
            databaseDir = new LinkedHashMap(lessonDBFiles);
        }
        
        String curFilename = null;
        File curFile = null;
        Iterator<String> it = databaseDir.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            try {
                curFilename = it.next();
                curFile = new File(curFilename);
                if (dbType == 0) {
                    InsertIntoDatabase(ParseStudentFiles(curFile));
                } else if (dbType == 1) {
                    InsertIntoDatabase(ParseTeacherFiles(curFile));
                } else if (dbType == 2) {
                    InsertIntoDatabase(ParseLessonFiles(curFile));
                }
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }
        }
    }
    
    /**
     * Parse basic student data from a student file.
     * @param file the file where student's data is contained
     * @return an instance of Student class to add to studentDB
     * @throws FileNotFoundException 
     * @see DBController#studentDB
     */
    private Student ParseStudentFiles (File file) throws FileNotFoundException {
        try (BufferedReader dbReader = new BufferedReader(new FileReader(file))) {
            String ID = dbReader.readLine();
            String name = dbReader.readLine();
            String cellNo = dbReader.readLine();
            String subjects = dbReader.readLine();
            String schName = dbReader.readLine();
            int schGrade = Integer.parseInt(dbReader.readLine());
            int cPackage = Integer.parseInt(dbReader.readLine());
            int totLessons = Integer.parseInt(dbReader.readLine());
            
            dbReader.close();
            
            return new Student(ID, name, cellNo, subjects, schName, schGrade, cPackage, totLessons);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    /**
     * Parse basic teacher data from a teacher file.
     * @param file the file where teacher's data is contained
     * @return an instance of Teacher class to add to teacherDB
     * @throws FileNotFoundException 
     * @see DBController#teacherDB
     */
    private Teacher ParseTeacherFiles (File file) throws FileNotFoundException {
        try (BufferedReader dbReader = new BufferedReader(new FileReader(file))) {
            String ID = dbReader.readLine();
            String name = dbReader.readLine();
            String cellNo = dbReader.readLine();
            String subjects = dbReader.readLine();
            
            dbReader.close();
            
            return new Teacher(ID, name, cellNo, subjects);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    /**
     * Parse a lesson data from a lesson file.
     * @param file the file where lesson's data is contained
     * @return an instance of Lesson class to add to lessonDB
     * @throws FileNotFoundException 
     * @see DBController#lessonDB
     */
    private Lesson ParseLessonFiles (File file) throws FileNotFoundException {
        try (BufferedReader dbReader = new BufferedReader(new FileReader(file))) {
            String ID = dbReader.readLine();
            String time = dbReader.readLine();
            String subjects = dbReader.readLine();
            String teachID = dbReader.readLine();
            
            String[] stuIDs = dbReader.readLine().split(",");
            ArrayList<String> tempStuIDs = new ArrayList<>();
            tempStuIDs.addAll(Arrays.asList(stuIDs));
            
            dbReader.close();
            
            return new Lesson(ID, time, subjects, tempStuIDs, teachID);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    /**
     * Generates a unique ID for new student, teacher, or lesson by looking up to ID reference file.
     * @param dataType 0 for student, 1 for teacher, 2 for lesson
     * @return the unique ID
     */
    public static String GenerateID (int dataType) {
        File file = new File("Database\\IDReference.txt");
        char prefix = 'a';
        int idLength = 0;
        if (dataType == 0) {
            prefix = 'M';
            idLength = 3;
        } else if (dataType == 1) {
            prefix = 'P';
            idLength = 3;
        } else if (dataType == 2) {
            prefix = 'L';
            idLength = 5;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            String line = null;
            String newID = null;
            boolean stop = false;
            int curID = -1;
            
            while (br.ready() && !stop) {
                line = br.readLine();
                if (line.charAt(0) == prefix) {
                    stop = true;
                    br.close();
                    curID = Integer.parseInt(line.substring(1));
                    curID++;
                    
                    newID = Integer.toString(curID);
                    while (newID.length() < idLength) {
                        newID = "0" + newID;
                    }
                    newID = prefix + newID;
                    
                    DBController.UpdateReferenceFile(line, newID);
                    
                    return newID;
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    /**
     * Inserts a student, teacher, or lesson data into their respective database.
     * @param <E> either a Student, Teacher, or Lesson object
     * @param newData the data to be added to a corresponding database type
     */
    public static <E> void InsertIntoDatabase (E newData) {
        if (newData instanceof Student) {
            studentDB.add((Student)newData);
        } else if (newData instanceof Teacher) {
            teacherDB.add((Teacher)newData);
        } else if (newData instanceof Lesson) {
            lessonDB.add((Lesson)newData);
        }
    }
    
     /**
      * Replaces the oldFile with the newFile.
      * @param oldFile the file to be replaced
      * @param newFile the file to replace
      */
    public static void ReplaceFile (File oldFile, File newFile) {
        if (oldFile.exists()) {
            if (!oldFile.delete()) {
                System.err.println("Could not delete file " + oldFile.toString() + ".");
                return;
            }
        }
        
        if (!newFile.setWritable(false)) {
            System.err.println("Could not set unwritable file " + newFile.toString() + ".");
        }
        
        if (!newFile.renameTo(oldFile)) {
            System.err.println("Could not rename file " + newFile.toString() + ".");
        }
    }
    
    /**
     * Updates the ID reference file so that it contains the latest IDs in existence.
     * @param old the old ID to be replaced
     * @param replacement the new ID to be written
     */
    public static void UpdateReferenceFile (String old, String replacement) {
        File refFile = new File("Database\\IDReference.txt");
        File tempFile = new File(refFile.getAbsolutePath() + ".tmp");
        try (BufferedReader br = new BufferedReader(new FileReader(refFile)); PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

            String line = null;

            while ((line = br.readLine()) != null) {
                if (!line.contains(old)) {
                    pw.println(line);
                } else {
                    pw.println(replacement);
                }
                pw.flush();
            }
            pw.close();
            br.close();

            ReplaceFile(refFile, tempFile);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
    
    /**
     * Remove a link of a student, teacher, or a lesson with their corresponding file by deleting the filemapping in their respective DBFile.
     * @param dbType 0 for student DBFile, 1 for teacher, 2 for lesson
     * @param stringToRemove the ID of a student, teacher, or lesson whose files is to be removed
     * @see DBController#studentDBFiles
     * @see DBController#teacherDBFiles
     * @see DBController#lessonDBFiles
     */
    public static void RemoveFileLink (int dbType, String stringToRemove) {
        File dbFile = null;
        switch (dbType) {
            case 0: {
                dbFile = new File("Database\\StudentsDB.txt");
                break;
            }
            case 1 : {
                dbFile = new File("Database\\TeachersDB.txt");
                break;
            }
            case 2 : {
                dbFile = new File("Database\\LessonsDB.txt");
                break;
            }
        }
        
        File tempFile = new File(dbFile.getAbsolutePath() + ".tmp");
        try (BufferedReader br = new BufferedReader(new FileReader(dbFile)); PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

            String line = null;

            while ((line = br.readLine()) != null) {
                if (!line.contains(stringToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            ReplaceFile(dbFile, tempFile);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
    
    /**
     * Parses schedule from a student or teacher file to a matrix.
     * @param file a student or teacher file
     * @return matrix of integer representing the student or teacher's schedule 
     * @see Participants#schedule
     */
    public static int[][] ParseSchedule (File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            ArrayList<String> temp = new ArrayList<>();
            int[][] schedule = new int[8][7];
            
            while (!(line = br.readLine()).equals("<J>"));
            while (!(line = br.readLine()).equals("</J>")) {
                temp.add(line);
            }
            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 7; j++) {
                    schedule[i][j] = Character.digit(temp.get(i).charAt(j), 10);
                }
            }
            
            br.close();
            
            return schedule;
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    
    
    /**
     * Parses lesson history of a student or teacher from his/her file.
     * @param file the student or teacher's file
     * @return an ArrayList containing lesson IDs that the student or teacher has been scheduled to
     */
    public static ArrayList<String> ParseLessonHistory (File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            ArrayList<String> temp = new ArrayList<>();
            
            while (!(line = br.readLine()).equals("<HL>"));
            while (!(line = br.readLine()).equals("</HL>")) {
                temp.add(line);
            }
            br.close();
            
            return temp;
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    /**
     * Parses subjects from code in database to their names, used to display subjects to user.
     * @param subjects string that represents subjects
     * @return subjects in subjects names
     * @see Participants#subjects
     */
    public static String DisplaySubjects (String subjects) {
        String result = "";
        char[] temp = subjects.toCharArray();
        
        if (temp[0] == '1') {
            result += "Matematika ";
        }
        if (temp[1] == '1') {
            result += "Fisika ";
        }
        if (temp[2] == '1') {
            result += "Kimia ";
        }
        if (temp[3] == '1') {
            result += "Akuntansi ";
        }
        
        String[] tempResult = result.trim().split(" ");
        result = "";
        int i = 0;
        while (i < tempResult.length)  {
            result += tempResult[i];
            i++;
            if (i < tempResult.length) {
                result += ", ";
            }
        }
        
        return result;
    }
    
    /**
     * Search for an instance of student, teacher, or lesson in their corresponding database.
     * @param dataType 0 for student, 1 for teacher, 2 for lesson
     * @param ID a student, teacher, or lesson's ID whose instance's index is  to be searched
     * @return the index where the student, teacher, or lesson with specified ID is stored in their corresponding database
     */
    public static int SearchForIndexID (int dataType, String ID) {
        if (dataType == 0) {
            for (int i = 0; i < DBController.studentDB.size(); i++) {
                if (ID.equals(DBController.studentDB.get(i).getID())) {
                    return i;
                }
            }
        } else if (dataType == 1) {
            for (int i = 0; i < DBController.teacherDB.size(); i++) {
                if (ID.equals(DBController.teacherDB.get(i).getID())) {
                    return i;
                }
            }
        } else if (dataType == 2) {
            for (int i = 0; i < DBController.lessonDB.size(); i++) {
                if (ID.equals(DBController.lessonDB.get(i).getLessonID())) {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Matches students' schedules with teacher's to create a lesson schedule recommendation.
     * @param studentIDs an ArrayList containing all students' ID who participate
     * @param teacherID the ID for the participating teacher
     * @return a new schedule recommendation
     */
    public int[][] MatchSchedule (ArrayList<String> studentIDs, String teacherID) {
        int[][] finalSchedule = new int[8][7];
        ArrayList<int[][]> studentsSchedule = new ArrayList<>();
        for (String stuID : studentIDs) {
            studentsSchedule.add(studentDB.get(SearchForIndexID(0, stuID)).getSchedule());
        }
        int[][] teacherSchedule = teacherDB.get(SearchForIndexID(1, teacherID)).getSchedule();
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                finalSchedule[i][j] = teacherSchedule[i][j];
            }
        }
        
        for (int k = 0; k < studentsSchedule.size(); k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 7; j++) {
                    finalSchedule[i][j] *= studentsSchedule.get(k)[i][j];
                }
            }
        }
        
        return finalSchedule;
    }
    
    public static String ParseSchoolGrade (int grade) {
        if (grade == 0) {
            return "SD";
        } else if (grade == 1) {
            return "SMP";
        } else if (grade == 2) {
            return "1 SMA";
        } else if (grade == 3) {
            return "2 SMA";
        } else if (grade == 4) {
            return "3 SMA";
        } else {
            return "";
        }
    }
}
