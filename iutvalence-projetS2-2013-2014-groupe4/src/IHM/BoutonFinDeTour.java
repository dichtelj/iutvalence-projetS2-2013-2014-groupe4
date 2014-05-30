package IHM;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoutonFinDeTour extends JButton{
	
	private boolean tourFini;
	
	public BoutonFinDeTour(ActionListener auditeur)
	{
		this.tourFini=false;
		this.addActionListener(auditeur);
		
	}
	
	public boolean getTourFini()
	{
		return this.tourFini;
	}
	
	public void setTourFini()
	{
		this.tourFini = tourFini;
	}
	
	public void afficherBouton()
	{
		this.setEnabled(true);
		this.setText("Fin du tour");
	}
}
