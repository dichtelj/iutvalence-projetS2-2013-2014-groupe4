package IHM;

import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Moteur.Carte;

public class BoutonMain extends JButton{
	
	private Carte carte;
	
	public BoutonMain(ActionListener auditeur)
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
