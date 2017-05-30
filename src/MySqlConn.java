import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by paul on 13.05.2017.
 */
public class MySqlConn {
    Connection connection = null;

    public static Connection dbConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/physiotherapy?user=portaladmin&password=portalAdmin");
//            JOptionPane.showMessageDialog(null, "Connection Successful");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
