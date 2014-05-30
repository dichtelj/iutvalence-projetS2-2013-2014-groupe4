package IHM;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import Moteur.Carte;
import Moteur.Heros;

public class BoutonHeros extends JButton{
	
	private Heros heros;
	
	public BoutonHeros(Heros heros)
	{
		this.heros=heros;
		
	}
	
	public Heros getHeros(){
		return this.heros;
	}
	
	public void setHeros(Heros heros){
		this.heros=heros;
	}
}
