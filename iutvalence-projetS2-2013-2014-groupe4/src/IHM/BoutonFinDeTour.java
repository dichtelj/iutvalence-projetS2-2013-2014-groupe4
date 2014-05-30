package IHM;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoutonFinDeTour extends JButton{
	
	public BoutonFinDeTour(ActionListener auditeur)
	{
		this.addActionListener(auditeur);
		this.setBackground(Color.green);
		
	}
	
	public void afficherBouton()
	{
		this.setEnabled(true);
		this.setText("Fin du tour");
	}
}
