package SPACE;

import java.io.File;

/**
 * Interface regulating common operations involving Participants only.
 * @author Jeremy Joseph Hanniel | 13510026
 * @see Participants
 */
public interface ParticipantsOperations<E extends Participants> {
    /**
     * Parses the participant's schedule from file
     * @param file the participant's file
     */
    public void ParseSchedule (File file);
}
