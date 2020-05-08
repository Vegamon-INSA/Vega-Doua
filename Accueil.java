import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Accueil implements ActionListener{
	private JButton jouer,resetPartie,scenario,pokedex, desacSon;
	private JLabel imageFond;
	private JPanel pPrincipal;
	private CJframe jFramePrincipal;
	private VariablesDeJeu variablesSession;
	private Sauvegarde sauvegardeJeu;
	private Musiques musiqueDeJeu;

	public Accueil(CJframe frame, VariablesDeJeu variables, Sauvegarde sauvegarde) {
		jFramePrincipal = frame;
		sauvegardeJeu = sauvegarde;
		variablesSession = variables;

		//Musique de fond
		try {
			musiqueDeJeu = new Musiques("Musiques/route1.wav");
			if (variablesSession.sondesac==0){
                musiqueDeJeu.pause();
            }

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e10) {
			System.out.println("musique intouvable");
			e10.printStackTrace();
		}

		
		//Panel principal
		pPrincipal = new JPanel();
		pPrincipal.setBounds(0, 0, 815, 845);
		pPrincipal.setLayout(null);
		jFramePrincipal.add(pPrincipal);

		//Boutton jouer
		jouer = new JButton("Jouer");
		jouer.setBounds(300, 240, 200, 100);
		jouer.setBackground(new Color(139,180,166));
		jouer.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(jouer);
		jouer.addActionListener(this);

		//Bouton reset
		resetPartie = new JButton("Reset la partie");
		resetPartie.setBounds(300, 370, 200, 70);
		resetPartie.setBackground(new Color(255,255,224));
		resetPartie.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(resetPartie);
		resetPartie.addActionListener(this);

		//Bouton vegadex
		pokedex = new JButton("Vegadex");
		pokedex.setBounds(300, 450, 200, 70);
		pokedex.setBackground(new Color(255,255,224));
		pokedex.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(pokedex);
		pokedex.addActionListener(this);

		//Bouton Scenario
		scenario = new JButton("Sc\u00e9nario");
		scenario.setBounds(300, 530, 200, 70);
		scenario.setBackground(new Color(255,255,224));
		scenario.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(scenario);
		scenario.addActionListener(this);
		
		//Bouton ON/OFF Musique
		desacSon = new JButton();
		if (variablesSession.sondesac==0){
			desacSon.setText("Musique ON");
		}
		else if (variablesSession.sondesac==1){
			desacSon.setText("Musique OFF");
		}
		desacSon.setBounds(300, 610, 200, 70);
		desacSon.setBackground(new Color(255,255,224));
		desacSon.setFont(new java.awt.Font(Font.SERIF, Font.BOLD, 25));
		pPrincipal.add(desacSon);
		desacSon.addActionListener(this);

		//Image fond
		imageFond = new JLabel(new ImageIcon("Images/fond.png"));
		imageFond.setBounds(0, 0, 800, 820);
		pPrincipal.add(imageFond);
		jFramePrincipal.setVisible(true);

	}

	public static void main(String[] args) {
		//Initialisation jeu
		CJframe jFramePrincipal = new CJframe();
		VariablesDeJeu variablesSession = new VariablesDeJeu();
		Sauvegarde sauvegardeJeu = new Sauvegarde();
		if (sauvegardeJeu.sauvegardeExiste()) {
			variablesSession = sauvegardeJeu.restaurerSauvegarde();
		} else {
			variablesSession.nouvelleCarte(000);
			variablesSession.sondesac=1;
			sauvegardeJeu.nouvelleSauvegarde(variablesSession);
		}
		new Accueil(jFramePrincipal, variablesSession, sauvegardeJeu);
	}

	public void actionPerformed(ActionEvent e) {
		//mode jeu
		if (e.getSource() == jouer) {
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
			try {
				musiqueDeJeu.stop();	
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				System.out.println("musique intouvable");
				e1.printStackTrace();
			}			new fenetreCarte(jFramePrincipal, variablesSession, sauvegardeJeu);

		}//reset la partie
		if (e.getSource() == resetPartie) {
			try {
				musiqueDeJeu.stop();	
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
				System.out.println("musique intouvable");
				e2.printStackTrace();
			}			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.validate();
			jFramePrincipal.repaint();
			int sonDesactive=variablesSession.sondesac;
			variablesSession = new VariablesDeJeu();
			variablesSession.nouvelleCarte(000);
			variablesSession.sondesac=sonDesactive;
			sauvegardeJeu.nouvelleSauvegarde(variablesSession);
			new fenetreCarte(jFramePrincipal, variablesSession, sauvegardeJeu);

		}//lecture du scenario
		if (e.getSource()==scenario){
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.validate();
			jFramePrincipal.repaint();
			new FenetreScenario(jFramePrincipal, variablesSession, sauvegardeJeu);
		}
		//Lecture du vegadex
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
			}
			new FenetrePokedex(jFramePrincipal, variablesSession, sauvegardeJeu);
		}
		//activation ou desactivation de la musique
		if (e.getSource()==desacSon){
			if (variablesSession.sondesac==0){
				desacSon.setText("Musique OFF");
				variablesSession.sondesac=1;
				try {
					musiqueDeJeu.resumeAudio();					
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException h) {
					System.out.println("musique intouvable");
					h.printStackTrace();
				}
			}
			else if (variablesSession.sondesac==1){
				desacSon.setText("Musique ON");
				musiqueDeJeu.pause();			
				variablesSession.sondesac=0;
			}
			sauvegardeJeu.nouvelleSauvegarde(variablesSession);
		}
	}
}
//bien checker toutes les zones avec les tableaux de gianni
