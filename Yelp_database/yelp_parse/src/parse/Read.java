package parse;
import java.sql.SQLException;

import parse.Checkin_parse;
import parse.review_parse;
import parse.business_parse;
import parse.user_parse;

public class Read {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		user_parse a = new user_parse();
		System.out.println("POPULATING USER NOW!!!");
		a.run_user(); 
		business_parse b = new business_parse();
		System.out.println("POPULATING BUSINESS NOW!!!");
		b.run_business();
		review_parse d = new review_parse();
		System.out.println("POPULATING REVIEW NOW!!!");
		d.run_review();
		Checkin_parse c = new Checkin_parse();
		System.out.println("POPULATING CHECKIN NOW!!!");
		c.run_checkin();  
	}

}
