//import javax.swing.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
///**
// * Created by alisi on 8.05.2017.
// */
//public class SqlConnection {
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
