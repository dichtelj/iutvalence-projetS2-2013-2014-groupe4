package IHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import Moteur.Joueur;
import Moteur.Personnage;
import Moteur.Position;

public class AffichageFenetre extends Joueur implements Affichage, Runnable, ActionListener 
{
	private JFrame fenetre;
	
	private Controleur controleur;
	
    private JPanel EcranDAccueil;
	
	private JPanel ListeCartesGenerales;
	
	private JPanel Partie;
	
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
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		cartesPoseesJoueur.setBackground(Color.yellow);
		
		//On sépare l'écran en deux partie coteHautEcra et coteBasEcran
		JSplitPane splitPaneMilieuDEcran= new JSplitPane(JSplitPane.VERTICAL_SPLIT, coteHautEcran, coteBasEcran);
		
		
		//On sépare ensuite la partie haute de l'écran en mainAdverse et cartesPoseesAdverse
		JSplitPane separationHautEcran= new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainAdverse, cartesPoseesAdverse);
		separationHautEcran.setBorder(null);
		separationHautEcran.setDividerSize(0);
		coteHautEcran.add(separationHautEcran);
		separationHautEcran.setMinimumSize(new Dimension(450,700));
		System.out.println(""+separationHautEcran.getParent());
		
		//De même avec la partie basse
		JSplitPane separationBasEcran = new JSplitPane(JSplitPane.VERTICAL_SPLIT, cartesPoseesJoueur, mainJoueur);
		separationBasEcran.setBorder(null);
		separationBasEcran.setDividerSize(0);
		coteBasEcran.add(separationBasEcran);

		partie.add(splitPaneMilieuDEcran);
		splitPaneMilieuDEcran.setResizeWeight(0.5);
		splitPaneMilieuDEcran.setBorder(null);
		splitPaneMilieuDEcran.setDividerSize(0);
		this.fenetre.add(splitPaneMilieuDEcran);
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