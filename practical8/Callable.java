package practical8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class Callable {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/javalab";
        String user = "root";
        String password = "root"; // Adjust if needed

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            // Establish a connection to the MySQL database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the callable statement to call the stored procedure "getStaffName"
            cstmt = conn.prepareCall("{call getStaffName(?,?)}");

            // Set the input parameter (staff id)
            int staffId = 101;
            cstmt.setInt(1, staffId);

            // Register the output parameter (staff name)
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // Execute the stored procedure
            cstmt.execute();

            // Retrieve and print the output parameter value
            String staffName = cstmt.getString(2);
            System.out.println("Staff Name: " + staffName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (cstmt != null)
                    cstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
