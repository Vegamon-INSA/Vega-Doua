import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class FenetrePokedex implements ActionListener{
    
    private JLabel Meloche, Aigloss, Murenss, Anemoniac, Sunfure, Pandalame, mel, aig, sun, ane, pan, mur, imageFond, lRetour;
    private JButton pok1, pok2, pok3, pok4, pok5, pok6, retour;
    private JPanel pPrincipal;
	private CJframe JFramePrincipal; 
	private VariablesDeJeu VariablesSession; 
	private Sauvegarde SauvegardeJeu;
	private Musiques MusiqueDeJeu;
    
	public FenetrePokedex( CJframe Frame,VariablesDeJeu variables, Sauvegarde sauvegarde, Musiques musique) {
		
		JFramePrincipal= Frame;
		VariablesSession=variables;
		SauvegardeJeu=sauvegarde;
		MusiqueDeJeu=musique;
        
        pPrincipal = new JPanel();
        pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
        JFramePrincipal.add(pPrincipal);
		
        pok1 = new JButton();
        pok1.setBounds(175, 250, 200, 90);
        pok1.setLayout(null);
        pok1.addActionListener(this);
        pPrincipal.add(pok1);

        pok2 = new JButton();
        pok2.setBounds(175, 350, 200, 90);
        pPrincipal.add(pok2);
        pok2.setLayout(null);
        pok2.addActionListener(this);
        
        pok3 = new JButton();
        pok3.setBounds(175, 450, 200, 90);
        pPrincipal.add(pok3);
        pok3.setLayout(null);
        pok3.addActionListener(this);
        
        pok4 = new JButton();
        pok4.setBounds(425, 250, 200, 90);
        pPrincipal.add(pok4);
        pok4.setLayout(null);
        pok4.addActionListener(this);
        
        pok5 = new JButton();
        pok5.setBounds(425, 350, 200, 90);
        pPrincipal.add(pok5);
        pok5.setLayout(null);
        pok5.addActionListener(this);
        
        pok6 = new JButton();
        pok6.setBounds(425, 450, 200, 90);
        pPrincipal.add(pok6);
        pok6.setLayout(null);
        pok6.addActionListener(this);
        
        retour = new JButton();
        retour.setBounds(325, 600, 150, 70);
        pPrincipal.add(retour);
        retour.setLayout(null);
        retour.addActionListener(this);
        
        
        Meloche = new JLabel(new ImageIcon("Images/Meloche.png")); 
        Meloche.setBounds(0,-5,100,100);
        pok1.add(Meloche);
        
        Aigloss = new JLabel(new ImageIcon("Images/Aigloss.png")); 
        Aigloss.setBounds(0,-5,100,100);
        pok2.add(Aigloss);
        
        Anemoniac = new JLabel(new ImageIcon("Images/Anemoniac.png")); 
        Anemoniac.setBounds(0,-5,100,100);
        pok3.add(Anemoniac);
        
        Sunfure = new JLabel(new ImageIcon("Images/Sunfure.png")); 
        Sunfure.setBounds(0,-5,100,100);
        pok4.add(Sunfure);
        
        Murenss = new JLabel(new ImageIcon("Images/Murenss.png")); 
        Murenss.setBounds(0,-5,100,100);
        pok5.add(Murenss);
        
        Pandalame = new JLabel(new ImageIcon("Images/Pandalame.png")); 
        Pandalame.setBounds(0,-5,100,100);
        pok6.add(Pandalame);
        
        mel = new JLabel (VariablesSession.pokedex.get(0).nom);
        mel.setBounds (120, 20, 700, 60);
        pok1.add(mel);
        
        aig = new JLabel (VariablesSession.pokedex.get(1).nom);
        aig.setBounds (120, 20, 700, 60);
        pok2.add(aig);
        
        ane = new JLabel (VariablesSession.pokedex.get(2).nom);
        ane.setBounds (120, 20, 700, 60);
        pok3.add(ane);
        
        sun = new JLabel (VariablesSession.pokedex.get(3).nom);
        sun.setBounds (120, 20, 700, 60);
        pok4.add(sun);
        
        mur = new JLabel (VariablesSession.pokedex.get(4).nom);
        mur.setBounds (120, 20, 700, 60);
        pok5.add(mur);
        
        pan = new JLabel (VariablesSession.pokedex.get(5).nom);
        pan.setBounds (120, 20, 700, 60);
        pok6.add(pan);
        
        lRetour = new JLabel ("RETOUR");
        lRetour.setBounds (50, 5, 700, 60);
        retour.add(lRetour);
        
        imageFond = new JLabel(new ImageIcon("Images/fond.png")); 
		imageFond.setBounds(0,0,800,820);
		pPrincipal.add(imageFond);
		JFramePrincipal.setVisible(true);
    }
        
    public void actionPerformed(ActionEvent e){
 
        if (e.getSource()==pok1){
            FenetrePoke Poke=new FenetrePoke(0, VariablesSession);
        } else if (e.getSource()==pok2){
            FenetrePoke Poke=new FenetrePoke(1, VariablesSession);
        } else if (e.getSource()==pok3){
            FenetrePoke Poke=new FenetrePoke(2, VariablesSession);
        } else if (e.getSource()==pok4){
            FenetrePoke Poke=new FenetrePoke(3, VariablesSession);
        } else if (e.getSource()==pok5){
            FenetrePoke Poke=new FenetrePoke(4, VariablesSession);
        } 
        else if (e.getSource()==retour){
			pPrincipal.removeAll();
			JFramePrincipal.remove(pPrincipal);
			JFramePrincipal.revalidate();
			JFramePrincipal.repaint();
			Accueil ecranAccueil= new Accueil(JFramePrincipal,VariablesSession,SauvegardeJeu,MusiqueDeJeu);
        }
	}
}
