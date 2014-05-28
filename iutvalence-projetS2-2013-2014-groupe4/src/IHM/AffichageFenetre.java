package IHM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import Moteur.Controleur;

public class AffichageFenetre implements Affichage, Runnable, ActionListener
{

	private JFrame fenetre;
	
	private Controleur controleur;
	
	private JPanel EcranDAccueil;
	
	private JPanel ListeCartesGenerales;
	
	private JPanel Partie;
	
	private MenuJeu menu;
	

	
	
	@Override
	public void run()
	{
		JFrame fenetre= new JFrame();
		this.fenetre.setSize(950, 700);
		this.fenetre.setTitle("Battle for Demacia");
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		MenuJeu barreDeMenu= new MenuJeu(this.fenetre);
		this.fenetre.setJMenuBar(barreDeMenu);
		this.initialiserPartie();
	}
	
	



	public void initialiserPartie() {
//		JPanel partie= new JPanel();
//		JSplitPane splitPaneMilieuDEcran= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//		partie.add(splitPaneMilieuDEcran);
//		JSplitPane splitPaneSuperieur = splitPaneMilieuDEcran.getTopComponent().add(new JSplitPane(JSplitPane.VERTICAL_SPLIT));
//		JSplitPane splitPaneInferieur = splitPaneMilieuDEcran.getBottomComponent().add(new JSplitPane(JSplitPane.VERTICAL_SPLIT));
//		
//		
	}





	public void actionPerformed(ActionEvent event)
	{
		
	}


	public void afficherMessageErreur(String str) {
		
	}
}