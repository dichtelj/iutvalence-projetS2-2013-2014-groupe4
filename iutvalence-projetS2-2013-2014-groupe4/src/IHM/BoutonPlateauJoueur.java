package IHM;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;

public class BoutonPlateauJoueur extends JButton{
	
	private Carte carte;
	
	
	public BoutonPlateauJoueur(ActionListener auditeur)
	{
		this.carte=null;
		this.addActionListener(auditeur);
	}
	
	public Carte getCarte(){
		return this.carte;
	}
	
	public void setCarte(Carte carte){
		this.carte=carte;
	}
	
	public void afficherBouton(){
		if (this.carte==null)
			this.setEnabled(false);
		else{
			this.setEnabled(true);
			this.setText(""+this.carte.toString());
		}
	}
}