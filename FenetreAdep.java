import javax.swing.*;
import java.awt.*;


public class FenetreAdep extends JFrame{
	private JTextArea explid;
	private JScrollPane scrollexplid;
	
	public FenetreAdep(){
		this.setTitle("Comment se deplacer sur la map");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
		explid = new JTextArea ("Pour se deplacer dans la map rien de plus simple ! \nIl suffit de cliquer avec ta souris l'endroit auquel tu souhaites te rendre et ton personnage trouvera le chemin par lui meme ! \n Tu peux cliquer sur des portes ou sur des fleches pour changer de map. Mais si tu cliques sur un arbre, un lac, ou sur un batiment (ailleurs que sur la porte) ton personnage ne pourra pas s'y rendre et ne bougera pas d'un pouce !\nTu peux aussi cliquer sur un personnage pour entre en interaction avec lui, pour discuter ou te battre ! \nAmuse-toi bien !"); 
		scrollexplid = new JScrollPane (explid); 
		explid.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
		explid.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
		explid.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
		explid.setEditable (false); //empêche l'utilisateur de modifier le texte

		scrollexplid.setBounds(40,40,500,350);
		this.add(scrollexplid);
	}
}
		
