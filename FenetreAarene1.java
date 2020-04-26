import javax.swing.*;
import java.awt.*;


public class FenetreAarene1 extends JFrame{
	private JTextArea arene1;
	private JScrollPane scrollarene1;
	
	public FenetreAarene1(){
		this.setTitle("Arene 1");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
		arene1 = new JTextArea ("Nous voila dans les travees, la premiere map un peu dangereuse de l'aventure. Plusieurs dresseurs nous attendent avec la ferme intention d'en decoudre. Pour tous les vaincre il peut etre utile d'entrainer notre Meloche dans les trefles et lui faire gagner quelques points d’experiences. \nBis repetita pour la map suivante : les travees 2, de plus feroces dresseurs encore nous font face. Une fois tous ces vegamons vaincus, nous nous retrouvons dans une agreable petite ville appelée PC (ou FIMI pour les plus temeraires). C'est dans cette ville que se trouve la fameuse arene, la premiere de notre aventure avec a sa tete Veronique, dresseuse de longue date, dont la reputation n'est plus a faire. Avant de l'affronter nous pouvons nous balader dans les autres turnes et discuter avec ses habitants. Nous pourrons decouvrir de chaleureux etudiants trinquant a leurs echecs du semestre, ou d'autres personnages un peu plus utiles qui nous donneront quelques conseils pour nous rendre dans l arene et vaincre Veronique. \nD'ailleurs il est grand temps de s y atteler ! En entrant dans l'arene, M.Securitas nous accueille, il nous avertit de la puissance des vegamon que nous allons affronter et nous defend d'entrer si notre Meloche n'a pas au moins *points d’XP*. Bonne chance, il faudra vaincre les 4 dresseurs qui se trouvent sur le chemin avant, enfin, de pouvoir se mesurer a Veronique.");
		scrollarene1 = new JScrollPane (arene1); 
		arene1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
		arene1.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
		arene1.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
		arene1.setEditable (false); //empêche l'utilisateur de modifier le texte

		scrollarene1.setBounds(40,40,500,350);
		this.add(scrollarene1);
	}
}
		
