import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Dimension;

public class BookingTimings {

	public JFrame frame;
	public int slot_no_db;

	/**
	 * Launch the application.
	 */
	

	public BookingTimings(String username, String movie_name) {
		// TODO Auto-generated constructor stub
		initialize(username,movie_name);
	}

	/**
	 * Create the application.
	 * 
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username,String movie_name) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 10, 986, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Database variables
		String certification_db = "";
		String genre_db = "";
		String duration_db = "";
		String date_db[] = new String[3];
		String stampPoster_db = "";
		String city_db = "";
		String timing[] = new String[3];
		String hall_name[] = new String[3];
		int i=0;
		int flag[]= {30};
		
		
		
		//Database Connection
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT distinct movie_hall FROM Movie_Details md,Booking_Timings bt where name= '"+movie_name+"' and md.movie_Id=bt.movie_Id");
			while(rs.next()) {
				hall_name[i] = rs.getString("movie_hall");
				System.out.println(hall_name[i]);
				i++;
			}
			rs = st.executeQuery("select * from movie_details where name='"+movie_name+"'");
			if(rs.next()) {
				certification_db = rs.getString("certification");
				genre_db = rs.getString("genre");
				duration_db = rs.getString("duration");
				stampPoster_db = rs.getString("stamp_Poster");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel("-");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		label.setFont(new Font("Tahoma", Font.PLAIN, 37));
		label.setBounds(931, 4, 14, 30);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("x");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label_2.setBounds(965, 0, 46, 31);
		frame.getContentPane().add(label_2);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bangalore", "Patna", "Ranchi"}));
		comboBox.setBounds(724, 52, 83, 38);
		frame.getContentPane().add(comboBox);
		
		JLabel label_3 = new JLabel(username);
		label_3.setBounds(849, 58, 72, 26);
		frame.getContentPane().add(label_3);
		
		JLabel label_5 = new JLabel("");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Login l = new Login();
				l.frame.setUndecorated(true);
				l.frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		label_5.setIcon(new ImageIcon(BookingTimings.class.getResource("/Images/logout (1).png")));
		label_5.setBounds(943, 53, 33, 38);
		frame.getContentPane().add(label_5);
		
		JLabel lblNewLabel_1 = new JLabel(movie_name);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(28, 125, 439, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblUa = new JLabel(certification_db);
		lblUa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUa.setBounds(28, 174, 33, 30);
		frame.getContentPane().add(lblUa);
		
		JLabel lblThriller = new JLabel(genre_db);
		lblThriller.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThriller.setBounds(71, 173, 60, 30);
		frame.getContentPane().add(lblThriller);

		String[] duration_time_array = duration_db.split(":");
		JLabel lblDuration = new JLabel("Duration : " + duration_time_array[0] + " hrs " + duration_time_array[1] +" min");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDuration.setBounds(28, 202, 207, 30);
		frame.getContentPane().add(lblDuration);
		
		JLabel lblNewLabel_4 = new JLabel(flag[0]+ " Aug, 2019");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(28, 227, 207, 26);
		frame.getContentPane().add(lblNewLabel_4);
		
		Border borderOrange = BorderFactory.createLineBorder(Color.ORANGE, 1);
		
		JLabel lbl31Aug = new JLabel("");
		
		JLabel lbl1Sept = new JLabel("");
		
		JLabel lbl30Aug = new JLabel("");
		lbl30Aug.setBorder(borderOrange);
		lbl30Aug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl30Aug.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				flag[0]=30;
				
				lbl30Aug.setBorder(borderOrange);
				lbl31Aug.setBorder(null);
				lbl1Sept.setBorder(null);
				
				lblNewLabel_4.setText(flag[0]+ " Aug, 2019");
			}
		});
		lbl30Aug.setIcon(new ImageIcon(BookingTimings.class.getResource("/Images/calendar-interface-symbol-tool.png")));
		lbl30Aug.setBounds(31, 279, 40, 51);
		lbl30Aug.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(lbl30Aug);
		
		lbl31Aug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl31Aug.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				flag[0]=31;
				
				lbl31Aug.setBorder(borderOrange);
				lbl30Aug.setBorder(null);
				lbl1Sept.setBorder(null);
				
				lblNewLabel_4.setText(flag[0]+ " Aug, 2019");
				
			}
		});
		lbl31Aug.setIcon(new ImageIcon(BookingTimings.class.getResource("/Images/calendar-interface-symbol-tool.png")));
		lbl31Aug.setBounds(102, 279, 40, 51);
		lbl31Aug.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(lbl31Aug);
		
		JLabel lblAug = new JLabel("30 Aug");
		lblAug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAug.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblAug.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAug.setBounds(28, 263, 60, 17);
		frame.getContentPane().add(lblAug);
		
		JLabel lblAug_1 = new JLabel("1 Sept");
		lblAug_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAug_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblAug_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAug_1.setBounds(166, 263, 46, 17);
		frame.getContentPane().add(lblAug_1);
		
		JLabel lblNewLabel_3 = new JLabel(hall_name[0]);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(28, 387, 166, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		JSeparator separator_1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_1.setPreferredSize(new Dimension(3, 50));
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBounds(28, 338, 918, 19);
		frame.getContentPane().add(separator_1);
		
		JLabel lblAm = new JLabel("9:00 AM");
		lblAm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAm.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='9:00:00' and movie_hall='"+hall_name[0]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		lblAm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAm.setBounds(347, 383, 72, 38);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		lblAm.setBorder(border);
		frame.getContentPane().add(lblAm);
		
		JLabel lblAm_1 = new JLabel("12:00 AM");
		lblAm_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAm_1.setCursor(new Cursor(Cursor.HAND_CURSOR));			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='12:00:00' and movie_hall='"+hall_name[0]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		lblAm_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAm_1.setBounds(485, 383, 72, 38);
		lblAm_1.setBorder(border);
		frame.getContentPane().add(lblAm_1);
		
		JLabel lblPm = new JLabel("3:00 PM");
		lblPm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPm.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='15:00:00' and movie_hall='"+hall_name[0]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		lblPm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPm.setBounds(624, 383, 72, 38);
		lblPm.setBorder(border);
		frame.getContentPane().add(lblPm);
		
		JLabel lblAbhinayTheatreGandhinagar = new JLabel(hall_name[1]);
		lblAbhinayTheatreGandhinagar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAbhinayTheatreGandhinagar.setBounds(28, 484, 226, 30);
		frame.getContentPane().add(lblAbhinayTheatreGandhinagar);
		
		JLabel label_8 = new JLabel("9:00 AM");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_8.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='9:00:00' and movie_hall='"+hall_name[1]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(347, 484, 72, 38);
		label_8.setBorder(border);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("12:00 AM");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_9.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='12:00:00' and movie_hall='"+hall_name[1]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_9.setBounds(485, 484, 72, 38);
		label_9.setBorder(border);
		frame.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("3:00 PM");
		label_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_10.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='15:00:00' and movie_hall='"+hall_name[1]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(624, 484, 72, 38);
		label_10.setBorder(border);
		frame.getContentPane().add(label_10);
		
		JLabel lblBalajiDigitalk = new JLabel(hall_name[2]);
		lblBalajiDigitalk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBalajiDigitalk.setBounds(28, 580, 264, 30);
		frame.getContentPane().add(lblBalajiDigitalk);
		
		JLabel label_11 = new JLabel("9:00 AM");
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_11.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='9:00:00' and movie_hall='"+hall_name[2]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(347, 572, 72, 38);
		label_11.setBorder(border);
		frame.getContentPane().add(label_11);
		
		JLabel label_12 = new JLabel("12:00 AM");
		label_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_12.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='12:00:00' and movie_hall='"+hall_name[2]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_12.setBounds(485, 572, 72, 38);
		label_12.setBorder(border);
		frame.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("3:00 PM");
		label_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_13.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Connection con;

				//Storing details in database
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select slot_id from booking_timings where timing='15:00:00' and movie_hall='"+hall_name[2]+"' and screening = '2019-08-"+flag[0]+"'");
					if(rs.next())
						slot_no_db = rs.getInt("slot_id");
				}
				catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				SeatConfirmation sc = new SeatConfirmation(username,slot_no_db,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
			}
		});
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_13.setBounds(624, 572, 72, 38);
		label_13.setBorder(border);
		frame.getContentPane().add(label_13);
		
		JLabel label_14 = new JLabel("BACK");
		label_14.setBorder(border);
		label_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MoviePage mv = new MoviePage(username,movie_name);
				mv.frame.setUndecorated(true);
				mv.frame.setVisible(true);
			}
		});
		label_14.setIcon(new ImageIcon(BookingTimings.class.getResource("/Images/icons8-long-arrow-left-52 (1).png")));
		label_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_14.setBounds(28, 4, 119, 48);
		frame.getContentPane().add(label_14);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(BookingTimings.class.getResource(stampPoster_db)));
		lblNewLabel_5.setBounds(724, 135, 222, 191);
		frame.getContentPane().add(lblNewLabel_5);
		
		lbl1Sept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag[0]=1;
				
				lbl1Sept.setBorder(borderOrange);
				lbl30Aug.setBorder(null);
				lbl31Aug.setBorder(null);
				
				lblNewLabel_4.setText(flag[0]+ " Sept, 2019");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl1Sept.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lbl1Sept.setIcon(new ImageIcon(BookingTimings.class.getResource("/Images/calendar-interface-symbol-tool.png")));
		lbl1Sept.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1Sept.setBounds(170, 279, 40, 51);
		frame.getContentPane().add(lbl1Sept);
		
		JLabel lblAug_2 = new JLabel("31 Aug");
		lblAug_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAug_2.setBounds(96, 263, 60, 17);
		frame.getContentPane().add(lblAug_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BookingTimings.class.getResource("/Images/raazi (5).jpg")));
		lblNewLabel.setBounds(0, 0, 966, 181);
		
		
	}
}
