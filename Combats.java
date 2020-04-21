

public class Combats{
		
 	private int[] listeCombats = new int[25]; //tableau contenenant la liste des comabts et leur état : 0= pas fait, 1 perdu, 2 gagné

	
	public void JouerMusiqueUneFois (String musique) {
		url = Musiques.class.getResource(musique);
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


