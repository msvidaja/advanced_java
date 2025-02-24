import java.sql.*;

public class connect{
	public static void main(String args[]) throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentDB","root","");
		
		if(!conn.isClosed())
		{
			System.out.println("Connected");
		}
	}
}