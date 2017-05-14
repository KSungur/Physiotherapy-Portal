import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;


class MySqlConn {
    Connection connection = null;
    static Connection dbConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/physiotherapy?user=portaladmin&password=portalAdmin");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
