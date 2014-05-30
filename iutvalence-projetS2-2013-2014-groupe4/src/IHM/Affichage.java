package IHM;

import Moteur.Carte;
import Moteur.Controleur;
import Moteur.Joueur;
import Moteur.Personnage;


public interface Affichage
{
		public void run();
		
		public void afficherMessageErreur(String str);
		
		public void initialiserPartie();
		
		public void afficherPlateau();
		
		public Carte choisirCarteDeck();

		public Carte choisirCarteAttaquante();

		public Personnage choisirPersonnageAAttaquer(Joueur joueurAdverse);

		public Personnage choisirCarteABuffer();

		public Carte choisirCarteAPoser();
		
		public Carte getCarteAPoser();

		public Carte getCarteAttaquante();
		
		public void associerControleur(Controleur controleur);

		public boolean getTourFini();

}