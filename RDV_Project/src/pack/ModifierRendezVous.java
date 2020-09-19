package pack;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class ModifierRendezVous {
	static java.sql.Connection connect = null ;
	private static String hr , dt;
	private static int co,cordv;
	private static Time heure1;
	private static  Date date,heure ;
	private static JFrame modifier;
	static JFrame fr= new JFrame();
	static CallableStatement stmt = null ;
	private static JPanel panel ;
	private static JLabel succesLabel ;
	private static JTextField textField;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static JTextField textField_9;
	
	
	public static void modifier() {
	
	class Succes  implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fr.dispose();
			modifier.dispose();
			WINDOWOPTIONS window = new WINDOWOPTIONS();
			window.options();
			//appeler la fenetre menu
		}	
	}
   class action implements ActionListener
   {
		public void actionPerformed(ActionEvent e) {

		co=	Integer.valueOf(textField.getText());
		cordv=	Integer.valueOf(textField_9.getText());
		dt = textField_7.getText();
		try {
			SimpleDateFormat std = new SimpleDateFormat("dd/MM/yyyy");
			date = std.parse(dt);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		hr = textField_8.getText();
		try {
			SimpleDateFormat st = new java.text.SimpleDateFormat("hh:mm");
			heure =st.parse(hr);
			heure1=new Time(heure.getHours(),heure.getMinutes(),0);
			
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		

		System.out.println(co);
		System.out.println(new java.sql.Date(date.getTime()));
		System.out.println(heure1);
		
		try {
			connect =Connexion.getConnection() ;

			
			stmt = connect.prepareCall("{call ModifierRendezVous(?,?,?,?)}");
			
			stmt.setInt( 1, co);
			stmt.setDate( 2, new java.sql.Date(date.getTime()));
			stmt.setTime( 3, heure1);
			stmt.setInt(4,cordv);
			//stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			 
			stmt.executeUpdate();
			
			//read the OUT parameter now
			//String result = stmt.getString(6);
			//System.out.println("Employee Record Save Success::"+result);
			
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
		}	
	}
	
		
		fr.setTitle("Modification avec succes");
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
	
   }
		//Le theme de fenetre
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		modifier = new JFrame();
		modifier.setTitle("Modifier un rendez-vous : ");
		modifier.setVisible(true);
		modifier.setBounds(100, 100, 493, 500);
		modifier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modifier.getContentPane().setLayout(null);
		
		 
		
		JLabel lblNewLabel = new JLabel("IdClient");
		lblNewLabel.setBounds(82, 77, 46, 14);
		modifier.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(150, 71, 212, 27);
		modifier.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabelrdv = new JLabel("IdRendezVous");
		lblNewLabelrdv.setBounds(60, 115, 100, 14);
		modifier.getContentPane().add(lblNewLabelrdv);
		
		textField_9 = new JTextField();
		textField_9.setBounds(150, 109, 212, 27);
		modifier.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Veuillez saisir les nouveaux information  :");
		lblNewLabel_1.setBounds(130, 144, 280, 27);
		modifier.getContentPane().add(lblNewLabel_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(150, 182, 212, 27);
		modifier.getContentPane().add(textField_7);
		
		JLabel lblDate = new JLabel("Date (DD/MM/YYYY) ");
		lblDate.setBounds(35, 188, 150, 14);
		modifier.getContentPane().add(lblDate);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(150, 242, 214, 27);
		modifier.getContentPane().add(textField_8);
		
		JLabel lblHeure = new JLabel("Heure (HH:MM) ");
		lblHeure.setBounds(50, 248, 100, 14);
		modifier.getContentPane().add(lblHeure);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBounds(195, 336, 114, 38);
		modifier.getContentPane().add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		modifier.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		btnNewButton.addActionListener(new action());
		modifier.setVisible(true);
	}

	
}
