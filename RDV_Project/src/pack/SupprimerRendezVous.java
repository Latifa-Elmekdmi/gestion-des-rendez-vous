package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class SupprimerRendezVous {
	static public java.sql.Connection conn = null ;
	static public JPanel panel ;
	static public JFrame supprimer ;
	static public JTextField text ;
	static public java.sql.Connection connect = null ;
	static public ResultSet resultas = null ;
	static public Statement  state = null ;
	static public CallableStatement call = null ;
	static public PreparedStatement prepa = null;
	static public int code,a,b ;
	public static String nom ;
	public static JButton menu ,confirmer;
	class fenetre extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {	
			 b = JOptionPane.showConfirmDialog(supprimer, "Vous être sure !?","WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
			if(b==JOptionPane.YES_OPTION)
				{ 
				supprimer.dispose();
				}
			}
	}

	public void supprimer()
	{
	String p = "koko";
	int age = 22 ;
	class action implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==confirmer)
			{
			code = Integer.valueOf(text.getText());
			a =JOptionPane.showConfirmDialog(supprimer, "vous voulez supprimer le rendez-vous de code : "+code,"warning", JOptionPane.YES_NO_CANCEL_OPTION);
			if(a==JOptionPane.OK_OPTION)
			{
				conn =Connexion.getConnection() ;
				 try {
					call = conn.prepareCall("{call SupprimerRendezVous(?)};");
					call.setInt(1,code);
					System.out.println("supprission avec succès");
					call.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
		}
	}
			else if(e.getSource()==menu)
			{
				supprimer.dispose();
				WINDOWOPTIONS window = new WINDOWOPTIONS();
				window.options();
			}
		}
	}
		
	try {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e) {
		e.printStackTrace();
	}
	  supprimer = new JFrame();
	 	supprimer.setTitle("supprimer un rendez-vous");
	 	supprimer.setSize(400, 500);
	 	supprimer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 	supprimer.setLocationRelativeTo(null);
		 panel = new JPanel();
		panel.setLayout(null);
		JLabel label = new JLabel("Veuillez saisir l'ID du rendez-vous:");
		label.setBounds(30, 70, 230, 50);
		panel.add(label);
		 text = new JTextField();
		text.setBounds(220, 70, 120, 50);
		panel.add(text);
	
		 confirmer = new JButton("CONFIRMER");
		confirmer.setBounds(120, 170, 150, 50);
		confirmer.addActionListener(new action());
		panel.add(text);
		panel.add(confirmer);
		//ajouter le button retour au menu
		menu = new JButton(new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\projet bsd\\lib\\home.png"));
		menu.setBounds(0,0, 80, 80);
		panel.add(menu);
		menu.addActionListener(new action());
		supprimer.addWindowListener(new fenetre());
		supprimer.setContentPane(panel);
		supprimer.setVisible(true);
	}
}
	