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
	
	private JPanel Partie;
		
		private BoutonMain[] boutonsMainJoueur;
		
		private BoutonPlateauAdverse[] boutonsPlateauAdverse;
		
		private BoutonPlateauJoueur[] boutonsPlateauJoueur;
		
	private MenuJeu menu;
	
	private Carte carteAttaquante;
	
	private Carte carteAPoser;
	
	private Personnage carteAttaquee;
	
	private Carte carteABuffer;
	
	private Carte carteDeck;

	private boolean tourFini;
	

	
	
	@Override
	public void run()
	{
		JFrame fenetre= new JFrame();
		this.fenetre=fenetre;
		this.fenetre.setSize(960, 755);
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
		this.tourFini=false;
	}
	
	



	public void initialiserPartie() {
		JPanel partie= new JPanel();
		PanelJoueur joueur1=new PanelJoueur(this.controleur.getJoueurs()[0].getHeros());
		joueur1.setOpaque(true);
		joueur1.setPreferredSize(new Dimension(375,120));
		
		PanelJoueur joueur2=new PanelJoueur(this.controleur.getJoueurs()[1].getHeros());
		joueur2.setOpaque(false);
		joueur2.setPreferredSize(new Dimension(375,120));
		
		ImagePanel infos= new ImagePanel(new ImageIcon("images/quart4.jpg").getImage());
		infos.setOpaque(false);
		
		JPanel cartesPoseesAdverse= new JPanel();
		cartesPoseesAdverse.setOpaque(false);
		cartesPoseesAdverse.setPreferredSize(new Dimension(750,120));
		
		JPanel mainJoueur= new JPanel();
		mainJoueur.setOpaque(false);
		mainJoueur.setPreferredSize(new Dimension(900,120));
		
		JPanel cartesPoseesJoueur= new JPanel();
		cartesPoseesJoueur.setOpaque(false);
		cartesPoseesJoueur.setPreferredSize(new Dimension(750,120));
		
		ImagePanel panelAdverse= new ImagePanel(new ImageIcon("images/quart3.png").getImage());
		ImagePanel panelJoueur= new ImagePanel(new ImageIcon("images/quart2.png").getImage());
		ImagePanel panelMain= new ImagePanel(new ImageIcon("images/quart1.png").getImage());
		
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
		cartesPoseesJoueur.add(new BoutonFinDeTour(this));


		for (int indiceBouton=0;indiceBouton < Jeu.NB_MAX_CARTES_MAIN; indiceBouton++){
			BoutonMain boutonCreer= new BoutonMain(this);
			mainJoueur.add(boutonCreer);
			this.boutonsMainJoueur[indiceBouton]=boutonCreer;
		}
		
		
		infos.setLayout(new GridLayout(1,2));
		mainJoueur.setLayout(new GridLayout(1,11,10 ,20));
		cartesPoseesAdverse.setLayout(new GridLayout(1,7, 20, 20));
		cartesPoseesJoueur.setLayout(new GridLayout(1,8, 20, 20));
		panelAdverse.add(cartesPoseesAdverse);
		panelJoueur.add(cartesPoseesJoueur);
		panelMain.add(mainJoueur);
		infos.add(joueur1);
		infos.add(joueur2);
		this.fenetre.setLayout(new GridLayout(4,1));
		this.fenetre.add(infos);
		this.fenetre.add(panelAdverse);
		this.fenetre.add(panelJoueur);
		this.fenetre.add(panelMain);		
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
		
		if (boutonAppuye instanceof BoutonFinDeTour){
			this.tourFini=true;
			this.tourFini=false;
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
		for (int indiceCarte=0;indiceCarte < Jeu.NB_CARTES_MAX_POSEES;indiceCarte++){
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException e1){
		
			}
			if (indiceCarte < this.controleur.getJoueurs()[1].getNbCartesPosees())
			this.boutonsPlateauAdverse[indiceCarte].setCarte(this.controleur.getJoueurs()[1].getCartesPosees().getCartes()[indiceCarte]);
			this.boutonsPlateauAdverse[indiceCarte].afficherBouton();
		}
		
		for (int indiceCarte=0; indiceCarte < Jeu.NB_CARTES_MAX_POSEES;indiceCarte++){
			try{
				Thread.sleep(1000);
			}
			catch (InterruptedException e1){
		
			}
			this.boutonsPlateauJoueur[indiceCarte].setCarte(this.controleur.getJoueurs()[0].getCartesPosees().getCartes()[indiceCarte]);
			this.boutonsPlateauJoueur[indiceCarte].afficherBouton();
		}
		
		for (int indiceCarte=0;indiceCarte < Jeu.NB_MAX_CARTES_MAIN;indiceCarte++){
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


	public boolean getTourFini() {
		return this.tourFini;
	}

	}