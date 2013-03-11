package SPACE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class that regulates all operations regarding teacher database, which is teacherDB.
 * @author Jeremy Joseph Hanniel | 13510026
 */
public class TeacherDB implements DBOperations<Teacher> {
    /**
     * Searches for the file of a teacher with specific ID.
     * @param ID the ID of the teacher whose file is to be searched
     * @return the file containing data of the teacher
     */
    @Override
    public File SearchForFile (String ID) {
        return new File(DBController.teacherDBFiles.get(ID));
    }
    
    /**
     * Register a new teacher into teacherDB. Also create a new physical file to store the teacher data in disk.
     * @param newData an instance of Teacher class, which is the new teacher
     * @see MainController#teacherDB
     */
    @Override
    public void Register(Teacher newData) {
        File newTeacherFile = new File("Teacher\\" + newData.getID() + ".txt");
        DBController.InsertIntoDatabase(newData);
        UpdateData(newTeacherFile, newData);
    }

    /**
     * Unregisters a teacher from teacherDB and lessonDB, and also remove his/her physical file.
     * @param data the teacher data to remove
     */
    @Override
    public void Unregister (Teacher data) {
        DBController.teacherDB.remove(DBController.SearchForIndexID(1, data.getID()));
        LessonDB lesDB = new LessonDB();
        
        ArrayList<Lesson> temp = LessonDB.GetLessonHistoryTables(data.getLessonHistory());
        for (Lesson lesson : temp) {
            lesson.setTeacherID("");
            LessonDB.UpdateData(lesDB.SearchForFile(lesson.getLessonID()), lesson);
        }
        
        DBController.teacherDBFiles.remove(data.getID());
        DBController.RemoveFileLink(0, data.getID());
        File file = SearchForFile(data.getID());
        file.delete();
    }

    /**
     * Updates a teacher data in teacherDB and his/her physical file.
     * @param file file to write the new teacher data into
     * @param newData an instance of Teacher class, which is the teacher whose data is to update
     * @see MainController#teacherDB
     */
    public static void UpdateData(File file, Teacher newData) {
        int idx = DBController.SearchForIndexID(1, newData.getID());
        DBController.teacherDB.remove(idx);
        DBController.teacherDB.add(idx, newData);
        
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        
         try (PrintWriter pw = new PrintWriter(new FileWriter(tempFile.getAbsoluteFile()))) {
            pw.println(newData.getID());
            pw.println(newData.getName());
            pw.println(newData.getCellNo());
            pw.println(newData.getSubjects());
            
            pw.println();
            
            if (!file.exists()) {
                pw.println("<J>");
                pw.println("</J>");
                pw.println();
                pw.println("<HL>");
                pw.println("</HL>");
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
    
    public ArrayList<Teacher> SearchForInstances (Teacher searchCrit) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Teacher teach : DBController.teacherDB) {
            if (teach.getName().contains(searchCrit.getName()) && teach.getCellNo().contains(searchCrit.getCellNo()) && teach.getSubjects().equals(searchCrit.getSubjects())) {
                result.add(teach);
            }
        }
        
        return result;
    }
}
