import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil implements ActionListener{
	private JButton jouer,resetPartie,scenario,pokedex;
	private JLabel imageFond;
	private JPanel pPrincipal;
	private CJframe JFramePrincipal;
	private VariablesDeJeu VariablesSession;
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu  = new Musiques();
	
	public Accueil(CJframe Frame, VariablesDeJeu variables, Sauvegarde sauvegarde) {
		JFramePrincipal = Frame;
		SauvegardeJeu = sauvegarde;
		VariablesSession = variables;

		MusiqueDeJeu.JouerMusiqueJouerEnBoucle("Musiques/route1.wav");

		pPrincipal = new JPanel();
		pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
		JFramePrincipal.add(pPrincipal);

		jouer = new JButton("Jouer");
		jouer.setBounds(300, 200, 200, 100);
		jouer.setBackground(Color.red);
		jouer.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(jouer);
		jouer.addActionListener(this);

		resetPartie = new JButton("Reset la partie");
		resetPartie.setBounds(300, 330, 200, 70);
		resetPartie.setBackground(Color.blue);
		resetPartie.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(resetPartie);
		resetPartie.addActionListener(this);

		pokedex = new JButton("Vegadex");
		pokedex.setBounds(300, 410, 200, 70);
		pokedex.setBackground(Color.blue);
		pokedex.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(pokedex);
		pokedex.addActionListener(this);

		scenario = new JButton("Sc\u00e9nario");
		scenario.setBounds(300, 490, 200, 70);
		scenario.setBackground(Color.blue);
		scenario.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(scenario);
		scenario.addActionListener(this);

		imageFond = new JLabel(new ImageIcon("Images/fond.png"));
		imageFond.setBounds(0, 0, 800, 820);
		pPrincipal.add(imageFond);
		JFramePrincipal.setVisible(true);

	}

	public static void main(String[] args) {

		CJframe JFramePrincipal = new CJframe();
		VariablesDeJeu VariablesSession = new VariablesDeJeu();
		Sauvegarde SauvegardeJeu = new Sauvegarde();
		new Accueil(JFramePrincipal, VariablesSession, SauvegardeJeu);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jouer) {
			MusiqueDeJeu.StopMusique();
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();

			if (SauvegardeJeu.SauvegardeExiste()) {
				VariablesSession = SauvegardeJeu.RestaurerSauvegarde();
			} else {
				VariablesSession.NouvelleCarte(000);
				SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			}
			new fenetreCarte(JFramePrincipal, VariablesSession, SauvegardeJeu);

		}
		if (e.getSource() == resetPartie) {
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();
			VariablesSession = new VariablesDeJeu();
			VariablesSession.NouvelleCarte(000);
			SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			new fenetreCarte(JFramePrincipal, VariablesSession, SauvegardeJeu);
			MusiqueDeJeu.StopMusique();

		}
		if (e.getSource()==scenario){
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();
			new FenetreScenario(JFramePrincipal, VariablesSession, SauvegardeJeu);
		}
		
		if (e.getSource()==pokedex){
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.validate();
			JFramePrincipal.repaint();
			if (SauvegardeJeu.SauvegardeExiste()){
				VariablesSession=SauvegardeJeu.RestaurerSauvegarde();
			}
			else {
				VariablesSession.NouvelleCarte(000);
				SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			}
			new FenetrePokedex(JFramePrincipal, VariablesSession, SauvegardeJeu);
		}
	}
}
//musiques et effets speciaux : Définir quels effets speciaux quelles musiques et quelle naration on veut jouer et à quel moment
//bien checker toutes les zones avec les tableaux de gianni
