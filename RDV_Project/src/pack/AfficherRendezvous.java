package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.DriverManager;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class AfficherRendezvous {
	static public JFrame afficher ;
	static public java.sql.Connection conn ;
	static public CallableStatement call = null ;
	static public ResultSet resultas = null ;
	static public JTextArea text ;
	static public java.sql.Connection connect = null ;
	static public Statement  state = null ;
	public int ab =12;
	public static String pr ;
	public static JButton menu ;
	public static int b ;
	class fenetre extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {	
			 b = JOptionPane.showConfirmDialog(afficher, "Vous être sure !?","WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
			if(b==JOptionPane.YES_OPTION)
				{ 
				afficher.dispose();
				}
			}
	}
	class action implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			afficher.dispose();
			WINDOWOPTIONS window = new WINDOWOPTIONS();
			window.options();
			
		}
		
	}
	public void afficher()
	{
		conn =Connexion.getConnection() ;
		 try {
		call = conn.prepareCall("{call AfficherClients }");
		resultas = call.executeQuery(); 
		
		 }
		 catch(Exception e) { e.printStackTrace(); }
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		afficher = new JFrame();
		afficher.setTitle("list des rendez-vous");
		afficher.setSize(1000, 500);
		afficher.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		afficher.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel label = new JLabel("la list des rendez-vous :");
		label.setBounds(400, 10, 180, 50);
		panel.add(label);
		 text = new JTextArea();
		text.setBounds(0, 100, 1800, 300);
		try
		{
			System.out.println("La liste est affiché");
			while(resultas.next())
			{
				text.append("code :"+resultas.getInt(1)+"  prenom : "+resultas.getString(2)+"  nom : "+resultas.getString(3)+"  age : "+resultas.getInt(4)+"  adresse : "+resultas.getString(5)+"  Adresse : "+resultas.getString(6)+"  mobile : "+resultas.getString(7)+"    idRendezvous   : "+resultas.getInt(8)+"  date : "+resultas.getDate(9)+"  Heure : "+resultas.getTime(10)+"\n");
			}
		}
		catch(Exception e) { 
			System.out.println("erreur");
		}
		panel.add(text);
		//ajouter le button retour au menu
		menu = new JButton(new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\projet bsd\\lib\\home.png"));
		menu.setBounds(0,0, 80, 80);
		panel.add(menu);
		menu.addActionListener(new action());
		afficher.addWindowListener(new fenetre());
		afficher.setContentPane(panel);
		afficher.setVisible(true);
	}
}
