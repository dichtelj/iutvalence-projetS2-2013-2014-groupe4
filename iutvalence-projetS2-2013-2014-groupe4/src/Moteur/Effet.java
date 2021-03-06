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

	/**
	 * Permet de créer un nouvel effet
	 * @param activation
	 * @param nom
	 * @param montantDeDegat
	 * @param nbCible
	 */
	
	public Effet(String activation, String nom, int montantDeDegat, int nbCible){
		this.activation=activation;
		this.montantDeDegat=montantDeDegat;
		this.nbCible=nbCible;
		this.nom=nom;
	}

	/**
	 * methode qui renvoie le mode d'activation de l'effet
	 * @return String 
	 */
	public String getActivation() {
		return this.activation;
	}

	/**
	 * methode qui renvoie le nom de l'effet
	 * @return String 
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * methode qui renvoie le montant de dégats qu'occasionne l'effet
	 * @return int 
	 */
	public int getMontantDeDegat() {
		return this.montantDeDegat;
	}

	/**
	 * methode qui renvoie le nombre de cibles affectées par l'effet
	 * @return int
	 */
	public int getNbCible() {
		return this.nbCible;
	}
	
	/**
	 * methode qui applique l'effet sur les cartes du joueur ou du joueur adverse , ou sur le joueur adverse
	 * @return void 
	 *@param joueur : le joueur lançant l'effet
	 *@param joueurAdverse : le joueur adverse
	 */
	public void appliquerEffet(Joueur joueur, Joueur joueurAdverse){
		Personnage carteChoisie=null;
		if (((this.nom.compareTo("degat direct"))==0) && (this.nbCible==7))
				for (int i=0; i < joueurAdverse.getCartesPosees().nbCartes; i++)
					joueurAdverse.getCartesPosees().cartes[i].subirDegats(this.montantDeDegat);
		
		if ((this.nom).compareTo("degat direct")==0){
			int nbCibleEffectif;
			if (joueurAdverse.getCartesPosees().getNbCartes() < this.nbCible-1)
				nbCibleEffectif=joueurAdverse.getCartesPosees().getNbCartes()+1;
			nbCibleEffectif=this.nbCible;
			for (int i=0; i<nbCibleEffectif;i++)
				carteChoisie=joueur.choisirPersonnageAAttaquer(joueurAdverse);
				carteChoisie.subirDegats(this.montantDeDegat);
		}
		
		if (this.nom.compareTo("buff attaque")==0 && joueur.getNbCartesPosees() > 0){
			carteChoisie=joueur.choisirCarteABuffer();
			((Carte)carteChoisie).setDegatAttaque(this.montantDeDegat);				
		}
		
		if (this.nom.compareTo("buff vie")==0 && joueur.getNbCartesPosees() > 0){
			carteChoisie=joueur.choisirCarteABuffer();
			((Carte)carteChoisie).buffVieMax(this.montantDeDegat);
		}
		
		if ((((this.nom.compareTo("heal"))==0) && (this.nbCible==7))){
		for (int i=0; i < joueur.getCartesPosees().getNbCartes();i++)
				joueur.getCartesPosees().cartes[i].soigner(this.montantDeDegat);
		}
			
		if ((this.nom.compareTo("heal")==0 && joueur.getNbCartesPosees() > 0)){
			carteChoisie=joueur.choisirCarteABuffer();
			carteChoisie.soigner(this.montantDeDegat);
		}
		
		if(this.nom.compareTo("piocher")==0)
			for (int cartePiochees=0; cartePiochees < this.montantDeDegat; cartePiochees++)
				joueur.piocherCarte();
	}
}
