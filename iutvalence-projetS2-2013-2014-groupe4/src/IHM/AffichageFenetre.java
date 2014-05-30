package IHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import Moteur.Carte;
import Moteur.Controleur;
import Moteur.Jeu;
import Moteur.Joueur;
import Moteur.Personnage;
import Moteur.Position;

public class AffichageFenetre extends Joueur implements Affichage, Runnable, ActionListener 
{
	private JFrame fenetre;
	
	private Controleur controleur;
	
//    private JPanel EcranDAccueil;
//	
//	private JPanel ListeCartesGenerales;
//	
//	private JPanel Partie;
	
	private MenuJeu menu;
	
	private Carte carteAttaquante;
	
	private Carte carteAPoser;
	
	private Personnage carteAttaquee;
	
	private Carte carteABuffer;
	
	private Carte carteDeck;
	

	
	
	@Override
	public void run()
	{
		JFrame fenetre= new JFrame();
		this.fenetre=fenetre;
		this.fenetre.setSize(950, 700);
		this.fenetre.setTitle("Battle for Demacia");
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuJeu barreDeMenu= new MenuJeu(this.fenetre);
		this.fenetre.setJMenuBar(barreDeMenu);
		this.initialiserPartie();
		this.carteABuffer=null;
		this.carteAPoser=null;
		this.carteAttaquante=null;
		this.carteAttaquee=null;
		this.carteDeck=null;
	}
	
	



	public void initialiserPartie() {
		JPanel partie= new JPanel();
		JPanel coteHautEcran= new JPanel();
		coteHautEcran.setBackground(Color.blue);
		JPanel coteBasEcran= new JPanel();
		coteBasEcran.setBackground(Color.black);
		JPanel mainAdverse= new JPanel();
		mainAdverse.setBackground(Color.cyan);
		JPanel cartesPoseesAdverse= new JPanel();
		cartesPoseesAdverse.setBackground(Color.green);
		JPanel mainJoueur= new JPanel();
		mainJoueur.setBackground(Color.magenta);
		JPanel cartesPoseesJoueur= new JPanel();
		
		for (int indiceBouton=0; indiceBouton< Jeu.NB_CARTES_MAX_POSEES; indiceBouton++){
			cartesPoseesAdverse.add(new BoutonPlateauAdverse(this));
			cartesPoseesJoueur.add(new BoutonPlateauJoueur(this));
		}
		
		for (int indiceBouton=0;indiceBouton < Jeu.NB_MAX_CARTES_MAIN; indiceBouton++)
			mainJoueur.add(new BoutonMain(this));
		
		mainJoueur.setLayout(new GridLayout(1,10,20 ,20));
		cartesPoseesAdverse.setLayout(new GridLayout(1,7, 20, 20));
		cartesPoseesJoueur.setLayout(new GridLayout(1,7, 20, 20));
		this.fenetre.setLayout(new GridLayout(4,1, 20, 20));
		this.fenetre.add(mainAdverse);
		this.fenetre.add(cartesPoseesAdverse);
		this.fenetre.add(cartesPoseesJoueur);
		this.fenetre.add(mainJoueur);		
		this.fenetre.setVisible(true);

	}
	
	public void initialiserAcceuil(){
		
	}



	public AffichageFenetre(int numeroDuJoueur, String nomHeros) {
		super(numeroDuJoueur, nomHeros);
	}


	public void actionPerformed(ActionEvent event)
	{
		JButton boutonAppuye = (JButton) event.getSource();
		if (boutonAppuye instanceof BoutonMain){
			this.carteAPoser=((BoutonMain)boutonAppuye).getCarte();
			this.carteAPoser=null;
		}
		
		if (boutonAppuye instanceof BoutonPlateauAdverse){
			this.carteAttaquee=((BoutonPlateauAdverse)boutonAppuye).getCarte();
			this.carteAttaquee=null;
		}
		
		if (boutonAppuye instanceof BoutonHeros){
			this.carteAttaquee=((BoutonHeros)boutonAppuye).getHeros();
		}
		
		if (boutonAppuye instanceof BoutonPlateauJoueur){
			this.carteAttaquante=((BoutonPlateauJoueur)boutonAppuye).getCarte();
			this.carteABuffer=((BoutonPlateauJoueur)boutonAppuye).getCarte();
			this.carteAttaquante=null;
			this.carteABuffer=null;
		}
	}


	public void afficherMessageErreur(String str) {
		JOptionPane.showMessageDialog(this.fenetre, str, "Mesage d'erreur", JOptionPane.INFORMATION_MESSAGE);
	}




	public Carte choisirCarteDeck() {
		while(this.carteDeck==null);
		return this.carteDeck;
	}




	public Carte choisirCarteAttaquante() {
		while(this.carteAttaquante==null);
		return this.carteAttaquante;
	}




	public Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse) {
		while(this.carteAttaquee==null);
		return this.carteAttaquee;
	}




	public Personnage choisirCarteABuffer() {
		while (this.carteABuffer==null);
		return this.carteABuffer;
	}


	public Carte choisirCarteAPoser() {
		while(this.carteAPoser==null);
		return this.carteAPoser;
	}
	
	public Carte getCarteAPoser(){
		return this.carteAPoser;
	}


	public Carte getCarteAttaquante() {
		return this.carteAttaquante;
	}
}