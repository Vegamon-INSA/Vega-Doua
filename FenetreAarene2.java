import javax.swing.*;
import java.awt.*;


public class FenetreAarene2 extends JFrame{
	private JTextArea arene2;
	private JScrollPane scrollarene2;
	
	public FenetreAarene2(){
		this.setTitle("Arene 2");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
		arene2 = new JTextArea ("Bravo champion, si tu es arrive a ce stade du scenario c'est que tu es deja champion de l'arène PC ! Mais comme tu as pu le comprendre, l'aventure ne s'arrete pas ici. Si tu veux devenir le dresseur le plus puissant de la Doua, il faut continuer ton chemin en direction des humas ! Tu y trouveras des dresseurs encore plus fort, et… une etrange statue. \n\nNous arrivons donc sur les humas1, une grande prairie luxuriante. Une etendue qui donne comme une envie folle de faire la fete toute la nuit. Mais avant cela, 4 dresseurs s'opposent a nous ! \nIl faudra les vaincre pour acceder a la map suivante : les humas2. De nouveau, 4 dresseurs sont presents pour se battre, une fois vaincus, nous pourrons enfin aller dans la derniere map du jeu. \n\nNous y voila, nous sommes a la Tete d'Or, la plus belle map du jeu, l'endroit parfait pour se balader et prendre du bon temps, mais surtout pour devenir le plus grand dresseur vegamon de la Doua ! En effet, c'est ici que se trouve la derniere arene du jeu, avec les dresseurs et les vegamons les plus puissants. Avant de nous y rendre, comme d'habitude, nous pouvons nous balader dans les differentes turnes pour obtenir quelques informations ou juste pour se faire de nouveaux amis. \nBonne chance pour ces derniers combats qui, je l'espere, te donneront du fil a retordre !"); 
		scrollarene2 = new JScrollPane (arene2); 
		arene2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
		arene2.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
		arene2.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
		arene2.setEditable (false); //empêche l'utilisateur de modifier le texte

		scrollarene2.setBounds(40,40,500,350);
		this.add(scrollarene2);
	}
}
		
