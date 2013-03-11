package SPACE;

/**
 * Main class where the whole program is run from.
 * @author Vincentius Martin | 13510017
 * @author Jeremy Joseph Hanniel | 13510026
 */
public class SPACE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBController dbControl = new DBController();
        
        new MainMenu().setVisible(true);
    }
}
