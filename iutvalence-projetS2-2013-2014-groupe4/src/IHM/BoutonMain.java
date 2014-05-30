package IHM;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;



import Moteur.Carte;

public class BoutonMain extends JButton{
	
	private Carte carte;
	
	public BoutonMain(ActionListener auditeur)
	{
		this.carte= new Carte("anduin", 0, 0, null, 4, null);
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
	}
}
