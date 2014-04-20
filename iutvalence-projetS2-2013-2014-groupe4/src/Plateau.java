public class Plateau {

	private int nbCartes;

	private ListeDeCarte cartesJoueur1;

	private ListeDeCarte carteJoueur2;

	private final int DEFAULT_CONSTANT_CARTEMAX = 7;

	public boolean estPlein() {
		return false;
	}

	public Plateau(){
		this.nbCartes=0;
		
		this.cartesJoueur1=new Deck();		
		this.carteJoueur2=new Deck();
	}
}
