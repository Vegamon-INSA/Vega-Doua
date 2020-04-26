import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreScenario implements ActionListener{
	
	private JButton adep, aintro, aarene1, aarene2, retouraccueil, retourScenario;
	private JPanel pPrincipal;
    private JLabel imageFond ;
	private CJframe JFramePrincipal; 
	private VariablesDeJeu VariablesSession; 
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu;
	private JTextArea intro, arene1, arene2, deplace ;
	private JScrollPane scrollintro,scrollarene1,scrollarene2, scrolldeplace;
	
	public FenetreScenario( CJframe Frame,VariablesDeJeu variables, Sauvegarde sauvegarde, Musiques musique) {
		
		JFramePrincipal= Frame;
		VariablesSession=variables;
		SauvegardeJeu=sauvegarde;
		MusiqueDeJeu=musique;
        
        pPrincipal = new JPanel();
        pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
        JFramePrincipal.add(pPrincipal);
		
        adep = new JButton("Comment se deplacer");
        adep.setBounds(200, 50, 200, 90);
        pPrincipal.add(adep);
        adep.setLayout(null);
        adep.addActionListener(this);

        aintro = new JButton("Introduction");
        aintro.setBounds(200, 175, 200, 90);
        pPrincipal.add(aintro);
        aintro.setLayout(null);
        aintro.addActionListener(this);	
        
        aarene1 = new JButton("Arene 1");
        aarene1.setBounds(200, 310, 200, 90);
        pPrincipal.add(aarene1);
        aarene1.setLayout(null);
        aarene1.addActionListener(this);
        	
        aarene2 = new JButton("Arene 2");
        aarene2.setBounds(200, 435, 200, 90);
        pPrincipal.add(aarene2);
        aarene2.setLayout(null);
        aarene2.addActionListener(this);
		
		retouraccueil = new JButton("RETOUR");
        retouraccueil.setBounds(325, 600, 150, 70);
        pPrincipal.add(retouraccueil);
        retouraccueil.setLayout(null);
        retouraccueil.addActionListener(this);
        
        retourScenario = new JButton("RETOUR");
        retourScenario.setBounds(325, 600, 150, 70);
        retourScenario.setLayout(null);
        retourScenario.addActionListener(this);

        imageFond = new JLabel(new ImageIcon("Images/fond.png")); 
		imageFond.setBounds(0,0,800,820);
		pPrincipal.add(imageFond);
		JFramePrincipal.setVisible(true);
		
		intro = new JTextArea ("Apres une paisible nuit, nous nous reveillons dans notre jolie turne, c'est ici que commence notre incroyable aventure Vegamon.\n En sortant de la turne, le merveilleux parc de la Feyssine s'offre à nous, l'air est frais, l'herbe est douce, le temps est parfait pour recevoir notre premier compagnon de voyage ! En effet, le professeur Vega, grand chercheur vegamon vient a notre rencontre et nous propose de devenir dresseur Vegamon. Il voit en nous un enorme potentiel et nous definit comme LA future star de la ville. C est pourquoi il nous fait don de notre premier Vegamon : Meloche. \nCe dernier est au niveau 1 et sera notre compagnon pour toute l aventure, il faudra en prendre soin. \nUn peu plus loin au sud, Flora, une amie du professeur nous donne quelque conseils pour bien debuter dans l'aventure. Juste a cote d'elle, sa turne, ou nous pouvons faire une petite halte et papoter avec ses autres habitants. Nous y apprenons qu il y a une arene au nord de la ville derriere les travees et que le meilleur dresseur s y trouve. L’un d’eux nous conseille de nous y rendre pour pourquoi pas devenir le nouveau champion de la ville. Mais attention, notre Meloche devra gagner beaucoup d experiences si nous voulons entrer dans cette arene. Une fois le pont traverse et avant d arriver aux travees, Remi, jeune dresseur lui aussi, nous avertit du danger que l'on encoure en poursuivant l'aventure. Brave, nous continuons sans hesiter et nous nous dirigeons vers les travees."); 
		arene1 = new JTextArea ("Nous voila dans les travees, la premiere map un peu dangereuse de l'aventure. Plusieurs dresseurs nous attendent avec la ferme intention d'en decoudre. Pour tous les vaincre il peut etre utile d'entrainer notre Meloche dans les trefles et lui faire gagner quelques points d’experiences. \nBis repetita pour la map suivante : les travees 2, de plus feroces dresseurs encore nous font face. Une fois tous ces vegamons vaincus, nous nous retrouvons dans une agreable petite ville appelée PC (ou FIMI pour les plus temeraires). C'est dans cette ville que se trouve la fameuse arene, la premiere de notre aventure avec a sa tete Veronique, dresseuse de longue date, dont la reputation n'est plus a faire. Avant de l'affronter nous pouvons nous balader dans les autres turnes et discuter avec ses habitants. Nous pourrons decouvrir de chaleureux etudiants trinquant a leurs echecs du semestre, ou d'autres personnages un peu plus utiles qui nous donneront quelques conseils pour nous rendre dans l arene et vaincre Veronique. \nD'ailleurs il est grand temps de s y atteler ! En entrant dans l'arene, M.Securitas nous accueille, il nous avertit de la puissance des vegamon que nous allons affronter et nous defend d'entrer si notre Meloche n'a pas au moins *points d’XP*. Bonne chance, il faudra vaincre les 4 dresseurs qui se trouvent sur le chemin avant, enfin, de pouvoir se mesurer a Veronique.");
		arene2 = new JTextArea ("Bravo champion, si tu es arrive a ce stade du scenario c'est que tu es deja champion de l'arène PC ! Mais comme tu as pu le comprendre, l'aventure ne s'arrete pas ici. Si tu veux devenir le dresseur le plus puissant de la Doua, il faut continuer ton chemin en direction des humas ! Tu y trouveras des dresseurs encore plus fort, et… une etrange statue. \n\nNous arrivons donc sur les humas1, une grande prairie luxuriante. Une etendue qui donne comme une envie folle de faire la fete toute la nuit. Mais avant cela, 4 dresseurs s'opposent a nous ! \nIl faudra les vaincre pour acceder a la map suivante : les humas2. De nouveau, 4 dresseurs sont presents pour se battre, une fois vaincus, nous pourrons enfin aller dans la derniere map du jeu. \n\nNous y voila, nous sommes a la Tete d'Or, la plus belle map du jeu, l'endroit parfait pour se balader et prendre du bon temps, mais surtout pour devenir le plus grand dresseur vegamon de la Doua ! En effet, c'est ici que se trouve la derniere arene du jeu, avec les dresseurs et les vegamons les plus puissants. Avant de nous y rendre, comme d'habitude, nous pouvons nous balader dans les differentes turnes pour obtenir quelques informations ou juste pour se faire de nouveaux amis. \nBonne chance pour ces derniers combats qui, je l'espere, te donneront du fil a retordre !"); 
		deplace = new JTextArea ("Pour se deplacer dans la map rien de plus simple ! \nIl suffit de cliquer avec ta souris l'endroit auquel tu souhaites te rendre et ton personnage trouvera le chemin par lui meme ! \n Tu peux cliquer sur des portes ou sur des fleches pour changer de map. Mais si tu cliques sur un arbre, un lac, ou sur un batiment (ailleurs que sur la porte) ton personnage ne pourra pas s'y rendre et ne bougera pas d'un pouce !\nTu peux aussi cliquer sur un personnage pour entre en interaction avec lui, pour discuter ou te battre ! \nAmuse-toi bien !"); 
			
		
        JFramePrincipal.setVisible(true);
		
	}
		public void actionPerformed (ActionEvent e){

		if (e.getSource()==adep){
			pPrincipal.removeAll();
			pPrincipal.add(retourScenario);
			pPrincipal.add(imageFond);
			
			deplace.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			deplace.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			deplace.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			deplace.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrolldeplace = new JScrollPane (deplace); 
			scrolldeplace.setBounds(40,40,500,350);
			pPrincipal.add(scrolldeplace);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
		}
		else if (e.getSource()==aintro){
			pPrincipal.removeAll();
			pPrincipal.add(retourScenario);
			pPrincipal.add(imageFond);
			
			intro.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			intro.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			intro.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			intro.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollintro = new JScrollPane (intro); 
			scrollintro.setBounds(40,40,500,350);
			pPrincipal.add(scrollintro);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
		}
		else if (e.getSource()==aarene1){
			pPrincipal.removeAll();
			pPrincipal.add(retourScenario);
			pPrincipal.add(imageFond);
			
			arene1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			arene1.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			arene1.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			arene1.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollarene1 = new JScrollPane (arene1); 
			scrollarene1.setBounds(40,40,500,350);
			pPrincipal.add(scrollarene1);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
		}
		else if (e.getSource()==aarene2){
			pPrincipal.removeAll();
			pPrincipal.add(retourScenario);
			pPrincipal.add(imageFond);
		
			arene2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			arene2.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			arene2.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			arene2.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollarene2 = new JScrollPane (arene2); 
			scrollarene2.setBounds(40,40,500,350);
			pPrincipal.add(scrollarene2);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
		}
		
		else if (e.getSource()==retouraccueil){
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
			Accueil ecranAccueil= new Accueil(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu);
        }
        else if (e.getSource()==retourScenario){
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
			FenetreScenario pokedex = new FenetreScenario(JFramePrincipal,VariablesSession,SauvegardeJeu, MusiqueDeJeu);
        }
		
	   
	}
}
