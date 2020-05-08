import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class FenetreScenario implements ActionListener{
	
	private JButton bCommentJouer, bIntro, bArene1, bArene2, bRetourAccueil, bRetourScenario;
	private JPanel pPrincipal;
    private JLabel imageFond ;
	private CJframe jFramePrincipal; 
	private VariablesDeJeu variablesSession; 
	private Sauvegarde sauvegardeJeu;
	private JTextArea intro, arene1, arene2, deplace ;
	private JScrollPane scrollIntro,scrollArene1,scrollArene2, scrollDeplace;
	
	public FenetreScenario( CJframe frame,VariablesDeJeu variables, Sauvegarde sauvegarde) {
		
		jFramePrincipal= frame;
		variablesSession=variables;
		sauvegardeJeu=sauvegarde;
        
        pPrincipal = new JPanel();
        pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
        jFramePrincipal.add(pPrincipal);
		
        bCommentJouer = new JButton("Comment jouer");
        bCommentJouer.setBounds(150, 290, 200, 90);
        pPrincipal.add(bCommentJouer);
        bCommentJouer.setLayout(null);
        bCommentJouer.addActionListener(this);

        bIntro = new JButton("Introduction");
        bIntro.setBounds(450, 290, 200, 90);
        pPrincipal.add(bIntro);
        bIntro.setLayout(null);
        bIntro.addActionListener(this);	
        
        bArene1 = new JButton("Ar\u00e8ne 1");
        bArene1.setBounds(150, 420, 200, 90);
        pPrincipal.add(bArene1);
        bArene1.setLayout(null);
        bArene1.addActionListener(this);
        	
        bArene2 = new JButton("Ar\u00e8ne 2");
        bArene2.setBounds(450, 420, 200, 90);
        pPrincipal.add(bArene2);
        bArene2.setLayout(null);
        bArene2.addActionListener(this);
		
		bRetourAccueil = new JButton("RETOUR");
        bRetourAccueil.setBounds(325, 600, 150, 70);
        pPrincipal.add(bRetourAccueil);
        bRetourAccueil.setLayout(null);
        bRetourAccueil.addActionListener(this);
        
        bRetourScenario = new JButton("RETOUR");
        bRetourScenario.setBounds(325, 660, 150, 70);
        bRetourScenario.setLayout(null);
        bRetourScenario.addActionListener(this);

        imageFond = new JLabel(new ImageIcon("Images/fond.png")); 
		imageFond.setBounds(0,0,800,820);
		pPrincipal.add(imageFond);
		jFramePrincipal.setVisible(true);
		
		intro = new JTextArea ("Apr\u00e8s une paisible nuit, nous nous r\u00e9veillons dans notre jolie turne, c'est ici que commence notre incroyable aventure V\u00e9gamon.\nA notre r\u00e9veil, notre maman nous donne quelques consignes et nous sugg\u00e8re d'aller voir le professeur Vega dans la turne voisine. En sortant, le merveilleux parc de la Feyssine s'offre \u00e0 nous, l'air est frais, l'herbe est douce, le temps est parfait pour recevoir notre premier compagnon de voyage ! En effet, le professeur Vega, grand chercheur V\u00e9gamon nous propose de devenir dresseur V\u00e9gamon. Il voit en nous un \u00e9norme potentiel et nous d\u00e9finit comme LA future star de la ville. C'est pourquoi il nous fait don de notre premier V\u00e9gamon : M\u00e9loche. \nCe dernier est au niveau 1 et sera notre compagnon pour toute l'aventure, il faudra en prendre soin. Un autre habitant de la turne nous apprend qu'il y \u00e0 une ar\u00e8ne au nord de la ville derri\u00e8re les trav\u00e9es et que le meilleur dresseur s'y trouve. Il nous conseille de nous y rendre pour, pourquoi pas, devenir le nouveau champion de la ville. Mais attention, notre M\u00e9loche devra gagner beaucoup d'exp\u00e9riences si nous voulons entrer dans cette ar\u00e8ne. \nD'ailleurs juste \u00e0 c\u00f4t\u00e9 de la turne, Flora, une amie du professeur, nous donne quelques conseils pour bien comprendre comment faire progresser notre M\u00e9loche. Nous sommes d\u00e9sormais pr\u00eat pour l'aventure ! \nEn continuant notre route, nous pourrons faire la rencontre de R\u00e9mi, jeune dresseur lui aussi, qui nous avertit du danger que l'on encoure en poursuivant notre route. Brave, nous continuons sans h\u00e9siter et nous nous dirigeons vers les trav\u00e9es."); 
		arene1 = new JTextArea ("Nous voil\u00e0 dans les trav\u00e9es, la premi\u00e8re map un peu dangereuse de l'aventure. Plusieurs dresseurs nous attendent avec la ferme intention d'en d\u00e9coudre. Pour tous les vaincre il peut etre utile d'entra\u00eener notre M\u00e9loche dans les tr\u00e8fles et lui faire gagner quelques points d exp\u00e9riences. \nBis repetita pour la map suivante : les trav\u00e9es 2, de plus f\u00e9roces dresseurs encore nous font face. Une fois tous ces V\u00e9gamons vaincus, nous nous retrouvons dans une agr\u00e9able petite ville appel\u00e9e PC (ou FIMI pour les plus t\u00e9m\u00e9raires). C'est dans cette ville que se trouve la fameuse ar\u00e8ne, la premi\u00e8re de notre aventure avec \u00e0 sa t\u00eate Veronique, dresseuse de longue date, dont la r\u00e9putation n'est plus \u00e0 faire. Avant de l'affronter nous pouvons nous balader dans les autres turnes et discuter avec ses habitants. Nous pourrons d\u00e9couvrir de chaleureux \u00e9tudiants trinquant \u00e0 leurs \u00e9checs du semestre, ou d'autres personnages un peu plus utiles lesquels nous donneront des conseils pour nous rendre dans l'ar\u00e8ne et vaincre V\u00e9ronique. \nD'ailleurs il est grand temps de s'y atteler ! En entrant dans l'ar\u00e8ne, M.S\u00e9curitas nous accueille, il nous avertit de la puissance des V\u00e9gamon que nous allons affronter et nous d\u00e9fend d'entrer si notre M\u00e9loche n'est pas suffisamment puissant. Bonne chance, il faudra vaincre les 4 dresseurs qui se trouvent sur le chemin avant, enfin, de pouvoir se mesurer \u00e0 V\u00e9ronique.");
		arene2 = new JTextArea ("Bravo champion, si tu es arriv\u00e9 \u00e0 ce stade du sc\u00e9nario c'est que tu es d\u00e9j\u00e0 champion de l'ar\u00e8ne PC ! Mais comme tu as pu le comprendre, l'aventure ne s'arr\u00eate pas ici. Si tu veux devenir le dresseur le plus puissant de la Doua, il faut continuer ton chemin en direction des humas ! Tu y trouveras des dresseurs encore plus fort, et aussi une \u00e9trange statue. \n\nNous arrivons donc sur les humas1, une grande prairie luxuriante. Une \u00e9tendue qui donne comme une envie folle de faire la f\u00eate toute la nuit. Mais avant cela, 4 dresseurs s'opposent \u00e0 nous ! \nIl faudra les vaincre pour acc\u00e9der \u00e0 la map suivante : les humas2. De nouveau, 4 dresseurs sont pr\u00e9sents pour se battre, une fois vaincus, nous pouvons enfin aller dans la derni\u00e8re map du jeu. \n\nNous nous retrouvons alors \u00e0 la T\u00eate d'Or, la plus belle map du jeu, l'endroit parfait pour se balader et prendre du bon temps, mais surtout pour devenir le plus grand dresseur V\u00e9gamon de la Doua ! En effet, c'est ici que se trouve la derni\u00e8re ar\u00e8ne du jeu, avec les dresseurs et les V\u00e9gamons les plus puissants. Avant de nous y rendre, comme d'habitude, nous pouvons nous balader dans les diff\u00e9rentes turnes pour obtenir quelques informations ou juste pour se faire de nouveaux amis. \nBonne chance pour ces derniers combats qui, je l'esp\u00e8re, te donneront du fil \u00e0 retordre !"); 
		deplace = new JTextArea ("Voici quelques conseils pour t'aider \u00e0 bien d\u00e9buter dans le jeu. \nLa premi\u00e8re chose que tu dois savoir est que le jeu est automatiquement sauvegard\u00e9, tu peux arr\u00eater le jeu quand tu veux, revenir quand tu veux, tous tes progr\u00e8s sont pr\u00e9cieusement enregistr\u00e9s. \nEnsuite, pour te d\u00e9placer dans les diff\u00e9rentes maps, rien de plus simple ! Il te suffit de cliquer avec ta souris \u00e0 l'endroit auquel tu souhaites te rendre, ton personnage trouvera le chemin par lui meme ! \nTu peux cliquer sur des portes ou sur des fleches pour changer de map, mais si tu cliques sur un arbre, un lac, ou sur un b\u00e2timent (ailleurs que sur la porte) ton personnage ne pourra pas s'y rendre et ne bougera pas d'un pouce !\nTu peux aussi cliquer sur un personnage pour entrer en interaction avec lui, pour discuter, lorsque tu es dans une ville, ou pour te battre lorsque tu es sur une route (les trav\u00e9es ou les humas) ou dans une ar\u00e8ne ! Une fois un dresseur vaincu, tu ne peux plus le combattre, mais tu peux toujours discuter avec lui ! Pour faire d\u00e9filer le texte, il te suffit d'appuyer sur la touche entr\u00e9e. \nDerni\u00e8re chose importante \u00e0 savoir, si tu perds un combat, tu seras directement redirig\u00e9 vers ta turne, et tu devras refaire tout le chemin  ! Alors fais attention et surtout amuse-toi bien !"); 
		
		
        jFramePrincipal.setVisible(true);
		
	}
		public void actionPerformed (ActionEvent e){

		if (e.getSource()==bCommentJouer){
			pPrincipal.removeAll();
			pPrincipal.add(bRetourScenario);
			pPrincipal.add(imageFond);
			
			deplace.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			deplace.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			deplace.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			deplace.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollDeplace = new JScrollPane (deplace); 
			scrollDeplace.setBounds(150,215,500,400);

			scrollDeplace.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			
			pPrincipal.add(scrollDeplace);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
		}
		else if (e.getSource()==bIntro){
			pPrincipal.removeAll();
			pPrincipal.add(bRetourScenario);
			pPrincipal.add(imageFond);
			
			intro.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			intro.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			intro.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			intro.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollIntro = new JScrollPane (intro); 
			scrollIntro.setBounds(150,215,500,400);
			scrollIntro.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			pPrincipal.add(scrollIntro);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
		}
		else if (e.getSource()==bArene1){
			pPrincipal.removeAll();
			pPrincipal.add(bRetourScenario);
			pPrincipal.add(imageFond);
			
			arene1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			arene1.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			arene1.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			arene1.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollArene1 = new JScrollPane (arene1); 
			scrollArene1.setBounds(150,215,500,400);
			scrollArene1.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			pPrincipal.add(scrollArene1);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
		}
		else if (e.getSource()==bArene2){
			pPrincipal.removeAll();
			pPrincipal.add(bRetourScenario);
			pPrincipal.add(imageFond);
		
			arene2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,18));
			arene2.setLineWrap (true); //Permet d'aller à la ligne en fin de fenêtre
			arene2.setWrapStyleWord (true); //Permet de ne pas couper les mots en fin de fenêtre
			arene2.setEditable (false); //empêche l'utilisateur de modifier le texte
			scrollArene2 = new JScrollPane (arene2); 
			scrollArene2.setBounds(150,215,500,400);
			scrollArene2.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			pPrincipal.add(scrollArene2);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
		}
		
		else if (e.getSource()==bRetourAccueil){
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
			new Accueil(jFramePrincipal, variablesSession, sauvegardeJeu);
        }
        else if (e.getSource()==bRetourScenario){
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
			new FenetreScenario(jFramePrincipal, variablesSession, sauvegardeJeu);
        }	
	}
}
