package IHM;

import java.awt.Dimension;
import java.awt.Insets;
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
		this.setMargin(new Insets(3, 3, 3, 3));
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
			this.setText("<html>"+this.carte.getNom()+"<br>"+this.carte.getAttaque()+"/"+this.carte.getVie()+"<br>"+"Cout:"+this.carte.getCout()+"</html>");
		}
	}
}