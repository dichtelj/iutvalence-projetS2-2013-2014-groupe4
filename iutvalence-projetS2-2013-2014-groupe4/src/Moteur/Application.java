package Moteur;
import IHM.Affichage;

public class Application {

	public static void main(String[] args) {
		Joueur[] joueurs= new Joueur[]{new JoueurAleatoire(1, "Noxus"), new JoueurAleatoire(2, "Demacia")};
		Affichage ihm=new AffichageConsole();
		Jeu jeu=new Jeu(joueurs, ihm);
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
