import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class FenetreCombat implements ActionListener{
	 
    private JPanel pHaut,pBas,pDialogue,pTextePokemonAdv,pTexteMonPokemon,pAttaque,viePerso, vieAdv, pPrincipal;
    private JLabel fond,lDialogue,lTextePokemonAdv,lTexteMonPokemon,lab2,Meloche,gif;
    private JButton attaque, soin,fuite,a1,a2,a3,a4,annuler;
    private Timer t,t2, t3, t4;
    private boolean freeze,AuTourDuJoueur;
    private VEGAMONS perso, advers, pA, pD;
    private int x, y, x1, y1,j,exp,type, numCase, PVmaxAdvIni;
    private ArrayList<VEGAMONS> pokedex; 
    private ArrayList<String> textes;
	private CJframe JFramePrincipal; //Jframe principal avec image de fond
	private VariablesDeJeu VariablesSession; //Variables de Jeu
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu= new Musiques();
	    
    public FenetreCombat(CJframe Frame, VariablesDeJeu variables,Sauvegarde sauvegarde,Musiques musique, int numeroCase){

		JFramePrincipal= Frame;
		VariablesSession=variables;
		SauvegardeJeu=sauvegarde;
		MusiqueDeJeu=musique;
		freeze = false;
        pokedex=VariablesSession.pokedex;
        textes=VariablesSession.textesCombats;
        perso = pokedex.get(0);
        numCase=numeroCase;
        x=100;
        y=135; // coordonnées des icons des pokemons
        x1=420;
        y1=30;
        

        ///INTERFACE COMBAT

        pPrincipal = new JPanel();
        pPrincipal.setBounds(100, 70, 600, 600);
		pPrincipal.setLayout(null);
        JFramePrincipal.add(pPrincipal);

        pHaut = new JPanel();
        pHaut.setBounds(0, 0, 600, 300);
		pHaut.setLayout(null);
        pPrincipal.add(pHaut);
        
        pBas = new JPanel();
        pBas.setBounds(0,300, 600, 300);
        pBas.setBackground(Color.gray);
        pBas.setLayout(null);
		pPrincipal.add(pBas);
		
		pAttaque = new JPanel();
        pAttaque.setBounds(0, 300, 600, 300);
        pAttaque.setBackground(Color.gray); 
        pAttaque.setLayout(null);

        attaque = new JButton("Attaque");
        attaque.setBounds(150, 50, 300, 100);
        attaque.setBackground(Color.red);
        pBas.add(attaque);
        attaque.addActionListener(this);
        
        soin = new JButton("Soin");
        soin.setBounds(150, 175, 130, 80);
        soin.setBackground(new Color(135, 211, 0));
        pBas.add(soin);
        soin.addActionListener(this);

        fuite = new JButton("Fuite");
        fuite.setBounds(320, 175, 130, 80);
        fuite.setBackground(new Color (0, 196, 220));
        pBas.add(fuite);
        fuite.addActionListener(this);
       
		pDialogue = new JPanel();
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
        
        fond = new JLabel(new ImageIcon("Images/interface.png")); 
        fond.setBounds(0,0,600,300);
        pHaut.add(fond);
        
        Meloche = new JLabel(new ImageIcon("Images/Meloche.png")); 
        Meloche.setBounds(x,y,80,100);
        fond.add(Meloche);
        
        gif = new JLabel(new ImageIcon("Images/explosion.gif")); 
        gif.setBounds(150,-80,600,300);
        fond.add(gif);
        gif.setVisible(false);
        
        lDialogue= new JLabel("Que dois-je faire ?" );
        lDialogue.setBounds(0,0,540,50);
        lDialogue.setBackground(Color.red);
        pDialogue.add(lDialogue);
        
        lTextePokemonAdv = new JLabel(" " );
        lTextePokemonAdv.setBounds(50,-10,268, 45);
        lTextePokemonAdv.setBackground(Color.white); 
        pTextePokemonAdv.add(lTextePokemonAdv);
        
        lTexteMonPokemon = new JLabel(perso.nom + "     XP : " + perso.XP + "     PV : " + perso.PV);
        lTexteMonPokemon.setBounds(20,-10,268, 45);
        lTexteMonPokemon.setBackground(Color.white);
        pTexteMonPokemon.add(lTexteMonPokemon);
         
        a1 = new JButton("feu :  "+perso.attaque1.nom); 
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
        
        viePerso = new JPanel();
        viePerso.setLocation(20, 33);
        viePerso.setSize((int)(230.0*(double)(perso.PV)/(double)(perso.PVmax)), 5);
        viePerso.setBackground(Color.green);
        pTexteMonPokemon.add(viePerso);
        
        vieAdv = new JPanel();
        vieAdv.setLocation(20, 33);
        vieAdv.setBackground(Color.green);
        pTextePokemonAdv.add(vieAdv);
       
        JFramePrincipal.setVisible(true); 

        debutCombat(numCase);

	}
	
	 
	public void debutCombat(int x){
		
        AuTourDuJoueur=false;
        perso.PV=perso.PVmax;
        int exp=1;
        if (x==5 || x==3){
            if (VariablesSession.numeroCarte==105 || VariablesSession.numeroCarte==301){      //trav1
                exp=1+(int)(2*Math.random());
            }else if (VariablesSession.numeroCarte==202 || VariablesSession.numeroCarte==401){   //trav2
                exp=5+(int)(5*Math.random());
            }else if (VariablesSession.numeroCarte==414 || VariablesSession.numeroCarte==601){   // huma1
                exp=15+(int)(10*Math.random());
            }else if (VariablesSession.numeroCarte==502 || VariablesSession.numeroCarte==701){   //huma2
                exp=25+(int)(14*Math.random());
            }
        } else if (x/10==1){
            exp = 8 + 2*(x%10);
        } else if (x/10==2){
            exp = 36 + 4*(x%10);
        }
        int num = 1+((int)(5*Math.random()));
        advers = pokedex.get(num);
        if (num==1){
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
        lab2.setBounds(x1,y1,100,100);
        advers.XP = exp;
        PVmaxAdvIni=advers.PVmax;
        advers.PVmax = (int)(Math.pow(exp, 0.3)*advers.PVmax)+(int)(Math.pow(exp, 1.2));
        advers.PV = advers.PVmax;
        lTextePokemonAdv.setText(advers.nom + "     XP : " + advers.XP + "     PV : " + advers.PV);
        vieAdv.setSize((int)(230.0*(double)(advers.PV)/(double)(advers.PVmax)), 5);
	}
    
    public void actionPerformed(ActionEvent e){
		//ATTAQUE
        if (e.getSource()==attaque && !freeze){
            System.out.println("Attaque");
            pPrincipal.remove(pBas);
			pPrincipal.add(pAttaque);
			pPrincipal.revalidate();
			pPrincipal.repaint();
        }
        
        boolean esquive;
        boolean fin=false;
        
        if (e.getSource()==a1 && !freeze){
            esquive = attaque(1);
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque1.nom+" !");
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque1.nom+" !    "+advers.nom+" esquive !");
            }
            t = new Timer(2000, this);
			t.start();
			freeze=true;
        }
        
        if (e.getSource()==a2 && !freeze){
            esquive = attaque(2);
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque2.nom+" !");
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque2.nom+" !	  "+advers.nom+" esquive !");
            }
            t = new Timer(2000, this);
			t.start();
			freeze=true;
        }
        
        if (e.getSource()==a3 && !freeze){
            esquive = attaque(3);
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque3.nom+" !"); 
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque3.nom+" !	   "+advers.nom+" esquive !");
            }
            t = new Timer(2000, this);
			t.start();
			freeze=true;
        }
        
        if (e.getSource()==a4 && !freeze){
            esquive = attaque(4);
            if (esquive==false && finCombat()==false){
                lDialogue.setText(perso.nom+" attaque avec "+perso.attaque4.nom+" !");
                graphAttak(true);
            }else if (esquive==true && finCombat()==false){
               lDialogue.setText(perso.nom+" attaque avec "+perso.attaque4.nom+" !  	"+advers.nom+" esquive !");
            }
            t = new Timer(2000, this);
			t.start();
			freeze=true;
        }
        
        if (e.getSource()==t) { //AuTourDuJoueur=true => a nous de jouer, AuTourDuJoueur=false, on a deja choisi notre attaque
            fin=finCombat();
			if (AuTourDuJoueur==true){
                t.stop();
                freeze=false;
				pPrincipal.remove(pAttaque);
                pPrincipal.add(pBas);
                pPrincipal.revalidate();
				pPrincipal.repaint();
                lDialogue.setText("Que dois-je faire ?");
                AuTourDuJoueur=false;
            }
            else{
                if(advers.PV<=0){
                    
                    if (numCase%100==3){
                        VariablesSession.listeInterractionsAvecDresseurs [numCase-301]=2;
                    }
                    t.stop();
                    advers.PVmax=PVmaxAdvIni;
                    t2 = new Timer(1000, this);
                    t2.start();
                    freeze=true;
                    perso.XP=perso.XP + (int)(Math.pow(advers.XP, 0.6));
                    perso.PVmax = (int)((Math.pow(perso.XP, 0.31)*40)+Math.pow(perso.XP, 1.21));
                    perso.PV=perso.PVmax;
                    VariablesSession.xpMeloche=perso.XP;

                } 
                else {
                    esquive=advattaque();
                    if (finCombat()==true){
                        if (numCase%100==3){
                            VariablesSession.listeInterractionsAvecDresseurs [numCase-301]=1;
                        }
                        lDialogue.setText("Vous avez perdu !");
                        advers.PVmax=PVmaxAdvIni;
                        perso.PV=perso.PVmax; 
                        t2 = new Timer(1900, this);
                        t2.start();
                        freeze=true;
                    } else if (esquive==false){
                        lDialogue.setText(advers.nom+" attaque avec "+advAtt()+" !");
                        graphAttak(false);
                        AuTourDuJoueur=true;
                    }else{
                        lDialogue.setText(advers.nom+" attaque avec "+advAtt()+" !  	"+perso.nom+" esquive !");
                        AuTourDuJoueur=true;
                    }
                }
            }
		}
		
        if (e.getSource()==annuler && !freeze){
			pPrincipal.remove(pAttaque);
			pPrincipal.add(pBas);
			pPrincipal.revalidate();
			pPrincipal.repaint();
        }
        
        if (e.getSource()==fuite && !freeze){
            if (numCase==5){
                perso.PV=perso.PVmax;
                advers.PVmax=PVmaxAdvIni;
                lDialogue.setText("Vous prenez la fuite !");			
                t2 = new Timer(2000, this);
                t2.start();
                freeze=true;
            } else {
                lDialogue.setText("Impossible de s'échapper !");
            }
        }
        
        if (e.getSource()==t2){
            pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
            JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
            fenetreCarte Map0= new fenetreCarte(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu);

            freeze=false;
            t2.stop();
		}
 
        
        
        if (e.getSource()==t4){
            gif.setVisible(false);
            t4.stop();
		}

        if (e.getSource()==soin && !freeze){
            soin();
			lDialogue.setText(perso.nom+" se soigne !");
			t = new Timer(2000, this);
			t.start();
			freeze=true;
        }
    }
    
    public boolean attaque (int j){
        boolean esquive = true;
        double prob = Math.random();
        if (prob > advers.esquive){
            int pts = (int)(1.5*(perso.attaque*coeff(j, advers)*(Math.pow(perso.XP, 0.35)))/advers.defense);
            advers.PV=advers.PV - pts;
            if (advers.PV<0){
                advers.PV=0;
            }   
            esquive = false;
            lTextePokemonAdv.setText(advers.nom + "     XP : " + advers.XP + "     PV : " + advers.PV);
            vieAdv.setSize((int)(230.0*(double)(advers.PV)/(double)(advers.PVmax)), 5);
        }
        t = new Timer(2000, this);
        t.start();
        freeze=true;
		pPrincipal.remove(pAttaque);
        pPrincipal.add(pBas);
        JFramePrincipal.revalidate();
        JFramePrincipal.repaint();
        
        if (finCombat()==true){
            lDialogue.setText("Vous venez de lui mettre le coup de grace !");
            graphAttak(true);
        }

        return esquive;
    }
    
    public boolean advattaque(){
        boolean esquive = true;
        double prob = Math.random();
        if (prob > perso.esquive){
            int pts = (int)(1.5*(advers.attaque*coeff(advers.attaque1.type, perso)*(Math.pow(advers.XP, 0.35)))/perso.defense);
            perso.PV=perso.PV - pts;
            if (perso.PV<0){
                perso.PV=0;
            }                                   
            esquive = false;
            lTexteMonPokemon.setText(perso.nom + "     XP : " + perso.XP + "     PV : " + perso.PV);
            viePerso.setSize((int)(230.0*(double)(perso.PV)/(double)(perso.PVmax)), 5);
        }
        return esquive;
    }
    
    public String advAtt(){
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
    
    public int coeff(int typeA , VEGAMONS pD){
        int typeD= pD.type;
        int [][] coef ={{6,6,9,6},{10,6,3,8},{4,8,7,6},{6,6,8,6}};
        return (coef[typeD-1][typeA-1]);
    }
    
    public void soin(){
        int soin=(int)(0.25*perso.PVmax);
        if(perso.PV+soin<perso.PVmax){
            perso.PV=perso.PV+soin;
        }else {
            perso.PV=perso.PVmax;
        }
        lTexteMonPokemon.setText(perso.nom + "     XP : " + perso.XP + "     PV : " + perso.PV);
        viePerso.setSize((int)(230.0*(double)(perso.PV)/(double)(perso.PVmax)), 5);
    }
    
    
    
    public boolean finCombat(){
        boolean CombatFini = false;
        if(perso.PV<=0 || advers.PV<=0){
            CombatFini=true;
        } 
        return CombatFini;
    }
    
    public void graphAttak(boolean b2){ // b==true notre attaque / b==false attaque adverse
        if (b2==true){
            gif.setBounds(150,-110,600,300);
            gif.setVisible(true);
            t4 = new Timer(1000, this);
            t4.start();
        } else {
            gif.setBounds(-170,-10,600,300);
            gif.setVisible(true);
            t4 = new Timer(1000, this);
            t4.start();
        }
    }
}
