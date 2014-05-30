package IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;
import Moteur.Heros;

public class BoutonHeros extends JButton{
	
	private Heros heros;
	
	public BoutonHeros(Heros heros, ActionListener auditeur)
	{
		this.heros=heros;
		this.addActionListener(auditeur);
	}
	
	public Heros getHeros(){
		return this.heros;
	}
}
