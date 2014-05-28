package Moteur;
import IHM.Affichage;

public interface Controleur {

		public void debutTour(Joueur joueur) ;

		public void preparerPartie() throws DeckInvalide ;

		public void jouer() ;

		public void jouerTourBot(JoueurAleatoire joueurCourant) ;

		public void jouerTour(Joueur joueurCourant) ;

		public void jouerTourIntermediaire(Joueur joueurCourant) ;

		public boolean partieFinie();
		
		public void finTour(Joueur joueur);
		
		public void poserCarte(Carte carte, Joueur joueur) throws PlateauPlein, ManaInsuffisant;
		
		public void attaquerAvecCarte(Carte carte, Personnage personnage, Joueur joueur) throws CibleInvalide, EstInactif;
		
		public boolean cibleViable(Personnage personnage, Joueur joueur);
		
		public boolean existeProvocation(Joueur joueur);
		
		public Joueur[] getJoueurs();
		
		public void construireDeck(ListeDeCartes liste);
		
		public void viderPlateau();
		
		public void reOrganiserCartes(Joueur joueur);		

		public void melangerDeck(Joueur joueur);		

		public void attribuerMainDepart(Joueur joueur);
		
		public void jeterCarte(Carte carte, Joueur joueur);		
		
		public String toString();
		
		public Affichage getVue();
		
	}
