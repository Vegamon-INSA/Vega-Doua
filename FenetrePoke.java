import javax.swing.*;

public class FenetrePoke extends JFrame{

    private static final long serialVersionUID = 1L;
    private JLabel poke, nom, attaque, defense, esquive, type, attaques;

	public FenetrePoke(int i, VariablesDeJeu variablesSession){
		this.setTitle(variablesSession.pokedex.get(i).nom);   // On crée une nouvelle petite fenetre
		this.setSize(600,400);
		this.setLocationRelativeTo(null);  // Pour placer la fenêtre au centre de l'écran
		this.setLayout(null);	
                                        // i correspondant au numéro du végamon
        if (i==0){                          // On ajoute l'image du vegamon
            poke = new JLabel(new ImageIcon("Images/Meloche-face.png"));
        } else if (i==1){
            poke = new JLabel(new ImageIcon("Images/Aigloss.png"));
        } else if (i==2){
            poke = new JLabel(new ImageIcon("Images/Murenss.png"));
        } else if (i==3){
            poke = new JLabel(new ImageIcon("Images/Sunfure.png"));
        } else if (i==4){
            poke = new JLabel(new ImageIcon("Images/Anemoniac.png"));
        } else if (i==5){
            poke = new JLabel(new ImageIcon("Images/Pandalame.png"));
        }
        poke.setBounds(60,80,120,120);
        this.add(poke);
        
        nom = new JLabel("nom :  "+variablesSession.pokedex.get(i).nom);  // On indique le nom, 
        nom.setBounds(300, 30, 300, 50);   // et le niveau d'attaque, de défense, le taux d'esquive
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
        
        type = new JLabel("type :  "+getType(i,variablesSession));  // On indique le type du vegamon
        type.setBounds(300, 190, 300, 50);
        this.add(type);
        
        attaques = new JLabel("attaques :  "+variablesSession.pokedex.get(i).attaque1.nom + ", "+variablesSession.pokedex.get(i).attaque2.nom + ", "+variablesSession.pokedex.get(i).attaque3.nom + ", "+variablesSession.pokedex.get(i).attaque4.nom + ", ");
        attaques.setBounds(150, 240, 400, 50);  // Enfin on indique les noms des 4 attaques du vegamon
        this.add(attaques);
          		this.setVisible(true);
	}
    
    public String getType (int i, VariablesDeJeu variablesSession ){
        String str=" ";
        int t = variablesSession.pokedex.get(i).type;  
        if (t==1){
            str = "feu";
        } else if (t==2){           // Méthode qui permet de traduire le type d'un entier vers un String correspondant
            str = "plante";
        } else if (t==3){
            str = "eau";
        } else if (t==4){
            str = "electricite";
        }
        return str; 
    }
}
