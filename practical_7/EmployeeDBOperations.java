package practical_7;

import java.sql.*;
import java.math.BigDecimal;

public class EmployeeDBOperations {
    public static void main(String[] args) {
        // Connection parameters (adjust these as per your DB server configuration)
        String url = "jdbc:mysql://localhost:3306/javalab";  // Connecting without specifying a database initially
        String user = "root";
        String password = "root";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. Connect to MySQL server
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database server.");

            // 2. Create Database "EmployeeDB"
            String createDB = "CREATE DATABASE IF NOT EXISTS EmployeeDB";
            ps = conn.prepareStatement(createDB);
            ps.executeUpdate();
            System.out.println("Database 'EmployeeDB' created successfully.");

            // Switch to the newly created database
            conn.setCatalog("EmployeeDB");

            // 3. Create Table "Employee"
            String createTable = "CREATE TABLE IF NOT EXISTS Employee ("
                    + "id INT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "salary DECIMAL(10,2)"
                    + ")";
            ps = conn.prepareStatement(createTable);
            ps.executeUpdate();
            System.out.println("Table 'Employee' created successfully.");

            // 4. Insert Records into "Employee" table
            String insertSQL = "INSERT INTO Employee (id, name, salary) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, 1);
            ps.setString(2, "John Doe");
            ps.setBigDecimal(3, new BigDecimal("50000.00"));
            ps.executeUpdate();
            System.out.println("Record inserted successfully.");

            // Retrieve and display the inserted record using ResultSet
            String selectSQL = "SELECT * FROM Employee";
            ps = conn.prepareStatement(selectSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int empId = rs.getInt("id");
                String empName = rs.getString("name");
                BigDecimal empSalary = rs.getBigDecimal("salary");
                System.out.println("Employee ID: " + empId + ", Name: " + empName + ", Salary: " + empSalary);
            }
            rs.close();

            // 5. Update the record (e.g., update salary)
            String updateSQL = "UPDATE Employee SET salary = ? WHERE id = ?";
            ps = conn.prepareStatement(updateSQL);
            ps.setBigDecimal(1, new BigDecimal("55000.00"));
            ps.setInt(2, 1);
            ps.executeUpdate();
            System.out.println("Record updated successfully.");

            // 6. Delete the record from the table
            String deleteSQL = "DELETE FROM Employee WHERE id = ?";
            ps = conn.prepareStatement(deleteSQL);
            ps.setInt(1, 1);
            ps.executeUpdate();
            System.out.println("Record deleted successfully.");

            // 7. Delete the table and the database using PreparedStatement
            // Drop the table
            String dropTableSQL = "DROP TABLE IF EXISTS Employee";
            ps = conn.prepareStatement(dropTableSQL);
            ps.executeUpdate();
            System.out.println("Table 'Employee' dropped successfully.");

            // Drop the database
            String dropDBSQL = "DROP DATABASE IF EXISTS EmployeeDB";
            ps = conn.prepareStatement(dropDBSQL);
            ps.executeUpdate();
            System.out.println("Database 'EmployeeDB' dropped successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
