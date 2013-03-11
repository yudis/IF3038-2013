package SPACE;

import java.io.File;

/**
 * Interface regulating some common operations concerning databases.
 * @author Jeremy Joseph Hanniel | 13510026
 */
public interface DBOperations<E> {
    /**
     * Searches for the file of a participant with specific ID.
     * @param ID the ID of student, teacher, or lesson whose file is to be searched
     * @return the file containing data of student, teacher, or lesson
     */
    public File SearchForFile (String ID);
    
    /**
     * Register a new student, teacher, or lesson into their corresponding database. Also create a new physical file to store the data in disk.
     * @param newData an instance of either Student, Teacher, or Lesson class
     */
    public void Register (E newData);
    
    /**
     * Unregisters a student, teacher, or lesson from their corresponding database and remove its physical file.
     * <p>Note that the remove operation is NOT cascading. The removal of student or teacher data does not erase their footprints in lesson history, except the lesson itself is deleted as well.
     * @param data the student, teacher, or lesson data to be removed
     */
    public void Unregister (E data);
}
