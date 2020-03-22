import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.mail.Session;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Receipt {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public Receipt(String username,int slot_no,String movie_name,int booking_no) {
		initialize(username,slot_no,movie_name,booking_no);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username,int slot_no,String movie_name,int booking_no) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Database variables
		String date_db = "";
		String movie_hall_db = "";
		String time_db = "";
		int booking_no_db=0;
		String email_db = "";
		
		//Database Connection
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Booking_Timings bt,Movie_Details md,customer_bookings cb where name= '"+movie_name+"' and bt.slot_id ="+slot_no);
			if(rs.next()) {
				date_db = rs.getString("screening");
				movie_hall_db = rs.getString("movie_hall");
				time_db = rs.getString("timing");	
			}
			rs = st.executeQuery("Select booking_no from customer_bookings where booking_no="+booking_no);
			if(rs.next())
				booking_no_db=rs.getInt("booking_no");
			rs = st.executeQuery("Select email from customer where username = '"+username+"'");
			if(rs.next()) {
				email_db = rs.getString("email");
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(email_db);
		
		JLabel label = new JLabel("x");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label.setBounds(965, 0, 46, 31);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("-");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 37));
		label_2.setBounds(940, 4, 14, 30);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel(username);
		label_3.setBounds(810, 58, 72, 26);
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
		});
		label_5.setIcon(new ImageIcon(Receipt.class.getResource("/Images/logout (1).png")));
		label_5.setBounds(943, 53, 33, 38);
		frame.getContentPane().add(label_5);
		
		JLabel lblBackToMain = new JLabel("BACK TO MAIN MENU");
		lblBackToMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MainPage mp = new MainPage(username);
				mp.frame.setUndecorated(true);
				mp.frame.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBackToMain.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblBackToMain.setIcon(new ImageIcon(Receipt.class.getResource("/Images/icons8-long-arrow-left-52 (1).png")));
		lblBackToMain.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBackToMain.setBounds(23, 10, 208, 48);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		lblBackToMain.setBorder(border);
		frame.getContentPane().add(lblBackToMain);
		
		JPanel panel = new JPanel();
		panel.setBounds(188, 118, 650, 379);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thank You !!");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(262, 0, 192, 61);
		panel.add(lblNewLabel);
		
		JLabel lblYourBookingsOn = new JLabel("Your bookings on");
		lblYourBookingsOn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYourBookingsOn.setBounds(262, 71, 100, 13);
		panel.add(lblYourBookingsOn);
		
		JLabel lblRaazi = new JLabel(movie_name);
		lblRaazi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRaazi.setBounds(240, 94, 181, 24);
		panel.add(lblRaazi);
		
		JLabel lblOn_1 = new JLabel("on");
		lblOn_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOn_1.setBounds(307, 128, 22, 13);
		panel.add(lblOn_1);
		
		String months[] = {"Jan","Feb","Mar","April","May","June","July","Aug","Sept","Oct","Nov","Dec"};
		String date[] = date_db.split("-");
		
		String postfix="";
		String time[] = time_db.split(":");
		if(time[0].equals("12"))
			postfix="PM";
		else if(Integer.parseInt(time[0])<12)
			postfix="AM";
		else 
			postfix="PM";
		for(int i=0;i<date.length;i++)
			System.out.println(date[i]);
		JLabel lblrdAugustPm = new JLabel(date[2]+" "+ months[Integer.parseInt(date[1])] + " ("+time[0]+" "+postfix+")");
		String lbl_details = date[2]+" "+ months[Integer.parseInt(date[1])] + " ("+time[0]+" "+postfix+")";
		lblrdAugustPm.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblrdAugustPm.setBounds(240, 151, 181, 24);
		panel.add(lblrdAugustPm);
		
		JLabel lblAt = new JLabel("at");
		lblAt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAt.setBounds(307, 192, 22, 13);
		panel.add(lblAt);
		
		JLabel lblCarnivalRocklineMall = new JLabel(movie_hall_db);
		lblCarnivalRocklineMall.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCarnivalRocklineMall.setBounds(227, 213, 192, 24);
		panel.add(lblCarnivalRocklineMall);
		
		JLabel lblIsConfirmed = new JLabel("is confirmed");
		lblIsConfirmed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsConfirmed.setBounds(285, 247, 71, 13);
		panel.add(lblIsConfirmed);
		
		JLabel lblYourBookingId = new JLabel("Your Booking Id is");
		lblYourBookingId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYourBookingId.setBounds(227, 280, 110, 16);
		panel.add(lblYourBookingId);
		
		JLabel label_7 = new JLabel(Integer.toString(booking_no_db));
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBounds(355, 280, 110, 16);
		panel.add(label_7);
		
		try {
			String content = "This is my content";
			ByteArrayOutputStream out = QRCode.from(content).to(ImageType.JPG).stream();
			File file = new File("E:\\QRCode.jpg");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(out.toByteArray());
			fos.flush();
			fos.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		/*JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Receipt.class.getResource("/Images/QR.jpg")));
		label_6.setBounds(262, 320, 100, 49);
		panel.add(label_6);*/
		
		//Sending Email
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String myAccountEmail = "ujjawalverma77@gmail.com";
		String myAccountPassword = "tushar@1";
		String recepient = email_db;
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail,myAccountPassword);
			}
		});
		
		Message message = prepareMessage(username,movie_name,lbl_details,movie_hall_db,session,myAccountEmail,recepient);
		
		try {
			Transport.send(message);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Message sent successfully");
	}
	
	public Message prepareMessage(String username,String movie_name,String lbl_details,String movie_hall_db,Session session,String myAccountEmail,String recepient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("BOOKING CONFIRMATION");
			message.setText("Thank you "+username+" for booking your ticket. Your bookings for "+movie_name+" on "+lbl_details+" at "+movie_hall_db+" is confirmed.Enjoy the Show.Have a Great Day");
			return message;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
