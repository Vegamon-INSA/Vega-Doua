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
	public FenetreAide Aide;
    private ArrayList<VEGAMONS> dex;
	//public FenetrePokedex pokedex;

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
		
		resetPartie= new JButton ("Reset la partie");
		resetPartie.setBounds(300,330,200,70);
		resetPartie.setBackground(Color.blue);
		resetPartie.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(resetPartie);
		resetPartie.addActionListener(this);
		
		pokedex = new JButton ("Vegadex");
		pokedex.setBounds(300,410,200,70);
		pokedex.setBackground(Color.blue);
		pokedex.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(pokedex);
		pokedex.addActionListener(this);

		aide= new JButton ("Scenario");
		aide.setBounds(300, 490, 200, 70);
		aide.setBackground(Color.blue);
		aide.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		JFramePrincipal.add(aide);
		aide.addActionListener(this);

		wow = new JLabel(new ImageIcon("Images/fond.png")); 
		wow.setBounds(0,0,800,820);
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
			JFramePrincipal.remove(wow);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();

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
			VariablesSession.NouvelleCarte(100);
			SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			fenetreCarte Map0= new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu, MusiqueDeJeu);
			MusiqueDeJeu.StopMusique();

		}
		if (e.getSource()==aide){
			Aide = new FenetreAide();
			Aide.setVisible(true);
		}
		
		if (e.getSource()==pokedex){
			if (SauvegardeJeu.SauvegardeExiste()){
				VariablesSession=SauvegardeJeu.RestaurerSauvegarde();
			}
			else {
				VariablesSession.NouvelleCarte(000);
				SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			}
			FenetrePokedex pokedex = new FenetrePokedex(VariablesSession.pokedex);
			pokedex.setVisible(true);
		}
	}
}
//musiques et effets speciaux : Définir quels effets speciaux quelles musiques et quelle naration on veut jouer et à quel moment
//bug des boutons fenetre combat
//bien checker toutes les zones avec les tableaux de gianni
//résoudre pb au lancement
