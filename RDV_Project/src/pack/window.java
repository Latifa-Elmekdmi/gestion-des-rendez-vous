package pack;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class window {
	public static JFrame window ;
	public static JButton connecter ;
	public static int a ;
	public static Connection conn ;
	public void window() {
		 conn =Connexion.getConnection() ;
		class action implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println("BIENVENUE CHEZ NOTRE PROJET");
				window.dispose();
				WINDOWOPTIONS w = new WINDOWOPTIONS();
				w.options();
			}	
		}
		class fenetre extends WindowAdapter
		{
					public void windowClosing(WindowEvent e) {	
					 a = JOptionPane.showConfirmDialog(window, "are you sur !?","WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
					if(a==JOptionPane.YES_OPTION)
						{ 
						window.dispose();
						}
					}
				}
		class souris extends MouseAdapter
		{
			public void mouseEntered(MouseEvent e)
			{
				connecter.setForeground(Color.BLUE);
				
			}
			public void mouseExited(MouseEvent e) 
			{
				connecter.setForeground(Color.BLACK);
			}
		}
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		window = new JFrame();
		window.setTitle("PRISES DES RENDEZ-VOUS");
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel text = new JLabel();
		text.setText("BIENVENUE CHEZ NOTRE HOPITAL");
		text.setBounds(150, 20, 250, 40);
		panel.add(text);
		//IMAGEICON D'UN HOPITAL
		JLabel image = new JLabel(new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\projet bsd\\lib\\20200623_135234.jpg"));
		image.setBounds(70, 50, 350, 350);
		panel.add(image);
		connecter = new JButton("CONNECTER");
		connecter.setBounds(180, 400, 130, 40);
		connecter.addActionListener(new action());
		connecter.addMouseListener(new souris());
		panel.add(connecter);
		window.addWindowListener(new fenetre());
		window.setContentPane(panel);
		window.setVisible(true);
	}

}
