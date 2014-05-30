package IHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
import Moteur.JoueurAleatoire;
import Moteur.Personnage;
import Moteur.Position;

public class AffichageFenetre extends Joueur implements Affichage, Runnable, ActionListener 
{
	private JFrame fenetre;
	
	private Controleur controleur;
	
    private JPanel EcranDAccueil;
	
	private JPanel ListeCartesGenerales;
	
	private ImagePanel Partie;
		
		private BoutonMain[] boutonsMainJoueur;
		
		private BoutonPlateauAdverse[] boutonsPlateauAdverse;
		
		private BoutonPlateauJoueur[] boutonsPlateauJoueur;
		
		private BoutonHeros[] boutonsHeros;
	
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
		this.fenetre.setSize(950, 755);
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
		ImagePanel mainAdverse= new ImagePanel(new ImageIcon("images/quart4.jpg").getImage());
		mainAdverse.setOpaque(false);
		ImagePanel cartesPoseesAdverse= new ImagePanel(new ImageIcon("images/quart3.png").getImage());
		cartesPoseesAdverse.setOpaque(false);
		ImagePanel mainJoueur= new ImagePanel(new ImageIcon("images/quart1.png").getImage());
		mainJoueur.setOpaque(true);
		ImagePanel cartesPoseesJoueur= new ImagePanel(new ImageIcon("images/quart2.png").getImage());
		this.boutonsMainJoueur=new BoutonMain[Jeu.NB_MAX_CARTES_MAIN];
		this.boutonsPlateauAdverse=new BoutonPlateauAdverse[Jeu.NB_CARTES_MAX_POSEES];
		this.boutonsPlateauJoueur=new BoutonPlateauJoueur[Jeu.NB_CARTES_MAX_POSEES];
		
		
		for (int indiceBouton=0; indiceBouton< Jeu.NB_CARTES_MAX_POSEES; indiceBouton++){
			BoutonPlateauAdverse boutonCreer=new BoutonPlateauAdverse(this);
			cartesPoseesAdverse.add(boutonCreer);
			this.boutonsPlateauAdverse[indiceBouton]=boutonCreer;
			BoutonPlateauJoueur boutonCreer2=new BoutonPlateauJoueur(this);
			this.boutonsPlateauJoueur[indiceBouton]=boutonCreer2;
			cartesPoseesJoueur.add(boutonCreer2);
		}

//		BoutonHeros herosJoueur1=new BoutonHeros(this.getControleur().getJoueurs()[1].getHeros());
//		BoutonHeros herosJoueur2=new BoutonHeros(this.getControleur().getJoueurs()[0].getHeros());
//		mainAdverse.add(herosJoueur1);
//		mainAdverse.add(herosJoueur2);
//		this.boutonsHeros[0].setHeros(this.controleur.getJoueurs()[0].getHeros());
//		this.boutonsHeros[1].setHeros(this.controleur.getJoueurs()[1].getHeros());
		for (int indiceBouton=0;indiceBouton < Jeu.NB_MAX_CARTES_MAIN; indiceBouton++){
			BoutonMain boutonCreer= new BoutonMain(this);
			mainJoueur.add(boutonCreer);
			this.boutonsMainJoueur[indiceBouton]=boutonCreer;
		}
		
		
		mainAdverse.setLayout(new GridLayout(1,2, 50, 50));
		mainJoueur.setLayout(new GridLayout(1,11,20 ,20));
		cartesPoseesAdverse.setLayout(new GridLayout(1,7, 20, 20));
		cartesPoseesJoueur.setLayout(new GridLayout(1,7, 20, 20));
		this.fenetre.setLayout(new GridLayout(4,1));
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
	
	public void associerControleur(Controleur controleur){
		this.controleur=controleur;
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
	
	
	public void afficherPlateau(){
		for (int indiceCarte=0;indiceCarte < this.controleur.getJoueurs()[1].getNbCartesPosees();indiceCarte++){
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException e1){
		
			}
			this.boutonsPlateauAdverse[indiceCarte].setCarte(this.controleur.getJoueurs()[1].getCartesPosees().getCartes()[indiceCarte]);
			this.boutonsPlateauAdverse[indiceCarte].afficherBouton();
		}
		
		for (int indiceCarte=0;indiceCarte < this.controleur.getJoueurs()[0].getNbCartesPosees();indiceCarte++){
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException e1){
		
			}
			this.boutonsPlateauJoueur[indiceCarte].setCarte(this.controleur.getJoueurs()[0].getCartesPosees().getCartes()[indiceCarte]);
			this.boutonsPlateauJoueur[indiceCarte].afficherBouton();
		}
		
		for (int indiceCarte=0;indiceCarte < this.controleur.getJoueurs()[0].getNbCartesMain();indiceCarte++){
			try{
				Thread.sleep(500);
			}
			catch (InterruptedException e1){
		
			}
			System.out.println(""+indiceCarte);
			this.boutonsMainJoueur[indiceCarte].setCarte(this.controleur.getJoueurs()[0].getMain().getCartes()[indiceCarte]);
			this.boutonsMainJoueur[indiceCarte].afficherBouton();
		}
		
		}
	
	public Controleur getControleur(){
		return controleur;
	}

	}