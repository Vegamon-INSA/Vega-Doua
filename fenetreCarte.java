import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class fenetreCarte implements ActionListener, MouseListener, KeyListener {
	private JPanel pPrincipal, pBoiteTexte, pNomCarte;
	private JLabel JLabelPersonnage, JLabelCarte, lBoiteTexte, gif ,lNomCarte; 
	private CJframe JFramePrincipal; //Jframe principal avec image de fond
	private VariablesDeJeu VariablesSession; //Variables de Jeu
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu;
	private KeyListener listener;
	private int[][] TableauCarte = new int[25][25];//tableau contenenant la consitution de la carte dans laquelle est le personnage
	private int[][] TableauChemin = new int[50][2];//Tableau généré par l'algorithme avec les coordonnées x dans la colonne 0 et y dans la colonne 1 du chemin pour aller d'un point à un autre
	private int[][] TableauCheminTrie = new int[50][2];// Tableau issu du tableau TableauChemin mais qui est cet fois-ci trié dans l'ordre de l'avancement du chemin
	private Timer t1, t2,t3;//Timer de déplacement de 'limage du personnage
	private int a=0;//variable a pour le timer du déplacement du joueur
	private boolean descente=true;
	private int ordonneePanel=-50;//variable a pour le timer du déplacement du joueur
	private int NbreCases = 25;//nombre de cases dans la grille qui découpe la carte
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
	private int NbreDeplacement;
	private int TailleCellule = 800/NbreCases;//Taille d'un carré : largeur de la fenetre divisé par le nombre de cellules
	
	//variables pour l'algorithme de recherche de chemin
	private Node [][] map = new Node[25][25];
	private boolean recherche = true;//Bloque ou pas l'algorithme de recherche de chemin
	private Algorithme AlgoDeplacement = new Algorithme();
	private Random r = new Random();
	private int verifications = 0;//Compteur du nombre de cases vérifiées par l'algorithme de recherche de chemin
	private fenetreCarte fcarte;
	private FenetreCombat fcombat;

	public fenetreCarte ( CJframe Frame,VariablesDeJeu variables, Sauvegarde sauvegarde, Musiques musique) {
		JFramePrincipal= Frame;
		VariablesSession=variables;
		SauvegardeJeu=sauvegarde;
		MusiqueDeJeu=musique;
		MusiqueDeJeu.StopMusique();
		//Musiques MusiqueDeJeu= new Musiques();
		//MusiqueDeJeu.JouerMusiqueUneFois("Musiques/MsgDeBienvenue.wav");
		pPrincipal = new JPanel();//Jpanel principal qui couvre toutela surface de la fenetre
        pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
        JFramePrincipal.add(pPrincipal);
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
        
        lNomCarte = new JLabel(VariablesSession.nomCarte);
		lNomCarte.setBounds(0, 0, 200, 50);
		lNomCarte.setText(VariablesSession.nomCarte);
		lNomCarte.setHorizontalAlignment(JLabel.CENTER);
		lNomCarte.setVerticalAlignment(JLabel.CENTER);
        pNomCarte.add(lNomCarte);
        
		// Boite de texte dans laquelle on affiche les dialogues entre notre personnage et les dresseurs que notre perso rencontre sur la carte
		
		pBoiteTexte = new JPanel();
		pBoiteTexte.setBounds(100, 700, 600, 70);
		pBoiteTexte.setLayout(null);
		pBoiteTexte.setBackground(Color.white);
		Border border2 = BorderFactory.createLineBorder(Color.BLACK);
		pBoiteTexte.setBorder(border);
        pPrincipal.add(pBoiteTexte);	
		pBoiteTexte.setVisible(false);	
        
        lBoiteTexte = new JLabel(VariablesSession.nomCarte);
		lBoiteTexte.setBounds(20, 5, 500, 60);
        pBoiteTexte.add(lBoiteTexte);
        
        gif = new JLabel(new ImageIcon("Images/FlecheEntrer.gif")); 
        gif.setBounds(550,30,30,30);
        pBoiteTexte.add(gif);
        
		//Algo de déplacement
		xDepart= VariablesSession.xDepart;
		yDepart= VariablesSession.yDepart;
		TableauCarte=VariablesSession.TableauCarte;
		genererCarteDesMurs();

        //Ajout du fond de la fenetre
        JLabelCarte = new JLabel (new ImageIcon(VariablesSession.imageFond)); 
        JLabelCarte.setBounds(0,0,800,800);
        JLabelCarte.setLayout(null);
        JLabelCarte.addMouseListener(this);
		pPrincipal.add(JLabelCarte); 

		// Ajout du Bonhomme à déplacer
        JLabelPersonnage = new JLabel(new ImageIcon("Images/Personnagepetit.png")); 
        JLabelPersonnage.setBounds((xDepart*TailleCellule-8),(yDepart*TailleCellule-28),31,52);
        JLabelPersonnage.setLayout(null);
        JLabelCarte.add(JLabelPersonnage);
        
        JFramePrincipal.revalidate();
		JFramePrincipal.repaint();
		xArriveeFinal= xDepart;
		yArriveeFinal= yDepart;
		xDepartInitial= xDepart;
		yDepartInitial= yDepart;
		
		actionDeLaCase(xDepart,yDepart, true);
	}
	
	
	public void affichageNomVille() {
	
			pNomCarte.setVisible(true);	
			t2 = new Timer(100, this);
			t2.start();
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
		
	}
		
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {//quand click sur l'écran
		resetMap();
		try {
			if (!affichertexte){
				stopDeplacement=false;
				xArrivee = e.getX()/TailleCellule;	//Récupération de l'abscisse et de l'ordonnée du point d'arrivée en fonction de la taille de la grille
				yArrivee = e.getY()/TailleCellule;
				xArriveeFinal= xArrivee;
				yArriveeFinal= yArrivee;
				xDepartInitial= VariablesSession.xDepart;
				yDepartInitial= VariablesSession.yDepart;
				System.out.println("xArrivee="+xArrivee);
				System.out.println(yArrivee);
				Node current = map[xArrivee][yArrivee];
				DeplacementJoueur (xArrivee,yArrivee);
				System.out.println("Deplacement terminé");
			}
		} catch(Exception z) {}	//catch les exceptions
	}
	

	public void resetMap() {	//reset du tableau de recherche du chemin
		for(int x = 0; x < NbreCases; x++) {
			for(int y = 0; y < NbreCases; y++) {
				Node current = map[x][y];
				if(current.getType() == 4 || current.getType() == 5)	//check si le node est le cehmin fianl ou le début ou l'arrivée
					map[x][y] = new Node(3,x,y);	//Si ce n'est pas le cas : on vide la case
			}
		}
		//reset les cases de début et de fin
		if(xDepart > -1 && yDepart > -1) {	
			map[xDepart][yDepart] = new Node(0,xDepart,yDepart);
			map[xDepart][yDepart].setesperance(0);
		}
		if(xArrivee > -1 && yArrivee > -1)
			map[xArrivee][yArrivee] = new Node(1,xArrivee,yArrivee);
		resetVariablesAlgo();	
		
		for(int s = 0; s < TableauChemin.length; s++) {
			TableauCheminTrie[s][0]=0;
			TableauCheminTrie[s][1]=0;

		}
	}
		
	
	public boolean PossibiliteDeplacement (int x, int y){ // Test si la poistion finale demandée lors du click est possible	
        boolean possibilite = true;
        if ((TableauCarte[y][x] == 0)|| (stopDeplacement==true)){
            possibilite=false;
        }
        System.out.println("Deplacement possible :"+possibilite);
        return possibilite;
    }
    

    public void DeplacementJoueur (int xFinal, int yFinal){
		if (PossibiliteDeplacement(xFinal,yFinal)){// Test si la poistion finale demandée est possible	
			genererCarteDesMurs();
			resetMap();
			demarrerRecherche();
			int colonneY=0;
			for(int x = 0; x < NbreCases; x++) {	
				for(int y = 0; y < NbreCases; y++) {
					if (map[x][y].getType()==5){
						TableauChemin[colonneY][0]=map[x][y].getX();
						TableauChemin[colonneY][1]=map[x][y].getY();
						colonneY++;
					}
				}
			}
			TriTableauChemin();
			t1= new Timer(1, this);
			t1.start();	

		}
	}
	

	public void TriTableauChemin() {//retirer les System.out.println pour nettoyer
		boolean finDuTri=false;
		NbreDeplacement=0;
		
		if((TableauChemin[0][0]==0)&&(TableauChemin[0][1]==0)){
			TableauChemin[0][0]=xArrivee;
			TableauChemin[0][1]=yArrivee;
		}
		
		System.out.println("x depart :"+xDepart);
		System.out.println("y depart  :"+yDepart);
		System.out.println("Tableau initial :");
		for(int s = 0; s < TableauChemin.length; s++) {	
			System.out.println(TableauChemin[s][0]+"=x, y="+TableauChemin[s][1]);
		}

        TableauCheminTrie[0][0]=xDepart;
        TableauCheminTrie[0][1]=yDepart;
        
        for (int j=0; j<TableauChemin.length; j++) {
			if ((TableauChemin[j][0]==xDepart) && (TableauChemin[j][1]==yDepart)){
				TableauChemin[j][0]=0;
				TableauChemin[j][1]=0;	
			}
		}
		
		for (int d=0; d<(TableauChemin.length-1); d++){
			if (finDuTri==true){
				System.out.println("Fin du tri");
				break;
			}
			System.out.println("d="+d);
			int w=0;
			while ((TableauCheminTrie[d+1][0]==0)||(TableauCheminTrie[d+1][1]==0)){
				if((TableauCheminTrie[d][0]==TableauChemin[w][0]) && ((TableauCheminTrie[d][1]==(TableauChemin[w][1]+1)) || (TableauCheminTrie[d][1]==TableauChemin[w][1]-1))){
					System.out.println("Déplacement vertcal");					
					TableauCheminTrie[d+1][0]=TableauChemin[w][0];
					TableauCheminTrie[d+1][1]=TableauChemin[w][1];
				}
				else if((TableauCheminTrie[d][1]==TableauChemin[w][1]) && ((TableauCheminTrie[d][0]==(TableauChemin[w][0]+1)) || (TableauCheminTrie[d][0]==TableauChemin[w][0]-1))){
					System.out.println("Déplacement horizontal");				
					TableauCheminTrie[d+1][0]=TableauChemin[w][0];
					TableauCheminTrie[d+1][1]=TableauChemin[w][1];
				}
						/*System.out.println(TableauCheminTrie[d][1]);
						System.out.println(TableauChemin[w][1]+1);
						System.out.println(TableauChemin[w][1]-1);
						//System.out.println((TableauCheminTrie[d][1]!=(TableauChemin[x][1]+1)) && (TableauCheminTrie[d][1]!=TableauChemin[x][1]-1));
						System.out.println(TableauCheminTrie[d][0]);
						System.out.println(TableauChemin[w][0]+1);
						System.out.println(TableauChemin[w][0]-1);
						System.out.println((TableauCheminTrie[d][0]!=(TableauChemin[x][0]+1)) && (TableauCheminTrie[d][0]!=(TableauChemin[x][0]-1)));*/
				else if(!( ( (TableauCheminTrie[d][1]!=(TableauChemin[w][1]+1)) && (TableauCheminTrie[d][1]!=(TableauChemin[w][1]-1)) ) || ( (TableauCheminTrie[d][0]!=(TableauChemin[w][0]+1)) && (TableauCheminTrie[d][0]!=(TableauChemin[w][0]-1) ) ) )){
						System.out.println("Déplacement diagonal");
						TableauCheminTrie[d+1][0]=TableauChemin[w][0];
						TableauCheminTrie[d+1][1]=TableauChemin[w][1];
				}				
				w++;
			}
			for(int h = 0; h < TableauCheminTrie.length; h++) {	
				System.out.println("Tableau trié : x= "+TableauCheminTrie[h][0]+" y= "+TableauCheminTrie[h][1]);
			}
			TableauChemin[w-1][0]=0;
			TableauChemin[w-1][1]=0;

			for(int s = 0; s < TableauChemin.length; s++) {
				if((TableauChemin[s][0]==0)&&(TableauChemin[s][1]==0)){
					finDuTri=true;
				}
				else {
					finDuTri=false;
					break;
				}
			}
		}
		
		System.out.println("Tableau initial :");
		for(int s = 0; s < TableauChemin.length; s++) {
			System.out.println(TableauChemin[s][0]+"=x,oui y="+TableauChemin[s][1]);
			if((TableauCheminTrie[s][0]!=0)&&(TableauCheminTrie[s][1]!=0)){
				NbreDeplacement++;
			}
		}
		TableauCheminTrie[NbreDeplacement][0]=xArrivee;
		TableauCheminTrie[NbreDeplacement][1]=yArrivee;
		System.out.println("NbreDeplacement ="+NbreDeplacement);		
		for(int h = 0; h < TableauCheminTrie.length; h++) {	
			TableauCheminTrie[h][0]=TableauCheminTrie[h][0]*TailleCellule;
			TableauCheminTrie[h][1]=TableauCheminTrie[h][1]*TailleCellule;
			System.out.println(TableauCheminTrie[h][0]+"=x, y="+TableauCheminTrie[h][1]);
		}
	}

	
	public void actionPerformed(ActionEvent u) {
		if(u.getSource()==t1){//Timer délpacement personnage

			/*if((TableauCheminTrie[a+1][0]!=xArrivee)&&(TableauCheminTrie[a+1][1]!=yArrivee) && ((String.valueOf(TableauCarte[yArrivee][xArrivee]).charAt(0)=='2') || (String.valueOf(TableauCarte[yArrivee][xArrivee]).charAt(0)=='2'))) {
				actionDeLaCase(xDepart,yDepart,false);
						
			}*/

			System.out.println((TableauCheminTrie[a][0]!=0)&&(TableauCheminTrie[a][1]!=0)&& (stopDeplacement==false) && (TableauCarte[yDepart][xDepart]!=2) && (TableauCarte[yDepart][xDepart]!=3));
			if((TableauCheminTrie[a][0]!=0)&&(TableauCheminTrie[a][1]!=0)&&(stopDeplacement==false)){
				actionDeLaCase(xDepart,yDepart,false);
				t1.stop();
			
					DeplacementImage();
					System.out.println("test4"+a);
				
				xDepart=TableauCheminTrie[a][0]/TailleCellule;
				yDepart=TableauCheminTrie[a][1]/TailleCellule;
				System.out.println("test2");
				t1= new Timer(250, this);
				if ((a<=NbreDeplacement)&&(stopDeplacement==false)){
					a++;
					t1.start();
					System.out.println("test3");

				}
			}
			else{
				t1.stop();
				actionDeLaCase(xDepart,yDepart,false);
			}

		}
			 
		if(u.getSource()==t2){//Timer délpacement boite nom de la carte
			t2.stop();
			pNomCarte.setBounds(300, ordonneePanel, 200, 50);
			t2= new Timer(3, this);
			if ((ordonneePanel<50)&&(descente==true)){
				t2.start();
				ordonneePanel++;
			}
			else if ((ordonneePanel==50)&&(descente==true)){
				descente=false;
				try
					{
						Thread.sleep(500);
					}
					catch(InterruptedException ex)
					{
						Thread.currentThread().interrupt();
				}
			}
			if ((ordonneePanel>-50)&&(descente==false)){
				t2.start();
				ordonneePanel--;
			}
			else if ((ordonneePanel==-50)&&(descente==false)){
				pNomCarte.setVisible(false);
			}
		}
	}		
		

	public void DeplacementImage() {//Déplacement du Bonhomme
		JLabelPersonnage.setBounds((TableauCheminTrie[a][0]-8),(TableauCheminTrie[a][1]-28),31,52);
		VariablesSession.xDepart=xDepart;
		VariablesSession.yDepart=yDepart;
	}
	
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==77) {//retour au menu
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
			stopDeplacement=true;
			SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
			Accueil ecranAccueil= new Accueil(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu);
		}
		if(arg0.getKeyCode()==10) {//texte suivant
			if (affichertexte){
				numeroLigneTexte+=1;
			}
			if ((affichertexte)&&(VariablesSession.texteAAfficher[numeroLigneTexte]!="fin_message")){
				AffichageBoiteTexte();
			}
			else if (VariablesSession.texteAAfficher[numeroLigneTexte]=="fin_message"){
				affichertexte=false;
				CacherBoiteTexte();
				if (((String.valueOf(TableauCarte[yDepart][xDepart]).charAt(0))=='3') && (VariablesSession.listeInterractionsAvecDresseurs[numeroDialogue]==0)){
					JFramePrincipal.remove(pPrincipal);
					JFramePrincipal.revalidate();
					JFramePrincipal.repaint();
					fcombat = new FenetreCombat(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu,numeroDialogue);
				}
			}
		}
    }

    public void keyReleased(KeyEvent arg0) {}

    public void keyTyped(KeyEvent arg0){}


	public void AffichageBoiteTexte(){
		System.out.println("numero ligne"+numeroLigneTexte);
		lBoiteTexte.setText(VariablesSession.texteAAfficher[numeroLigneTexte]);
		pBoiteTexte.setVisible(true);	
        JFramePrincipal.revalidate();
		JFramePrincipal.repaint();
	}
	
	
	public void CacherBoiteTexte(){
		pBoiteTexte.setVisible(false);	
		JFramePrincipal.revalidate();
		JFramePrincipal.repaint();
	}


	public void actionDeLaCase(int x, int y, boolean spawn){
		System.out.println("actionDeLaCase"+TableauCarte[y][x]);
		System.out.println(x);
		System.out.println(xArriveeFinal);
		System.out.println(y);
		System.out.println(yArriveeFinal);
		SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
		switch(String.valueOf(TableauCarte[y][x]).charAt(0)) {
			case '2':{//Dialogue sans combat
				numeroDialogue = TableauCarte[y][x];
				if (numeroDialogue>200) {
					numeroDialogue = (numeroDialogue-200);
				}
				
				if (((TableauCarte[yArriveeFinal][xArriveeFinal]==2) || (VariablesSession.listeInterractionsAvecDresseurs[numeroDialogue]==0) || (spawn==true)) && ((Math.abs(y-yArriveeFinal)<3) && (Math.abs(x-xArriveeFinal)<3))){
					stopDeplacement=true;
					System.out.println("numero dialogue"+numeroDialogue);
					VariablesSession.DialogueAvecDresseur(numeroDialogue);
					numeroLigneTexte=0;
					affichertexte=true;
					AffichageBoiteTexte();
				}
				break;
			}
			case '3':{//Dialogue avec combat
					numeroDialogue = TableauCarte[y][x];
				if (numeroDialogue>300) {
					numeroDialogue = (numeroDialogue-300);
				}
				if (((TableauCarte[yArriveeFinal][xArriveeFinal]==3) || (VariablesSession.listeInterractionsAvecDresseurs[numeroDialogue]==0)|| (spawn==true)) && ((Math.abs(y-yArriveeFinal)<3) && (Math.abs(x-xArriveeFinal)<3))){
					stopDeplacement=true;
					System.out.println("numero dialogue"+numeroDialogue);
					VariablesSession.DialogueAvecDresseur(numeroDialogue);
					numeroLigneTexte=0;
					affichertexte=true;
					AffichageBoiteTexte();
				}
				break;
				
			}
			case '4':{//Changement de carte
				if ((TableauCarte[yArriveeFinal][xArriveeFinal]==TableauCarte[y][x])&&(spawn==false)){
					System.out.println("trigger");
					pPrincipal.removeAll();
					JFramePrincipal.remove(pPrincipal);
					JFramePrincipal.revalidate();
					JFramePrincipal.repaint();
					stopDeplacement=true;
					int valeurTableau = (TableauCarte[y][x]-4000);	
					VariablesSession.NouvelleCarte(valeurTableau);
					SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
					fcarte = new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu, MusiqueDeJeu);
				}
				else if((yArrivee==0)&&(xArrivee==x)){
					pPrincipal.removeAll();
					JFramePrincipal.remove(pPrincipal);
					JFramePrincipal.revalidate();
					JFramePrincipal.repaint();
					stopDeplacement=true;
					int valeurTableau = (TableauCarte[y][x]-4000);	
					VariablesSession.NouvelleCarte(valeurTableau);
					SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
					fcarte = new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu, MusiqueDeJeu);
				}
				break;
			}
			case '5':{//Combat contre un animal sauvage dans hautes herbes
				if ((Math.random()>0.75)&&(xDepartInitial!=x)&&(yDepartInitial!=y)){
					try
					{
						Thread.sleep(300);
					}
					catch(InterruptedException ex)
					{
						Thread.currentThread().interrupt();
					}
					stopDeplacement=true;
					pPrincipal.removeAll();
					JFramePrincipal.remove(pPrincipal);
					JFramePrincipal.revalidate();
					JFramePrincipal.repaint();
					SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
					fcombat = new FenetreCombat(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu,500);
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
				map[x][y] = new Node(3,x,y);//On vide tous les Nodes 
			}
		}
		resetVariablesAlgo();

	}
	
	
	public void genererCarteDesMurs() {	//genère le tableau des murs pour l'algorithme de recherche de chemin
		viderCarte();//On vide entièrement le tableau
		for(int i = 0; i < 24; i++) {
            for(int j = 0; j < 24; j++) {
                Node current;
                current = map[i][j];
                if (((TableauCarte[j][i]==0) || (TableauCarte[j][i] == 2) || (TableauCarte[j][i]==3)|| (TableauCarte[j][i]==6))){//On ajoute les murs sinon on laisse vide
					current.setType(2);
                }  
            }
		}
	}
	
	
	public void demarrerRecherche() {	//Démarrer la recherche
		if(recherche) {
			AlgoDeplacement.AlgorithmeAEtoile();
		}
	}


	public void resetVariablesAlgo() {	//reset les variables de l'algorithme de recherche du chemin
		recherche = true;
		verifications = 0;
		a=0;
	}


	class Algorithme {	//Classe de l'algorithme de déplacement

		public void AlgorithmeAEtoile() {
			ArrayList<Node> priorite = new ArrayList<Node>();
			priorite.add(map[xDepart][yDepart]);
			while(recherche) {
				if(priorite.size() <= 0) {
					recherche = false;
					break;
				}
				int esperance = priorite.get(0).getesperance()+1;
				ArrayList<Node> DejaVisite = explorerVoisins(priorite.get(0),esperance);
				if(DejaVisite.size() > 0) {
					priorite.remove(0);
					priorite.addAll(DejaVisite);
				} else {
					priorite.remove(0);
				}
				Tri(priorite);	//tri des priorités
			}
		}
		
		
		public ArrayList<Node> Tri(ArrayList<Node> listeTriee) {	//Tri de la liste des priorités
			int c = 0;
			while(c < listeTriee.size()) {
				int sm = c;
				for(int i = c+1; i < listeTriee.size(); i++) {
					if(listeTriee.get(i).getEuclidDist()+listeTriee.get(i).getesperance() < listeTriee.get(sm).getEuclidDist()+listeTriee.get(sm).getesperance())
						sm = i;
				}
				if(c != sm) {
					Node temp = listeTriee.get(c);
					listeTriee.set(c, listeTriee.get(sm));
					listeTriee.set(sm, temp);
				}	
				c++;
			}
			return listeTriee;
		}
		
		
		public ArrayList<Node> explorerVoisins(Node current, int esperance) {	//explorer les voisins
			ArrayList<Node> DejaVisite = new ArrayList<Node>();	//LIST OF NODES THAT HAVE BEEN DejaVisite
			for(int a = -1; a <= 1; a++) {
				for(int b = -1; b <= 1; b++) {
					int xbound = current.getX()+a;
					int ybound = current.getY()+b;
					if((xbound > -1 && xbound < NbreCases) && (ybound > -1 && ybound < NbreCases)) {	//MAKES SURE THE NODE IS NOT OUTSIDE THE GRID
						Node voisin = map[xbound][ybound];
						if((voisin.getesperance()==-1 || voisin.getesperance() > esperance) && voisin.getType()!=2) {	//CHECKS IF THE NODE IS NOT A WALL AND THAT IT HAS NOT BEEN DejaVisite
							explorer(voisin, current.getX(), current.getY(), esperance);	//EXPLORE THE NODE
							DejaVisite.add(voisin);	//ADD THE NODE TO THE LIST
						}
					}
				}
			}
			return DejaVisite;
		}
		
		
		public void explorer(Node current, int lastx, int lasty, int esperance) {	//explorer A NODE
			if(current.getType()!=0 && current.getType() != 1)	//CHECK THAT THE NODE IS NOT THE START OR FINISH
				current.setType(4);	//SET IT TO DejaVisite
			current.setLastNode(lastx, lasty);	//KEEP TRACK OF THE NODE THAT THIS NODE IS DejaVisite FROM
			current.setesperance(esperance);	//SET THE esperance FROM THE START
			verifications++;
			if(current.getType() == 1) {	//IF THE NODE IS THE FINISH THEN BACKTRACK TO GET THE PATH
				marcheArriere(current.getLastX(), current.getLastY(),esperance);
			}
		}
		
		
		public void marcheArriere(int lx, int ly, int esperance) {	//Permet de faire marche arrière une fois que la case d'arrivee a été atteinte pour pouvoir définir le chemin que va prendre le perso
			while(esperance > 1) {	//BACKTRACK FROM THE END OF THE PATH TO THE START
				Node current = map[lx][ly];
				current.setType(5);
				lx = current.getLastX();
				ly = current.getLastY();
				esperance--;
			}
			recherche = false;
		}
	}
	
	
	class Node {
		
		// 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = finalpath
		private int cellType = 0;
		private int esperance;
		private int x;
		private int y;
		private int lastX;
		private int lastY;
		private double dToEnd = 0;
	
		public Node(int type, int x, int y) {	//CONSTRUCTOR
			cellType = type;
			this.x = x;
			this.y = y;
			esperance = -1;
		}
		
		public double getEuclidDist() {		//CALCULATES THE EUCLIDIAN DISTANCE TO THE FINISH NODE
			int xdif = Math.abs(x-xArrivee);
			int ydif = Math.abs(y-yArrivee);
			dToEnd = Math.sqrt((xdif*xdif)+(ydif*ydif));
			return dToEnd;
		}
		
		public int getX() {return x;}		//GET METHODS
		public int getY() {return y;}
		public int getLastX() {return lastX;}
		public int getLastY() {return lastY;}
		public int getType() {return cellType;}
		public int getesperance() {return esperance;}
		
		public void setType(int type) {cellType = type;}		//SET METHODS
		public void setLastNode(int x, int y) {lastX = x; lastY = y;}
		public void setesperance(int esperance) {this.esperance = esperance;}
	}
}


		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
	









