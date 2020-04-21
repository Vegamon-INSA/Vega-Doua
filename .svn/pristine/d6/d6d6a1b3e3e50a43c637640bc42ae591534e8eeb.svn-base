import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreAide extends JFrame implements ActionListener{
	private JButton adep, astory;
	public FenetreAdep Adep;
	public FenetreStory Astory;

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

        astory = new JButton("Scenario du jeu");
        astory.setBounds(200, 200, 200, 90);
        this.add(astory);
        astory.setLayout(null);
        astory.addActionListener(this);	
        	
        this.setVisible(true);
		
	}
		public void actionPerformed (ActionEvent e){

		if (e.getSource()==adep){
			Adep = new FenetreAdep();
			Adep.setVisible(true);

		}
		if (e.getSource()==astory){
			Astory = new FenetreStory();
			Astory.setVisible(true);
		}
		
	   
	}
}
