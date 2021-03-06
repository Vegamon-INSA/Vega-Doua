import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class FenetreCarte implements ActionListener, MouseListener, KeyListener {
	private JPanel pPrincipal, pBoiteTexte, pNomCarte;
	private JLabel JLabelPersonnage, JLabelCarte, lBoiteTexte, gif ,lNomCarte; 
	private CJframe jFramePrincipal; //Jframe principal avec image de fond
	private VariablesDeJeu variablesSession; //Variables de Jeu
	private Sauvegarde sauvegardeJeu;
	private Musiques musiqueDeJeu;
	private int[][] tableauCarte = new int[25][25];//tableau contenenant la consitution de la carte dans laquelle est le personnage
	private int[][] tableauChemin = new int[50][2];//Tableau généré par l'algorithme avec les coordonnées x dans la colonne 0 et y dans la colonne 1 du chemin pour aller d'un point à un autre
	private int[][] tableauCheminTrie = new int[50][2];// Tableau issu du tableau tableauChemin mais qui est cet fois-ci trié dans l'ordre de l'avancement du chemin
	private Timer t1, t2;//Timer de déplacement de 'limage du personnage
	private int a=0;//variable a pour le timer du déplacement du joueur
	private boolean descente=true;
	private int ordonneePanel=-50;//variable a pour le timer du déplacement du joueur
	private int nbreCases = 25;//nombre de cases dans la grille qui découpe la carte
	private boolean stopDeplacement=false;
	private boolean affichertexte;
	private int numeroLigneTexte;
	private int numeroDialogue;

	//coordonnées de départ et d'arrivée
	private int xDepart;
	private int yDepart;
	private int xArrivee;//Change à chaque déplacement de une case
	private int yArrivee;
	private int xArriveeFinal, xDepartInitial;//Change quand le déplacement global est terminé
	private int yArriveeFinal, yDepartInitial;
	private int nbreDeplacement;
	private int tailleCellule = 800/nbreCases;//Taille d'un carré : largeur de la fenetre divisé par le nombre de cellules
	
	//variables pour l'algorithme de recherche de chemin
	private Noeud [][] map = new Noeud[25][25];
	private boolean recherche = true;//Bloque ou pas l'algorithme de recherche de chemin
	private Algorithme algoDeplacement = new Algorithme();
	
	public FenetreCarte(CJframe Frame, VariablesDeJeu variables, Sauvegarde sauvegarde) {
		jFramePrincipal = Frame;
		variablesSession = variables;
		sauvegardeJeu = sauvegarde;

		try {
			musiqueDeJeu = new Musiques(variablesSession.musique);
			if (variablesSession.sondesac==0){
                musiqueDeJeu.pause();
            }

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
		pPrincipal = new JPanel();// Jpanel principal qui couvre toutela surface de la fenetre
		pPrincipal.setBounds(0, 0, 815, 845);
		pPrincipal.setLayout(null);
		jFramePrincipal.add(pPrincipal);
		pPrincipal.addKeyListener(this);
		pPrincipal.requestFocusInWindow();

		// Boite de texte dans laquelle on affiche le nom de la carte

		pNomCarte = new JPanel();
		pNomCarte.setBounds(300, -50, 200, 50);
		pNomCarte.setLayout(null);
		pNomCarte.setBackground(Color.white);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		pNomCarte.setBorder(border);
		pNomCarte.setVisible(false);
		pPrincipal.add(pNomCarte);

		lNomCarte = new JLabel(variablesSession.nomCarte);
		lNomCarte.setBounds(0, 0, 200, 50);
		lNomCarte.setText(variablesSession.nomCarte);
		lNomCarte.setHorizontalAlignment(JLabel.CENTER);
		lNomCarte.setVerticalAlignment(JLabel.CENTER);
		pNomCarte.add(lNomCarte);

		// Boite de texte dans laquelle on affiche les dialogues entre notre personnage
		// et les dresseurs que notre perso rencontre sur la carte

		pBoiteTexte = new JPanel();
		pBoiteTexte.setBounds(100, 680, 600, 70);
		pBoiteTexte.setLayout(null);
		pBoiteTexte.setBackground(Color.white);
		Border border2 = BorderFactory.createLineBorder(Color.BLACK);
		pBoiteTexte.setBorder(border2);
		pPrincipal.add(pBoiteTexte);
		pBoiteTexte.setVisible(false);

		lBoiteTexte = new JLabel(variablesSession.nomCarte);
		lBoiteTexte.setBounds(20, 5, 500, 60);
		pBoiteTexte.add(lBoiteTexte);

		gif = new JLabel(new ImageIcon("Images/FlecheEntrer.gif"));
		gif.setBounds(550, 30, 30, 30);
		pBoiteTexte.add(gif);

		// Algo de déplacement
		xDepart = variablesSession.xDepart;
		yDepart = variablesSession.yDepart;
		tableauCarte = variablesSession.tableauCarte;
		genererCarteDesMurs();

		// Ajout du fond de la fenetre
		JLabelCarte = new JLabel(new ImageIcon(variablesSession.imageFond));
		JLabelCarte.setBounds(0, 0, 800, 800);
		JLabelCarte.setLayout(null);
		JLabelCarte.addMouseListener(this);
		pPrincipal.add(JLabelCarte);

		// Ajout du Bonhomme à déplacer
		JLabelPersonnage = new JLabel(new ImageIcon("Images/Personnage.png"));
		JLabelPersonnage.setBounds((xDepart * tailleCellule - 8), (yDepart * tailleCellule - 28), 31, 52);
		JLabelPersonnage.setLayout(null);
		JLabelCarte.add(JLabelPersonnage);

		jFramePrincipal.revalidate();
		jFramePrincipal.repaint();
		xArriveeFinal = xDepart;
		yArriveeFinal = yDepart;
		xDepartInitial = xDepart;
		yDepartInitial = yDepart;

		actionDeLaCase(xDepart, yDepart, true);
	}

	public void affichageNomVille() {

		pNomCarte.setVisible(true);
		t2 = new Timer(1, this);
		t2.start();
		jFramePrincipal.revalidate();
		jFramePrincipal.repaint();

	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {// quand click sur l'écran
		int xTest=e.getX() / tailleCellule;
		int yTest=e.getY() / tailleCellule;
		if (possibiliteDeplacement(xTest,yTest)) {	
			resetMap();
			try {
				if (!affichertexte) {
					stopDeplacement = false;	
					xArrivee = xTest; // Récupération de l'abscisse et de l'ordonnée du point d'arrivé
					yArrivee = yTest;// en fonction de la taille de la grille
					xDepartInitial = xArriveeFinal;
					yDepartInitial = yArriveeFinal;
					xArriveeFinal = xArrivee;
					yArriveeFinal = yArrivee;
					deplacementJoueur(xArrivee, yArrivee);
				}
			} catch (Exception z) {
			} // catch les exceptions
		}
		else {
			try {
				musiqueDeJeu.jouerAlerte();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e7) {
				e7.printStackTrace();
			}
		}
	}

	public void resetMap() { // reset du tableau de recherche du chemin
		for (int x = 0; x < nbreCases; x++) {
			for (int y = 0; y < nbreCases; y++) {
				Noeud current = map[x][y];
				if (current.getType() == 4 || current.getType() == 5) // check si le Noeud est le cehmin fianl ou le
																		// début ou l'arrivée
					map[x][y] = new Noeud(3, x, y); // Si ce n'est pas le cas : on vide la case
			}
		}
		// reset les cases de début et de fin
		if (xDepart > -1 && yDepart > -1) {
			map[xDepart][yDepart] = new Noeud(0, xDepart, yDepart);
			map[xDepart][yDepart].setEsperance(0);
		}
		if (xArrivee > -1 && yArrivee > -1){
			map[xArrivee][yArrivee] = new Noeud(1, xArrivee, yArrivee);
		}

		resetVariablesAlgo();
		for (int s = 0; s < tableauChemin.length; s++) {
			tableauCheminTrie[s][0] = 0;
			tableauCheminTrie[s][1] = 0;

		}
	}

	public boolean possibiliteDeplacement(int x, int y) { // Test si la poistion finale demandée lors du click est
															// possible
		boolean possibilite = true;
		if (((tableauCarte[y][x] == 0) || (stopDeplacement == true)) || ((x == xDepart) && (y == yDepart))) {
			possibilite = false;
		}
		return possibilite;
	}

	public void deplacementJoueur(int xFinal, int yFinal) {
		genererCarteDesMurs();
		resetMap();
		demarrerRecherche();	
		int colonneY = 0;
		for (int x = 0; x < nbreCases; x++) {
			for (int y = 0; y < nbreCases; y++) {
				if (map[x][y].getType() == 5) {
					tableauChemin[colonneY][0] = map[x][y].getX();
					tableauChemin[colonneY][1] = map[x][y].getY();
					colonneY++;
				}
			}
		}
		triTableauChemin();
		t1 = new Timer(1, this);
		t1.start();
	}

	public void triTableauChemin() {
		boolean finDuTri = false;
		nbreDeplacement = 0;

		if ((tableauChemin[0][0] == 0) && (tableauChemin[0][1] == 0)) {
			tableauChemin[0][0] = xArrivee;
			tableauChemin[0][1] = yArrivee;
		}

		tableauCheminTrie[0][0] = xDepart;
		tableauCheminTrie[0][1] = yDepart;

		for (int j = 0; j < tableauChemin.length; j++) {
			if ((tableauChemin[j][0] == xDepart) && (tableauChemin[j][1] == yDepart)) {
				tableauChemin[j][0] = 0;
				tableauChemin[j][1] = 0;
			}
		}

		for (int d = 0; d < (tableauChemin.length - 1); d++) {
			if (finDuTri == true) {
				break;
			}
			int w = 0;
			while ((tableauCheminTrie[d + 1][0] == 0) || (tableauCheminTrie[d + 1][1] == 0)) {
				if ((tableauCheminTrie[d][0] == tableauChemin[w][0])
						&& ((tableauCheminTrie[d][1] == (tableauChemin[w][1] + 1))
								|| (tableauCheminTrie[d][1] == tableauChemin[w][1] - 1))) {
					tableauCheminTrie[d + 1][0] = tableauChemin[w][0];
					tableauCheminTrie[d + 1][1] = tableauChemin[w][1];
				} else if ((tableauCheminTrie[d][1] == tableauChemin[w][1])
						&& ((tableauCheminTrie[d][0] == (tableauChemin[w][0] + 1))
								|| (tableauCheminTrie[d][0] == tableauChemin[w][0] - 1))) {
					tableauCheminTrie[d + 1][0] = tableauChemin[w][0];
					tableauCheminTrie[d + 1][1] = tableauChemin[w][1];
				} else if (!(((tableauCheminTrie[d][1] != (tableauChemin[w][1] + 1))
						&& (tableauCheminTrie[d][1] != (tableauChemin[w][1] - 1)))
						|| ((tableauCheminTrie[d][0] != (tableauChemin[w][0] + 1))
								&& (tableauCheminTrie[d][0] != (tableauChemin[w][0] - 1))))) {
					tableauCheminTrie[d + 1][0] = tableauChemin[w][0];
					tableauCheminTrie[d + 1][1] = tableauChemin[w][1];
				}
				w++;
			}

			tableauChemin[w - 1][0] = 0;
			tableauChemin[w - 1][1] = 0;

			for (int s = 0; s < tableauChemin.length; s++) {
				if ((tableauChemin[s][0] == 0) && (tableauChemin[s][1] == 0)) {
					finDuTri = true;
				} else {
					finDuTri = false;
					break;
				}
			}
		}

		for (int s = 0; s < tableauChemin.length; s++) {
			if ((tableauCheminTrie[s][0] != 0) && (tableauCheminTrie[s][1] != 0)) {
				nbreDeplacement++;
			}
		}
		tableauCheminTrie[nbreDeplacement][0] = xArrivee;
		tableauCheminTrie[nbreDeplacement][1] = yArrivee;
		for (int h = 0; h < tableauCheminTrie.length; h++) {
			tableauCheminTrie[h][0] = tableauCheminTrie[h][0] * tailleCellule;
			tableauCheminTrie[h][1] = tableauCheminTrie[h][1] * tailleCellule;
		}
	}

	public void actionPerformed(ActionEvent u) {
		
		if (u.getSource() == t1) {// Timer délpacement personnage
			xDepart = tableauCheminTrie[a][0] / tailleCellule;
			yDepart = tableauCheminTrie[a][1] / tailleCellule;

			if ((tableauCarte[yDepart][xDepart] != 2) && (tableauCarte[yDepart][xDepart] != 3)) {
						JLabelPersonnage.setBounds((xDepart * tailleCellule - 8), (yDepart * tailleCellule - 28), 31, 52);
						if ((a!=0))
							actionDeLaCase(variablesSession.xDepart, variablesSession.yDepart, false);
				t1.stop();
				t1 = new Timer(200, this);
			} else if (stopDeplacement == false) {
				t1.stop();
				xDepart = tableauCheminTrie[a-1][0] / tailleCellule;
				yDepart = tableauCheminTrie[a-1][1] / tailleCellule;
				if ((a!=0) ) {
					actionDeLaCase(xDepart,yDepart, false);
				}
			} else {
				t1.stop();
			}

			if ((a < nbreDeplacement) && (stopDeplacement == false)) {
				a++;
				variablesSession.xDepart = xDepart;
				variablesSession.yDepart = yDepart;
				t1.start();
			}

		}

		if (u.getSource() == t2) {// Timer délpacement boite nom de la carte
			t2.stop();
			pNomCarte.setBounds(300, ordonneePanel, 200, 50);
			t2 = new Timer(3, this);
			if ((ordonneePanel < 50) && (descente == true)) {
				t2.start();
				ordonneePanel++;
			} else if ((ordonneePanel == 50) && (descente == true)) {
				descente = false;
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			if ((ordonneePanel > -50) && (descente == false)) {
				t2.start();
				ordonneePanel--;
			} else if ((ordonneePanel == -50) && (descente == false)) {
				pNomCarte.setVisible(false);
			}
		}
	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == 77) {// retour au menu
			try {
				if (variablesSession.sondesac==1){
					musiqueDeJeu.stop();	
				}					
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e3) {
				e3.printStackTrace();
			}		
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
			stopDeplacement = true;
			sauvegardeJeu.nouvelleSauvegarde(variablesSession);
			new Accueil(jFramePrincipal, variablesSession, sauvegardeJeu);
		}
		if (arg0.getKeyCode() == 10) {// texte suivant
			variablesSession.xDepart = xDepart;
			variablesSession.yDepart = yDepart;
			if (affichertexte) {
				numeroLigneTexte += 1;
			}
			if ((affichertexte) && (variablesSession.texteAAfficher[numeroLigneTexte] != "fin_message")) {
				affichageBoiteTexte();
			} else if (variablesSession.texteAAfficher[numeroLigneTexte] == "fin_message") {
				affichertexte = false;
				cacherBoiteTexte();
				if (((String.valueOf(tableauCarte[variablesSession.yDepart][variablesSession.xDepart]).charAt(0)) == '3') && ((variablesSession.listeInterractionsAvecDresseurs[numeroDialogue] == 0) || (variablesSession.listeInterractionsAvecDresseurs[numeroDialogue] == 1))) {
					jFramePrincipal.remove(pPrincipal);
					jFramePrincipal.revalidate();
					jFramePrincipal.repaint();
					try {
						if (variablesSession.sondesac==1){
							musiqueDeJeu.stop();	
						}					
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e3) {
						e3.printStackTrace();
					}					
					new FenetreCombat(jFramePrincipal, variablesSession, sauvegardeJeu, numeroDialogue);
				}
				if (numeroDialogue==1) {
					variablesSession.listeInterractionsAvecDresseurs[numeroDialogue]=1;
				}
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void affichageBoiteTexte() {
		lBoiteTexte.setText(variablesSession.texteAAfficher[numeroLigneTexte]);
		pBoiteTexte.setVisible(true);
		jFramePrincipal.revalidate();
		jFramePrincipal.repaint();
	}

	public void cacherBoiteTexte() {
		pBoiteTexte.setVisible(false);
		jFramePrincipal.revalidate();
		jFramePrincipal.repaint();
		stopDeplacement = false;
	}

	public void actionDeLaCase(int x, int y, boolean spawn) {
		sauvegardeJeu.nouvelleSauvegarde(variablesSession);
		switch (String.valueOf(tableauCarte[y][x]).charAt(0)) {
			case '2': {// Dialogue sans combat
				numeroDialogue = tableauCarte[y][x];
				if (numeroDialogue > 200) {
					numeroDialogue = (numeroDialogue - 200);
				}

				if (((tableauCarte[yArriveeFinal][xArriveeFinal] == 2) && ((Math.abs(y - yArriveeFinal) < 2) && (Math.abs(x - xArriveeFinal) < 2))) || (variablesSession.listeInterractionsAvecDresseurs[numeroDialogue] == 0) ||(variablesSession.listeInterractionsAvecDresseurs[numeroDialogue] == 10) || (spawn == true)) {
					stopDeplacement = true;
					variablesSession.dialogueAvecDresseur(numeroDialogue);
					sauvegardeJeu.nouvelleSauvegarde(variablesSession);
					numeroLigneTexte = 0;
					affichertexte = true;
					affichageBoiteTexte();

				}
				break;
			}
			case '3': {// Dialogue avec combat
				numeroDialogue = tableauCarte[y][x];
				if (numeroDialogue > 300) {
					numeroDialogue = (numeroDialogue - 300);
				}
				if (((tableauCarte[yArriveeFinal][xArriveeFinal] == 3) && ((Math.abs(y - yArriveeFinal) < 2) && (Math.abs(x - xArriveeFinal) < 2))) || (variablesSession.listeInterractionsAvecDresseurs[numeroDialogue] == 0) || (spawn == true)) {

					stopDeplacement = true;
					variablesSession.dialogueAvecDresseur(numeroDialogue);
					sauvegardeJeu.nouvelleSauvegarde(variablesSession);
					numeroLigneTexte = 0;
					affichertexte = true;
					affichageBoiteTexte();
				}
				break;

			}
			case '4': {// Changement de carte

				if ((tableauCarte[yArriveeFinal][xArriveeFinal] == tableauCarte[y][x]) && (spawn == false)) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					pPrincipal.removeAll();
					jFramePrincipal.remove(pPrincipal);
					jFramePrincipal.revalidate();
					jFramePrincipal.repaint();
					stopDeplacement = true;
					int valeurTableau = (tableauCarte[y][x] - 4000);
					variablesSession.nouvelleCarte(valeurTableau);
					sauvegardeJeu.nouvelleSauvegarde(variablesSession);
					try {
						if (variablesSession.sondesac==1){
							musiqueDeJeu.stop();	
						}					
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e4) {
						e4.printStackTrace();
					}	
					new FenetreCarte(jFramePrincipal, variablesSession, sauvegardeJeu);
				} else if ((yArrivee == 0) && (xArrivee == x)) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					pPrincipal.removeAll();
					jFramePrincipal.remove(pPrincipal);
					jFramePrincipal.revalidate();
					jFramePrincipal.repaint();
					stopDeplacement = true;
					int valeurTableau = (tableauCarte[y][x] - 4000);
					variablesSession.nouvelleCarte(valeurTableau);
					sauvegardeJeu.nouvelleSauvegarde(variablesSession);
					try {
						if (variablesSession.sondesac==1){
							musiqueDeJeu.stop();	
						}					
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e5) {
						e5.printStackTrace();
					}
					new FenetreCarte(jFramePrincipal, variablesSession, sauvegardeJeu);
				}
				break;
			}
			case '5': {// Combat contre un animal sauvage dans hautes herbes
				if ((Math.random() > 0.75) && (xDepartInitial != x) && (yDepartInitial != y)) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					stopDeplacement = true;
					pPrincipal.removeAll();
					jFramePrincipal.remove(pPrincipal);
					jFramePrincipal.revalidate();
					jFramePrincipal.repaint();
					sauvegardeJeu.nouvelleSauvegarde(variablesSession);
					try {
						if (variablesSession.sondesac==1){
							musiqueDeJeu.stop();	
						}					
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e6) {
						e6.printStackTrace();
					}					new FenetreCombat(jFramePrincipal, variablesSession, sauvegardeJeu, 500);
				}
				break;
			}

		}
		if (spawn==true) {
			affichageNomVille();
		}
	}
	
	
	
    public void viderCarte() {	//vide le tableau de l'algorithme de recherche de chemin
		for(int x = 0; x < 25; x++) {
			for(int y = 0; y < 25; y++) {
				map[x][y] = new Noeud(3,x,y);//On vide tous les Noeuds 
			}
		}
		resetVariablesAlgo();

	}
	
	
	public void genererCarteDesMurs() {	//genère le tableau des murs pour l'algorithme de recherche de chemin
		viderCarte();//On vide entièrement le tableau
		for(int i = 0; i < 24; i++) {
            for(int j = 0; j < 24; j++) {
                Noeud current;
                current = map[i][j];
                if (((tableauCarte[j][i]==0) || (tableauCarte[j][i] == 2) || (tableauCarte[j][i]==3)|| (tableauCarte[j][i]==6))){//On ajoute les murs sinon on laisse vide
					current.setType(2);
                }  
            }
		}
	}
	
	
	public void demarrerRecherche() {	//Démarrer la recherche
		if(recherche) {
			algoDeplacement.algorithmeAEtoile();
		}
	}


	public void resetVariablesAlgo() {	//reset les variables de l'algorithme de recherche du chemin
		recherche = true;
		a=0;
	}


	class Algorithme {	//Classe de l'algorithme de déplacement

		public void algorithmeAEtoile() {
			ArrayList<Noeud> priorite = new ArrayList<Noeud>();
			priorite.add(map[xDepart][yDepart]);
			while(recherche) {
				if(priorite.size() <= 0) {
					recherche = false;
					break;
				}
				int esperance = priorite.get(0).getEsperance()+1;
				ArrayList<Noeud> DejaVisite = explorerVoisins(priorite.get(0),esperance);
				if(DejaVisite.size() > 0) {
					priorite.remove(0);
					priorite.addAll(DejaVisite);
				} else {
					priorite.remove(0);
				}
				Tri(priorite);	//tri des priorités
			}
		}
		
		
		public ArrayList<Noeud> Tri(ArrayList<Noeud> listeTriee) {	//Tri de la liste des priorités
			int c = 0;
			while(c < listeTriee.size()) {
				int sm = c;
				for(int i = c+1; i < listeTriee.size(); i++) {
					if(listeTriee.get(i).getDistanceEuclidienne()+listeTriee.get(i).getEsperance() < listeTriee.get(sm).getDistanceEuclidienne()+listeTriee.get(sm).getEsperance())
						sm = i;
				}
				if(c != sm) {
					Noeud temp = listeTriee.get(c);
					listeTriee.set(c, listeTriee.get(sm));
					listeTriee.set(sm, temp);
				}	
				c++;
			}
			return listeTriee;
		}
		
		
		public ArrayList<Noeud> explorerVoisins(Noeud current, int esperance) {	//explorer les voisins
			ArrayList<Noeud> DejaVisite = new ArrayList<Noeud>();	//liste des NoeudS qui ont déjà été visistés
			for(int a = -1; a <= 1; a++) {
				for(int b = -1; b <= 1; b++) {
					int xlimite = current.getX()+a;
					int ylimite = current.getY()+b;
					if((xlimite > -1 && xlimite < nbreCases) && (ylimite > -1 && ylimite < nbreCases)) {	// on s'assure que les noeuds testés ne sont pas en dehors de la grille
						Noeud voisin = map[xlimite][ylimite];
						if((voisin.getEsperance()==-1 || voisin.getEsperance() > esperance) && voisin.getType()!=2) {	// On vérifie si le noeaud n'a pas déjà éte testé et si ce n'est pas un mur 
							explorer(voisin, current.getX(), current.getY(), esperance);	// on teste le noeud 
							DejaVisite.add(voisin);	//on ajoute le noeud à la liste
						}
					}
				}
			}
			return DejaVisite;
		}
		
		
		public void explorer(Noeud current, int dernierX, int lasty, int esperance) {	//exploration d'un noeud
			if(current.getType()!=0 && current.getType() != 1)	//Verification si le noeud n'est pas un noeud qui correspond au départ ou à l'arrivée du personnage
				current.setType(4);	//On défini le noeud comme déjà visisté
			current.setDernierNoeud(dernierX, lasty);	//On enregistre que ce noeaud est le dernier noeud visité 
			current.setEsperance(esperance);	//on met à jour l'esperance du noeud
			if(current.getType() == 1) {	//Si le noeud correspond à la case d'arrivée alors on recupère le chemin final
				marcheArriere(current.getDernierX(), current.getDernierY(),esperance);
			}
		}
		
		
		public void marcheArriere(int lx, int ly, int esperance) {	//Permet de faire marche arrière une fois que la case d'arrivee a été atteinte pour pouvoir définir le chemin que va prendre le perso
			while(esperance > 1) {	//récupération du chemin final du dernier noeud consulté jusqu'au noeud de daprt
				Noeud current = map[lx][ly];
				current.setType(5);
				lx = current.getDernierX();
				ly = current.getDernierY();
				esperance--;
			}
			recherche = false;
		}
	}
	
	
	class Noeud {
		
		// 0 = début, 1 = fin, 2 = mur, 3 = vide, 4 = vérifié, 5 = chemin final
		private int typeDeCase = 0;
		private int esperance;
		private int x;
		private int y;
		private int dernierX;
		private int lastY;
		private double distanceJusquArrivee = 0;
	
		public Noeud(int type, int x, int y) {	//constructeur d'un noeud
			typeDeCase = type;
			this.x = x;
			this.y = y;
			esperance = -1;
		}
		
		public double getDistanceEuclidienne() {		//Calcul de la distance euclidienne jusqu'à la case finale
			int xdif = Math.abs(x-xArrivee);
			int ydif = Math.abs(y-yArrivee);
			distanceJusquArrivee = Math.sqrt((xdif*xdif)+(ydif*ydif));
			return distanceJusquArrivee;
		}
		
		public int getX() {return x;}		
		public int getY() {return y;}
		public int getDernierX() {return dernierX;}
		public int getDernierY() {return lastY;}
		public int getType() {return typeDeCase;}
		public int getEsperance() {return esperance;}
		
		public void setType(int type) {typeDeCase = type;}		
		public void setDernierNoeud(int x, int y) {dernierX = x; lastY = y;}
		public void setEsperance(int esperance) {this.esperance = esperance;}
	}
}
