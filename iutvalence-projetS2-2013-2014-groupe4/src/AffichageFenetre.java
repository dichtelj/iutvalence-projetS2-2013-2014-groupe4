import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AffichageFenetre implements Affichage, Runnable
{

	@Override
	public void run()
	{
		JFrame fenetre= new JFrame();
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fenetre.setVisible(true);
	}
}