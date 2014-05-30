package IHM;

import Moteur.Carte;
import Moteur.Controleur;
import Moteur.Joueur;
import Moteur.Personnage;


public class AffichageConsole implements Affichage{


	public void run() {
		
	}

	public void afficherMessageErreur(String str) {
		System.out.println(str);		
	}

	public void initialiserPartie() {
		
	}

	@Override
	public void afficherPlateau() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Carte choisirCarteDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carte choisirCarteAttaquante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnage choisirCarteABuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carte choisirCarteAPoser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carte getCarteAPoser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carte getCarteAttaquante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void associerControleur(Controleur controleur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getTourFini() {
		// TODO Auto-generated method stub
		return false;
	}

}
