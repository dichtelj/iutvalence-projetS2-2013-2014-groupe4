package IHM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Moteur.Controleur;

public class AffichageFenetre implements Affichage, Runnable, ActionListener
{

	private JFrame fenetre;
	
	private Controleur controleur;
	
	private JPanel EcranDAccueil;
	
	private JPanel ListeCartesGenerales;
	
	private JPanel Partie;
	
	private JMenuItem menuItemFermer;
	
	private JMenuItem menuItemAPropos;
	
	private JMenuItem menuItemRegles;
	
	
	@Override
	public void run()
	{
		JFrame fenetre= new JFrame();
//		this.fenetre.setSize(950, 700);
//		this.fenetre.setTitle("Battle for Demacia");
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JMenuBar barreDeMenu = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenu aPropos = new JMenu("A Propos");		
		this.menuItemAPropos = new JMenuItem("Informations");
		this.menuItemAPropos.addActionListener(this);
		aPropos.add(this.menuItemAPropos);		
		this.menuItemRegles = new JMenuItem("Regles du jeu");
		this.menuItemRegles.addActionListener(this);
		menu.add(this.menuItemRegles);		
		this.menuItemFermer = new JMenuItem("Fermer");
		this.menuItemFermer.addActionListener(this);
		menu.add(this.menuItemFermer);		
		barreDeMenu.add(menu);
		barreDeMenu.add(aPropos);		
		//this.fenetre.setJMenuBar(barreDeMenu);		
		fenetre.setVisible(true);
	}



	public void actionPerformed(ActionEvent event)
	{
		JMenuItem itemSelectionne = (JMenuItem) event.getSource();

		if (itemSelectionne == this.menuItemAPropos)
		{
			JOptionPane.showMessageDialog(this.fenetre, "Battle For Demacia, jeu cree par 4 étudiants à l'iut de valence.", "A propos", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (itemSelectionne == this.menuItemFermer)
		{
		
			if (JOptionPane.showConfirmDialog(this.fenetre, "Voulez-vous fermer le jeu?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
				this.fenetre.dispose();
		}
		
		if (itemSelectionne == this.menuItemRegles)
		{ 
			JOptionPane.showMessageDialog(this.fenetre, "anduin", "Regles du Memory", JOptionPane.INFORMATION_MESSAGE);
			return;
		}		
	}


	public void afficherMessageErreur(String str) {
		
	}
}