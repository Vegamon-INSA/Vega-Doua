import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FenetrePoke extends JFrame{
    
    private ArrayList<VEGAMONS> pokedex;
    private JLabel poke, nom, attaque, defense, esquive, type, attaques;

	public FenetrePoke(int i, VariablesDeJeu variablesSession){
		this.setTitle(variablesSession.pokedex.get(i).nom);
		this.setSize(600,400);
		// Pour placer la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setLayout(null);	
        
        if (i==0){
            poke = new JLabel(new ImageIcon("Images/Meloche.png"));
        } else if (i==1){
            poke = new JLabel(new ImageIcon("Images/Aigloss.png"));
        } else if (i==2){
            poke = new JLabel(new ImageIcon("Images/Anemoniac.png"));
        } else if (i==3){
            poke = new JLabel(new ImageIcon("Images/Sunfure.png"));
        } else if (i==4){
            poke = new JLabel(new ImageIcon("Images/Murenss.png"));
        } else if (i==5){
            poke = new JLabel(new ImageIcon("Images/Pandalame.png"));
        }
        poke.setBounds(70,90,100,100);
        this.add(poke);
        
        nom = new JLabel("nom :  "+variablesSession.pokedex.get(i).nom);
        nom.setBounds(300, 30, 300, 50);
        this.add(nom);
        
        attaque = new JLabel("attaque :  "+variablesSession.pokedex.get(i).attaque);
        attaque.setBounds(300, 70, 300, 50);
        this.add(attaque);
        
        defense = new JLabel("defense :  "+variablesSession.pokedex.get(i).defense);
        defense.setBounds(300, 110, 300, 50);
        this.add(defense);
        
        esquive = new JLabel("esquive :  "+100*variablesSession.pokedex.get(i).esquive);
        esquive.setBounds(300, 150, 300, 50);
        this.add(esquive);
        
        type = new JLabel("type :  "+getType(i,variablesSession));
        type.setBounds(300, 190, 300, 50);
        this.add(type);
        
        attaques = new JLabel("attaques :  "+variablesSession.pokedex.get(i).attaque1.nom + ", "+variablesSession.pokedex.get(i).attaque2.nom + ", "+variablesSession.pokedex.get(i).attaque3.nom + ", "+variablesSession.pokedex.get(i).attaque4.nom + ", ");
        attaques.setBounds(150, 240, 400, 50);
        this.add(attaques);
          		this.setVisible(true);
	}
    
    public String getType (int i, VariablesDeJeu variablesSession ){
        String str=" ";
        int t = variablesSession.pokedex.get(i).type;
        if (t==1){
            str = "feu";
        } else if (t==2){
            str = "plante";
        } else if (t==3){
            str = "eau";
        } else if (t==4){
            str = "electricite";
        }
        return str; 
    }
}
