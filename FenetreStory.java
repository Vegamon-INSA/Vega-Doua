import javax.swing.*;
import java.awt.*;

public class FenetreIntro extends JFrame{
	private JTextArea intro;
	private JButton feyssine, travees, pc, humas, teteor;
	
	public FenetreIntro(){
		this.setTitle("Introduction");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
		intro = new JTextArea ("Après une paisible nuit, nous nous réveillons dans notre jolie turne, c’est ici que commence notre incroyable aventure Végamon.\n En sortant de la turne, le merveilleux parc de la Feyssine s’offre à nous, l’air est frais, l’herbe est douce, le temps est parfait pour recevoir notre premier compagnon de voyage ! En effet, en sortant de notre turne, et après que notre mère nous est expliquée comment nous déplacer dans les maps, le professeur Véga vient à notre rencontre et nous propose de devenir dresseur Végamon. Il voit en nous un énorme potentiel et nous voit comme la future star de la ville. C’est pourquoi il nous fait don de notre premier Végamon : Méloche. \nCe dernier est au niveau 1 et sera notre compagnon pour toute l’aventure, il faudra en prendre soin."); 
		JScrollPane scrollstory = new JScrollPane (story); 
		intro.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
		intro.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
		intro.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
		intro.setEditable (false); //empêche l'utilisateur de modifier le texte

		intro.setBounds(40,40,500,500);
		this.add(scrollstory);
		
	}
}
		
