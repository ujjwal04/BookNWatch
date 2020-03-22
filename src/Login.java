import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.sql.Statement;

import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPasswordField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;
	private JTextField textField_1;
	private JPasswordField passwordField;
	String[] listofImages = {"/Images/Slider_Images/adhm (1).jpg","/Images/Slider_Images/Dhoom2 (1).jpg","/Images/Slider_Images/raazi (9).jpg","/Images/Slider_Images/Fittor (1).jpg","/Images/Slider_Images/Kaabil (1).jpg","/Images/Slider_Images/kedarnath (1).jpg","/Images/Slider_Images/capAmerica.jpg"};
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login window = new Login();
		window.frame.setUndecorated(true);
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Login() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel.setBounds(0, 0, 403, 563);
		frame.getContentPane().add(panel);
		
		
		
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(538, 214, 258, 38);
		frame.getContentPane().add(textField_1);
		
		JLabel lblInvalidUsername = new JLabel("");
		lblInvalidUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInvalidUsername.setForeground(Color.RED);
		lblInvalidUsername.setBounds(538, 251, 111, 29);
		frame.getContentPane().add(lblInvalidUsername);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(538, 338, 258, 38);
		frame.getContentPane().add(passwordField_1);
		
		Button button = new Button("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(passwordField_1.getPassword());
				if(textField_1.getText().length()<8 || textField_1.getText().length()>30) {
					lblInvalidUsername.setText("* Invalid Username");
					Border border = BorderFactory.createLineBorder(Color.RED, 1);
					textField_1.setBorder(border);
				}
				else if(pass.equals("")==false) {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM Customer where username='"+textField_1.getText()+"' and password='"+pass+"'"); 
						if(rs.next()) {
							System.out.println("Login Successful");
							System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
							frame.dispose();
							MainPage mp = new MainPage(rs.getString(1));
							mp.frame.setUndecorated(true);
							mp.frame.setVisible(true);
						}
						else {
							System.out.println(textField_1.getText()+passwordField.getPassword());
						}
					}
					catch(Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
				else {
					System.out.println("Kuch nahi");
				}
			}	
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 20));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 69, 0));
		button.setBounds(538, 455, 258, 57);
		frame.getContentPane().add(button);
		
		JLabel lblEmail = new JLabel("Username\r\n");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(538, 166, 138, 38);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(538, 290, 138, 38);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblX = new JLabel("x");
		lblX.setBackground(new Color(255, 165, 0));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblX.setBounds(875, 0, 46, 31);
		frame.getContentPane().add(lblX);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNewLabel_1.setBounds(846, 3, 14, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(538, 54, 60, 57);
		frame.getContentPane().add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(538, 96, 53, 51);
		frame.getContentPane().add(separator);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Register rForm = new Register();
				rForm.setUndecorated(true);
				rForm.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegister.setBounds(632, 67, 85, 31);
		frame.getContentPane().add(lblRegister);
		
		JLabel lblForgotPassword = new JLabel("Forgot Password?");
		lblForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				ForgotPassword fp = new ForgotPassword();
				fp.setUndecorated(true);
				fp.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblForgotPassword.setForeground(Color.BLUE);
		lblForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblForgotPassword.setBounds(538, 373, 111, 38);
		frame.getContentPane().add(lblForgotPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLUE);
		separator_1.setBounds(538, 402, 90, 30);
		frame.getContentPane().add(separator_1);
		
		
	}
}
