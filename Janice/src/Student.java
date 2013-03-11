package SPACE;

import java.util.ArrayList;

/**
 * Class for representing a student in Rumah Belajar, derived from Participants.
 * @author Vincentius Martin | 13510017
 * @author Jeremy Joseph Hanniel | 13510026
 * @see Participants
 */
public class Student extends Participants {
    /**
     * The school name where this student is enrolled.
     */
    private String schoolName = null;
    /**
     * The grade this student is on at his/her school.
     * 0 denotes SD, 1 denotes SMP, 2 to 4 denotes 1 SMA, 2 SMA, and 3 SMA respectively.
     */
    private int schoolGrade = -1;
    /**
     * The course package this student chooses in Rumah Belajar.
     * This package specifies the number of lessons this student is allowed to take per week.
     */
    private int coursePackage = 0;
    /**
     * The count of lessons already taken this week, regardless of this student actually attends them or not.
     */
    private int totalLessons = 0;
    /**
     * The test results of this student.
     */
    private ArrayList<TestResult> testResult = new ArrayList<>();
    
    /**
     * Creates a new student.
     * @param iden the student's ID
     * @param nm the student's name
     * @param cell the student's cell phone numbers
     * @param sbj subjects the student applies to study in Rumah Belajar
     * @param schName the school name where the student is enrolled
     * @param schGrade the school grade which the student is on
     * @param cPackage the package the student chooses in Rumah Belajar
     * @param totLessons how many times the student has been scheduled for a lesson this week
     * @see Student#schoolGrade
     * @see Student#coursePackage
     */
    public Student (String iden, String nm, String cell, String sbj, String schName, int schGrade, int cPackage, int totLessons) {
        super (iden, nm, cell, sbj);
        schoolName = schName;
        schoolGrade = schGrade;
        coursePackage = cPackage;
        totalLessons = totLessons;
    }
    
    // G E T T E R   F U N C T I O N
    
    /**
     * Gets the school name where this student is enrolled.
     * @return the school name of this student
     */
    public String getSchoolName() {
        return schoolName;
    }
    
    /**
     * Gets the grade this student is on at his/her school.
     * @return the grade of this student
     */
    public int getSchoolGrade() {
        return schoolGrade;
    }
    
    /**
     * Gets how many lessons this student has taken this week.
     * @return the count of lessons this student has taken this week
     */
    public int getTotalLessons() {
        return totalLessons;
    }
    
    /**
     * Gets how many lessons this student is allowed to take per week. This corresponds with coursePackage.
     * @return the maximum number of lesson this student is allowed to take
     * @see Student#coursePackage
     */
    public int getMaxLessons() {
        return coursePackage;
    }
    
    /**
     * Gets the test results history of this student.
     * @return the test results history
     */
    public ArrayList<TestResult> getTestResult() {
        return testResult;
    }
    
    // S E T T E R   F U N C T I O N S

    /**
     * Sets the school name where this student is enrolled.
     * @param schName the new school name for this student
     */
    public void setSchoolName (String schName) {
        schoolName = schName;
    }
    
    /**
     * Sets the school grade this student is currently on in his/her school.
     * @param schGrade the new grade for this student
     */
    public void setSchoolGrade (int schGrade) {
        schoolGrade = schGrade;
    }
    
    /**
     * Sets the course package this student is applying for.
     * @param cPackage the new course package
     * @see Student#coursePackage
     */
    public void setCoursePackage (int cPackage) {
        coursePackage = cPackage;
    }
    
    /**
     * Sets the number of lessons this student has been scheduled to this week.
     * @param totLes the new total lessons
     */
    public void setTotalLessons (int totLes) {
        totalLessons = totLes;
    }
    
    /**
     * Sets the test results of this student with a new one.
     * @param newTR the new test result
     */
    public void setTestResult (ArrayList<TestResult> newTR) {
        testResult = new ArrayList<>(newTR);
    }
}
