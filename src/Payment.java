import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JRadioButton;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Payment {

	public JFrame frame;
	private JTextField cardNoField;
	private JTextField textField_1;
	private JTextField expiryDateField;
	private JTextField cvvField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Payment(String username,int slot_no,int amount,String movie_name,int booking_status_db[]) {
		initialize(username,slot_no,amount,movie_name,booking_status_db);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username,int slot_no,int amount,String movie_name,int booking_status_db[]) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 1);
		panel.setBorder(border);
		panel.setBounds(152, 94, 725, 494);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton selectCreditCard = new JRadioButton("Credit Card");
		selectCreditCard.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectCreditCard.setBounds(174, 32, 105, 21);
		panel.add(selectCreditCard);
		
		JRadioButton selectDebitCard = new JRadioButton("Debit Card");
		selectDebitCard.setFont(new Font("Tahoma", Font.BOLD, 12));
		selectDebitCard.setBounds(39, 33, 105, 21);
		selectDebitCard.setSelected(true);
		panel.add(selectDebitCard);
		
		JLabel lblName = new JLabel("Card No.");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(39, 85, 58, 26);
		panel.add(lblName);
		
		cardNoField = new JTextField();
		cardNoField.setBounds(131, 86, 191, 33);
		panel.add(cardNoField);
		cardNoField.setColumns(10);
		
		JLabel lblNameOnThe = new JLabel("Name on The Card");
		lblNameOnThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNameOnThe.setBounds(39, 158, 134, 26);
		panel.add(lblNameOnThe);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(174, 152, 191, 33);
		panel.add(textField_1);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExpiryDate.setBounds(39, 228, 80, 26);
		panel.add(lblExpiryDate);
		
		expiryDateField = new JTextField("MM/YY");
		expiryDateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(expiryDateField.getText().equals("MM/YY"))
					expiryDateField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(expiryDateField.getText().equals("MM/YY") || expiryDateField.getText().equals(""))
					expiryDateField.setText("MM/YY");
			}
		});
		expiryDateField.setColumns(10);
		expiryDateField.setBounds(131, 222, 80, 33);
		panel.add(expiryDateField);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCvv.setBounds(256, 228, 29, 26);
		panel.add(lblCvv);
		
		cvvField = new JTextField();
		cvvField.setColumns(10);
		cvvField.setBounds(295, 222, 70, 33);
		panel.add(cvvField);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(Payment.class.getResource("/Images/visa_PNG32.jpg")));
		label_7.setBounds(51, 301, 90, 58);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(Payment.class.getResource("/Images/mastercard_PNG24.jpg")));
		label_8.setBounds(189, 301, 90, 58);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(Payment.class.getResource("/Images/580b57fcd9996e24bc43c530.jpg")));
		label_9.setBounds(331, 301, 105, 58);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(Payment.class.getResource("/Images/kisspng-google-pay-send-android-apple-wallet-google-play-icon-size-google-wallet-logo-5ab0813204f484.2495445215215168500203.jpg")));
		label_10.setBounds(481, 301, 105, 58);
		panel.add(label_10);
		
		JLabel lblinvalidCardNumber = new JLabel();
		lblinvalidCardNumber.setForeground(Color.RED);
		lblinvalidCardNumber.setBounds(131, 119, 120, 13);
		panel.add(lblinvalidCardNumber);
		
		JLabel lblinvalidCvv = new JLabel();
		lblinvalidCvv.setForeground(Color.RED);
		lblinvalidCvv.setBounds(295, 258, 120, 13);
		panel.add(lblinvalidCvv);
		
		JLabel lblinvalidExpiry = new JLabel();
		lblinvalidExpiry.setForeground(Color.RED);
		lblinvalidExpiry.setBounds(131, 258, 120, 13);
		panel.add(lblinvalidExpiry);
		
		Button button = new Button("Pay");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cardRegex = "^((4\\d{3})|(5[1-5]\\d{2})|(6011))-?\\d{4}-?\\d{4}-?\\d{4}|3[4,7]\\d{13}$";
				Pattern pat = Pattern.compile(cardRegex);
				Matcher m = pat.matcher(cardNoField.getText());
				
				String cvvRegex= "^[0-9]{3,4}$";
				pat = Pattern.compile(cvvRegex);
				Matcher m1 = pat.matcher(cvvField.getText());
				
				String expDateRegex= "^\\d{2}\\/\\d{2}$";
				pat = Pattern.compile(expDateRegex);
				Matcher m2 = pat.matcher(expiryDateField.getText());
				
				if(m.matches() == false) {
					lblinvalidCardNumber.setText("*Invalid Card number");
				}
				else if(m1.matches()==false) {
					lblinvalidCvv.setText("* Invalid CVV");
				}
				else if(m2.matches()==false) {
					lblinvalidExpiry.setText("*Invalid Date");
				}
				else {
					lblinvalidCardNumber.setVisible(false);
					lblinvalidCvv.setVisible(false);
					lblinvalidExpiry.setVisible(false);
					
					Connection con;
					//Storing details in database
					try {
						String timing = "";
						int movie_id=0;
						int booking_no=0;
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
						PreparedStatement store;
						for(int i=0;i<36;i++) {
							if(booking_status_db[i]==1) {
								store = con.prepareStatement("UPDATE seat_bookings set username='"+username+"',booking_status= 1 where seat_no="+(i+1)+" and slot_id_for_seat="+slot_no);
								store.executeUpdate();
							}
						}
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from movie_details,booking_timings where name='"+movie_name+"' and slot_id="+slot_no); 
						if(rs.next()) {
							timing = rs.getString("timing");
							movie_id = rs.getInt("movie_id");
						}
						int flag=0;
						Random rand = new Random();
						while(flag==0) {
							flag=1;
							booking_no = rand.nextInt(1000);
							rs = st.executeQuery("select booking_no from customer_bookings");
							while(rs.next()) {
								if(booking_no==rs.getInt("booking_no")) {
										flag=0;
										break;
								}
							}
						}
						store = con.prepareStatement("insert into customer_bookings values("+booking_no+",'"+username+"',"+slot_no+","+movie_id+",'"+timing+"',"+amount+")");
						store.executeUpdate();
						frame.dispose();
						Receipt r = new Receipt(username,slot_no,movie_name,booking_no);
						r.frame.setUndecorated(true);
						r.frame.setVisible(true);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace(); 
					}
					
					
				}				
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 20));
		button.setBackground(new Color(255, 69, 0));
		button.setBounds(196, 395, 258, 57);
		panel.add(button);
		
		selectDebitCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectCreditCard.isSelected())
					selectCreditCard.setSelected(false);
			}
		});
		
		selectCreditCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectDebitCard.isSelected())
					selectDebitCard.setSelected(false);
			}
		});
		
		
		
		JLabel label = new JLabel("x");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label.setBounds(970, 0, 46, 31);
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
		label_3.setBounds(810, 41, 68, 26);
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
		label_5.setIcon(new ImageIcon(Payment.class.getResource("/Images/logout (1).png")));
		label_5.setBounds(946, 35, 33, 38);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("BACK");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				SeatConfirmation sc = new SeatConfirmation(username,slot_no,movie_name);
				sc.frame.setUndecorated(true);
				sc.frame.setVisible(true);
				
			}
		});
		label_6.setIcon(new ImageIcon(Payment.class.getResource("/Images/icons8-long-arrow-left-52 (1).png")));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(10, 10, 119, 48);
		Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
		label_6.setBorder(border1);
		frame.getContentPane().add(label_6);
		
		JLabel lblAmountToBe = new JLabel("Amount to be paid : "+amount);
		lblAmountToBe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAmountToBe.setBounds(152, 71, 151, 13);
		frame.getContentPane().add(lblAmountToBe);
	}
}
