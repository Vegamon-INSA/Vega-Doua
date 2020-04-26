import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreAide extends JFrame implements ActionListener{
	private JButton adep, aintro, aarene1, aarene2;
	public FenetreAdep Adep;
	public FenetreIntro Aintro;
	public FenetreAarene1 Aarene1;
	public FenetreAarene2 Aarene2;

	public FenetreAide(){
		this.setTitle("Aide");
		this.setSize(600,620);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);		
		this.setVisible(false);
		
        adep = new JButton("Comment se deplacer");
        adep.setBounds(200, 50, 200, 90);
        this.add(adep);
        adep.setLayout(null);
        adep.addActionListener(this);

        aintro = new JButton("Introduction");
        aintro.setBounds(200, 175, 200, 90);
        this.add(aintro);
        aintro.setLayout(null);
        aintro.addActionListener(this);	
        
        aarene1 = new JButton("Arene 1");
        aarene1.setBounds(200, 310, 200, 90);
        this.add(aarene1);
        aarene1.setLayout(null);
        aarene1.addActionListener(this);
        	
        aarene2 = new JButton("Arene 2");
        aarene2.setBounds(200, 435, 200, 90);
        this.add(aarene2);
        aarene2.setLayout(null);
        aarene2.addActionListener(this);
        	
        	
        this.setVisible(true);
		
	}
		public void actionPerformed (ActionEvent e){

		if (e.getSource()==adep){
			Adep = new FenetreAdep();
			Adep.setVisible(true);

		}
		if (e.getSource()==aintro){
			Aintro = new FenetreIntro();
			Aintro.setVisible(true);
		}
		if (e.getSource()==aarene1){
			Aarene1 = new FenetreAarene1();
			Aarene1.setVisible(true);
		}
		if (e.getSource()==aarene2){
			Aarene2 = new FenetreAarene2();
			Aarene2.setVisible(true);
		}
		
	   
	}
}
