package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChercherRendezvous {
	static public JButton menu ;
	static public JButton confirmer ;
	static public int code,b ;
	static public CallableStatement call = null ;
	static public java.sql.Connection conn = null ;
	static public ResultSet resultas = null ;
	static public JTextArea afficher ;
	static JFrame chercher ;
	static JPanel panel ;
	static JTextField text ;
	class fenetre extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {	
			 b = JOptionPane.showConfirmDialog(chercher, "Vous être sure !?","WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
			if(b==JOptionPane.YES_OPTION)
				{ 
				chercher.dispose();
				}
			}
	}
	public void rechercher()
	{
		class action implements ActionListener
		{

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==confirmer)
				{
				panel.add(afficher);
				code = Integer.valueOf(text.getText());
				 try {
						call = conn.prepareCall("{call ChercherClient(?) }");
						call.setInt(1,code);
						resultas = call.executeQuery(); 
						 }
				 catch(Exception e1) { e1.printStackTrace(); }
				try
				{
					while(resultas.next())
					{
						System.out.println("ok");
						afficher.append("code         : "+resultas.getInt(1)+" \nprenom    : "+resultas.getString(2)+"  \nnom          : "+resultas.getString(3)+"\nage           : "+resultas.getInt(4)+"  \nadresse   : "+resultas.getString(5)+" \nemail        : "+resultas.getString(6)+" \nmobile      : "+resultas.getString(7)+"\nidRendezVous:"+resultas.getInt(8)+"\ndate           : "+resultas.getDate(9)+"\nHeure        : "+resultas.getTime(10));
					}
				}
				catch(Exception e1) { e1.printStackTrace(); }
			}
				else if(e.getSource()==menu)
				{
					chercher.dispose();
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
		conn =Connexion.getConnection() ;

		    chercher = new JFrame();
		    chercher.setTitle("Chercher un rendez-vous");
		    chercher.setSize(400, 500);
		    chercher.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    chercher.setLocationRelativeTo(null);
			 panel = new JPanel();
			panel.setLayout(null);
			JLabel label = new JLabel("    Entrez votre Code  :");
			label.setBounds(60, 70, 200, 50);
			panel.add(label);
			 text = new JTextField();
			text.setBounds(200, 70, 120, 50);
			panel.add(text);
			confirmer	  = new JButton("CHERCHER");
			confirmer.setBounds(140, 150, 100, 50);
			panel.add(confirmer);
			
			confirmer.addActionListener(new action());
			afficher = new JTextArea();
			afficher.setBounds(0, 220, 490, 300);
			//ajouter le button retour au menu
			menu = new JButton(new ImageIcon("C:\\Users\\hp\\eclipse-workspace\\projet bsd\\lib\\home.png"));
			menu.setBounds(0,0, 80, 80);
			menu.addActionListener(new action());
			panel.add(menu);
			chercher.addWindowListener(new fenetre());
			chercher.setContentPane(panel);
			chercher.setVisible(true);
			

	}

}
