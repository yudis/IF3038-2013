package SPACE;

/**
 * Class for representing a teacher in Rumah Belajar, derived from Participants.
 * @author Vincentius Martin | 13510017
 * @author Jeremy Joseph Hanniel | 13510026
 * @see Participants
 */
public class Teacher extends Participants {
    /**
     * Creates a new teacher.
     * @param iden the teacher's ID
     * @param nm the teacher's name
     * @param cell the teacher's cell phone numbers
     * @param sbj the subjects the teacher applies to teach in Rumah Belajar
     */
    public Teacher(String iden, String nm, String cell, String sbj) {
        super(iden, nm, cell, sbj);
    }
}
