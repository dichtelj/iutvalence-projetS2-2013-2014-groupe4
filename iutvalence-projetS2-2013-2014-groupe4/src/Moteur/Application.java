package Moteur;
import IHM.Affichage;
import IHM.AffichageConsole;
import IHM.AffichageFenetre;
public class Application {

	public static void main(String[] args) {
		Joueur[] joueurs= new Joueur[]{new JoueurAleatoire(1, "Noxus"), new JoueurAleatoire(2, "Demacia")};
		Affichage ihm=new AffichageFenetre();
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
