public class Partie {

	private Plateau plateau;

	private ListeDeCarte[] decks;

	public Partie() {
		
		this.plateau= new Plateau();
		
		this.decks= new ListeDeCarte[2];
	}
}
