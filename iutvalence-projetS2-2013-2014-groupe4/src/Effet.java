
/**
 * Définit un effet d'une carte
 * @author antoine
 *
 */
public class Effet {
	
	/**
	 * Définit le moment où l'effet s'active (au début d'un tour, a la fin, a la mort de la créature, etc...)
	 */
	private String activation;
	
	
	/**
	 * Définit le nom de l'effet
	 */
	private String nom;
	
	/**
	 * Définit les degats (ou pas) infligés par l'effet
	 */
	private int montantDeDegat;
	
	/**
	 * Définit le nombre de cible affecté par l'effet
	 */
	private int nbCible;

	
	
	private Effet(String activation, String nom, int montantDeDegat, int nbCible){
		this.activation=activation;
		this.montantDeDegat=montantDeDegat;
		this.nbCible=nbCible;
		this.nom=nom;
	}


	public String getActivation() {
		return this.activation;
	}


	public String getNom() {
		return this.nom;
	}


	public int getMontantDeDegat() {
		return this.montantDeDegat;
	}


	public int getNbCible() {
		return this.nbCible;
	}
	
	public void appliquerEffet(Plateau plateau, Joueur joueur, int numeroJoueur){
		if ((this.nom).compareTo("sort")==0)
			for (int i=0; i<this.nbCible;i++)
				if (numeroJoueur==1)
					Carte cartechoisie=joueur.choisirCarte(plateau.getCartesJoueur2());
				else Carte cartechoisie=joueur.choisirCarte(plateau.getCartesJoueur1());
		
		
	}
}
