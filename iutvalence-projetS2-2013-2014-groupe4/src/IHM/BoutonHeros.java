package IHM;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Moteur.Carte;
import Moteur.Heros;

public class BoutonHeros extends JButton{
	
	private Heros heros;
	
	public BoutonHeros(Heros heros)
	{
		this.heros=heros;
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setForeground(Color.white);
	}
	
	public Heros getHeros(){
		return this.heros;
	}
	
	public void setHeros(Heros heros){
		this.heros=heros;
	}
	
	public void afficherBouton(){
		this.setText("<html>"+this.heros.getNom()+"<br>Points de vie : "+this.heros.getPointsDeVie()+"<br> Mana max :"+this.heros.getNbManaMax()+"<br> Mana courant : "+this.heros.getNbManaCourant());
	}
}
