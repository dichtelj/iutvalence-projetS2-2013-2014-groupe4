
public class Application {

	public static void main(String[] args) {
		Joueur[] joueurs= new Joueur[]{new JoueurAleatoire(1, "Noxus"), new JoueurAleatoire(2, "Demacia")};
		Jeu jeu=new Jeu(joueurs);
		try
		{
			jeu.preparerPartie();
		}
		
		catch (DeckInvalide e1){
			System.out.println("Deck non rempli");
		}
		jeu.jouer();
	}

}
