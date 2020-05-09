
// Programme pour gérer l'audio 
// utilise un objet Clip 
import java.io.File; 
import java.io.IOException; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

  
public class Musiques{

  
    // pour stocker la position actuelle
    private Long currentFrame; 
    private Clip clip; 
      
    //Statut actuel du clip 
      
    private AudioInputStream audioInputStream; 
    static String filePath; 
  
    // constructeur pour initialiser le clip 
    public Musiques(String cheminFichier) throws IOException, LineUnavailableException, UnsupportedAudioFileException  
         
    { 
        filePath=cheminFichier;
        try
        {
            
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
            
            // créé un clip de référence  
            clip = AudioSystem.getClip(); 
            
            // ouvre audioInputStream dans le clip 
            clip.open(audioInputStream); 
                clip.loop(Clip.LOOP_CONTINUOUSLY); 
            
            
        
        }
        catch (Exception ex)  
        { 
            ex.printStackTrace(); 
          
          } 
    } 

    public void jouerAlerte() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
{ 
                // créé un objet AudioInputStream  
            AudioInputStream audioInputStreamAlerte = AudioSystem.getAudioInputStream(new File("Musiques/erreur.wav").getAbsoluteFile()); 
            
            // créé un clip de reference 
            Clip clipAlerte = AudioSystem.getClip(); 
            
            // ouvre audioInputStream dans le clip 
            clipAlerte.open(audioInputStreamAlerte); 
            
            clipAlerte.start();

        }      
      
        // Méthode pour jouer l'audio 
        public void play()  
        { 
            //début du clip 
            clip.start(); 
              
        } 
          
        // Méthode pour mettre sur pause l'audio 
        public void pause()  
        { 
            this.currentFrame = this.clip.getMicrosecondPosition(); 
            clip.stop(); 
        } 
          
        // Méthode pour reprendre l'audio 
        public void resumeAudio() throws UnsupportedAudioFileException, 
                                    IOException, LineUnavailableException  
        { 
            clip.close(); 
            resetAudioStream(); 
            clip.setMicrosecondPosition(currentFrame); 
            this.play(); 
        } 
          
        // Méthode pour recommencer l'audio 
        public void restart() throws IOException, LineUnavailableException, 
                                                UnsupportedAudioFileException  
        { 
            clip.stop(); 
            clip.close(); 
            resetAudioStream(); 
            currentFrame = 0L; 
            clip.setMicrosecondPosition(0); 
            this.play(); 
        } 
          
        // Méthode pour arrêter l'audio 
        public void stop() throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
        { 
            currentFrame = 0L; 
            clip.stop(); 
            clip.close(); 
        } 
          
        // Méthode pour réinitilaliser le stream audio  
        public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
                                                LineUnavailableException  
        { 
           audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
            clip.open(audioInputStream); 
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
        } 
      
    } 

