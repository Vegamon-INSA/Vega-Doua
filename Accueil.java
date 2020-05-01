import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil implements ActionListener{
	private JButton jouer,resetPartie,scenario,pokedex;
	private JLabel imageFond;
	private JPanel pPrincipal;
	private CJframe jFramePrincipal;
	private VariablesDeJeu variablesSession;
	private Sauvegarde sauvegardeJeu;
	private Musiques musiqueDeJeu  = new Musiques();
	
	public Accueil(CJframe frame, VariablesDeJeu variables, Sauvegarde sauvegarde) {
		jFramePrincipal = frame;
		sauvegardeJeu = sauvegarde;
		variablesSession = variables;

		musiqueDeJeu.jouerMusiqueJouerEnBoucle("Musiques/route1.wav");

		pPrincipal = new JPanel();
		pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
		jFramePrincipal.add(pPrincipal);

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
		jFramePrincipal.setVisible(true);

	}

	public static void main(String[] args) {

		CJframe jFramePrincipal = new CJframe();
		VariablesDeJeu variablesSession = new VariablesDeJeu();
		Sauvegarde sauvegardeJeu = new Sauvegarde();
		new Accueil(jFramePrincipal, variablesSession, sauvegardeJeu);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jouer) {
			musiqueDeJeu.stopMusique();
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.validate();
			jFramePrincipal.repaint();

			if (sauvegardeJeu.sauvegardeExiste()) {
				variablesSession = sauvegardeJeu.restaurerSauvegarde();
			} else {
				variablesSession.nouvelleCarte(000);
				sauvegardeJeu.nouvelleSauvegarde(variablesSession);
			}
			new fenetreCarte(jFramePrincipal, variablesSession, sauvegardeJeu);

		}
		if (e.getSource() == resetPartie) {
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.validate();
			jFramePrincipal.repaint();
			variablesSession = new VariablesDeJeu();
			variablesSession.nouvelleCarte(000);
			sauvegardeJeu.nouvelleSauvegarde(variablesSession);
			new fenetreCarte(jFramePrincipal, variablesSession, sauvegardeJeu);
			musiqueDeJeu.stopMusique();

		}
		if (e.getSource()==scenario){
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.validate();
			jFramePrincipal.repaint();
			new FenetreScenario(jFramePrincipal, variablesSession, sauvegardeJeu);
		}
		
		if (e.getSource()==pokedex){
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.validate();
			jFramePrincipal.repaint();
			if (sauvegardeJeu.sauvegardeExiste()){
				variablesSession=sauvegardeJeu.restaurerSauvegarde();
			}
			else {
				variablesSession.nouvelleCarte(000);
				sauvegardeJeu.nouvelleSauvegarde(variablesSession);
			}
			new FenetrePokedex(jFramePrincipal, variablesSession, sauvegardeJeu);
		}
	}
}
//bien checker toutes les zones avec les tableaux de gianni
