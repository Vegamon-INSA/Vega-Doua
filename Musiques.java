import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Musiques{
		
	private AudioClip ac;
	private URL url;
	
	public void JouerAlerte () {
		url = Musiques.class.getResource("Musiques/erreur.wav");
		ac = Applet.newAudioClip(url);
		ac.play();
	}
	public void JouerMusiqueJouerEnBoucle (String musique) {
		url = Musiques.class.getResource(musique);
		ac = Applet.newAudioClip(url);
		ac.loop();
	}
	public void StopMusique() {
		ac.stop();
	}
}
