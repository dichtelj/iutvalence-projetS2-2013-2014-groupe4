package IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;

public class BoutonPlateauJoueur extends JButton{
	
	private Carte carte;
	
	public BoutonPlateauJoueur(Carte carte, ActionListener auditeur)
	{
		this.carte=carte;
		this.addActionListener(auditeur);
	}
}