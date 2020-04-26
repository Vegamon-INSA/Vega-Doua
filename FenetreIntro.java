import javax.swing.*;
import java.awt.*;

public class FenetreIntro extends JFrame{
	private JTextArea intro;
	private JScrollPane scrollintro;
	
	public FenetreIntro(){
		this.setTitle("Introduction");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
		intro = new JTextArea ("Apres une paisible nuit, nous nous reveillons dans notre jolie turne, c'est ici que commence notre incroyable aventure Vegamon.\n En sortant de la turne, le merveilleux parc de la Feyssine s'offre à nous, l'air est frais, l'herbe est douce, le temps est parfait pour recevoir notre premier compagnon de voyage ! En effet, le professeur Vega, grand chercheur vegamon vient a notre rencontre et nous propose de devenir dresseur Vegamon. Il voit en nous un enorme potentiel et nous definit comme LA future star de la ville. C est pourquoi il nous fait don de notre premier Vegamon : Meloche. \nCe dernier est au niveau 1 et sera notre compagnon pour toute l aventure, il faudra en prendre soin. \nUn peu plus loin au sud, Flora, une amie du professeur nous donne quelque conseils pour bien debuter dans l'aventure. Juste a cote d'elle, sa turne, ou nous pouvons faire une petite halte et papoter avec ses autres habitants. Nous y apprenons qu il y a une arene au nord de la ville derriere les travees et que le meilleur dresseur s y trouve. L’un d’eux nous conseille de nous y rendre pour pourquoi pas devenir le nouveau champion de la ville. Mais attention, notre Meloche devra gagner beaucoup d experiences si nous voulons entrer dans cette arene. Une fois le pont traverse et avant d arriver aux travees, Remi, jeune dresseur lui aussi, nous avertit du danger que l'on encoure en poursuivant l'aventure. Brave, nous continuons sans hesiter et nous nous dirigeons vers les travees."); 
		scrollintro = new JScrollPane (intro); 
		intro.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
		intro.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
		intro.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
		intro.setEditable (false); //empêche l'utilisateur de modifier le texte

		scrollintro.setBounds(40,40,500, 350);
		this.add(scrollintro);
		
	}
}
		
