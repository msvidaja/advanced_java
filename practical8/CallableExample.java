package practical8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class CallableExample {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/javalab";
        String user = "root";
        String password = "root"; // Adjust if needed

        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            // Establish connection to the MySQL database
            conn = DriverManager.getConnection(url, user, password);

            // Prepare the callable statement for the stored procedure "getEmployeeName"
            cstmt = conn.prepareCall("{call getEmployeeName(?,?)}");

            // Set the input parameter (employee id)
            int employeeId = 101;
            cstmt.setInt(1, employeeId);

            // Register the output parameter (employee name)
            cstmt.registerOutParameter(2, Types.VARCHAR);

            // Execute the stored procedure
            cstmt.execute();

            // Retrieve and print the output parameter value
            String employeeName = cstmt.getString(2);
            System.out.println("Employee Name: " + employeeName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
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
