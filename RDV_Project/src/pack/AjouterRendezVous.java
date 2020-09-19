package pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AjouterRendezVous {

	static public Time heure1;
	static public Date date,heure ;
	static java.sql.Connection connect = null ;
	static CallableStatement stmt = null ;
	static Statement state = null ;
	public static JPanel panel ;
	public static JLabel succesLabel ;
	public static String pr , nm , em  , mo ,ad,tl ,hr , dt;
	public static int ag ,code1;
	public static JTextField Code , Nom , Prenom , Email , Age , Mobile , Adresse ,Tel,Date, Heure ;
	public static JFrame fr = new JFrame();
	public static int recres;
	public static JFrame add = new JFrame();
	public static JFrame fenetreErreur = new JFrame();
	
	
	   public void inserer()
	   {
		   

		 
		   class Succes  implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					
					fr.dispose();
					add.dispose();
					WINDOWOPTIONS window = new WINDOWOPTIONS();
					window.options();
					//appeler la fenetre menu
					
				}	
			}
		   class action implements ActionListener
		   {
				public void actionPerformed(ActionEvent e) {
				
				pr = Prenom.getText();
				nm = Nom.getText();
				ag = Integer.valueOf(Age.getText());
				ad = Adresse.getText();
				em = Email.getText();
				mo = Mobile.getText();
				dt = Date.getText();
				try {
					SimpleDateFormat std = new SimpleDateFormat("dd/MM/yyyy");
					date = std.parse(dt);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				hr = Heure.getText();
				try {
					SimpleDateFormat st = new java.text.SimpleDateFormat("hh:mm");
					heure =st.parse(hr);
					heure1=new Time(heure.getHours(),heure.getMinutes(),0);
					
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
				
				System.out.println(pr);
				System.out.println(nm);
				System.out.println(ag);
				System.out.println(ad);
				System.out.println(em);
				System.out.println(mo);
				System.out.println(new java.sql.Date(date.getTime()));
				System.out.println(heure1);
				
				
				
				
				
				try {
					connect =Connexion.getConnection() ;
					stmt = connect.prepareCall("{call AjouterClient(?,?,?,?,?,?)}");
					stmt.setString(1, pr);
					stmt.setString(2, nm);
					stmt.setInt(3, ag);
					stmt.setString(4, ad);
					stmt.setString(5, em);
					stmt.setString(6, mo);
					stmt.executeUpdate();
					
					
					state=connect.createStatement();
					ResultSet rs = state.executeQuery("select MAX(IdClient)  from TClients");
					rs.next();
					if(rs.getInt(1)==0)
						code1=1;
					else
						code1=rs.getInt(1);
					
					stmt = connect.prepareCall("{? = call AjouterRendezVous(?,?,?)}");
					stmt.registerOutParameter(1, java.sql.Types.INTEGER);
					stmt.setDate( 2, new java.sql.Date(date.getTime()));
					stmt.setTime( 3, heure1);
					stmt.setInt( 4, code1);
					stmt.executeUpdate();
					recres=stmt.getInt(1);
					int a;
					switch(recres) {
					  case 0:
						  a =	JOptionPane.showConfirmDialog(add, "votre ajout est refusé car Le creneau est depassé", "ERREUR", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
						  if(a==JOptionPane.OK_OPTION)
							{
								//supprimer la fenetre actuel
								add.dispose();
								//redemarer la fenetre 
								//insert.inserer();
								
								}
						  
					    break;
					  case 2:
						  a =	JOptionPane.showConfirmDialog(add, "votre ajout est refusé car Veuillez saisir une date à partir d'ajourd'hui", "ERREUR", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
						  if(a==JOptionPane.OK_OPTION)
							{
								//supprimer la fenetre actuel
								add.dispose();
								WINDOWOPTIONS window = new WINDOWOPTIONS();
								window.options();
								//redemarer la fenetre 
								//insert.inserer();
								}
						  
						  
						  break;
					  case 3:
						  a =	JOptionPane.showConfirmDialog(add, "votre ajout est refusé car Veuillez saisir une heure à partir de l''heure actuelle", "ERREUR", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
						  if(a==JOptionPane.OK_OPTION)
							{
								//supprimer la fenetre actuel
								add.dispose();
								WINDOWOPTIONS window = new WINDOWOPTIONS();
								window.options();
								//redemarer la fenetre 
								//insert.inserer();
								}
						  break;
					  case 4:
						  a =	JOptionPane.showConfirmDialog(add, "votre ajout est refusé car Le docteur est en pause  de 12:00 à 13:00", "ERREUR", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
						  if(a==JOptionPane.OK_OPTION)
							{
								//supprimer la fenetre actuel
								add.dispose();
								WINDOWOPTIONS window = new WINDOWOPTIONS();
								window.options();
								//redemarer la fenetre 
								//insert.inserer();
								}
						  break;
					  case 5:
						  a =	JOptionPane.showConfirmDialog(add, "votre ajout est refusé car L'horaire de travail est : 09:00 -> 16:00", "ERREUR", JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
						  if(a==JOptionPane.OK_OPTION)
							{
								//supprimer la fenetre actuel
								add.dispose();
								WINDOWOPTIONS window = new WINDOWOPTIONS();
								window.options();
								//redemarer la fenetre 
								//insert.inserer();
								}
						  break;
					  default :
						    fr.setSize(300, 150);
							fr.setLocationRelativeTo(null);
							fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							panel = new JPanel();
							panel.setLayout(null);
							JButton boutton=new JButton("OK");
							panel.add(boutton) ;
							boutton.setBounds(105, 60, 70, 30);
							boutton.addActionListener(new Succes());
							succesLabel = new JLabel("votre ajout s'est fait avec succes ! ") ;
							succesLabel.setBounds(50,0,200,80);
							panel.add(succesLabel);
							fr.setContentPane(panel);
							fr.setVisible(true);
					
					}
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("non");
				}
				
					finally{ 
						try {
						stmt.close();
						connect.close();
					}catch (Exception e1) {
						e1.printStackTrace();
				}	}
			
			
				
				
				
				
				
			
			}
		}
	
	
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	
	add.setTitle("AJOUTER UN CLIENT");
	add.setSize(400, 600);
	add.setLocationRelativeTo(null);
	add.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 panel = new JPanel();
	panel.setLayout(null);
	JLabel text = new JLabel("AJOUTER UN RENDEZ-VOUS :");
	text.setBounds(110, 20, 250, 30);
	panel.add(text);
	JLabel prenom = new JLabel("Prénom");
	prenom.setBounds(80, 70, 50, 30);
	panel.add(prenom);
	 Prenom = new JTextField();
	Prenom.setBounds(200, 70, 150, 30);
	panel.add(Prenom);
	JLabel nom 	  = new JLabel("Nom");
	nom.setBounds(80, 120, 50, 30);
	panel.add(nom);
	 Nom = new JTextField();
	Nom.setBounds(200, 120, 150, 30);
	panel.add(Nom);
	JLabel age = new JLabel("Age");
	age.setBounds(80, 170, 50, 30);
	panel.add(age);
	 Age = new JTextField();
	Age.setBounds(200, 170, 150, 30);
	panel.add(Age);
	JLabel adresse = new JLabel("Adresse");
	adresse.setBounds(80, 220, 50, 30);
	panel.add(adresse);
	 Adresse = new JTextField();
	Adresse.setBounds(200, 220, 150, 30);
	panel.add(Adresse);
	JLabel email = new JLabel("Email");
	email.setBounds(80, 270, 50, 30);
	panel.add(email);
	 Email = new JTextField();
	Email.setBounds(200, 270, 150, 30);
	panel.add(Email);
	JLabel mobile = new JLabel("Mobile");
	mobile.setBounds(80, 320, 50, 30);
	panel.add(mobile);
	 Mobile = new JTextField();
	 Mobile.setBounds(200,320, 150, 30);
	panel.add(Mobile);
	JLabel date = new JLabel("Date (DD/MM/YYYY)");
	date.setBounds(80, 370, 150, 30);
	panel.add(date);
	 Date = new JTextField();
	Date.setBounds(200, 370, 150, 30);
	panel.add(Date);
	JLabel heure = new JLabel("Heure (HH:MM)");
	heure.setBounds(80, 420, 100, 30);
	panel.add(heure);
	 Heure = new JTextField();
	Heure.setBounds(200, 420, 150, 30);
	panel.add(Heure);
	JButton valider = new JButton();
	
	
	valider.setText("valider");
	valider.setBounds(150, 470, 100, 40);
	valider.addActionListener(new action());
	panel.add(valider);
	add.setContentPane(panel);
	add.setVisible(true);
	
	 
}
}
