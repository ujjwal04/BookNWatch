import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.border.Border;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.PreparedStatement;

public class SeatConfirmation {

	public JFrame frame;

	/**
	 * Create the application.
	 */
	public SeatConfirmation(String username,int slot_no,String movie_name) {
		initialize(username,slot_no,movie_name);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username,int slot_no,String movie_name) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		//Database variables
		int seat_no_db[] = new int[36];
		int booking_status_db[] = new int[36];
		
		for(int i=0;i<36;i++)
			booking_status_db[i]=0;
		
		
		
		
		//Database Connection
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM booking_timings bt,seat_bookings sb where slot_id= "+slot_no+" and bt.slot_id=sb.slot_id_for_seat");
			while(rs.next()) {
				seat_no_db[rs.getInt("seat_no")-1] = rs.getInt("seat_no");
				booking_status_db[rs.getInt("seat_no")-1] = rs.getInt("booking_status");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel(username);
		label.setBounds(810, 48, 68, 26);
		frame.getContentPane().add(label);
		
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
		label_3.setBounds(934, 4, 14, 30);
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
		label_5.setIcon(new ImageIcon(SeatConfirmation.class.getResource("/Images/logout (1).png")));
		label_5.setBounds(941, 42, 33, 38);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("BACK");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				BookingTimings bt = new BookingTimings(username,movie_name);
				bt.frame.setUndecorated(true);
				bt.frame.setVisible(true);
			}
		});
		label_6.setIcon(new ImageIcon(SeatConfirmation.class.getResource("/Images/icons8-long-arrow-left-52 (1).png")));
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		label_6.setBorder(border);
		label_6.setBounds(21, 4, 119, 48);
		frame.getContentPane().add(label_6);
		
		JLabel lblChooseYourSeats = new JLabel("Choose Your Seats : ");
		lblChooseYourSeats.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChooseYourSeats.setBounds(21, 88, 166, 48);
		frame.getContentPane().add(lblChooseYourSeats);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setPreferredSize(new Dimension(3, 50));
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(21, 126, 150, 19);
		frame.getContentPane().add(separator);
		
		JLabel lblExecutive = new JLabel("EXECUTIVE-Rs. 200.00");
		lblExecutive.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExecutive.setBounds(21, 146, 166, 31);
		frame.getContentPane().add(lblExecutive);
		
		JSeparator separator_1 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_1.setPreferredSize(new Dimension(3, 50));
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBounds(21, 175, 530, 19);
		frame.getContentPane().add(separator_1);
		
		JLabel seat[] = new JLabel[36];
		
		for(int i=0;i<36;i++) {
			System.out.println(i);
			seat[i] = new JLabel(String.valueOf(i+1));
			seat[i].setOpaque(true);
			seat[i].setBorder(border);
			seat[i].setHorizontalAlignment(SwingConstants.CENTER);
			frame.getContentPane().add(seat[i]);
		}
		
		seat[0].setBounds(40, 194, 46, 38);
		seat[1].setBounds(115, 194, 46, 38);
		seat[2].setBounds(191, 194, 42, 38);
		seat[3].setBounds(297, 194, 42, 38);
		seat[4].setBounds(359, 194, 42, 38);
		seat[5].setBounds(419, 194, 42, 38);
		seat[6].setBounds(40, 259, 46, 38);
		seat[7].setBounds(115, 259, 46, 38);
		seat[8].setBounds(191, 259, 42, 38);
		seat[9].setBounds(297, 259, 42, 38);
		seat[10].setBounds(359, 259, 42, 38);
		seat[11].setBounds(419, 259, 42, 38);
		seat[12].setBounds(40, 385, 46, 38);
		seat[13].setBounds(115, 385, 46, 38);
		seat[14].setBounds(191, 385, 42, 38);
		seat[15].setBounds(297, 385, 42, 38);
		seat[16].setBounds(359, 385, 42, 38);
		seat[17].setBounds(419, 385, 42, 38);
		seat[18].setBounds(40, 439, 46, 38);
		seat[19].setBounds(115, 439, 46, 38);
		seat[20].setBounds(191, 439, 42, 38);
		seat[21].setBounds(297, 439, 42, 38);
		seat[22].setBounds(359, 439, 42, 38);
		seat[23].setBounds(419, 439, 42, 38);
		seat[24].setBounds(40, 539, 46, 38);
		seat[25].setBounds(115, 539, 46, 38);
		seat[26].setBounds(191, 539, 42, 38);
		seat[27].setBounds(297, 539, 42, 38);
		seat[28].setBounds(359, 539, 42, 38);
		seat[29].setBounds(419, 539, 42, 38);
		seat[30].setBounds(40, 587, 46, 38);
		seat[31].setBounds(115, 587, 46, 38);
		seat[32].setBounds(191, 587, 42, 38);
		seat[33].setBounds(297, 587, 42, 38);
		seat[34].setBounds(359, 587, 42, 38);
		seat[35].setBounds(419, 587, 42, 38);
		
		if(booking_status_db[0]==1)
			seat[0].setBackground(Color.GRAY);
		else {
		seat[0].setBackground(Color.WHITE);
		seat[0].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[0].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[0].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[0].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[0] = 1;
				}
				else {
					seat[0].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[0] = 0;
				}
			}
		});
		}
		//frame.getContentPane().add(seat_1);
		
		if(booking_status_db[9]==1)
			seat[9].setBackground(Color.GRAY);
		else {
		seat[9].setBackground(Color.WHITE);
		seat[9].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[9].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[9].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[9].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[9] = 1;
				}
				else {
					seat[9].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[9] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[1]==1)
			seat[1].setBackground(Color.GRAY);
		else {
		seat[1].setBackground(Color.WHITE);	
		seat[1].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[1].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[1].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[1].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[1] = 1;
				}
				else {
					seat[1].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[1] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[2]==1)
			seat[2].setBackground(Color.GRAY);
		else {
		seat[2].setBackground(Color.WHITE);
		seat[2].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[2].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[2].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[2].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[2] = 1;
				}
				else {
					seat[2].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[2] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[3]==1)
			seat[3].setBackground(Color.GRAY);
		else {
		seat[3].setBackground(Color.WHITE);
		seat[3].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[3].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[3].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[3].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[3] = 1;
				}
				else {
					seat[3].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[3] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[8]==1)
			seat[8].setBackground(Color.GRAY);
		else {
		seat[8].setBackground(Color.WHITE);
		seat[8].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[8].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[8].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[8].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[8] = 1;
				}
				else {
					seat[8].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[8] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[10]==1)
			seat[10].setBackground(Color.GRAY);
		else {
		seat[10].setBackground(Color.WHITE);
		seat[10].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[10].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[10].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[10].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[10] = 1;
				}
				else {
					seat[10].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[10] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[11]==1)
			seat[11].setBackground(Color.GRAY);
		else {
		seat[11].setBackground(Color.WHITE);	
		seat[11].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[11].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[11].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[11].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[11] = 1;
				}
				else {
					seat[11].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[11] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[4]==1)
			seat[4].setBackground(Color.GRAY);
		else {
		seat[4].setBackground(Color.WHITE);
		seat[4].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[4].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[4].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[4].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[4] = 1;
				}
				else {
					seat[4].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[4] = 0;
				}
			}
		});
		}
		
		
		if(booking_status_db[5]==1)
			seat[5].setBackground(Color.GRAY);
		else {
		seat[5].setBackground(Color.WHITE);
		seat[5].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[5].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[5].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[5].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[5] = 1;
				}
				else {
					seat[5].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[5] = 0;
				}
			}
		});
		}

		if(booking_status_db[6]==1)
			seat[6].setBackground(Color.GRAY);
		else {
		seat[6].setBackground(Color.WHITE);
		seat[6].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[6].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[6].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[6].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[6] = 1;
				}
				else {
					seat[6].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[6] = 0;
				}
			}
		});
		}
		seat[7].setBorder(border);
		seat[7].setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(seat[7]);
		
		if(booking_status_db[7]==1)
			seat[7].setBackground(Color.GRAY);
		else {
		seat[7].setBackground(Color.WHITE);
		seat[7].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[7].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[7].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[7].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[7] = 1;
				}
				else {
					seat[7].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[7] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[12]==1)
			seat[12].setBackground(Color.GRAY);
		else {
		seat[12].setBackground(Color.WHITE);
		seat[12].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[12].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[12].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[12].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[12] = 1;
				}
				else {
					seat[12].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[12] = 0;
				}
			}
		});
		}

		if(booking_status_db[13]==1)
			seat[13].setBackground(Color.GRAY);
		else {
		seat[13].setBackground(Color.WHITE);	
		seat[13].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[13].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[13].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[13].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[13] = 1;
				}
				else {
					seat[13].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[13] = 0;
				}
			}
		});
		}
		seat[13].setBorder(border);
		seat[13].setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(seat[13]);
		
		if(booking_status_db[14]==1)
			seat[14].setBackground(Color.GRAY);
		else {
		seat[14].setBackground(Color.WHITE);	
		seat[14].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[14].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[14].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[14].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[14] = 1;
				}
				else {
					seat[14].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[14] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[15]==1)
			seat[15].setBackground(Color.GRAY);
		else {
			seat[15].setBackground(Color.WHITE);
		seat[15].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[15].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[15].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[15].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[15] = 1;
				}
				else {
					seat[15].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[15] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[16]==1)
			seat[16].setBackground(Color.GRAY);
		else {
			seat[16].setBackground(Color.WHITE);	
		seat[16].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[16].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[16].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[16].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[16] = 1;
				}
				else {
					seat[16].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[16] = 0;
				}
			}
		});
		}

		if(booking_status_db[17]==1)
			seat[17].setBackground(Color.GRAY);
		else {
			seat[17].setBackground(Color.WHITE);
		seat[17].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[17].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[17].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[17].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[17] = 1;
				}
				else {
					seat[17].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[17] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[18]==1)
			seat[18].setBackground(Color.GRAY);
		else {
			seat[18].setBackground(Color.WHITE);
		seat[18].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[18].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[18].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[18].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[18] = 1;
				}
				else {
					seat[18].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[18] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[19]==1)
			seat[19].setBackground(Color.GRAY);
		else {
			seat[19].setBackground(Color.WHITE);
		seat[19].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[19].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[19].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[19].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[19] = 1;
				}
				else {
					seat[19].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[19] = 0;
				}
			}
		});
		}

		if(booking_status_db[20]==1)
			seat[20].setBackground(Color.GRAY);
		else {
			seat[20].setBackground(Color.WHITE);
		seat[20].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[20].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[20].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[20].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[20] = 1;
				}
				else {
					seat[20].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[20] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[21]==1)
			seat[21].setBackground(Color.GRAY);
		else {
			seat[21].setBackground(Color.WHITE);
		seat[21].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[21].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[21].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[21].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[21] = 1;
					
				}
				else {
					seat[21].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[21] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[22]==1)
			seat[22].setBackground(Color.GRAY);
		else {
			seat[22].setBackground(Color.WHITE);
		seat[22].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[22].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[22].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[22].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[22] = 1;
				}
				else {
					seat[22].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[22] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[23]==1)
			seat[23].setBackground(Color.GRAY);
		else {
			seat[23].setBackground(Color.WHITE);
		seat[23].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[23].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[23].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[23].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[23] = 1;
				}
				else {
					seat[23].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[23] = 0;
				}
			}
		});
		}
		
		JLabel lblSilverrs = new JLabel("SILVER-Rs. 200.00");
		lblSilverrs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSilverrs.setBounds(21, 328, 166, 31);
		frame.getContentPane().add(lblSilverrs);
		
		JSeparator separator_2 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_2.setPreferredSize(new Dimension(3, 50));
		separator_2.setForeground(Color.DARK_GRAY);
		separator_2.setBounds(21, 356, 530, 19);
		frame.getContentPane().add(separator_2);
		
		if(booking_status_db[24]==1)
			seat[24].setBackground(Color.GRAY);
		else {
			seat[24].setBackground(Color.WHITE);
		seat[24].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[24].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[24].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[24].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[24] = 1;
				}
				else {
					seat[24].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[24] = 0;
				}
			}
		});
		}
		seat[24].setBorder(border);
		seat[24].setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(seat[24]);
		
		if(booking_status_db[25]==1)
			seat[25].setBackground(Color.GRAY);
		else {
			seat[25].setBackground(Color.WHITE);
		seat[25].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[25].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[25].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[25].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[25] = 1;
				}
				else {
					seat[25].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[25] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[26]==1)
			
			seat[26].setBackground(Color.GRAY);
		else {
			seat[26].setBackground(Color.WHITE);
		seat[26].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[26].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[26].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[26].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[26] = 1;
				}
				else {
					seat[26].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[26] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[27]==1)
			seat[27].setBackground(Color.GRAY);
		else {
			seat[27].setBackground(Color.WHITE);
		seat[27].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[27].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[27].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[27].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[27] = 1;
				}
				else {
					seat[27].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[27] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[28]==1)
			seat[28].setBackground(Color.GRAY);
		else {
			seat[28].setBackground(Color.WHITE);
		seat[28].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[28].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[28].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[28].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[28] = 1;
				}
				else {
					seat[28].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[28] = 0;
				}
			}
		});
		}
		seat[28].setBorder(border);
		seat[28].setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(seat[28]);
		
		if(booking_status_db[29]==1)
			seat[29].setBackground(Color.GRAY);
		else {
			seat[29].setBackground(Color.WHITE);
		seat[29].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[29].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[29].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[29].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[29] = 1;
				}
				else {
					seat[29].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[29] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[30]==1)
			seat[30].setBackground(Color.GRAY);
		else {
			seat[30].setBackground(Color.WHITE);
		seat[30].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[30].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[30].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[30].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[30] = 1;
				}
				else {
					seat[30].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[30] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[31]==1)
			seat[31].setBackground(Color.GRAY);
		else {
			seat[31].setBackground(Color.WHITE);
		seat[31].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[31].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[31].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[31].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[31] = 1;
				}
				else {
					seat[31].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[31] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[32]==1)
			seat[32].setBackground(Color.GRAY);
		else {
			seat[32].setBackground(Color.WHITE);
		seat[32].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[32].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[32].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[32].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[32] = 1;
				}
				else {
					seat[32].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[32] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[33]==1)
			seat[33].setBackground(Color.GRAY);
		else {
			seat[33].setBackground(Color.WHITE);
		seat[33].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[33].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[33].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[33].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[33] = 1;
				}
				else {
					seat[33].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[33] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[34]==1)
			seat[34].setBackground(Color.GRAY);
		else {
			seat[34].setBackground(Color.WHITE);
		seat[34].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[34].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[34].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[34].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[34] = 1;
				}
				else {
					seat[34].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[34] = 0;
				}
			}
		});
		}
		
		if(booking_status_db[35]==1)
			seat[35].setBackground(Color.GRAY);
		else {
			seat[35].setBackground(Color.WHITE);
		seat[35].addMouseListener(new MouseAdapter() {
			int hover=0;
			@Override
			public void mouseEntered(MouseEvent e) {
				seat[35].setBackground(new Color(0, 128, 0));
				System.out.println(hover);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(hover==0)
				seat[35].setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hover==0) {
					seat[35].setBackground(new Color(0, 128, 0));
					hover=1;
					
					booking_status_db[35] = 1;
				}
				else {
					seat[35].setBackground(Color.WHITE);
					hover=0;
					
					booking_status_db[35] = 0;
				}
			}
		});
		}
		
		JLabel lblNormalrs = new JLabel("NORMAL-Rs.180");
		lblNormalrs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNormalrs.setBounds(21, 490, 166, 31);
		frame.getContentPane().add(lblNormalrs);
		
		JSeparator separator_3 = new JSeparator(SwingConstants.HORIZONTAL);
		separator_3.setPreferredSize(new Dimension(3, 50));
		separator_3.setForeground(Color.DARK_GRAY);
		separator_3.setBounds(21, 516, 530, 19);
		frame.getContentPane().add(separator_3);
		
		Button button = new Button("Proceed");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag=0;
				
				for(int i=0;i<36;i++)
					if(seat[i].getBackground().equals(new Color(0,128,0)))
						flag=1;
				if(flag==1) {
				frame.dispose();
				Connection con;
				//Storing details in database
				try {
					int totalAmount = 0;
					int getAmount = 0;
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_ticket_database","root","");
					PreparedStatement store;
					for(int i=0;i<36;i++) {
						if(booking_status_db[i]==1) {
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("SELECT amount from seat_bookings where seat_no="+(i+1)+" and slot_id_for_seat="+slot_no);
							if(rs.next()) {
								if(seat[i].getBackground().equals(new Color(0, 128, 0))) {
									getAmount = rs.getInt("amount");
								    totalAmount+=getAmount;
								}
							}
						}
					}
					Payment p = new Payment(username,slot_no,totalAmount,movie_name,booking_status_db);
					p.frame.setUndecorated(true);
					p.frame.setVisible(true);
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
		button.setBounds(143, 730, 258, 57);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SeatConfirmation.class.getResource("/Images/newscreen.png")));
		lblNewLabel.setBounds(126, 636, 433, 102);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(100, 10, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
