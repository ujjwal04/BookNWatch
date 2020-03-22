import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.util.*;
import java.util.regex.*;

import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.*;
public class Register extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;
	private JTextField textField_1;
	String[] listofImages = {"/Images/Slider_Images/adhm (1).jpg","/Images/Slider_Images/Dhoom2 (1).jpg","/Images/Slider_Images/raazi (9).jpg","/Images/Slider_Images/Fittor (1).jpg","/Images/Slider_Images/Kaabil (1).jpg","/Images/Slider_Images/kedarnath (1).jpg","/Images/Slider_Images/capAmerica.jpg"};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setUndecorated(true);
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
	public Register() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Setting database connection
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 403, 563);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		Timer t = new Timer();
        TimerTask task = new TimerTask() {
        	int i=0;
        	public void run() {
        		try {
        			if(i==7)
        				i=0;
        			lblNewLabel.setIcon(new ImageIcon(MainPage.class.getResource(listofImages[i])));
        			i++;
        		}
        		catch(Exception e) {
        			System.out.println(e.getMessage());
        		}
        	}
        };
        t.scheduleAtFixedRate(task,0,3000);
		panel.add(lblNewLabel);
		
		JLabel lblInvalidUsername = new JLabel("");
		lblInvalidUsername.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblInvalidUsername.setForeground(Color.RED);
		lblInvalidUsername.setBounds(538, 184, 169, 29);
		contentPane.add(lblInvalidUsername);
		
		JLabel lblItShouldBetween = new JLabel("It should between 8 to 30 characters");
		lblItShouldBetween.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblItShouldBetween.setBounds(538, 180, 169, 38);
		contentPane.add(lblItShouldBetween);
		
		JLabel lblInvalidEmail = new JLabel();
		lblInvalidEmail.setForeground(Color.RED);
		lblInvalidEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblInvalidEmail.setBounds(538, 275, 169, 29);
		contentPane.add(lblInvalidEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(538, 304, 138, 20);
		contentPane.add(lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(538, 334, 258, 38);
		contentPane.add(passwordField_1);
		
		JLabel lblInvalidPassword = new JLabel("");
		lblInvalidPassword.setForeground(Color.RED);
		lblInvalidPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblInvalidPassword.setBounds(538, 363, 169, 30);
		contentPane.add(lblInvalidPassword);
		
		JLabel lblPasswordNotMatch = new JLabel("");
		lblPasswordNotMatch.setForeground(Color.RED);
		lblPasswordNotMatch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPasswordNotMatch.setBounds(538, 483, 169, 20);
		contentPane.add(lblPasswordNotMatch);
		
		JLabel passConstraints = new JLabel("<html>It should be atleast 8 characrters long with atleast <br>one lowercase alphabet,one uppercase alphabet,one digit between 0-9 and one special character<html>");
		passConstraints.setFont(new Font("Tahoma", Font.PLAIN, 9));
		passConstraints.setBounds(538, 363, 258, 55);
		contentPane.add(passConstraints);
		
		Button button = new Button("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailRegx = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                        "[a-zA-Z0-9_+&*-]+)*@" + 
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                        "A-Z]{2,7}$"; 
				Pattern pat = Pattern.compile(emailRegx);
				Matcher m = pat.matcher(textField_1.getText());	
				
				String passRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[0-9])[a-zA-Z0-9@#$%]{8,40}";
				String passText = new String(passwordField_1.getPassword());
				String repassText = new String(passwordField.getPassword());
				Pattern patPass = Pattern.compile(passRegex);
				Matcher matPass = patPass.matcher(passText);
				
				if(textField.getText().length()<8 || textField.getText().length()>30) {
					lblInvalidUsername.setText("* Invalid Username");
					Border border = BorderFactory.createLineBorder(Color.RED, 1);
					textField.setBorder(border);
					contentPane.remove(lblItShouldBetween);
				}
				else if(m.matches()== false) {
					lblInvalidEmail.setText("* Invalid Email");
					Border border = BorderFactory.createLineBorder(Color.RED, 1);
					textField_1.setBorder(border);
				}
				else if(passText.length()<8 || passText.length()>30) {
					lblInvalidPassword.setText("* Invalid Password");
					Border border = BorderFactory.createLineBorder(Color.RED, 1);
					passwordField_1.setBorder(border);
					passConstraints.setVisible(false);
					
				}
				else if(passText.length()>=8 || passText.length()<=30) {	
					if(matPass.matches()==false) {
						Border border = BorderFactory.createLineBorder(Color.RED, 1);
						passwordField_1.setBorder(border);
						lblInvalidPassword.setText("* Invalid Password");
						passConstraints.setVisible(false);
						
					}
					else {
						if(passText.equals(repassText)==false) {
							Border border = BorderFactory.createLineBorder(Color.RED, 1);
							passwordField.setBorder(border);
							lblPasswordNotMatch.setText("* Password does not match");
						}
						else {
							dispose();
							
							Connection con;
							//Storing details in database
							try {
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
								String password = new String(passwordField.getPassword());
								System.out.println(password);
								PreparedStatement store = con.prepareStatement("INSERT INTO Customer(Username,Email,Password) VALUES('"+textField.getText()+"','"+textField_1.getText()+"','"+password+"')");
								store.executeUpdate();
								System.out.println("Insertion successful");
								MainPage mv = new MainPage(textField.getText());
								mv.frame.setUndecorated(true);
								mv.frame.setVisible(true);	
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 20));
		button.setBackground(new Color(255, 69, 0));
		button.setBounds(538, 506, 258, 57);
		contentPane.add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(538, 448, 258, 38);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("Login");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login login = new Login();
				login.frame.setUndecorated(true);
				login.frame.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(538, 54, 138, 57);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Register");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(632, 67, 85, 31);
		contentPane.add(label_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(632, 96, 86, 51);
		contentPane.add(separator_1);
		
		JLabel label_2 = new JLabel("Username");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(538, 122, 138, 20);
		contentPane.add(label_2);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(538, 212, 138, 20);
		contentPane.add(lblEmail);
		
		JLabel lblReenterPassword = new JLabel("Re-Enter Password");
		lblReenterPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReenterPassword.setBounds(538, 416, 138, 20);
		contentPane.add(lblReenterPassword);
		
		JLabel label_3 = new JLabel("x");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label_3.setBounds(875, 0, 46, 31);
		contentPane.add(label_3);
		
		JLabel label_5 = new JLabel("-");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 37));
		label_5.setBounds(846, 3, 14, 30);
		contentPane.add(label_5);
		
		textField = new JTextField();
		textField.setBounds(538, 152, 258, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(538, 242, 258, 38);
		contentPane.add(textField_1);
	}
}
