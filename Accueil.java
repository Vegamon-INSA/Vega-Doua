import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Accueil implements ActionListener{
	private JButton jouer,resetPartie,aide,parametre,pokedex;
	private JLabel wow;
	private JMenu menu,menu1,menu2,menu3,menu4;
	private JMenuBar menuBar= new JMenuBar();
	private CJframe JFramePrincipal;
	private VariablesDeJeu VariablesSession;
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu;
	public FenetrePara Para;
	public FenetreAide Aide;
	public FenetrePokedex pkx;
    private ArrayList<VEGAMONS> dex;
	
	public Accueil(CJframe Frame, VariablesDeJeu variables,Sauvegarde sauvegarde, Musiques musique){	
		JFramePrincipal=Frame;
		SauvegardeJeu=sauvegarde;
		VariablesSession=variables;
		MusiqueDeJeu=musique;
		dex=VariablesSession.pokedex;
		
		jouer = new JButton ("Jouer");
		jouer.setBounds(300,200,200,100);
		jouer.setBackground(Color.red);
		jouer.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(jouer);
		jouer.addActionListener(this);
		
		aide= new JButton ("Aide");
		aide.setBounds(300, 490, 200, 70);
		aide.setBackground(Color.blue);
		aide.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(aide);
		aide.addActionListener(this);
		
		resetPartie= new JButton ("Reset la partie");
		resetPartie.setBounds(300,330,200,70);
		resetPartie.setBackground(Color.blue);
		resetPartie.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(resetPartie);
		resetPartie.addActionListener(this);
		
		parametre= new JButton ("Parametres");
		parametre.setBounds(300,570,200,70);
		parametre.setBackground(Color.blue);
		parametre.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(parametre);
		parametre.addActionListener(this);
		
		pokedex = new JButton ("Pokedex");
		pokedex.setBounds(300,410,200,70);
		pokedex.setBackground(Color.blue);
		pokedex.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(pokedex);
		pokedex.addActionListener(this);

		wow = new JLabel(new ImageIcon("Images/wow.jpg")); 
		wow.setBounds(177,60,446,588);
		JFramePrincipal.add(wow);
		JFramePrincipal.setVisible(true);

	}
	
	public static void main(String[] args){

		CJframe JFramePrincipal = new CJframe();
		VariablesDeJeu VariablesSession= new VariablesDeJeu();	
		Sauvegarde SauvegardeJeu= new Sauvegarde();
		Musiques MusiqueDeJeu= new Musiques();
		MusiqueDeJeu.JouerMusiqueJouerEnBoucle("Musiques/route1.wav");
		Accueil ecranAccueil= new Accueil(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu);
   }
		public void actionPerformed (ActionEvent e){

		if (e.getSource()==jouer){
			JFramePrincipal.remove(jouer);
			JFramePrincipal.remove(aide);
			JFramePrincipal.remove(resetPartie);
			JFramePrincipal.remove(pokedex);
			JFramePrincipal.remove(parametre);
			JFramePrincipal.remove(wow);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();
			
			/*menuBar = new JMenuBar();
			menu = new JMenu("Nouvelle partie");
			menu.add(jouer);
			menuBar.add(menu);	
			menu2 = new JMenu("Pokedex");
			menu2.add(pokedex);
			menuBar.add(menu2);
			menu3 = new JMenu("Aide");
			menu3.add(aide);
			menuBar.add(menu3);
			menu4 = new JMenu("Parametres");
			menu4.add(parametre);
			menuBar.add(menu4);		
            JFramePrincipal.setJMenuBar(menuBar);*/	

			if (SauvegardeJeu.SauvegardeExiste()){
				VariablesSession=SauvegardeJeu.RestaurerSauvegarde();
			}
			else {
				VariablesSession.NouvelleCarte(000);
				SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			}
			fenetreCarte Map0= new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu, MusiqueDeJeu);
			MusiqueDeJeu.StopMusique();

		}
		if (e.getSource()==resetPartie){
			JFramePrincipal.remove(jouer);
			JFramePrincipal.remove(aide);
			JFramePrincipal.remove(resetPartie);
			JFramePrincipal.remove(parametre);
			JFramePrincipal.remove(pokedex);
			JFramePrincipal.remove(wow);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();
			VariablesSession.NouvelleCarte(000);
			SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			fenetreCarte Map0= new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu, MusiqueDeJeu);
			MusiqueDeJeu.StopMusique();

		}
		if (e.getSource()==aide){
			Aide = new FenetreAide();
			Aide.setVisible(true);
		}
		if (e.getSource()==parametre){
			Para=new FenetrePara();
			Para.setVisible(true);
			
		}
		if (e.getSource()==pokedex){
			pkx = new FenetrePokedex(dex);
			pkx.setVisible(true);
		}
	}
}
//lier toutes les cartes entre elles (Gianni) (c'est presque bon il manque juste à agrandir les zones d'entrée et de sortie)
//lier les persos dans les cartes avec leur combat (Gianni & Victor) (Il faut faire une nouvelle classe je pense)
//gérer l'xp des personnages etc(Estelle & Adrien) (vous en êtes où les zamis ?)
//musiques et effets speciaux : Définir quels effets speciaux quelles musiques et quelle naration on veut jouer et à quelle moment
//menu (sessions différentes, menu bande en haut)(Victor)
//test
