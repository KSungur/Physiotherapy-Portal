//import java.sql.*;
//import javax.swing.*;
//
//
//public class sqliteConnection {
//
//    Connection connection = null;
//
//    public static Connection dbConnector() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\alisi\\Desktop\\Software\\Java\\Physiotherapy-Portal\\PhysiotherapyPortalDatabase.sqlite");
//            JOptionPane.showMessageDialog(null, "Connection Successful");
//            return conn;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//            return null;
//        }
//    }
//}
