import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MoviePage {

	public JFrame frame;

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public MoviePage(String username,String movie_name) {
		initialize(username,movie_name);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username,String movie_name) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 10, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Variables for database
		String movie_name_db = "";
		String trailer_db = "";
		float imdbRating_db=0;
		String language_db= "";
		String certification_db = "" ;
		String cast_db = "";
		String description_db = "";
		String moviePoster_db = "";
		
		
		
		//Database Connection
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Movie_Details where name= '"+movie_name+"'");
			System.out.println("Success");
			if(rs.next()) {
				movie_name_db = rs.getString(1);
				trailer_db = rs.getString(3);
				imdbRating_db = rs.getFloat(4);
				language_db = rs.getString(5);
				certification_db = rs.getString(6);
				cast_db = rs.getString(7);
				description_db = rs.getString(8);
				moviePoster_db = rs.getString(9);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String cities[] = {"Bangalore","Patna","Ranchi"};
		JComboBox cb = new JComboBox(cities);
		cb.setModel(new DefaultComboBoxModel(new String[] {"Bangalore", "Patna", "Ranchi"}));
		cb.setBounds(695, 52, 83, 38);
		
		frame.getContentPane().add(cb);
		
		JLabel lblUjjwal = new JLabel(username);
		lblUjjwal.setBounds(822, 58, 87, 26);
		frame.getContentPane().add(lblUjjwal);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Login l = new Login();
				l.frame.setUndecorated(true);
				l.frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblNewLabel.setBounds(943, 53, 33, 38);
		lblNewLabel.setIcon(new ImageIcon(MoviePage.class.getResource("/Images/logout (1).png")));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(49, 136, 367, 531);
		frame.getContentPane().add(panel);	
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MoviePage.class.getResource(moviePoster_db)));
		panel.add(lblNewLabel_3);
		
		frame.getContentPane().add(createVerticalSeperator());
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(603, 123, 83, 38);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel movieName_field = new JLabel("<html>"+movie_name_db+"</html>");
		movieName_field.setBounds(759, 123, 165, 38);
		movieName_field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(movieName_field);
		
		frame.getContentPane().add(createHorizontalSeperator());
		
		JLabel lblTrailer = new JLabel("Trailer");
		lblTrailer.setBounds(603, 171, 62, 38);
		lblTrailer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblTrailer);
		
		JLabel trailer_Field = new JLabel(trailer_db);
		trailer_Field.setBounds(759, 171, 83, 38);
		trailer_Field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(trailer_Field);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBounds(603, 219, 342, 19);
		separator.setPreferredSize(new Dimension(3, 50));
		separator.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(separator);
		
		JLabel lblImdbRating = new JLabel("IMDB Rating");
		lblImdbRating.setBounds(603, 237, 95, 38);
		lblImdbRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblImdbRating);
		
		JLabel imdbRating_field = new JLabel(Float.toString(imdbRating_db));
		imdbRating_field.setBounds(759, 237, 83, 38);
		imdbRating_field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(imdbRating_field);
		
		JSeparator separator_1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_1.setBounds(597, 285, 348, 19);
		separator_1.setPreferredSize(new Dimension(3, 50));
		separator_1.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(separator_1);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(603, 303, 95, 38);
		lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblLanguage);
		
		JLabel language_Field = new JLabel(language_db);
		language_Field.setBounds(759, 303, 83, 38);
		language_Field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(language_Field);
		
		JSeparator separator_2 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_2.setBounds(603, 341, 348, 19);
		separator_2.setPreferredSize(new Dimension(3, 50));
		separator_2.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(separator_2);
		
		JLabel lblCertification = new JLabel("Certification");
		lblCertification.setBounds(603, 358, 95, 38);
		lblCertification.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblCertification);
		
		JLabel certification_Field = new JLabel(certification_db);
		certification_Field.setBounds(759, 358, 83, 38);
		certification_Field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(certification_Field);
		
		JSeparator separator_3 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_3.setBounds(597, 406, 348, 19);
		separator_3.setPreferredSize(new Dimension(3, 50));
		separator_3.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(separator_3);
		
		JLabel lblCast = new JLabel("Cast");
		lblCast.setBounds(603, 424, 95, 38);
		lblCast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblCast);
		
		JLabel starcast_Field = new JLabel(cast_db);
		starcast_Field.setBounds(759, 406, 177, 82);
		starcast_Field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(starcast_Field);
		
		JSeparator separator_4 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_4.setBounds(603, 498, 348, 19);
		separator_4.setPreferredSize(new Dimension(3, 50));
		separator_4.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(separator_4);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(603, 515, 95, 38);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblDescription);
		
		JLabel movieDescription_Field = new JLabel(description_db);
		movieDescription_Field.setBounds(759, 370, 211, 393);
		movieDescription_Field.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(movieDescription_Field);
		
		JSeparator separator_5 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_5.setBounds(598, 648, 348, 19);
		separator_5.setPreferredSize(new Dimension(3, 50));
		separator_5.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(separator_5);
		
		Button button = new Button("Book Tickets");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				BookingTimings bt = new BookingTimings(username,movie_name);
				bt.frame.setUndecorated(true);
				bt.frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		button.setBounds(365, 701, 258, 57);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 20));
		button.setBackground(new Color(255, 69, 0));
		frame.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("x");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label_1.setBounds(965, 0, 46, 31);
		frame.getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel("-");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 37));
		label_3.setBounds(933, 4, 14, 30);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("BACK");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MainPage mp = new MainPage(username);
				mp.frame.setUndecorated(true);
				mp.frame.setVisible(true);
				
				
			}
		});
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		label_4.setBorder(border);
		label_4.setIcon(new ImageIcon(MoviePage.class.getResource("/Images/icons8-long-arrow-left-52 (1).png")));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(49, 10, 119, 48);
		frame.getContentPane().add(label_4);
		
	}

	private Component createVerticalSeperator() {
		JSeparator vertical = new JSeparator(SwingConstants.VERTICAL);
		vertical.setBounds(495, 151, 50, 493);
		vertical.setForeground(Color.DARK_GRAY);
		vertical.setPreferredSize(new Dimension(3,50));
        return vertical;
	}
	
	private Component createHorizontalSeperator() {
		JSeparator horizontal = new JSeparator(SwingConstants.HORIZONTAL);
		horizontal.setBounds(603, 163, 342, 19);
		horizontal.setForeground(Color.DARK_GRAY);
		horizontal.setPreferredSize(new Dimension(3,50));
        return horizontal;
	}
}
