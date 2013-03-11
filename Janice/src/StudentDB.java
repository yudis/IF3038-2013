package SPACE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class that regulates all operations regarding student database, which is studentDB.
 * @author Jeremy Joseph Hanniel | 13510026
 * @see MainController#studentDB
 */
public class StudentDB implements DBOperations<Student> {
    public static ArrayList<String> allSchNames;
    /**
     * Searches for the file of a student with specific ID.
     * @param ID the ID of the student whose file is to be searched
     * @return the file containing data of the student
     */
    @Override
    public File SearchForFile (String ID) {
        return new File(DBController.studentDBFiles.get(ID));
    }

    /**
     * Registers a new student into studentDB. Also create a new physical file to store the student data in disk.
     * @param newData an instance of Student class, which is the new student
     * @see MainController#studentDB
     */
    @Override
    public void Register(Student newData) {
        DBController.InsertIntoDatabase(newData);
        File newStudentFile = new File("Student\\" + newData.getID() + ".txt");
        UpdateData(newStudentFile, newData);
    }

    /**
     * Unregisters a student from studentDB and lessonDB, and also remove his/her physical file.
     * @param data the student data to remove
     */
    @Override
    public void Unregister (Student data) {
        DBController.studentDB.remove(DBController.SearchForIndexID(0, data.getID()));
        LessonDB lesDB = new LessonDB();
        
        int idx = -1;
        ArrayList<Lesson> temp = LessonDB.GetLessonHistoryTables(data.getLessonHistory());
        for (Lesson lesson : temp) {
            int i = 0;
            while (i < lesson.getStudentsID().size() && lesson.getStudentsID().get(i).equals(data.getID())) {
                i++;
            }
            idx = i;
            lesson.getStudentsID().remove(idx);
            LessonDB.UpdateData(lesDB.SearchForFile(lesson.getLessonID()), lesson);
        }
        
        DBController.studentDBFiles.remove(data.getID());
        DBController.RemoveFileLink(0, data.getID());
        File file = SearchForFile(data.getID());
        file.delete();
    }

    /**
     * Updates basic data of a student in studentDB and his/her physical file.
     * @param file file to write the new student data into
     * @param newData an instance of Student class, which is the student whose data is to update
     * @see MainController#studentDB
     */
    public static void UpdateData(File file, Student newData) {
        int idx = DBController.SearchForIndexID(0, newData.getID());
        DBController.studentDB.remove(idx);
        DBController.studentDB.add(idx, newData);
        
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        
         try (PrintWriter pw = new PrintWriter(new FileWriter(tempFile.getAbsoluteFile()))) {
            pw.println(newData.getID());
            pw.println(newData.getName());
            pw.println(newData.getCellNo());
            pw.println(newData.getSubjects());
            pw.println(newData.getSchoolName());
            pw.println(newData.getSchoolGrade());
            pw.println(newData.getMaxLessons());
            pw.println(newData.getTotalLessons());
            
            pw.println();
            
            if (!file.exists()) {
                pw.println("<J>");
                pw.println("</J>");
                pw.println();
                pw.println("<HL>");
                pw.println("</HL>");
                pw.println();
                pw.println("<N>");
                pw.println("</N>");
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                    String line = null;
                    while (!(line = br.readLine()).equals(""));
                    while ((line = br.readLine()) != null) {
                        pw.println(line);
                    }

                    br.close();
                } catch (IOException ioe) {
                    System.err.println(ioe);
                }
            }
            
            pw.close();
            
            DBController.ReplaceFile(file, tempFile);
         } catch (IOException ioe) {
             System.err.println(ioe); 
         }
    }

    public ArrayList<TestResult> ParseTestResult (File studentFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(studentFile))) {
            String line = null;
            String[] splittedLine = null;
            ArrayList<TestResult> temp = new ArrayList<>();
            
            while (!(line = br.readLine()).equals("<N>"));
            while (!(line = br.readLine()).equals("</N>")) {
                splittedLine = line.split(" ");
                temp.add(new TestResult(splittedLine[0], splittedLine[1], Float.parseFloat(splittedLine[2])));
            }
            
            br.close();
            
            return temp;
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        
        return null;
    }
    
    public ArrayList<Student> SearchForInstances (Student searchCrit) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student stu : DBController.studentDB) {
            if (stu.getName().contains(searchCrit.getName()) && stu.getCellNo().contains(searchCrit.getCellNo()) && stu.getSubjects().equals(searchCrit.getSubjects()) && stu.getSchoolName().contains(searchCrit.getSchoolName())) {
                if (searchCrit.getSchoolGrade() != -1) {
                    if (searchCrit.getMaxLessons() != 0) {
                        if (stu.getSchoolGrade() == searchCrit.getSchoolGrade() && stu.getMaxLessons() == searchCrit.getMaxLessons()) {
                            result.add(stu);
                        }
                    } else {
                        if (stu.getSchoolGrade() == searchCrit.getSchoolGrade()) {
                            result.add(stu);
                        }
                    }
                } else {
                    if (searchCrit.getMaxLessons() != 0) {
                        if (stu.getMaxLessons() == searchCrit.getMaxLessons()) {
                            result.add(stu);
                        }
                    } else {
                        result.add(stu);
                    }
                }
            }
        }
        
        return result;
    }
    
    public void ListAllSchoolsName() {
        allSchNames = new ArrayList<>();
        for (Student stu : DBController.studentDB) {
            if (!allSchNames.contains(stu.getSchoolName())) {
                allSchNames.add(stu.getSchoolName());
            }
        }
    }
    
    public ArrayList<Student> ListAllStudents (String schName, int schGrade) {
        ArrayList<Student> studentList = new ArrayList<>();
        for (Student stu : DBController.studentDB) {
            if (stu.getSchoolName().equals(schName) && stu.getSchoolGrade() == schGrade) {
                studentList.add(stu);
            }
        }
        return studentList;
    }
}
