package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ReviewInfo extends JFrame {

	private JPanel contentPane;
	public JTable review_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewInfo frame = new ReviewInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReviewInfo() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 33, 919, 606);
		contentPane.add(scrollPane);
		
		review_table = new JTable();
		scrollPane.setViewportView(review_table);
		
	
	}
	String ReviewQuery;
	public ReviewInfo(String Query) {
		this.ReviewQuery =Query;
		
		addData();
	}
	void addData() {
		DefaultTableModel dt = (DefaultTableModel)review_table.getModel();
		
		Vector v = new Vector();
		v.add(ReviewQuery);
		dt.addRow(v);
	}
}
