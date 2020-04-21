import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class FenetrePokedex extends JFrame implements ActionListener{
    
    private ArrayList<VEGAMONS> pokedex;
    private JLabel Meloche, Aigloss, Murenss, Anemoniac, Sunfure, Pandalame, mel, aig, sun, ane, pan, mur;
    private JButton pok1, pok2, pok3, pok4, pok5, pok6;
    public FenetrePoke Poke;


	public FenetrePokedex(ArrayList<VEGAMONS> lePokedex){
		this.setTitle("Pokedex");
		this.setSize(1000,1000);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(false);
        
        pokedex=lePokedex;
        
        pok1 = new JButton();
        pok1.setBounds(100, 0, 200, 90);
        this.add(pok1);
        pok1.setLayout(null);
        pok1.addActionListener(this);
        
        pok2 = new JButton();
        pok2.setBounds(100, 100, 200, 90);
        this.add(pok2);
        pok2.setLayout(null);
        pok2.addActionListener(this);
        
        pok3 = new JButton();
        pok3.setBounds(100, 200, 200, 90);
        this.add(pok3);
        pok3.setLayout(null);
        pok3.addActionListener(this);
        
        pok4 = new JButton();
        pok4.setBounds(100, 300, 200, 90);
        this.add(pok4);
        pok4.setLayout(null);
        pok4.addActionListener(this);
        
        pok5 = new JButton();
        pok5.setBounds(100, 400, 200, 90);
        this.add(pok5);
        pok5.setLayout(null);
        pok5.addActionListener(this);
        
        pok6 = new JButton();
        pok6.setBounds(100, 500, 200, 90);
        this.add(pok6);
        pok6.setLayout(null);
        pok6.addActionListener(this);
        
        
        
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
        
        
        mel = new JLabel (pokedex.get(0).nom);
        mel.setBounds (120, 20, 700, 60);
        pok1.add(mel);
        
        aig = new JLabel (pokedex.get(1).nom);
        aig.setBounds (120, 20, 700, 60);
        pok2.add(aig);
        
        ane = new JLabel (pokedex.get(2).nom);
        ane.setBounds (120, 20, 700, 60);
        pok3.add(ane);
        
        sun = new JLabel (pokedex.get(3).nom);
        sun.setBounds (120, 20, 700, 60);
        pok4.add(sun);
        
        mur = new JLabel (pokedex.get(4).nom);
        mur.setBounds (120, 20, 700, 60);
        pok5.add(mur);
        
        pan = new JLabel (pokedex.get(5).nom);
        pan.setBounds (120, 20, 700, 60);
        pok6.add(pan);
    }
        
    public void actionPerformed(ActionEvent e){
 
        if (e.getSource()==pok1){
            Poke=new FenetrePoke(0, pokedex);
        } else if (e.getSource()==pok2){
            Poke=new FenetrePoke(1, pokedex);
        } else if (e.getSource()==pok3){
            Poke=new FenetrePoke(2, pokedex);
        } else if (e.getSource()==pok4){
            Poke=new FenetrePoke(3, pokedex);
        } else if (e.getSource()==pok5){
            Poke=new FenetrePoke(4, pokedex);
        } else if (e.getSource()==pok6){
            Poke=new FenetrePoke(5, pokedex);
        }
        Poke.setVisible(true);
	}
}
