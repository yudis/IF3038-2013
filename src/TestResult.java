package SPACE;

/**
 * Class that represents tests result of a student in Rumah Belajar.
 * @author Jeremy Joseph Hanniel | 13510026
 */
public class TestResult {
    private String date = null;
    private String subject = null;
    private float mark = -1;
    
    public TestResult (String dt, String sbj, float score) {
        date = dt;
        subject = sbj;
        mark = score;
    }
}
