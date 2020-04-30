import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class FenetreCombat implements ActionListener{
	 
    private JPanel pHaut,pBas,pDialogue,pTextePokemonAdv,pTexteMonPokemon,pAttaque,viePerso, vieAdv, pPrincipal;
    private JLabel fond,lDialogue,lTextePokemonAdv,lTexteMonPokemon,lab2,Meloche,gif;
    private JButton attaque, soin,fuite,a1,a2,a3,a4,annuler;
    private Timer t,t2, t4, t5, t6;
    private boolean freeze,AuTourDuJoueur;
    private VEGAMONS perso, advers, pA, pD;
    private int x, y, x1, y1,j,exp,type, numCase, PVmaxAdvIni;
    private ArrayList<VEGAMONS> pokedex; 
    private ArrayList<String> textes;
	private CJframe JFramePrincipal; //Jframe principal avec image de fond
	private VariablesDeJeu VariablesSession; //Variables de Jeu
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu= new Musiques();
	private fenetreCarte fcarte;
	   
    public FenetreCombat(CJframe Frame, VariablesDeJeu variables,Sauvegarde sauvegarde,Musiques musique, int numeroCase){

		JFramePrincipal= Frame;
		VariablesSession=variables;
		SauvegardeJeu=sauvegarde;
		MusiqueDeJeu=musique;
		freeze = false;
        pokedex=VariablesSession.pokedex;
        perso = pokedex.get(0);  // On récupère notre personnage
        numCase=numeroCase;  // numéro donnant des infos sur le type de combat
        System.out.println("numero case"+numCase);
        x=100;
        y=135; // coordonnées des icons des personnages
        x1=420;
        y1=30;
        

        ///INTERFACE COMBAT

        pPrincipal = new JPanel();   // panneau contenant tous les éléments concernant les combats
        pPrincipal.setBounds(100, 70, 600, 600);
		pPrincipal.setLayout(null);
        JFramePrincipal.add(pPrincipal);

        pHaut = new JPanel();     // panneau de la moitié supérieur de la fenetre de combat
        pHaut.setBounds(0, 0, 600, 300);
		pHaut.setLayout(null);
        pPrincipal.add(pHaut);
        
        pBas = new JPanel();     // panneau de la moitié inférieur avec attaque soin ou fuite
        pBas.setBounds(0,300, 600, 300);
        pBas.setBackground(Color.gray);
        pBas.setLayout(null);
		pPrincipal.add(pBas);
		
		pAttaque = new JPanel();     // panneau de la moitié inférieur avec les 4 attaques possibles
        pAttaque.setBounds(0, 300, 600, 300);
        pAttaque.setBackground(Color.gray); 
        pAttaque.setLayout(null);

        attaque = new JButton("Attaque");   // Bouton pour attaquer
        attaque.setBounds(150, 50, 300, 100);
        attaque.setBackground(Color.red);
        pBas.add(attaque);
        attaque.addActionListener(this);
        
        soin = new JButton("Soin");        // Bouton pour se soigner
        soin.setBounds(150, 175, 130, 80);
        soin.setBackground(new Color(135, 211, 0));
        pBas.add(soin);
        soin.addActionListener(this);

        fuite = new JButton("Fuite");      // Bouton pour fuire
        fuite.setBounds(320, 175, 130, 80);
        fuite.setBackground(new Color (0, 196, 220));
        pBas.add(fuite);
        fuite.addActionListener(this);
       
		pDialogue = new JPanel();            // 3 panneaux où nosu avons mis les zones de textes
        pDialogue.setBounds(30,246,540,50);
        pDialogue.setBackground(Color.white);
        pHaut.add(pDialogue);
        
        pTextePokemonAdv = new JPanel();
        pTextePokemonAdv.setBounds(29, 13, 271, 48);
        pTextePokemonAdv.setBackground(Color.white);
        pTextePokemonAdv.setLayout(null);
        pHaut.add(pTextePokemonAdv);
        
        pTexteMonPokemon = new JPanel();
        pTexteMonPokemon.setBounds(306, 174, 271, 48);
        pTexteMonPokemon.setBackground(Color.white);
        pTexteMonPokemon.setLayout(null);
        pHaut.add(pTexteMonPokemon);
        
        fond = new JLabel(new ImageIcon("Images/interface.png"));    // Dessin de l'arrière plan du combat
        fond.setBounds(0,0,600,300);
        pHaut.add(fond);
        
        Meloche = new JLabel(new ImageIcon("Images/Meloche.png"));   //Image de notre personnage
        Meloche.setBounds(x,y,80,100);
        fond.add(Meloche);
        
        gif = new JLabel(new ImageIcon("Images/explosion.gif"));   // Image d'explosion pour les attaques
        gif.setBounds(150,-80,600,300);
        fond.add(gif);
        gif.setVisible(false);
        
        lDialogue= new JLabel("Que dois-je faire ?");  // Zone de texte avec toutes les informations
        lDialogue.setBounds(0,0,540,50);
        lDialogue.setBackground(Color.red);
        pDialogue.add(lDialogue);
        
        lTextePokemonAdv = new JLabel(" " );   // Infos le personnage adverse, initialisé vide avant de le créer 
        lTextePokemonAdv.setBounds(50,-10,268, 45);
        lTextePokemonAdv.setBackground(Color.white); 
        pTextePokemonAdv.add(lTextePokemonAdv);
        
        lTexteMonPokemon = new JLabel(perso.nom + "     XP : " + perso.XP + "     PV : " + perso.PV);  // Infos sur notre personnage
        lTexteMonPokemon.setBounds(20,-10,268, 45);
        lTexteMonPokemon.setBackground(Color.white);
        pTexteMonPokemon.add(lTexteMonPokemon);
         
        a1 = new JButton("feu :  "+perso.attaque1.nom);    // Les 4 boutons des 4 attaques possibles
        a1.setBounds(100, 50, 165, 50);
        a1.setBackground(new Color(240, 34, 51));
        a1.addActionListener(this);
        pAttaque.add(a1);
        
        a2 = new JButton("plante :  "+perso.attaque2.nom);
        a2.setBounds(335, 50, 165, 50);
        a2.setBackground(new Color(135, 211, 0));
        a2.addActionListener(this); 
        pAttaque.add(a2);
        
        a3 = new JButton("eau :  "+perso.attaque3.nom);
        a3.setBounds(100, 125, 165, 50);
        a3.setBackground(new Color (0, 196, 220));
        a3.addActionListener(this);
        pAttaque.add(a3);
        
        a4 = new JButton("elec :  "+perso.attaque4.nom);
        a4.setBounds(335, 125, 165, 50);
        a4.setBackground(new Color (250, 230, 0));
        a4.addActionListener(this);
        pAttaque.add(a4);
        
        annuler = new JButton("Annuler");
        annuler.setBounds(155, 200, 300, 50);
        annuler.setBackground(new Color (152, 143, 134));
        annuler.addActionListener(this); 
        pAttaque.add(annuler);
        
        viePerso = new JPanel();   // barre symbolisant nos points de vie
        viePerso.setLocation(20, 33);
        viePerso.setSize((int)(230.0*(double)(perso.PV)/(double)(perso.PVmax)), 5);
        viePerso.setBackground(Color.green);
        pTexteMonPokemon.add(viePerso);
        
        vieAdv = new JPanel();     // barre symbolisant les points de vie de l'adversaire
        vieAdv.setLocation(20, 33);
        vieAdv.setBackground(Color.green);
        pTextePokemonAdv.add(vieAdv);
       
        JFramePrincipal.setVisible(true);   // On affiche la fenetre
        debutCombat();        // On crée un adversaire
		System.out.println("Fenetre combat");
	}
	 
	public void debutCombat(){  // Méthode qui crée un adversaire à chaque début de combat
		
        AuTourDuJoueur=false;
        perso.PV=perso.PVmax;      //On réinitialise nos points de vie au max
        int exp=1;
                 // On indique le niveau de l'adversaire en fonction des cas où on est (avec un peu de hasard)
        if (VariablesSession.numeroCarte==105 || VariablesSession.numeroCarte==301){      //travée1
			exp=1+(int)(3*Math.random());                    // En fonction de la carte
		}else if (VariablesSession.numeroCarte==202 || VariablesSession.numeroCarte==401){   //travée2
			exp=6+(int)(7*Math.random());
        }else if (VariablesSession.numeroCarte==414 || VariablesSession.numeroCarte==601){   // huma1
			exp=42+(int)(16*Math.random());
        }else if (VariablesSession.numeroCarte==502 || VariablesSession.numeroCarte==701){   //huma2
			exp=60+(int)(20*Math.random());
        }
        if (numCase/10==2){   // Ou différemment pour les dresseurs des arênes
            exp = 45 - 4*(numCase%10);
        } else if (numCase/10==5){
            exp = 120 - 7*(numCase%10);
        }
        
        int num = 1+((int)(5*Math.random()));
        advers = pokedex.get(num);        // On crée un adversaire aléatoirement
        if (num==1){                    // On importe l'image correspondant à l'adversaire
            lab2=new JLabel(new ImageIcon("Images/Aigloss.png"));
        }else if (num==2){
            lab2=new JLabel(new ImageIcon("Images/Murenss.png"));
        }else if (num==3){
            lab2=new JLabel(new ImageIcon("Images/Sunfure.png"));
        }else if (num==4){
            lab2=new JLabel(new ImageIcon("Images/Anemoniac.png"));
        }else if (num==5){
            lab2=new JLabel(new ImageIcon("Images/Pandalame.png"));
        }
        fond.add(lab2);
        lab2.setBounds(x1,y1,100,100);   // On ajoute l'image au panneau
        advers.XP = exp;
        PVmaxAdvIni=advers.PVmax;
        advers.PVmax = (int)(Math.pow(exp, 0.3)*advers.PVmax)+(int)(Math.pow(exp, 1.2));
        advers.PV = advers.PVmax;                                        // On met l'expérience et les points de vie à jour
        lTextePokemonAdv.setText(advers.nom + "     XP : " + advers.XP + "     PV : " + advers.PV);
        vieAdv.setSize((int)(230.0*(double)(advers.PV)/(double)(advers.PVmax)), 5);  // On met l'affichage à jour
	}
    
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource()==soin && !freeze){
            soin();         // Si on appuie sur soin, on se soigne, et on l'affiche
			lDialogue.setText(perso.nom+" se soigne !");
			t = new Timer(1600, this);   // puis on lance l'attaque adverse
			t.start();
			freeze=true;
        }
        
		//ATTAQUE
        if (e.getSource()==attaque && !freeze){
            System.out.println("Attaque");  // En cliquant sur attaque, on affiche les boutons pour choisir quelle attaque on veut réaliser
            pPrincipal.remove(pBas);
			pPrincipal.add(pAttaque);
			pPrincipal.revalidate();
			pPrincipal.repaint();
        }
        
        boolean esquive;
        boolean fin=false;
        
        if (e.getSource()==a1 && !freeze){
            esquive = attaque(1);  // On lance l'attaque 1
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque1.nom+" !");
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque1.nom+" !    "+advers.nom+" esquive !");
            }                                    // On affiche l'attaque choisie et les graphismes d'attaque, ou alors on indique l'esquive
            t = new Timer(2000, this);
			t.start();    // Timer qui va lancer l'attaque de l'adversaire avant de revenir sur la notre
			freeze=true;
        }
        
        if (e.getSource()==a2 && !freeze){
            esquive = attaque(2);  // On lance l'attaque 2
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque2.nom+" !");
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque2.nom+" !	  "+advers.nom+" esquive !");
            }                                    // On affiche l'attaque choisie et les graphismes d'attaque, ou alors on indique l'esquive
            t = new Timer(2000, this);
			t.start();  // Timer qui va lancer l'attaque de l'adversaire avant de revenir sur la notre
			freeze=true;
        }
        
        if (e.getSource()==a3 && !freeze){
            esquive = attaque(3);   // On lance l'attaque 3
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque3.nom+" !"); 
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque3.nom+" !	   "+advers.nom+" esquive !");
            }                                     // On affiche l'attaque choisie et les graphismes d'attaque, ou alors on indique l'esquive
            t = new Timer(2000, this);
			t.start();    // Timer qui va lancer l'attaque de l'adversaire avant de revenir sur la notre
			freeze=true;
        }
        
        if (e.getSource()==a4 && !freeze){
            esquive = attaque(4);  // On lance l'attaque 4
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque4.nom+" !");
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque4.nom+" !  	"+advers.nom+" esquive !");
            }                                 // On affiche l'attaque choisie et les graphismes d'attaque, ou alors on indique l'esquive
            t = new Timer(2000, this);
			t.start();     // Timer qui va lancer l'attaque de l'adversaire avant de revenir sur la notre
			freeze=true;
        }
        
        if (e.getSource()==t) { //AuTourDuJoueur=true => a nous de jouer, AuTourDuJoueur=false, on a deja choisi notre attaque
            fin=finCombat();
			if (AuTourDuJoueur==true){
                t.stop();                      // On arrete ce timer
                freeze=false;
				pPrincipal.remove(pAttaque);   // On revient au panneau pour choir ce qu'on va faire
                pPrincipal.add(pBas);
                pPrincipal.revalidate();
				pPrincipal.repaint();
                lDialogue.setText("Que dois-je faire ?");
                AuTourDuJoueur=false;          // On change d'attaquant
            }
            else{
                if(advers.PV<=0){    // cas ou on a gagné le combat
                    if (numCase!=500){   // si on a battu un dresseur, on mémorise cette information
                        VariablesSession.listeInterractionsAvecDresseurs[numCase]=2;
                    }
                    t.stop();
                    advers.PVmax=PVmaxAdvIni;    // On réinitialise les points de vie de l'adversaire
                    t2 = new Timer(100, this);  
                    t2.start();            // Timer pour sortir de la fenetre de combat
                    freeze=true;
                    if ((((advers.XP-perso.XP)/2.5)+Math.pow(advers.XP, 0.36))>0){
                        perso.XP=perso.XP +(int)(((advers.XP-perso.XP)/2.5)+Math.pow(advers.XP, 0.36));  // Notre personnage gagne de l'expérience
                        perso.PVmax = (int)((Math.pow(perso.XP, 0.31)*40)+Math.pow(perso.XP, 1.21));
                    }
                    perso.PV=perso.PVmax;                    // Notre personnage gagne des points de vie
                    VariablesSession.xpMeloche=perso.XP;     // On mémorise notre expérience
                } 
                else {
                    esquive=advattaque();     // On lance l'attaque de l'adversaire
                    if (finCombat()==true){   // Cas ou on a perdu
                        if (numCase!=500){   // si on a perdu contre un dresseur, on mémorise cette information
							VariablesSession.listeInterractionsAvecDresseurs[numCase]=1;
                        }
                        lDialogue.setText("Retournes t'entrainer avec de revenir !");   // Message d'information pour savoir qu'on a perdu
                        graphAttak(false);
                        advers.PVmax=PVmaxAdvIni;   // On réinitialise les points de vie de l'adversaire
                        perso.PV=perso.PVmax;      // On réinitialise nos points de vie
                        VariablesSession.xpMeloche=perso.XP;
                        VariablesSession.NouvelleCarte(000);
                        VariablesSession.listeInterractionsAvecDresseurs[1]=2;
                        t2 = new Timer(1500, this);    
                        t2.start();               // On ferme la fenetre après un petit moment
                        freeze=true;
                    } else if (esquive==false){
                        lDialogue.setText(advers.nom+" attaque avec "+advAtt()+" !");  // On indique quelle attaque
                        graphAttak(false);      // Graphismes
                        AuTourDuJoueur=true;     // On change d'attaquant
                    }else{
                        lDialogue.setText(advers.nom+" attaque avec "+advAtt()+" !  	"+perso.nom+" esquive !");  // Et si on esquive
                        AuTourDuJoueur=true;    // On change d'attaquant
                    }
                }
            }
		}
		
        if (e.getSource()==annuler && !freeze){
			pPrincipal.remove(pAttaque);  // On réaffiche le panneau pour choisir entre attaque, soin ou, fuite
			pPrincipal.add(pBas);
			pPrincipal.revalidate();
			pPrincipal.repaint();
        }
        
        if (e.getSource()==fuite && !freeze){
            if (numCase==500){
                perso.PV=perso.PVmax;  // On réinitialise les points de vie des personnage
                advers.PVmax=PVmaxAdvIni;
                lDialogue.setText("Quelle poule mouill\u00e9e, tu t'enfuis !"); // On affiche qu'on fuit
                t2 = new Timer(2000, this);  // Puis après avoir eu le temps de voir le message on quitte la fenetre
                t2.start();
                freeze=true;
            } else {
                lDialogue.setText("Impossible de t'\u00e9chapper !");  // Si un dresseur nous attaque, on ne peut pas fuire
                AuTourDuJoueur=true;
                t = new Timer(1500, this);   // puis on lance l'attaque adverse
				t.start();
            }
        }
        
        if (e.getSource()==t2){
            pPrincipal.removeAll();          // A la fin du combat, on enleve le panneau de combat, pour remettre la carte
			JFramePrincipal.remove(pPrincipal);
            JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
			SauvegardeJeu.NouvelleSauvegarde(VariablesSession);
            fcarte= new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu);
            freeze=false;
            t2.stop();
		}
 
        if (e.getSource()==t4){
            gif.setVisible(false);  // fin de l'animation d'explosion
            t4.stop();
		}
        
        if (e.getSource()==t5){      // animation attaques
			x=x+27;    
            y=y-9;      // On décale le dessin en diagonale 
            Meloche.setLocation(x,y);  
            if (y<=15){   // Une fois arrivé sur l'autre personnage :
                x=100;
                y=135;
                Meloche.setLocation(x,y); // On le remet à la position initiale
                t5.stop();
            }
		}
        
        if (e.getSource()==t6){   // Même principe que t5 mais pour déplacer le dessin de l'adversaire
            x1=x1-27;     
            y1=y1+9;
            lab2.setLocation(x1,y1);
            if (y1>=150){
                x1=420;
                y1=30;
                lab2.setLocation(x1,y1);
                t6.stop();
            }
		}
    }
    
    public boolean attaque (int j){   // Méthode notre attaque
        boolean esquive = true;
        double prob = Math.random();   // On commence par déterminer aléatoirement si l'attaque est esquivée
        if (prob > advers.esquive){
            int pts = (int)(1.4*(perso.attaque*coeff(j, advers)*(Math.pow(perso.XP, 0.4)))/advers.defense);
            advers.PV=advers.PV - pts;  // Puis on calcule et soustrait les dégats infligés
            if (advers.PV<0){
                advers.PV=0;  // On limite les points de vie à 0 minimum
            }   
            esquive = false;
            lTextePokemonAdv.setText(advers.nom + "     XP : " + advers.XP + "     PV : " + advers.PV);
            vieAdv.setSize((int)(230.0*(double)(advers.PV)/(double)(advers.PVmax)), 5);  // On met à jour l'affichage des points de vie
        }
        t = new Timer(2000, this);  // Timer qui va lancer l'attaque de l'adversaire avant de revenir sur la notre
        t.start();
        freeze=true;
		pPrincipal.remove(pAttaque);    // On remet les boutons et panneaux pour l'attaque suivante
        pPrincipal.add(pBas);
        JFramePrincipal.revalidate();
        JFramePrincipal.repaint();
        
        if (finCombat()==true){
            lDialogue.setText("Vous venez de lui mettre le coup de grace !");
            graphAttak(true);   // Message et affichage spécial si on a gagné le combat
        }
        return esquive;   // On renvoie si l'attaque a été esquivée ou non
    }
    
    public boolean advattaque(){  // Méthode de l'attaque de l'adversaire
        boolean esquive = true;
        double prob = Math.random();   // On commence par déterminer aléatoirement si l'attaque est esquivée
        if (prob > perso.esquive){   
            int pts = (int)(1.4*(advers.attaque*coeff(advers.attaque1.type, perso)*(Math.pow(advers.XP, 0.4)))/perso.defense);
            perso.PV=perso.PV - pts;     // Puis on calcule et soustrait les dégats infligés
            if (perso.PV<0){
                perso.PV=0;    // On empeche les points de vie de devenir négatifs
            }                                   
            esquive = false;
            lTexteMonPokemon.setText(perso.nom + "     XP : " + perso.XP + "     PV : " + perso.PV);
            viePerso.setSize((int)(230.0*(double)(perso.PV)/(double)(perso.PVmax)), 5);  // On met à jour l'affichage de la vie
        }
        return esquive;   // On renvoie si l'attaque a été esquivé ou non
    }
    
    public String advAtt(){   //Méthode qui sélectionne et renvoie une attaque au hasard pour l'adversaire
        String str=" ";
        int i = (int)(4*Math.random());
        if (i==0){
            str=advers.attaque1.nom;
        } else if (i==1){
            str=advers.attaque2.nom;
        } else if (i==2){
            str=advers.attaque3.nom;
        } else if (i==3){
            str=advers.attaque4.nom;
        }
        return str;
    }
    
    public int coeff(int typeA , VEGAMONS pD){  // méthode qui détermine l'efficacité d'une attaque en fonction des types des personnages et des attaques
        int typeD= pD.type;
        int [][] coef ={{6,6,9,6},{10,6,3,8},{4,8,7,6},{6,6,8,6}};  // tableau ou chaque case représente l'éffecacité d'une attaque
        return (coef[typeD-1][typeA-1]);        // On renvoie la valeur de la case correspondante
    }
    
    public void soin(){                // Méthode pour récupérer des points de vie puis met à jour l'affichage 
        int soin=(int)(0.25*perso.PVmax);
        if(perso.PV+soin<perso.PVmax){
            perso.PV=perso.PV+soin;
        }else {
            perso.PV=perso.PVmax;    // On limite les PV pour ne pas dépasser le max
        }
        lTexteMonPokemon.setText(perso.nom + "     XP : " + perso.XP + "     PV : " + perso.PV);
        viePerso.setSize((int)(230.0*(double)(perso.PV)/(double)(perso.PVmax)), 5);   // On met à jour l'affichage
    }
    
    public boolean finCombat(){  // Méthode qui permet de savoir si le combat est fini (gagné ou perdu)
        boolean CombatFini = false;
        if(perso.PV<=0 || advers.PV<=0){  // Pour cela on test si l'un des deux perso n'a plus de PV
            CombatFini=true;
        } 
        return CombatFini;
    }
    
    public void graphAttak(boolean b2){ // méthode qui fait des animation lors des attaques 
        if (b2==true){
            t5 = new Timer(1, this);  //lance le déplacement en diagonale de l'attaquant
            t5.start();
            
            gif.setBounds(150,-110,600,300);     //explosion
            gif.setVisible(true);
            t4 = new Timer(1000, this);      // qui s'arrête après une seconde
            t4.start();
        } else {
            t6 = new Timer(1, this);   //lance le déplacement en diagonale de l'attaquant
            t6.start();
            
            gif.setBounds(-170,-10,600,300);   //explosion
            gif.setVisible(true);
            t4 = new Timer(1000, this);     // qui s'arrête après une seconde
            t4.start();
        }
    }
}
