
public class Plateau {


	private int nbCartes;
	

	private ListeDeCarte cartesJoueur1;
	
	private ListeDeCarte carteJoueur2;
	
	private final int  DEFAULT_CONSTANT_CARTEMAX=7;
	
	
	public boolean estPlein()
	{
		if (this.cartesJoueur1.length()==this.DEFAULT_CONSTANT_CARTEMAX)
				return true;
		return false ;
	
	}
	
}
