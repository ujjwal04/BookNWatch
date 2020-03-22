import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.eclipse.swt.graphics.Image;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSlider;
import java.awt.TextField;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainPage {

    public JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    /*MainPage window =new MainPage();
                    window.frame.setUndecorated(true);
                    window.frame.setVisible(true);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public MainPage(String username) {
        initialize(username);
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String username) {
        frame = new JFrame();
        frame.setBounds(50, 1, 1450, 2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setBackground(Color.WHITE);
        JScrollPane jsp = new JScrollPane(container);
        container.setPreferredSize(new Dimension(1450, 2000));
        container.setLayout(null);
        
        textField = new JTextField("Search for Movies");
        textField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if(textField.getText().equals("Search for Movies"))
					textField.setText("");
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(textField.getText().equals("Search for Movies") || textField.getText().equals(""))
        			textField.setText("");
        	}
        });
        textField.setColumns(10);
        textField.setBounds(25, 50, 496, 38);
        container.add(textField);
        
        JLabel label = new JLabel("x");
        label.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.exit(0);
        	}
        });
        label.setFont(new Font("Tahoma", Font.PLAIN, 23));
        label.setBounds(1415, 0, 46, 31);
        container.add(label);
        
        JLabel label_2 = new JLabel("-");
        label_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setState(JFrame.ICONIFIED);
        	}
        });
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 37));
        label_2.setBounds(1385, 4, 14, 30);
        container.add(label_2);
        
        JComboBox comboBox = new JComboBox(new Object[]{});
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bangalore", "Patna", "Ranchi"}));
        comboBox.setBounds(1130, 49, 132, 38);
        container.add(comboBox);
       
        JLabel label_3 = new JLabel(username);
        label_3.setBounds(1295, 55, 72, 26);
        container.add(label_3);
        
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
        label_5.setIcon(new ImageIcon(MainPage.class.getResource("/Images/logout (1).png")));
        label_5.setBounds(1382, 50, 51, 38);
        container.add(label_5);
        Border border = BorderFactory.createLineBorder(Color.GREEN, 4);
        
        JLayeredPane layeredPane_1 = new JLayeredPane();
        
        JPanel panel1_Front = new JPanel();
        panel1_Front.setBounds(0, 0, 316, 326);
        layeredPane_1.add(panel1_Front);
        
        JPanel panel1_Back = new JPanel();
        panel1_Back.setLayout(null);
        panel1_Back.setBounds(0, 0, 316, 326);
        layeredPane_1.add(panel1_Back);
        panel1_Front.setLayout(null); 
       
        JLabel lblPanel_1 = new JLabel("");
        lblPanel_1.setBounds(0, 0, 316, 326);
        lblPanel_1.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/adhm (2).jpg")));
        panel1_Front.add(lblPanel_1);
        
        JLabel lblAeDilHai = new JLabel("Ae Dil Hai Mushkil");
        lblAeDilHai.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAeDilHai.setBounds(25, 439, 147, 26);
        container.add(lblAeDilHai);
        
        layeredPane_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_1.moveToFront(panel1_Back);
        		layeredPane_1.moveToBack(panel1_Front);
        		panel1_Back.setBackground(new Color(0,0,0,80));
        		panel1_Back.setOpaque(true);
        		layeredPane_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		lblPanel_1.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_1.moveToFront(panel1_Front);
        		
        		lblPanel_1.setBorder(null);
        		
        		//panel1_Front.remove(label_10);
        	}
        });
        layeredPane_1.setBounds(25, 115, 316, 326);
        container.add(layeredPane_1);
        layeredPane_1.setLayout(null);
        
  
        
        JLabel lblPanel = new JLabel("");
        panel1_Back.add(lblPanel);
        
        JLabel label_10 = new JLabel();
        label_10.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        label_10.setBounds(130,40,64,64);
        panel1_Back.add(label_10);
        
        JLabel lblNewLabel = new JLabel("5.8/10");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(133,125, 65, 14);
        panel1_Back.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Drama");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(127,150,79, 20);
        panel1_Back.add(lblNewLabel_1);
        
        JLabel lblMusic = new JLabel("Music");
        lblMusic.setFont(new Font("Tahoma", Font.BOLD,20));
        lblMusic.setForeground(Color.WHITE);
        lblMusic.setBounds(130,200,70,15);
        panel1_Back.add(lblMusic);
        
        JLabel btnViewDetails = new JLabel("View Details");
        btnViewDetails.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Ae Dil Hai Mushkil");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_1.moveToFront(panel1_Back);
        		layeredPane_1.moveToBack(panel1_Front);
        		panel1_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_1.setBorder(border);
        		
        		
        	}
        });
        btnViewDetails.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails.setBackground(Color.GREEN);
        btnViewDetails.setForeground(Color.WHITE);
        btnViewDetails.setOpaque(true);
        btnViewDetails.setBounds(120,250,85,40);
        panel1_Back.add(btnViewDetails);
        
        JLayeredPane layeredPane_2 = new JLayeredPane(); 
        
        JPanel panel2_Front = new JPanel();
        panel2_Front.setLayout(null);
        panel2_Front.setBounds(0, 0, 316, 326);
        layeredPane_2.add(panel2_Front);
        
        JLabel label_6 = new JLabel("");
        label_6.setBounds(0, 0, 316, 326);
        panel1_Front.add(label_6);
        
        
        
        JPanel panel2_Back = new JPanel();
        panel2_Back.setBounds(0, 0, 316, 326);
        panel2_Back.setLayout(null);
        layeredPane_2.add(panel2_Back);
        
        JLabel label_9 = new JLabel("");
        panel2_Back.add(label_9);
        
        JLabel lblPanel_2 = new JLabel("");
        lblPanel_2.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/Fittor (2).jpg")));
        lblPanel_2.setBounds(0, 0, 316, 326);
        panel2_Front.add(lblPanel_2);
        
        JLabel lblFitoor = new JLabel("Fitoor");
        lblFitoor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFitoor.setBounds(378, 439, 147, 26);
        container.add(lblFitoor);
        
        layeredPane_2.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_2.moveToFront(panel2_Back);
        		layeredPane_2.moveToBack(panel2_Front);
        		panel2_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel2_Back.setOpaque(true);
        		
        		lblPanel_2.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_2.moveToFront(panel2_Front);
        		lblPanel_2.setBorder(null);
        	}
        });
        
        layeredPane_2.setLayout(null);
        layeredPane_2.setBounds(378, 115, 316, 326);
        container.add(layeredPane_2);
        
        JLabel star_2 = new JLabel();
        star_2.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_2.setBounds(130,40,64,64);
        panel2_Back.add(star_2);
        
        JLabel rating_2 = new JLabel("5.4/10");
        rating_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_2.setForeground(Color.WHITE);
        rating_2.setBounds(133,125, 65, 14);
        panel2_Back.add(rating_2);
        
        JLabel genre_2 = new JLabel("Drama");
        genre_2.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_2.setForeground(Color.WHITE);
        genre_2.setBounds(127,150,79, 20);
        panel2_Back.add(genre_2);
        
        JLabel genre2_2 = new JLabel("Romance");
        genre2_2.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_2.setForeground(Color.WHITE);
        genre2_2.setBounds(120,200,95,15);
        panel2_Back.add(genre2_2);
        
        JLabel btnViewDetails_2 = new JLabel("View Details");
        btnViewDetails_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Fitoor");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_2.moveToFront(panel2_Back);
        		layeredPane_2.moveToBack(panel2_Front);
        		panel2_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_2.setBorder(border);
        	}
        });
        btnViewDetails_2.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_2.setBackground(Color.GREEN);
        btnViewDetails_2.setForeground(Color.WHITE);
        btnViewDetails_2.setOpaque(true);
        btnViewDetails_2.setBounds(120,250,85,40);
        panel2_Back.add(btnViewDetails_2);
        
        JLayeredPane layeredPane_3 = new JLayeredPane();
        
        
        
        
        
        JPanel panel3_Front = new JPanel();
        panel3_Front.setLayout(null);
        panel3_Front.setBounds(0, 0, 316, 326);
        layeredPane_3.add(panel3_Front);
        
        JLabel lblPanel_3 = new JLabel("");
        lblPanel_3.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/capAmerica (1).jpg")));
        lblPanel_3.setBounds(0, 0, 316, 326);
        panel3_Front.add(lblPanel_3);
        
       
        
        JPanel panel3_Back = new JPanel();
        panel3_Back.setBounds(0, 0, 316, 326);
        panel3_Back.setLayout(null);
        layeredPane_3.add(panel3_Back);
        
        JLabel label_12 = new JLabel("");
        panel3_Back.add(label_12);
        
        JLabel lblCaptainAmerica = new JLabel("Captain America");
        lblCaptainAmerica.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCaptainAmerica.setBounds(738, 439, 147, 26);
        container.add(lblCaptainAmerica);
        
        layeredPane_3.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_3.moveToFront(panel3_Back);
        		layeredPane_3.moveToBack(panel3_Front);
        		panel3_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel3_Back.setOpaque(true);
        		
        		lblPanel_3.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_3.moveToFront(panel3_Front);
        		lblPanel_3.setBorder(null);
        	}
        });
        
        layeredPane_3.setLayout(null);
        layeredPane_3.setBounds(738, 115, 316, 326);
        container.add(layeredPane_3);  
        
        JLabel star_3 = new JLabel();
        star_3.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_3.setBounds(130,40,64,64);
        panel3_Back.add(star_3);
        
        JLabel rating_3 = new JLabel("6.9/10");
        rating_3.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_3.setForeground(Color.WHITE);
        rating_3.setBounds(133,125, 65, 14);
        panel3_Back.add(rating_3);
        
        JLabel genre_3 = new JLabel("Action");
        genre_3.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_3.setForeground(Color.WHITE);
        genre_3.setBounds(127,150,79, 20);
        panel3_Back.add(genre_3);
        
        JLabel genre2_3 = new JLabel("Adventure");
        genre2_3.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_3.setForeground(Color.WHITE);
        genre2_3.setBounds(120,200,110,15);
        panel3_Back.add(genre2_3);
        
        JLabel btnViewDetails_3 = new JLabel("View Details");
        btnViewDetails_3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Captain America: The First Avenger");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_3.moveToFront(panel3_Back);
        		layeredPane_3.moveToBack(panel3_Front);
        		panel3_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_3.setBorder(border);
        	}
        });
        btnViewDetails_3.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_3.setBackground(Color.GREEN);
        btnViewDetails_3.setForeground(Color.WHITE);
        btnViewDetails_3.setOpaque(true);
        btnViewDetails_3.setBounds(120,250,85,40);
        panel3_Back.add(btnViewDetails_3);
        
        JLayeredPane layeredPane_4 = new JLayeredPane();
        
        JPanel panel4_Front = new JPanel();
        panel4_Front.setLayout(null);
        panel4_Front.setBounds(0, 0, 316, 326);
        layeredPane_4.add(panel4_Front);
        
        JLabel lblPanel_4 = new JLabel("");
        lblPanel_4.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/Dhoom2 (2).jpg")));
        lblPanel_4.setBounds(0, 0, 316, 326);
        panel4_Front.add(lblPanel_4);
        
        
        
        JPanel panel4_Back = new JPanel();
        panel4_Back.setBounds(0, 0, 316, 326);
        panel4_Back.setLayout(null);
        layeredPane_4.add(panel4_Back);
        
        JLabel label_14 = new JLabel("");
        panel4_Back.add(label_14);
        
        JLabel lblDhoom = new JLabel("Dhoom: 2");
        lblDhoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDhoom.setBounds(1095, 439, 147, 26);
        container.add(lblDhoom);
        
        layeredPane_4.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_4.moveToFront(panel4_Back);
        		layeredPane_4.moveToBack(panel4_Front);
        		panel4_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel4_Back.setOpaque(true);
        		
        		lblPanel_4.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_4.moveToFront(panel4_Front);
        		lblPanel_4.setBorder(null);
        	}
        });
        
        layeredPane_4.setLayout(null);
        layeredPane_4.setBounds(1096, 115, 316, 326);
        container.add(layeredPane_4);
        
        JLabel star_4 = new JLabel();
        star_4.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_4.setBounds(130,40,64,64);
        panel4_Back.add(star_4);
        
        JLabel rating_4 = new JLabel("6.5/10");
        rating_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_4.setForeground(Color.WHITE);
        rating_4.setBounds(133,125, 65, 14);
        panel4_Back.add(rating_4);
        
        JLabel genre_4 = new JLabel("Action");
        genre_4.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_4.setForeground(Color.WHITE);
        genre_4.setBounds(127,150,79, 20);
        panel4_Back.add(genre_4);
        
        JLabel genre2_4 = new JLabel("Thriller");
        genre2_4.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_4.setForeground(Color.WHITE);
        genre2_4.setBounds(125,200,110,15);
        panel4_Back.add(genre2_4);
        
        JLabel btnViewDetails_4 = new JLabel("View Details");
        btnViewDetails_4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Dhoom 2");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_4.moveToFront(panel4_Back);
        		layeredPane_4.moveToBack(panel4_Front);
        		panel4_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_4.setBorder(border);
        	}
        });
        btnViewDetails_4.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_4.setBackground(Color.GREEN);
        btnViewDetails_4.setForeground(Color.WHITE);
        btnViewDetails_4.setOpaque(true);
        btnViewDetails_4.setBounds(120,250,85,40);
        panel4_Back.add(btnViewDetails_4);
        
        JLayeredPane layeredPane_5 = new JLayeredPane();
        
        JPanel panel5_Front = new JPanel();
        panel5_Front.setLayout(null);
        panel5_Front.setBounds(0, 0, 316, 326);
        layeredPane_5.add(panel5_Front);
        
        JLabel label_15 = new JLabel("");
        label_15.setBounds(0, 0, 316, 326);
        panel5_Front.add(label_15);
        
        JLabel lblPanel_5 = new JLabel("");
        lblPanel_5.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/Kaabil (3).jpg")));
        lblPanel_5.setBounds(0, 0, 316, 326);
        panel5_Front.add(lblPanel_5);
        
        JPanel panel5_Back = new JPanel();
        panel5_Back.setBounds(0, 0, 316, 326);
        panel5_Back.setLayout(null);
        layeredPane_5.add(panel5_Back);
        
        JLabel label_17 = new JLabel("");
        panel5_Back.add(label_17);
        
        JLabel lblKaabil = new JLabel("Kaabil");
        lblKaabil.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblKaabil.setBounds(25, 810, 147, 26);
        container.add(lblKaabil);
        
        layeredPane_5.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_5.moveToFront(panel5_Back);
        		layeredPane_5.moveToBack(panel5_Front);
        		panel5_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel5_Back.setOpaque(true);
        		
        		lblPanel_5.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_5.moveToFront(panel5_Front);
        		lblPanel_5.setBorder(null);
        	}
        });
        
        layeredPane_5.setLayout(null);
        layeredPane_5.setBounds(25, 485, 316, 326);
        container.add(layeredPane_5);
        
        JLabel star_5 = new JLabel();
        star_5.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_5.setBounds(130,40,64,64);
        panel5_Back.add(star_5);
        
        JLabel rating_5 = new JLabel("7.1/10");
        rating_5.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_5.setForeground(Color.WHITE);
        rating_5.setBounds(133,125, 65, 14);
        panel5_Back.add(rating_5);
        
        JLabel genre_5 = new JLabel("Action");
        genre_5.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_5.setForeground(Color.WHITE);
        genre_5.setBounds(127,150,79, 20);
        panel5_Back.add(genre_5);
        
        JLabel genre2_5 = new JLabel("Drama");
        genre2_5.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_5.setForeground(Color.WHITE);
        genre2_5.setBounds(125,200,100,15);
        panel5_Back.add(genre2_5);
        
        JLabel btnViewDetails_5 = new JLabel("View Details");
        btnViewDetails_5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Kaabil");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_5.moveToFront(panel5_Back);
        		layeredPane_5.moveToBack(panel5_Front);
        		panel5_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_5.setBorder(border);
        	}
        });
        btnViewDetails_5.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_5.setBackground(Color.GREEN);
        btnViewDetails_5.setForeground(Color.WHITE);
        btnViewDetails_5.setOpaque(true);
        btnViewDetails_5.setBounds(120,250,85,40);
        panel5_Back.add(btnViewDetails_5);
        
        JLayeredPane layeredPane_6 = new JLayeredPane();
        
        JPanel panel6_Front = new JPanel();
        panel6_Front.setLayout(null);
        panel6_Front.setBounds(0, 0, 316, 326);
        layeredPane_6.add(panel6_Front);
        
        JLabel lblPanel_6 = new JLabel("");
        lblPanel_6.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/kedarnath (2).jpg")));
        lblPanel_6.setBounds(0, 0, 316, 326);
        panel6_Front.add(lblPanel_6);
        
        
        
        JPanel panel6_Back = new JPanel();
        panel6_Back.setBounds(0, 0, 316, 326);
        panel6_Back.setLayout(null);
        layeredPane_6.add(panel6_Back);
        
        JLabel label_20 = new JLabel("");
        panel6_Back.add(label_20);
        
        JLabel lblKedarnath = new JLabel("Kedarnath");
        lblKedarnath.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblKedarnath.setBounds(378, 810, 147, 26);
        container.add(lblKedarnath);
        
        layeredPane_6.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_6.moveToFront(panel6_Back);
        		layeredPane_6.moveToBack(panel6_Front);
        		panel6_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel6_Back.setOpaque(true);
        		
        		lblPanel_6.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_6.moveToFront(panel6_Front);
        		lblPanel_6.setBorder(null);
        	}
        });
        
        
        layeredPane_6.setLayout(null);
        layeredPane_6.setBounds(378, 485, 316, 326);
        container.add(layeredPane_6);
        
        JLabel star_6 = new JLabel();
        star_6.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_6.setBounds(130,40,64,64);
        panel6_Back.add(star_6);
        
        JLabel rating_6 = new JLabel("6.0/10");
        rating_6.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_6.setForeground(Color.WHITE);
        rating_6.setBounds(133,125, 65, 14);
        panel6_Back.add(rating_6);
        
        JLabel genre_6 = new JLabel("Drama");
        genre_6.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_6.setForeground(Color.WHITE);
        genre_6.setBounds(127,150,79, 20);
        panel6_Back.add(genre_6);
        
        JLabel genre2_6 = new JLabel("Romance");
        genre2_6.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_6.setForeground(Color.WHITE);
        genre2_6.setBounds(125,200,100,15);
        panel6_Back.add(genre2_6);
        
        JLabel btnViewDetails_6 = new JLabel("View Details");
        btnViewDetails_6.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Kedarnath");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_6.moveToFront(panel6_Back);
        		layeredPane_6.moveToBack(panel6_Front);
        		panel6_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_6.setBorder(border);
        	}
        });
        btnViewDetails_6.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_6.setBackground(Color.GREEN);
        btnViewDetails_6.setForeground(Color.WHITE);
        btnViewDetails_6.setOpaque(true);
        btnViewDetails_6.setBounds(120,250,85,40);
        panel6_Back.add(btnViewDetails_6);
        
        JLayeredPane layeredPane_7 = new JLayeredPane();
        
        JPanel panel7_Front = new JPanel();
        panel7_Front.setLayout(null);
        panel7_Front.setBounds(0, 0, 316, 326);
        layeredPane_7.add(panel7_Front);
        
        JLabel lblPanel_7 = new JLabel("");
        lblPanel_7.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/ekThaTiger.jpg")));
        lblPanel_7.setBounds(0, 0, 316, 326);
        panel7_Front.add(lblPanel_7);
        
        JLabel label_22 = new JLabel("");
        
        label_22.setBounds(0, 0, 316, 326);
        panel7_Front.add(label_22);
        
        JPanel panel7_Back = new JPanel();
        panel7_Back.setBounds(0, 0, 316, 326);
        panel7_Back.setLayout(null);
        layeredPane_7.add(panel7_Back);
        
        JLabel label_23 = new JLabel("");
        panel7_Back.add(label_23);
        
        JLabel lblEkThaTiger = new JLabel("Ek Tha Tiger");
        lblEkThaTiger.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEkThaTiger.setBounds(738, 810, 147, 26);
        container.add(lblEkThaTiger);
        
        layeredPane_7.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_7.moveToFront(panel7_Back);
        		layeredPane_7.moveToBack(panel7_Front);
        		panel7_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel7_Back.setOpaque(true);
        		
        		lblPanel_7.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_7.moveToFront(panel7_Front);
        		lblPanel_7.setBorder(null);
        	}
        });
        
        layeredPane_7.setLayout(null);
        layeredPane_7.setBounds(738, 485, 316, 326);
        container.add(layeredPane_7);
        
        JLabel star_7 = new JLabel();
        star_7.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_7.setBounds(130,40,64,64);
        panel7_Back.add(star_7);
        
        JLabel rating_7 = new JLabel("5.5/10");
        rating_7.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_7.setForeground(Color.WHITE);
        rating_7.setBounds(133,125, 65, 14);
        panel7_Back.add(rating_7);
        
        JLabel genre_7 = new JLabel("Action");
        genre_7.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_7.setForeground(Color.WHITE);
        genre_7.setBounds(127,150,79, 20);
        panel7_Back.add(genre_7);
        
        JLabel genre2_7 = new JLabel("Romance");
        genre2_7.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_7.setForeground(Color.WHITE);
        genre2_7.setBounds(125,200,100,15);
        panel7_Back.add(genre2_7);
        
        JLabel btnViewDetails_7 = new JLabel("View Details");
        btnViewDetails_7.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Ek Tha Tiger");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_7.moveToFront(panel7_Back);
        		layeredPane_7.moveToBack(panel7_Front);
        		panel7_Back.setBackground(new Color(0,0,0,80));
        		lblPanel_7.setBorder(border);
        	}
        });
        btnViewDetails_7.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_7.setBackground(Color.GREEN);
        btnViewDetails_7.setForeground(Color.WHITE);
        btnViewDetails_7.setOpaque(true);
        btnViewDetails_7.setBounds(120,250,85,40);
        panel7_Back.add(btnViewDetails_7);
        
        JLayeredPane layeredPane_8 = new JLayeredPane();
        
        JPanel panel8_Front = new JPanel();
        panel8_Front.setLayout(null);
        panel8_Front.setBounds(0, 0, 316, 326);
        layeredPane_8.add(panel8_Front);
        
        JLabel label_24 = new JLabel("");
        label_24.setIcon(new ImageIcon(MainPage.class.getResource("/Images/MainPage_Posters/raazi (10).jpg")));
        label_24.setBounds(0, 0, 316, 326);
        panel8_Front.add(label_24);
        
        
        
        JPanel panel8_Back = new JPanel();
        panel8_Back.setBounds(0, 0, 316, 326);
        panel8_Back.setLayout(null);
        layeredPane_8.add(panel8_Back);
        
        JLabel label_26 = new JLabel("");
        panel8_Back.add(label_26);
        
        JLabel lblRaazi = new JLabel("Raazi");
        lblRaazi.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRaazi.setBounds(1095, 810, 147, 26);
        container.add(lblRaazi);
        
        layeredPane_8.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_8.moveToFront(panel8_Back);
        		layeredPane_8.moveToBack(panel8_Front);
        		panel8_Back.setBackground(new Color(0,0,0,80));
        		layeredPane_8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        		panel8_Back.setOpaque(true);
        		
        		label_24.setBorder(border);
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent e) {
        		layeredPane_8.moveToFront(panel8_Front);
        		label_24.setBorder(null);
        	}
        });
        
        layeredPane_8.setLayout(null);
        layeredPane_8.setBounds(1096, 485, 316, 326);
        container.add(layeredPane_8);
        
        JLabel star_8 = new JLabel();
        star_8.setIcon(new ImageIcon(MainPage.class.getResource("/Images/star.png")));
        star_8.setBounds(130,40,64,64);
        panel8_Back.add(star_8);
        
        JLabel rating_8 = new JLabel("7.8/10");
        rating_8.setFont(new Font("Tahoma", Font.BOLD, 18));
        rating_8.setForeground(Color.WHITE);
        rating_8.setBounds(133,125, 65, 14);
        panel8_Back.add(rating_8);
        
        JLabel genre_8 = new JLabel("Drama");
        genre_8.setFont(new Font("Tahoma", Font.BOLD, 22));
        genre_8.setForeground(Color.WHITE);
        genre_8.setBounds(127,150,79, 20);
        panel8_Back.add(genre_8);
        
        JLabel genre2_8 = new JLabel("Thriller");
        genre2_8.setFont(new Font("Tahoma", Font.BOLD,20));
        genre2_8.setForeground(Color.WHITE);
        genre2_8.setBounds(125,200,100,15);
        panel8_Back.add(genre2_8);
        
        JLabel btnViewDetails_8 = new JLabel("View Details");
        btnViewDetails_8.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.dispose();
        		MoviePage mp = new MoviePage(username,"Raazi");
        		mp.frame.setUndecorated(true);
        		mp.frame.setVisible(true);
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		layeredPane_8.moveToFront(panel8_Back);
        		layeredPane_8.moveToBack(panel8_Front);
        		panel8_Back.setBackground(new Color(0,0,0,80));
        		
        		label_24.setBorder(border);
        		
        	}
        	
        });
        btnViewDetails_8.setHorizontalAlignment(SwingConstants.CENTER);
        btnViewDetails_8.setBackground(Color.GREEN);
        btnViewDetails_8.setForeground(Color.WHITE);
        btnViewDetails_8.setOpaque(true);
        btnViewDetails_8.setBounds(120,250,85,40);
        panel8_Back.add(btnViewDetails_8);
       
        frame.getContentPane().add(jsp);
        
    }
}