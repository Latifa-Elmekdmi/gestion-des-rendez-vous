package pack;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pack.AjouterRendezVous;
public class WINDOWOPTIONS {
	public static JButton ajouter ,chercher , modifier ,supprimer,afficher,quitter ;
	public static JFrame options ;
	public static int b ;
	class sourisajouter extends MouseAdapter
	{
		public void mouseEntered(MouseEvent e)
		{
				if(e.getSource()==ajouter)
				ajouter.setForeground(Color.BLUE);
				else if(e.getSource()==chercher)
					chercher.setForeground(Color.RED);
				else if(e.getSource()==modifier)
				modifier.setForeground(Color.PINK);
				else if(e.getSource()==supprimer)
					supprimer.setForeground(Color.GRAY);
				else if(e.getSource()==afficher)
					afficher.setForeground(Color.ORANGE);
				else
					quitter.setForeground(Color.MAGENTA);
			
		}
		public void mouseExited(MouseEvent e) 
		{
			if(e.getSource()==ajouter)
				ajouter.setForeground(Color.BLACK);
			else if(e.getSource()==afficher)
				afficher.setForeground(Color.BLACK);
			else if(e.getSource()==modifier)
				modifier.setForeground(Color.BLACK);
			else if(e.getSource()==supprimer)
				supprimer.setForeground(Color.BLACK);
			else if(e.getSource()==chercher)
				chercher.setForeground(Color.BLACK);
			else
				quitter.setForeground(Color.BLACK);
		}
	}
	class fenetre extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {	
			 b = JOptionPane.showConfirmDialog(options, "Vous être sure !?","WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
			if(b==JOptionPane.YES_OPTION)
				{ 
				options.dispose();
				}
			}
	}
	class action implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==ajouter)
			{
				options.dispose();
				pack.AjouterRendezVous add = new pack.AjouterRendezVous();
				add.inserer();
			}
			else if(e.getSource()==afficher)
			{
				options.dispose();
				AfficherRendezvous list = new AfficherRendezvous();
				list.afficher();
			}
			else if(e.getSource()==chercher)
			{
				options.dispose();
				ChercherRendezvous cherche = new ChercherRendezvous();
				cherche.rechercher();
			}
			else if(e.getSource()==supprimer)
			{
				options.dispose();
				SupprimerRendezVous supp = new SupprimerRendezVous();
				supp.supprimer();
			}
			else if(e.getSource()==modifier)
			{
				options.dispose();
				ModifierRendezVous mod = new ModifierRendezVous();
				mod.modifier();
			}
			else if(e.getSource()==quitter)
			{
				options.dispose();
			}

		}
		
		
	}

		public void options()
		{
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		options = new JFrame();
		options.setTitle("OPTIONS");
		options.setLocationRelativeTo(null);
		options.setSize(400, 500);
		options.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		JLabel help = new JLabel("COMMENT JE PEUT VOUS AIDEZ ?");
		help.setBounds(100, 40, 250, 50);
		panel2.add(help);
		ajouter = new JButton("Ajouter un rendez-vous");
		ajouter.setBounds(100, 100, 190, 30);
		ajouter.addMouseListener(new sourisajouter());
		ajouter.addActionListener(new action());
		panel2.add(ajouter);
		afficher = new JButton("List des rendez-vous");
		afficher.setBounds(100, 150, 190, 30);
		afficher.addMouseListener(new sourisajouter());
		afficher.addActionListener(new action());
		panel2.add(afficher);
		chercher = new JButton("Chercher un rendez-vous");
		chercher.setBounds(100, 200, 190, 30);
		chercher.addMouseListener(new sourisajouter());
		chercher.addActionListener(new action());
		//chercher.addMouseListener(new souris());
		panel2.add(chercher);
		modifier = new JButton("Modifier un rendez-vous");
		modifier.setBounds(100, 250, 190, 30);
		modifier.addMouseListener(new sourisajouter());
		modifier.addActionListener(new action());
		panel2.add(modifier);
		supprimer = new JButton("Supprimer un rendez-vous");
		supprimer.setBounds(100, 300, 190, 30);
		supprimer.addMouseListener(new sourisajouter());
		supprimer.addActionListener(new action());
		panel2.add(supprimer);
		quitter = new JButton("QUITTER");
		quitter.setBounds(100, 350, 190, 30);
		quitter.addMouseListener(new sourisajouter());
		quitter.addActionListener(new action());
		panel2.add(quitter);

	 
		options.addWindowListener(new fenetre());
		options.setContentPane(panel2);
		options.setVisible(true);
		}
	}

