import javax.swing.*;
import java.awt.event.*;

public class FenetrePokedex implements ActionListener{
    
    private JLabel meloche, aigloss, murenss, anemoniac, sunfure, pandalame, mel, aig, sun, ane, pan, mur, imageFond, lRetour;
    private JButton vegamon1, vegamon2, vegamon3, vegamon4, vegamon5, vegamon6, retour;
    private JPanel pPrincipal;
	private CJframe jFramePrincipal; 
	private VariablesDeJeu variablesSession; 
	private Sauvegarde sauvegardeJeu;
    
	public FenetrePokedex( CJframe frame,VariablesDeJeu variables, Sauvegarde sauvegarde) {
		
		jFramePrincipal= frame;
		variablesSession=variables;
		sauvegardeJeu=sauvegarde;
        
        pPrincipal = new JPanel();
        pPrincipal.setBounds(0, 0, 800, 800);
		pPrincipal.setLayout(null);
        jFramePrincipal.add(pPrincipal);
		
        vegamon1 = new JButton();            // Les 6 boutons pok i correspondent aux différents vegamons
        vegamon1.setBounds(175, 250, 200, 90);
        vegamon1.setLayout(null);
        vegamon1.addActionListener(this);
        pPrincipal.add(vegamon1);

        vegamon2 = new JButton();
        vegamon2.setBounds(175, 350, 200, 90);
        pPrincipal.add(vegamon2);
        vegamon2.setLayout(null);
        vegamon2.addActionListener(this);
        
        vegamon3 = new JButton();
        vegamon3.setBounds(175, 450, 200, 90);
        pPrincipal.add(vegamon3);
        vegamon3.setLayout(null);
        vegamon3.addActionListener(this);
        
        vegamon4 = new JButton();
        vegamon4.setBounds(425, 250, 200, 90);
        pPrincipal.add(vegamon4);
        vegamon4.setLayout(null);
        vegamon4.addActionListener(this);
        
        vegamon5 = new JButton();
        vegamon5.setBounds(425, 350, 200, 90);
        pPrincipal.add(vegamon5);
        vegamon5.setLayout(null);
        vegamon5.addActionListener(this);
        
        vegamon6 = new JButton();
        vegamon6.setBounds(425, 450, 200, 90);
        pPrincipal.add(vegamon6);
        vegamon6.setLayout(null);
        vegamon6.addActionListener(this);
        
        retour = new JButton();          // Pour retourner à la page d'accueil
        retour.setBounds(325, 600, 150, 70);
        pPrincipal.add(retour);
        retour.setLayout(null);
        retour.addActionListener(this);
        
                                //  On affiche les dessins de chaque vegamon sur le bouton correspondant
        meloche = new JLabel(new ImageIcon("Images/Meloche-face.png")); 
        meloche.setBounds(0,-5,100,100);
        vegamon1.add(meloche);
        
        aigloss = new JLabel(new ImageIcon("Images/Aigloss.png")); 
        aigloss.setBounds(0,-5,100,100);
        vegamon2.add(aigloss);
        
        anemoniac = new JLabel(new ImageIcon("Images/Anemoniac.png")); 
        anemoniac.setBounds(0,-5,100,100);
        vegamon5.add(anemoniac);
        
        sunfure = new JLabel(new ImageIcon("Images/Sunfure.png")); 
        sunfure.setBounds(0,-5,100,100);
        vegamon4.add(sunfure);
        
        murenss = new JLabel(new ImageIcon("Images/Murenss.png")); 
        murenss.setBounds(0,-5,100,100);
        vegamon3.add(murenss);
        
        pandalame = new JLabel(new ImageIcon("Images/Pandalame.png")); 
        pandalame.setBounds(0,-5,100,100);
        vegamon6.add(pandalame);
    
                                // On affiche le nom du vegamon correspondant à l'image sur chaque bouton
        mel = new JLabel (variablesSession.pokedex.get(0).nom);
        mel.setBounds (120, 20, 700, 60);
        vegamon1.add(mel);
        
        aig = new JLabel (variablesSession.pokedex.get(1).nom);
        aig.setBounds (120, 20, 700, 60);
        vegamon2.add(aig);
        
        ane = new JLabel (variablesSession.pokedex.get(2).nom);
        ane.setBounds (120, 20, 700, 60);
        vegamon3.add(ane);
        
        sun = new JLabel (variablesSession.pokedex.get(3).nom);
        sun.setBounds (120, 20, 700, 60);
        vegamon4.add(sun);
        
        mur = new JLabel (variablesSession.pokedex.get(4).nom);
        mur.setBounds (120, 20, 700, 60);
        vegamon5.add(mur);
        
        pan = new JLabel (variablesSession.pokedex.get(5).nom);
        pan.setBounds (120, 20, 700, 60);
        vegamon6.add(pan);
        
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
        if (e.getSource()==vegamon1){
            new FenetrePoke(0, variablesSession);
        } else if (e.getSource()==vegamon2){
            new FenetrePoke(1, variablesSession);
        } else if (e.getSource()==vegamon3){
            new FenetrePoke(2, variablesSession);
        } else if (e.getSource()==vegamon4){
            new FenetrePoke(3, variablesSession);
        } else if (e.getSource()==vegamon5){
            new FenetrePoke(4, variablesSession);
        } else if (e.getSource()==vegamon6){
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
