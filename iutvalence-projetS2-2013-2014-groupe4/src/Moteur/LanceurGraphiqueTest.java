package Moteur;

import javax.swing.SwingUtilities;

import IHM.AffichageFenetre;

public class LanceurGraphiqueTest {

	public static void main(String[] args) {
		AffichageFenetre ihm= new AffichageFenetre(1, "demacia");
		SwingUtilities.invokeLater(ihm);

	}

}
