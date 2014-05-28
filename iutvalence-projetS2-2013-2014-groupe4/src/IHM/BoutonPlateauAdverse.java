package IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;

public class BoutonPlateauAdverse extends JButton{
	
	private Carte carte;
	
	public BoutonPlateauAdverse(Carte carte, ActionListener auditeur)
	{
		this.carte=carte;
		this.addActionListener(auditeur);
	}
}