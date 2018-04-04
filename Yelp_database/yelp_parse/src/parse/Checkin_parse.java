package parse;

import java.io.BufferedReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Checkin_parse 
{

	public void run_checkin() throws SQLException
	{
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String sqlquery = "INSERT INTO check_in" 
		+ "(dayandtime , ci_count, business_id) VALUES" 
				+ "(?,?,?)";
		
		JSONParser parser = new JSONParser();
		try
		{
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sqlquery);
			FileReader filereader = new FileReader("C:\\Users\\UmaKarthik\\Documents\\DBMS\\Assign3\\Assignments_export\\Assignment 3\\YelpDataset\\YelpDataset-CptS451\\yelp_checkin.json");
			
			BufferedReader bufferedReader = new BufferedReader(filereader);
			String line;
			while ((line = bufferedReader.readLine()) != null) 
			{
				Object obj = parser.parse(line);
				JSONObject jsonObject = (JSONObject) obj;
				
				String business_id = (String) jsonObject.get("business_id");
				preparedStatement.setString(3, business_id);
							
				JSONObject checkinTime = (JSONObject) jsonObject.get("checkin_info");
				System.out.println(checkinTime);
				Set<String> keys = checkinTime.keySet();
				for (String temp : keys) {
					System.out.println(temp);
					System.out.println(checkinTime.get(temp));
					preparedStatement.setString(2, checkinTime.get(temp).toString());
					String[] hourday = temp.split("-");
					Float fHrsDay = Integer.parseInt(hourday[1]) + Integer.parseInt(hourday[0]) / 24F;
					preparedStatement.setFloat(1, fHrsDay);
				}
				preparedStatement.executeUpdate();
			}
			filereader.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (preparedStatement != null) 
			{
				preparedStatement.close();
			}
			
			if (dbConnection != null) 
			{
				dbConnection.close();
			}
		}

	}
	
	public static String convert_day(String a)
	{
		String[] b = a.split("-");
		String c = "WRONG ENTRY!!!";
		
		if (b[1].equals("0"))
		{
			c = "SUNDAY";
		}
		if (b[1].equals("1"))
		{
			c = "MONDAY";
		}
		if (b[1].equals("2"))
		{
			c = "TUESDAY";
		}
		if (b[1].equals("3"))
		{
			c = "WEDNESDAY";
		}
		if (b[1].equals("4"))
		{
			c = "THURSDAY";
		}
		if (b[1].equals("5"))
		{
			c = "FRIDAY";
		}
		if (b[1].equals("6"))
		{
			c = "SATURDAY";
		}
		
		return c;
	}
	
	public static int convert_hour(String a)
	{
		String[] b = a.split("-");
		int c;
		c = Integer.parseInt(b[0]);
		return c;
	}
	
	public static Connection getDBConnection() 
	{

		Connection dbConnection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

		//	dbConnection = DriverManager.getConnection(
        //            "jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
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
