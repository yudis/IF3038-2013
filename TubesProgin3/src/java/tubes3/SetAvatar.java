package tubes3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raymond
 */
public class SetAvatar {

    private Tubes3Connection db;
    private Connection connection;
    private ResultSet rs;

    public String getAvatar(String user) {
        String avatar = null;
        try {
            String queryGetAvatar = "SELECT avatar FROM pengguna WHERE username='" + user + "';";
            db = new Tubes3Connection();
            connection = db.getConnection();
            rs = db.coba(connection, queryGetAvatar);
            rs.first();
            avatar = rs.getString(1);
            //System.out.println("AAVATARE:" + avatar);
        } catch (SQLException ex) {
            Logger.getLogger(SetAvatar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (avatar);
    }
}
