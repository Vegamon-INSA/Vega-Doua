import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Musiques{
		
	private AudioClip ac1;
	private AudioClip ac2;
	private URL url1;
	private URL url2;
	private VariablesDeJeu variableDeJeu;

	public void jouerAlerte () {
		url1 = Musiques.class.getResource("Musiques/erreur.wav");
		ac2 = Applet.newAudioClip(url1);
		ac2.play();
	}
	public void jouerMusiqueJouerEnBoucle(String musique,VariablesDeJeu variable) {
		variableDeJeu=variable; 
		if (variableDeJeu.sondesac !=0){
			url2 = Musiques.class.getResource(musique);
			ac1 = Applet.newAudioClip(url2);
			ac1.loop();
		}
	}
	public void stopMusique(VariablesDeJeu variable) {
		variableDeJeu=variable; 
		if (variableDeJeu.sondesac!=0){
			ac1.stop();
		}
	}
}
