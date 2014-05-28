package Moteur;

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

	
	
	public Effet(String activation, String nom, int montantDeDegat, int nbCible){
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
	
	public void appliquerEffet(Joueur joueur, Joueur joueurAdverse){
		Personnage carteChoisie=null;
		if (((this.nom.compareTo("degat direct"))==0) && (this.nbCible==7)){
				for (int nbCible=0; nbCible < Jeu.NB_CARTES_MAX_POSEES; nbCible++)
					joueur.getCartesPosees().cartes[nbCible].subirDegats(this.montantDeDegat);
		}
		if ((this.nom).compareTo("degat direct")==0){
			for (int i=0; i<this.nbCible;i++)
				carteChoisie=joueur.choisirPersonnageAAttaquer(joueurAdverse);
				carteChoisie.subirDegats(this.montantDeDegat);
		}
		
		if (this.nom.compareTo("buff attaque")==0 && joueur.getNbCartesPlateau() > 0){
			carteChoisie=joueur.choisirCarteABuffer();
			((Carte)carteChoisie).setDegatAttaque(this.montantDeDegat);				
		}
		
		if (this.nom.compareTo("buff vie")==0 && joueur.getNbCartesPlateau() > 0){
			carteChoisie=joueur.choisirCarteABuffer();
			((Carte)carteChoisie).buffVieMax(this.montantDeDegat);
		}
		
		if ((((this.nom.compareTo("heal"))==0) && (this.nbCible==7))){
			for (int nbCible=0; nbCible < joueur.getNbCartesPlateau(); nbCible++)
				joueur.getCartesPosees().cartes[nbCible].soigner(this.montantDeDegat);
		}
			
		if ((this.nom.compareTo("heal")==0 && joueur.getNbCartesPlateau() > 0)){
			carteChoisie=joueur.choisirCarteABuffer();
			carteChoisie.soigner(this.montantDeDegat);
		}
		
		if(this.nom.compareTo("piocher")==0)
			for (int cartePiochees=0; cartePiochees < this.montantDeDegat; cartePiochees++)
				joueur.piocherCarte();
	}
}
