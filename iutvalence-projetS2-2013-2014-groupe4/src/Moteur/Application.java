package Moteur;
import javax.swing.SwingUtilities;

import IHM.Affichage;
import IHM.AffichageConsole;
import IHM.AffichageFenetre;
public class Application {

	public static void main(String[] args) {
		Joueur[] joueurs= new Joueur[]{new JoueurAleatoire(2,"Joueur du bas"), new JoueurAleatoire(1, "Joueur du haut")};

		Affichage ihm = new AffichageFenetre(2,"Noxus");
		Jeu jeu=new Jeu(joueurs, ihm);
		SwingUtilities.invokeLater((Runnable) ihm);
		ihm.associerControleur(jeu);
		try
		{
			jeu.preparerPartie();
		}
		
		catch (DeckInvalide e1){
			jeu.getVue().afficherMessageErreur("Deck non rempli");
		}
		jeu.jouer();
	}

}