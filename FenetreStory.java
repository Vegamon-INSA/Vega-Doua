import javax.swing.*;
import java.awt.*;

public class FenetreStory extends JFrame{
	private JTextArea story;
	private JButton feyssine, travees, pc, humas, teteor;
	
	public FenetreStory(){
		this.setTitle("Scenario du jeu");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
		story = new JTextArea ("Disponible prochainement !"); 
		JScrollPane scrollstory = new JScrollPane (story); 
		story.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
		story.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
		story.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
		story.setEditable (false); //empêche l'utilisateur de modifier le texte

		scrollstory.setBounds(40,40,500,500);
		this.add(scrollstory);
		
	}
}
		
