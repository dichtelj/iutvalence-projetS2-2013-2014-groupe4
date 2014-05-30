package IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;

public class BoutonPlateauAdverse extends JButton{
	
	private Carte carte;
	
	public BoutonPlateauAdverse(ActionListener auditeur)
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
}