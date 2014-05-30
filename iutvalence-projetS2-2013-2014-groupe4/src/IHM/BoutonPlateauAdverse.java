package IHM;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;

public class BoutonPlateauAdverse extends JButton{
	
	private Carte carte;
	
	public BoutonPlateauAdverse(ActionListener auditeur)
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
		if (this.carte!=null){
			   this.setEnabled(true);
			   this.setText("<html>"+this.carte.getNom()+"<br>"+this.carte.getAttaque()+"/"+this.carte.getVie()+"<br>"+"Cout:"+this.carte.getCout()+"</html>");
		}
		else{
			this.setText(null);
			this.setEnabled(false);
		}
	}
}