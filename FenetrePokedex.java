import javax.swing.*;
import java.awt.event.*;

public class FenetrePokedex implements ActionListener{
    
    private JLabel Meloche, Aigloss, Murenss, Anemoniac, Sunfure, Pandalame, mel, aig, sun, ane, pan, mur, imageFond, lRetour;
    private JButton pok1, pok2, pok3, pok4, pok5, pok6, retour;
    private JPanel pPrincipal;
	private CJframe jFramePrincipal; 
	private VariablesDeJeu variablesSession; 
	private Sauvegarde sauvegardeJeu;
    
	public FenetrePokedex( CJframe Frame,VariablesDeJeu variables, Sauvegarde sauvegarde) {
		
		jFramePrincipal= Frame;
		variablesSession=variables;
		sauvegardeJeu=sauvegarde;
        
        pPrincipal = new JPanel();
        pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
        jFramePrincipal.add(pPrincipal);
		
        pok1 = new JButton();            // Les 6 boutons pok i correspondent aux différents vegamons
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
        
        retour = new JButton();          // Pour retourner à la page d'accueil
        retour.setBounds(325, 600, 150, 70);
        pPrincipal.add(retour);
        retour.setLayout(null);
        retour.addActionListener(this);
        
                                //  On affiche les dessins de chaque vegamon sur le bouton correspondant
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
    
                                // On affiche le nom du vegamon correspondant à l'image sur chaque bouton
        mel = new JLabel (variablesSession.pokedex.get(0).nom);
        mel.setBounds (120, 20, 700, 60);
        pok1.add(mel);
        
        aig = new JLabel (variablesSession.pokedex.get(1).nom);
        aig.setBounds (120, 20, 700, 60);
        pok2.add(aig);
        
        ane = new JLabel (variablesSession.pokedex.get(2).nom);
        ane.setBounds (120, 20, 700, 60);
        pok3.add(ane);
        
        sun = new JLabel (variablesSession.pokedex.get(3).nom);
        sun.setBounds (120, 20, 700, 60);
        pok4.add(sun);
        
        mur = new JLabel (variablesSession.pokedex.get(4).nom);
        mur.setBounds (120, 20, 700, 60);
        pok5.add(mur);
        
        pan = new JLabel (variablesSession.pokedex.get(5).nom);
        pan.setBounds (120, 20, 700, 60);
        pok6.add(pan);
        
        lRetour = new JLabel ("RETOUR");
        lRetour.setBounds (50, 5, 700, 60);
        retour.add(lRetour);
        
        imageFond = new JLabel(new ImageIcon("Images/fond.png")); 
		imageFond.setBounds(0,0,800,820);
		pPrincipal.add(imageFond);
		jFramePrincipal.setVisible(true);
    }
        
    public void actionPerformed(ActionEvent e){
 
        // En cliquant sur un vegamon, on ouvre une petite fenetre qui donnera des informations supplémentaire sur ce vegamon
        if (e.getSource()==pok1){
            new FenetrePoke(0, variablesSession);
        } else if (e.getSource()==pok2){
            new FenetrePoke(1, variablesSession);
        } else if (e.getSource()==pok3){
            new FenetrePoke(2, variablesSession);
        } else if (e.getSource()==pok4){
            new FenetrePoke(3, variablesSession);
        } else if (e.getSource()==pok5){
            new FenetrePoke(4, variablesSession);
        } else if (e.getSource()==pok6){
            new FenetrePoke(5, variablesSession);
        } 
        else if (e.getSource()==retour){   
			pPrincipal.removeAll();
			jFramePrincipal.remove(pPrincipal);    // Ou sur retour on revient à l'accueil
			jFramePrincipal.revalidate();
			jFramePrincipal.repaint();
			new Accueil(jFramePrincipal, variablesSession, sauvegardeJeu);
        }
	}
}
