package SPACE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class for representing lessons schedule database.
 * @author Jeremy Joseph Hanniel | 13510026
 */
public class LessonDB implements DBOperations<Lesson> {
    /**
     * Searches for the file of a lesson with specific ID.
     * @param ID the ID of the lesson whose file is to be searched
     * @return the file containing data of the lesson
     */
    @Override
    public File SearchForFile (String ID) {
        return new File(DBController.lessonDBFiles.get(ID));
    }

    /**
     * Register a new lesson into lessonDB. Also create a new physical file to store the lesson data in disk.
     * @param newData an instance of Lesson class, which is the new lesson
     * @see MainController#lessonDB
     */
    @Override
    public void Register(Lesson newData) {
        DBController.InsertIntoDatabase(newData);
        File newLessonFile = new File("Lesson\\" + newData.getLessonID()+ ".txt");
        UpdateData(newLessonFile, newData);
    }

    /**
     * Unregisters a lesson from lessonDB and remove his/her physical file.
     * <p>Note that the remove operation is NOT cascading. The removal of lesson data does not erase his/her footprints in lesson history, except the lesson itself is deleted as well.
     * @param data the lesson data to remove
     */
    @Override
    public void Unregister (Lesson data) {
        StudentDB stuDB = new StudentDB();
        TeacherDB teachDB = new TeacherDB();
        
        DBController.lessonDB.remove(DBController.SearchForIndexID(2, data.getLessonID()));
        
        int i = 0;
        Student stu = null;
        for (String stuID : data.getStudentsID()) {
            stu = DBController.studentDB.get(DBController.SearchForIndexID(1, stuID));
            
            i = 0;
            while (i < stu.getLessonHistory().size() && stu.getLessonHistory().get(i).equals(data.getLessonID())) {
                i++;
            }
            stu.getLessonHistory().remove(i);
            StudentDB.UpdateData(stuDB.SearchForFile(stuID), stu);
        }
        
        Teacher teach = DBController.teacherDB.get(DBController.SearchForIndexID(1, data.getTeacherID()));
        i = 0;
        while (i < teach.getLessonHistory().size() && teach.getLessonHistory().get(i).equals(data.getLessonID())) {
            i++;
        }
        teach.getLessonHistory().remove(i);
        TeacherDB.UpdateData(teachDB.SearchForFile(teach.getID()), teach);
        
        DBController.lessonDBFiles.remove(data.getLessonID());
        DBController.RemoveFileLink(0, data.getLessonID());
        File file = SearchForFile(data.getLessonID());
        file.delete();
    }

    /**
     * Updates a lesson data in lessonDB and its physical file.
     * @param file file to write the new lesson data into
     * @param newData an instance of Lesson class, which is the lesson whose data is to update
     * @see MainController#lessonDB
     */
    public static void UpdateData(File file, Lesson newData) {
        int idx = DBController.SearchForIndexID(2, newData.getLessonID());
        DBController.lessonDB.remove(idx);
        DBController.lessonDB.add(idx, newData);
        
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        
         try (PrintWriter pw = new PrintWriter(new FileWriter(tempFile.getAbsoluteFile()))) {
            pw.println(newData.getLessonID());
            pw.println(newData.getTime());
            pw.println(newData.getSubjects());
            pw.println(newData.getTeacherID());
            
            int i = 0;
            while (i < newData.getStudentsID().size()) {
                pw.print(newData.getStudentsID().get(i));
                i++;
                if (i < newData.getStudentsID().size()) {
                    pw.print(", ");
                }
            }
            
            pw.close();
            
            DBController.ReplaceFile(file, tempFile);
         } catch (IOException ioe) {
             System.err.println(ioe); 
         }
    }

    public static ArrayList<Lesson> GetLessonHistoryTables (ArrayList<String> lessonIDs) {
        ArrayList<Lesson> temp = new ArrayList<>();
        for (String ID : lessonIDs) {
            temp.add(DBController.lessonDB.get(DBController.SearchForIndexID(2, ID)));
        }
        return temp;
    }
}
