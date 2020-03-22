import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class ForgotPassword extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		Button button = new Button("Submit");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.PLAIN, 20));
		button.setBackground(new Color(255, 69, 0));
		button.setBounds(538, 445, 258, 57);
		getContentPane().add(button);
		
		JLabel label = new JLabel("Username\r\n");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(538, 127, 138, 38);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(538, 175, 258, 38);
		getContentPane().add(textField);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(538, 273, 138, 38);
		getContentPane().add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(538, 321, 258, 38);
		getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ForgotPassword.class.getResource("/Images/raazi (2).jpg")));
		label_1.setBounds(0, 0, 420, 600);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 37));
		label_2.setBounds(822, 3, 14, 30);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
			}
		});
		label_3.setIcon(new ImageIcon(ForgotPassword.class.getResource("/Images/Webp.net-resizeimage.png")));
		label_3.setBounds(846, 12, 30, 13);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("x");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label_4.setBounds(875, 0, 46, 31);
		getContentPane().add(label_4);
		
		JLabel lblBack = new JLabel("BACK");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Login lg = new Login();
				lg.frame.setUndecorated(true);
				lg.frame.setVisible(true);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblBack.setIcon(new ImageIcon(ForgotPassword.class.getResource("/Images/icons8-long-arrow-left-52 (1).png")));
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBack.setBounds(430, 12, 119, 48);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		lblBack.setBorder(border);
		getContentPane().add(lblBack); 
	}
}
