package demo;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;

public class Tesing_uI {

	private JFrame frame;
	String SUBHEADINGCOLOR = "#99b3ff";
	private Border borderList = BorderFactory.createLineBorder(Color.BLUE, 1);
	HashMap<String, Integer> days;
	DefaultListModel<String> addMainCategoryList = new DefaultListModel<String>();
	DefaultListModel<String> addSubCategoryList = new DefaultListModel<String>();
	DefaultListModel<String> addAttributeList = new DefaultListModel<String>();
	DefaultListModel<String> addResultList = new DefaultListModel<String>();
	DefaultTableModel resultTable;
	DefaultTableModel reviewTable;
	

	String connectionString;
	
	static PreparedStatement ps = null;
	static ResultSet rs = null, RetrivedGenreMid = null;
	
	
	JTable tableResults;
	JButton executeQuerry;
	JComboBox ANDOR;
	JList main_Catlist;
	JList sub_Catlist;
	JComboBox from;
	HashMap<Integer,String> bIDMap;
	
	ArrayList<String> selectedMainCategoryList = new ArrayList<String>();
	ArrayList<String> selectedSubCategoryList = new ArrayList<String>();
	//ArrayList<String> selectedAttributeList = new ArrayList<String>();
	ArrayList<String> selectedResultList = new ArrayList<String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tesing_uI window = new Tesing_uI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//private javax.swing.JTable ResultTable;
	}

	/**
	 * Create the application.
	 */
	Connection connection = null;
	private JTable table;
	private JTextField userReviewCount_value;
	private JTextField userFriends_value;
	private JTextField userAvgStars_value;
	private JTextField check_count_value;
	private JTextField review_vote_count;
	private JCheckBox isUserQuery;
	private JTextField ck_from_hour;
	private JTextField ck_to_hour;
	private JTextField review_star_count;
	
	public Tesing_uI() {
		connection = Dbconnection.getDBConnection();
		String oldQuery;
		initialize();
		bIDMap = new HashMap<Integer,String>();
		populateMainCategoryList();
		
		
		days = new HashMap<String, Integer>();
        String vDay[] = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        for (int i = 0; i < vDay.length; i++) {
            days.put(vDay[i], i);
        }
	}

	private void isUserQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isUserQueryActionPerformed
	        // TODO add your handling code here:
	}//GEN-LAST:event_isUserQueryActionPerformed
		

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String oldQuery;
		frame = new JFrame();
		frame.setBounds(20, 20, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		resultTable = new DefaultTableModel();
		tableResults = new JTable();
		tableResults.setModel(resultTable);
		connectionString = "INTERSECT";
		
		//Creating a pane for Days of Week
		JPanel daysOfWeekDropDownPane = new JPanel();
		daysOfWeekDropDownPane.setLayout(null);
		
		JLabel lblUser = new JLabel("Users");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUser.setBounds(82, 366, 69, 22);
		//daysOfWeekDropDownPane.add(lblUser);
		frame.getContentPane().add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Review Count:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 428, 99, 22);
		//daysOfWeekDropDownPane.add(lblNewLabel);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox userReviewCount = new JComboBox();
		userReviewCount.setBounds(138, 430, 119, 22);
		//daysOfWeekDropDownPane.add(comboBox);
		userReviewCount.addItem("");
		userReviewCount.addItem("=");
		userReviewCount.addItem("<");
		userReviewCount.addItem(">");
		frame.getContentPane().add(userReviewCount);
		
		//text field for review count
		userReviewCount_value = new JTextField();
		userReviewCount_value.setBounds(263, 431, 73, 19);
		frame.getContentPane().add(userReviewCount_value);
		userReviewCount_value.setColumns(10);
		
		JLabel lblNumofFriends = new JLabel("Num.of friends:");
		lblNumofFriends.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumofFriends.setBounds(10, 461, 99, 22);
		//daysOfWeekDropDownPane.add(lblNumofFriends);
		frame.getContentPane().add(lblNumofFriends);
		
		JComboBox userFriends = new JComboBox();
		userFriends.setBounds(138, 463, 119, 22);
		userFriends.addItem("");
		userFriends.addItem("=");
		userFriends.addItem("<");
		userFriends.addItem(">");
		frame.getContentPane().add(userFriends);
		
		userFriends_value = new JTextField();
		userFriends_value.setBounds(263, 464, 73, 19);
		frame.getContentPane().add(userFriends_value);
		userFriends_value.setColumns(10);
		
		JLabel lblAvgstars = new JLabel("Avg.Stars:");
		lblAvgstars.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvgstars.setBounds(10, 494, 89, 22);
		frame.getContentPane().add(lblAvgstars);
		
		JComboBox userAvgStars = new JComboBox();
		userAvgStars.setBounds(138, 496, 119, 22);
		userAvgStars.addItem("");
		userAvgStars.addItem("=");
		userAvgStars.addItem("<");
		userAvgStars.addItem(">");
		frame.getContentPane().add(userAvgStars);
		
		//text field for num of friends
		userAvgStars_value = new JTextField();
		userAvgStars_value.setBounds(263, 497, 73, 19);
		frame.getContentPane().add(userAvgStars_value);
		userAvgStars_value.setColumns(10);
		
	
		JLabel lblMemberSince = new JLabel("Member Since:");
		lblMemberSince.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemberSince.setBounds(10, 395, 99, 22);
		//daysOfWeekDropDownPane.add(lblMemberSince);
		frame.getContentPane().add(lblMemberSince);
		
		JDateChooser userDate = new JDateChooser();
		userDate.setBounds(138, 399, 119, 20);
		frame.getContentPane().add(userDate);
		userDate.setDateFormatString("yyyy-MM");
		
		JLabel lblNewLabel_4 = new JLabel("Select:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 526, 73, 24);
		frame.getContentPane().add(lblNewLabel_4);
		
		JComboBox<String> userOperator = new JComboBox<String>();
		userOperator.setBounds(75, 529, 195, 22);
		frame.getContentPane().add(userOperator);
		//userOperator.addItem("AND,OR between attributes");
		userOperator.addItem("AND");
		userOperator.addItem("OR");
		userOperator.setSelectedIndex(1);

		JLabel lblNewLabel_1 = new JLabel("CheckIn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(426, 11, 73, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("From");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(385, 39, 73, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox<String> ck_from_day = new JComboBox<String>();
		ck_from_day.setBounds(379, 55, 89, 22);
		
		ck_from_day.addItem("");
		ck_from_day.addItem("MON");
		ck_from_day.addItem("TUE");
		ck_from_day.addItem("WED");
		ck_from_day.addItem("THU");
		ck_from_day.addItem("FRI");
		ck_from_day.addItem("SAT");
		ck_from_day.addItem("SUN");
		frame.getContentPane().add(ck_from_day);
		
		JLabel lblNewLabel_3 = new JLabel("To");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(385, 88, 49, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JComboBox<String> ck_to_day = new JComboBox<String>();
		ck_to_day.setBounds(379, 111, 89, 22);
		
		ck_to_day.addItem("");
		ck_to_day.addItem("MON");
		ck_to_day.addItem("TUE");
		ck_to_day.addItem("WED");
		ck_to_day.addItem("THU");
		ck_to_day.addItem("FRI");
		ck_to_day.addItem("SAT");
		ck_to_day.addItem("SUN");	
		frame.getContentPane().add(ck_to_day);
		
		JLabel lblHour = new JLabel("hour");
		lblHour.setBounds(492, 36, 49, 22);
		frame.getContentPane().add(lblHour);
		 
		ck_from_hour = new JTextField();
		ck_from_hour.setBounds(473, 56, 82, 20);
		frame.getContentPane().add(ck_from_hour);
		ck_from_hour.setColumns(10);
		 
		JLabel label_2 = new JLabel("hour");
		label_2.setBounds(492, 85, 49, 22);
		frame.getContentPane().add(label_2);
		 
		ck_to_hour = new JTextField();
		ck_to_hour.setBounds(473, 112, 82, 20);
		frame.getContentPane().add(ck_to_hour);
		ck_to_hour.setColumns(10);
		
        JLabel lblNewLabel_5 = new JLabel("Num.of Checkins:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(385, 152, 114, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JComboBox check_count = new JComboBox();
		check_count.setBounds(379, 177, 89, 22);
		check_count.addItem("");
		check_count.addItem("=");
		check_count.addItem("<");
		check_count.addItem(">");
		frame.getContentPane().add(check_count);
		
		//text fied for number of checkins
		check_count_value = new JTextField();
		check_count_value.setBounds(473, 177, 82, 20);
		frame.getContentPane().add(check_count_value);
		check_count_value.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Review");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(426, 218, 73, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Star:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(420, 243, 62, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JComboBox review_stars = new JComboBox();
		review_stars.setBounds(385, 268, 83, 19);
		review_stars.addItem("");
		review_stars.addItem("=");
		review_stars.addItem("<");
		review_stars.addItem(">");
		frame.getContentPane().add(review_stars);
		
		//text field for review stars
		review_star_count = new JTextField();
		review_star_count.setBounds(473, 268, 82, 22);
		frame.getContentPane().add(review_star_count);
		review_star_count.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Votes:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(410, 298, 62, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		JComboBox review_votes = new JComboBox();
		review_votes.setBounds(385, 319, 83, 22);
		review_votes.addItem("");
		review_votes.addItem("=");
		review_votes.addItem("<");
		review_votes.addItem(">");
		frame.getContentPane().add(review_votes);	
		
		//text field for votes
		review_vote_count = new JTextField();
		review_vote_count.setBounds(473, 320, 82, 20);
		frame.getContentPane().add(review_vote_count);
		review_vote_count.setColumns(10);
		
		JLabel lblBusiness = new JLabel("Business");
		lblBusiness.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBusiness.setBounds(99, 15, 89, 14);
		JPanel businessTitle = new JPanel();
		businessTitle.setBackground(Color.decode(SUBHEADINGCOLOR));
		//businessTitle.add(lblBusiness);
		frame.getContentPane().add(lblBusiness);
		
		//pane for main category
		JLabel lblNewLabel_9 = new JLabel("Category");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(48, 34, 73, 22);
		frame.getContentPane().add(lblNewLabel_9);
		
		main_Catlist = new JList();
		main_Catlist.setLocation(10, 57);
		//list.setBounds(10, 56, 73, 129);
		main_Catlist.setBorder(borderList);
		frame.getContentPane().add(main_Catlist);
		
		JScrollPane scrollPane = new JScrollPane(main_Catlist);
		scrollPane.setBounds(10, 56, 178, 311);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);		
		
		// pane for sub category
		JLabel lblNewLabel_10 = new JLabel("Sub Category");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(193, 33, 87, 25);
		frame.getContentPane().add(lblNewLabel_10);
		
		sub_Catlist = new JList();
		sub_Catlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedSubCategoryList = (ArrayList<String>) sub_Catlist.getSelectedValuesList();
			}
		});
		//sub_Catlist.setBounds(101, 58, 89, 127);
		sub_Catlist.setBorder(borderList);
		frame.getContentPane().add(sub_Catlist);
		
		JScrollPane scrollPane_1 = new JScrollPane(sub_Catlist);
		scrollPane_1.setBounds(191, 58, 178, 309);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane_1);		
		
		//from date chooser for review
		JLabel label = new JLabel("From");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(612, 243, 62, 14);
		frame.getContentPane().add(label);
		
		JDateChooser review_from_date = new JDateChooser();
		review_from_date.setBounds(587, 268, 125, 19);
		review_from_date.setDateFormatString("yyyy-MM-dd");
		frame.getContentPane().add(review_from_date);
	
		//To date chooser for review
		JLabel label_1 = new JLabel("To");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(612, 298, 49, 14);
		frame.getContentPane().add(label_1);
		
		JDateChooser review_to_date = new JDateChooser();
		review_to_date.setBounds(587, 321, 125, 20);
		review_to_date.setDateFormatString("yyyy-MM-dd");
		frame.getContentPane().add(review_to_date);

		// Result Pane
		JLabel lblNewLabel_11 = new JLabel("Results");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_11.setBounds(920, 11, 73, 22);
		frame.getContentPane().add(lblNewLabel_11);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(722, 39, 452, 512);
		frame.getContentPane().add(scrollPane_2);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		
		//check box for user
		JCheckBox isUserQuery = new JCheckBox("select for User");
		isUserQuery.setFont(new Font("Tahoma", Font.PLAIN, 10));
		 isUserQuery.addActionListener(new java.awt.event.ActionListener() {
		 	public void actionPerformed(java.awt.event.ActionEvent evt) {
		 		isUserQueryActionPerformed(evt);
		 	}
		 });
		 
		 isUserQuery.setBounds(151, 369, 119, 23);
		 frame.getContentPane().add(isUserQuery);
		 
		 JCheckBox isCheckIn = new JCheckBox("Select for Checkin");
		 isCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		 isCheckIn.setBounds(521, 13, 107, 23);
		 frame.getContentPane().add(isCheckIn);
		 
		 JCheckBox isReview = new JCheckBox("select for Review");
		 isReview.setFont(new Font("Tahoma", Font.PLAIN, 10));
		 isReview.setBounds(521, 216, 125, 23);
		 frame.getContentPane().add(isReview);
		 
		 //Query Area
		 JScrollPane scrollPane_3 = new JScrollPane();
		 scrollPane_3.setBounds(342, 396, 366, 120);
		 frame.getContentPane().add(scrollPane_3);
			
		 JTextArea QueryArea = new JTextArea();
		 scrollPane_3.setViewportView(QueryArea);
		 QueryArea.setEditable(false);
		 QueryArea.setColumns(20);
		 QueryArea.setRows(5);

		 table.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	               // ResultTableMouseClicked(evt);
	            	String reviewQuery = "";
	        		//String oldQuery = QueryArea.getText();
	        		int selectedRowID = table.getSelectedRow();
	        		String[] rowObj = new String[7];
	        	    ReviewInfo newFrame = new ReviewInfo();
	        		newFrame.setVisible(true);
	        		reviewTable = new DefaultTableModel(); 
					newFrame.review_table.setModel(reviewTable);
					if (isUserQuery.isSelected()) {// Users Selected
	        			String id = (String) table.getValueAt(selectedRowID, table.getColumnModel().getColumnIndex("USER_ID"));
	        			reviewQuery = reviewQuery + " Select y.name, r.r_date, r.stars, r.review_id, r.text, r.votes from reviews r, yelp_user y where r.user_id = y.user_id and r.user_id = '" + id + "'";
	        			reviewTable.addColumn("User Name");
						reviewTable.addColumn("Review Date");
						reviewTable.addColumn("Stars");
						reviewTable.addColumn("Review_Id");
						reviewTable.addColumn("Review");
						reviewTable.addColumn("Votes");
						
	        		}
	        		else {// Business Selected
	                 	String id = (String) table.getValueAt(selectedRowID, table.getColumnModel().getColumnIndex("BUSINESS_ID"));
	                 	reviewQuery = reviewQuery + " Select y.name, r.r_date, r.stars,  r.review_id, r.text, r.votes from reviews r,yelp_user y,business b where b.business_id = r.business_id and r.user_id = y.user_id AND r.business_id = '" + id + "'";
	                 	reviewTable.addColumn("User Name");
						reviewTable.addColumn("Review Date");
						reviewTable.addColumn("Stars");
						reviewTable.addColumn("Review_Id");
						reviewTable.addColumn("Review");
						reviewTable.addColumn("Votes");
						
	        		} 
  
	        		System.out.println("reviewQuery " + reviewQuery);
	        		 QueryArea.setText(reviewQuery);
	            	try {
	        			ResultSet review = null;
	        			ps=connection.prepareStatement(reviewQuery);
	        			review = ps.executeQuery(reviewQuery);
	        			while(review.next())
						{
	        				rowObj = new String[] {review.getString("NAME"), review.getString("R_DATE"), review.getString("STARS"),review.getString("REVIEW_ID"), review.getString("TEXT"), review.getString("VOTES")};
	        				reviewTable.addRow(rowObj);
						}	
	        			ps.close();
	        			review.close();
	        		} catch(Exception ex) {
	        			System.out.println(ex);
	        		}
	            }
	        });
		 
		// scrollPane_3.setViewportView(ResultTable);   

	// Execute Query button operation
		 executeQuerry = new JButton("Execute Query");
		 executeQuerry.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt ) {
			       if (isUserQuery.isSelected()) {
			    	   //System.out.println("isUserQuery 1 :"+ isUserQuery);
			            boolean isWhereInserted = false;
			            String userQuery = "select name,yelping_since,average_stars,user_id,friends_count from yelp_user ";
			            String userEnteredDate = ((JTextField) userDate.getDateEditor().getUiComponent()).getText();
			            String userEnteredReviewCount = userReviewCount_value.getText();
			            String userEnteredFriends = userFriends_value.getText();
			            String userEnteredAvgStars = userAvgStars_value.getText();
			            String userSelector = userOperator.getSelectedItem().toString();

			            System.out.println("Initial user query :" + userQuery);
			            if (!userEnteredDate.equals("")) {
			                try {
			                    userQuery = userQuery + " where yelping_since > '" + userEnteredDate + "' ";
			                    isWhereInserted = true;
			                    
			                } catch (Exception e) {
			                    e.printStackTrace();
			                }
			                System.out.println("user query for selecting yelping_since :" + userQuery);
			            }
			            if (!userEnteredReviewCount.equals("")) {
			                String sysmbol = userReviewCount.getSelectedItem().toString();
			                if (isWhereInserted) {
			                    userQuery = userQuery + "  " + userSelector + " review_count " + sysmbol + " " + userEnteredReviewCount + " ";
			                } else {
			                    userQuery = userQuery + " where " + " review_count " + sysmbol + " " + userEnteredReviewCount + " ";
			                    isWhereInserted = true;
			                }
			                System.out.println("user query for selecting review_count :" + userQuery);
			            }
			            if (!userEnteredFriends.equals("")) {
			                String sysmbol = userFriends.getSelectedItem().toString();
			                if (isWhereInserted) {
			                    userQuery = userQuery + "  " + userSelector + " friends_count " + sysmbol + " " + userEnteredFriends + " ";
			                } else {
			                    userQuery = userQuery + " where " + " friends_count " + sysmbol + " " + userEnteredFriends + " ";
			                    isWhereInserted = true;
			                }
			                System.out.println("user query for selecting friends_count :" + userQuery);
			            }
			            if (!userEnteredAvgStars.equals("")) {
			                String sysmbol = userAvgStars.getSelectedItem().toString();
			                if (isWhereInserted) {
			                    userQuery = userQuery + "  " + userSelector + " average_stars " + sysmbol + " " + userEnteredAvgStars + " ";
			                } else {
			                    userQuery = userQuery + " where " + " average_stars " + sysmbol + " " + userEnteredAvgStars + " ";
			                    isWhereInserted = true;
			                }
			                System.out.println("user query for selecting average_stars :" + userQuery); 
			            }
			            System.out.println("final user query :"+userQuery); 
			            QueryArea.setText(userQuery); 
			           
			        	try {
							ResultSet rs13 = null;
							ps=connection.prepareStatement(userQuery);
							rs13 = ps.executeQuery(userQuery);
							table.setModel(DbUtils.resultSetToTableModel(rs13));
							ps.close();
							rs13.close();
						} catch(Exception ex) {
							System.out.println(ex);
						}
			}		
			else {
	            //Business
	            String  mainQuery = "select b.name, b.city, b.state, b.stars, b.business_id from business b where b.business_id in( ";
	            String  buisnessQuery = "select  distinct bb.business_id from business bb";
	            boolean isCategoryWhereInlcued = false;
	            String  CheckinQuery = "Select distinct business_id from check_in";
	            String  reviewQuery = "Select distinct business_id from reviews";

	            if (selectedMainCategoryList.size() > 0) {
	                isCategoryWhereInlcued = true;
	                buisnessQuery += " where bb.business_id in ( select bm.business_id from b_main_category bm, b_sub_category bs where bm.c_name in(";
	                for (int i = 0; i < selectedMainCategoryList.size(); i++) {
	                    buisnessQuery = buisnessQuery +"'"+selectedMainCategoryList.get(i)+"'";
	                    if (!(i + 1 == selectedMainCategoryList.size())) {
	                        buisnessQuery = buisnessQuery + " , ";
	                    }
	                }
	                buisnessQuery = buisnessQuery + " ) ";
	            }
           
                System.out.println("BusinessQuery :" + buisnessQuery);
                
	            if (selectedSubCategoryList.size() > 0) {
	                if (!isCategoryWhereInlcued) {
	                    buisnessQuery += " where business_id in ( select bs.business_id from b_sub_category bs where bs.c_name in(";
	                } else {
	                    buisnessQuery += " and bs.c_name in(";
	                }
	                for (int i = 0; i < selectedSubCategoryList.size(); i++) {
	                    buisnessQuery = buisnessQuery + "'" + selectedSubCategoryList.get(i) + "'";
	                    if (!(i + 1 == selectedSubCategoryList.size())) {
	                        buisnessQuery = buisnessQuery + " , ";
	                    }
	                }
	                buisnessQuery = buisnessQuery + " ) ";
	                System.out.println("buisnessQuery_sub selected :"+buisnessQuery); 
	            }
	            if (isCategoryWhereInlcued) {
	                buisnessQuery = buisnessQuery + " ) ";
	                System.out.println("buisnessQuery final :"+buisnessQuery); 
	            }
	            
	            if (isCheckIn.isSelected()) {

	                boolean isWhereInserted = false;
	                String numberOfCheckin = check_count_value.getText();
	                String fromHour = ck_from_hour.getText();
	                String toHour = ck_to_hour.getText();
                    if(!fromHour.equals("") && (ck_from_day.getSelectedItem().toString()).equals(""))  {
                    	JOptionPane.showMessageDialog(null, "Please select From Day");
                      }
	               
                    if (!fromHour.equals("") && !(ck_from_day.getSelectedItem().toString()).equals("")) {
	                	//System.out.println("day :"+ (ck_from_day.getSelectedItem().toString()));
	                    int day = days.get(ck_from_day.getSelectedItem().toString());
	                    System.out.println("CheckinQuery fromhour initial :" +CheckinQuery);
	                    int hour = Integer.parseInt(fromHour);
	                    CheckinQuery = CheckinQuery + " where dayandtime >= " + (day + hour / 24f) + " ";
	                    System.out.println("CheckinQuery fromhour :" +CheckinQuery);
	                    isWhereInserted = true;
	                }
                    if(!toHour.equals("") && (ck_to_day.getSelectedItem().toString()).equals(""))  {
                    	JOptionPane.showMessageDialog(null, "Please select To Day");
                      }
	                if (!toHour.equals("") && !(ck_to_day.getSelectedItem().toString()).equals("")) {
	                    int day = days.get(ck_to_day.getSelectedItem().toString());
	                    int hour = Integer.parseInt(toHour);
	                    if (isWhereInserted) {
	                        CheckinQuery = CheckinQuery + " and dayandtime < " + (day + hour / 24f) + " ";
	                    } else {
	                        isWhereInserted = true;
	                        CheckinQuery = CheckinQuery + "  where dayandtime < " + (day + hour / 24f) + " ";
	                        
	                    }
	                    System.out.println("CheckinQuery tohour :" +CheckinQuery);
	                }

	                if (!numberOfCheckin.equals("")) {
	                    String sysmbol = check_count.getSelectedItem().toString();

	                    CheckinQuery = CheckinQuery + " group by business_id having sum(ci_count)" + sysmbol + " " + numberOfCheckin + " ";
	                    System.out.println("CheckinQuery Num_of_checkin :" +CheckinQuery);
	                }

	            }
	            
	            if (isReview.isSelected()) {
	                boolean isWhereInserted = false;
	                String from_date = ((JTextField) review_from_date.getDateEditor().getUiComponent()).getText();
	                String to_date = ((JTextField) review_to_date.getDateEditor().getUiComponent()).getText();
	                String enteredStars = review_star_count.getText();
	                String enteredVotes = review_vote_count.getText();

	                if (!from_date.equals("")) {
	                    try {
	                        reviewQuery = reviewQuery + " where r_date >= '" + from_date + "' ";
	                        isWhereInserted = true;
	                        System.out.println("ReviewQuery for from date :" +reviewQuery);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	                if (!to_date.equals("")) {
	                    try {
	                        if (isWhereInserted) {
	                            reviewQuery = reviewQuery + " and r_date < '" + to_date + "' ";
	                        } else {
	                            reviewQuery = reviewQuery + " where r_date < '" + to_date + "' ";
	                            isWhereInserted = true;
	                        }
	                        System.out.println("ReviewQuery for to date :" +reviewQuery);

	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
                   if(!review_stars.getSelectedItem().toString().equals("") && enteredStars.equals("")) {
                	   JOptionPane.showMessageDialog(null, "Please enter the stars value");  
                	   
                   }
	               if (!enteredStars.equals("")) {
	                    String sysmbol = review_stars.getSelectedItem().toString();
	                    int Stars = Integer.parseInt( review_star_count.getText());
	                    if (isWhereInserted) {
	                        reviewQuery = reviewQuery + "  and " + " stars " + sysmbol + " " + Stars + " ";
	                    } else {
	                        reviewQuery = reviewQuery + " where " + " stars " + sysmbol + " " + Stars + " ";
	                        isWhereInserted = true;
	                    }
	                    System.out.println("ReviewQuery for stars  :" +reviewQuery);
	               }

	                if (!enteredVotes.equals("")) {
	                    String sysmbol = review_votes.getSelectedItem().toString();
	                    if (isWhereInserted) {
	                        reviewQuery = reviewQuery + "  and " + " votes " + sysmbol + " " + enteredVotes + " ";
	                    } else {
	                        reviewQuery = reviewQuery + " where " + " votes " + sysmbol + " " + enteredVotes + " ";
	                        isWhereInserted = true;
	                    }
	                    System.out.println("ReviewQuery for votes :" +reviewQuery);
	                }
	            }
	            mainQuery = mainQuery + " " + buisnessQuery;
	            if (isCheckIn.isSelected()) {
	                mainQuery = mainQuery + " Intersect " + CheckinQuery;
	                System.out.println("Checkin selected final :" +mainQuery);
	            }
	            if (isReview.isSelected()) {
	                mainQuery = mainQuery + " Intersect " + reviewQuery;
	                System.out.println("review selected final :" +mainQuery);
	            } 
	            mainQuery = mainQuery + " )";
	            System.out.println("final business query :" +mainQuery);
	            QueryArea.setText(mainQuery);
	        	try {
					ResultSet rs13 = null;
					ps=connection.prepareStatement(mainQuery);
					rs13 = ps.executeQuery(mainQuery);
					table.setModel(DbUtils.resultSetToTableModel(rs13));
					ps.close();
					rs13.close();
				} catch(Exception ex) {
					System.out.println(ex);
				}
			}// else part ends here   
          }//execute Query ends here
		});
		executeQuerry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		executeQuerry.setBounds(455, 527, 137, 23);
		frame.getContentPane().add(executeQuerry);
  }

	//Method to populate Main Category elements and update sub categories on click
	private void populateMainCategoryList() {
		System.out.println("main");
	    //updateUnionIntersect();
		
        String getMainCategories = "select distinct c_name from b_main_category order by c_name\n";
	
        try {
		ResultSet rs11 = null;
		ps=connection.prepareStatement(getMainCategories);
		rs11 = ps.executeQuery(getMainCategories);
		int i = 0;
		while(rs11.next())
		{
			if(!addMainCategoryList.contains(rs11.getString("c_name")))
			{
				addMainCategoryList.addElement(rs11.getString("c_name"));
			}
		}
		ps.close();
		rs11.close();
        } catch(Exception ex) {
		System.out.println(ex);
        }
        main_Catlist.setModel(addMainCategoryList);
	
        MouseListener mainCategoryMouseListener = new MouseAdapter() 
        {
		public void mouseClicked(MouseEvent e) 
		{
			if (e.getClickCount() == 1)
			{
				if (tableResults.getRowCount() > 0) {
					for (int i = tableResults.getRowCount() - 1; i > -1; i--) {
						resultTable.removeRow(i);
					}
				}
				selectedMainCategoryList = (ArrayList<String>) main_Catlist.getSelectedValuesList();
				
				if(selectedMainCategoryList.size()!=0)
				{
					addSubCategoryList.clear();
					String FinalSubQuery = "select distinct bt.c_name\r\n" + 
							"from b_main_category b, b_sub_category bt\r\n" + 
							"where b.business_id = bt.business_id and b.c_name";
					for(int i=0;i<selectedMainCategoryList.size();i++) {
						FinalSubQuery += " like '"+selectedMainCategoryList.get(i)+"' "+ connectionString +" SELECT DISTINCT BT.C_NAME "
								+ "from b_main_category b, b_sub_category bt where b.business_id = bt.business_id and b.c_name";
					}
					if(selectedMainCategoryList.size()>0 && (connectionString.equals("UNION"))){ 
						FinalSubQuery = FinalSubQuery.substring(0, FinalSubQuery.length()-107);
						
					}
					if(selectedMainCategoryList.size()>0 && (connectionString.equals("INTERSECT"))){ 
						FinalSubQuery = FinalSubQuery.substring(0, FinalSubQuery.length()-127);
						
					}
					try {
						ResultSet rs12 = null;
						ps=connection.prepareStatement(FinalSubQuery);
						rs12 = ps.executeQuery(FinalSubQuery);
						while(rs12.next())
						{
							if(!addSubCategoryList.contains(rs12.getString("c_name")))
							{
								addSubCategoryList.addElement(rs12.getString("c_name"));
							}
						}
						ps.close();
						rs12.close();
					} catch(Exception ex) {
						System.out.println(ex);
					}
					sub_Catlist.setModel(addSubCategoryList);
				}
			}
		}
	};
	main_Catlist.addMouseListener(mainCategoryMouseListener);
	}
}	

