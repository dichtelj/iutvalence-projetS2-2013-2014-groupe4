package IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;

public class BoutonMain extends JButton{
	
	private Carte carte;
	
	public BoutonMain(Carte carte, ActionListener auditeur)
	{
		this.carte=carte;
		this.addActionListener(auditeur);
	}
	
	public Carte getCarte(){
		return this.carte;
	}
}
