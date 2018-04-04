package demo;
import java.sql.*;
import javax.swing.*;


public class Dbconnection {
	public static Connection getDBConnection() 
	{

		Connection dbConnection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "system", "uma");
			if(dbConnection != null)
			{
				System.out.println("Connected to Database Successfully!");
			}
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}		

}
