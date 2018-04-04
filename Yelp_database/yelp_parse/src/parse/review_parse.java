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
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class review_parse
{

	public void run_review() throws SQLException
	{
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String sqlquery = "INSERT INTO REVIEWS" 
		+ "(user_id, review_id, stars, r_date, text, type, business_id, votes) VALUES" 
				+ "(?,?,?,?,?,?,?,?)";
		
		JSONParser parser = new JSONParser();
		try
		{
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sqlquery);
			
			FileReader filereader = new FileReader("C:\\Users\\UmaKarthik\\Documents\\DBMS\\Assign3\\Assignments_export\\Assignment 3\\YelpDataset\\YelpDataset-CptS451\\yelp_review.json");
			BufferedReader bufferedReader = new BufferedReader(filereader);
			String line;
			while ((line = bufferedReader.readLine()) != null) 
			{
				Object obj = parser.parse(line);
				JSONObject jsonObject = (JSONObject) obj;
				
				JSONObject votes = (JSONObject) jsonObject.get("votes");

				int votesCount = 0;
				// System.out.println(votes);
				Set<String> keys = votes.keySet();
				for (String temp : keys) {
					votesCount = votesCount + Integer.parseInt((votes.get(temp).toString()));
				}
				preparedStatement.setInt(8, votesCount);
				
				String user_id = (String) jsonObject.get("user_id");
				preparedStatement.setString(1, user_id);
				
				String review_id = (String) jsonObject.get("review_id");
				preparedStatement.setString(2, review_id);
				
			/*	int stars = ((Long) jsonObject.get("stars")).intValue();
				preparedStatement.setInt(3, stars); */
				
				float stars = ((Long) jsonObject.get("stars")).floatValue();
				preparedStatement.setFloat(3, stars);	
				
				String date = (String) jsonObject.get("date");
				preparedStatement.setString(4, date);
				
				String text = (String) jsonObject.get("text");
				preparedStatement.setString(5, text);
				
				String type = (String) jsonObject.get("type");
				preparedStatement.setString(6, type);
				
				String business_id = (String) jsonObject.get("business_id");
				preparedStatement.setString(7, business_id);
				
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
	public static Connection getDBConnection() 
	{

		Connection dbConnection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			//dbConnection = DriverManager.getConnection(
             //       "jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
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
