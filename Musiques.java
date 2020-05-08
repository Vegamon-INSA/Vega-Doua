
// Java program to play an Audio 
// file using Clip Object 
import java.io.File; 
import java.io.IOException; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

  
public class Musiques{

  
    // to store current position 
    private Long currentFrame; 
    private Clip clip; 
      
    // current status of clip 
      
    private AudioInputStream audioInputStream; 
    static String filePath; 
  
    // constructor to initialize streams and clip 
    public Musiques(String cheminFichier) throws IOException, LineUnavailableException, UnsupportedAudioFileException  
         
    { 
        filePath=cheminFichier;
        try
        {
            
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
            
            // create clip reference 
            clip = AudioSystem.getClip(); 
            
            // open audioInputStream to the clip 
            clip.open(audioInputStream); 
                clip.loop(Clip.LOOP_CONTINUOUSLY); 
            
            
        
        }
        catch (Exception ex)  
        { 
            System.out.println("Error with playing sound."); 
            ex.printStackTrace(); 
          
          } 
    } 

    public void jouerAlerte() throws UnsupportedAudioFileException, 
    IOException, LineUnavailableException  
{ 
                // create AudioInputStream object 
            AudioInputStream audioInputStreamAlerte = AudioSystem.getAudioInputStream(new File("Musiques/erreur.wav").getAbsoluteFile()); 
            
            // create clip reference 
            Clip clipAlerte = AudioSystem.getClip(); 
            
            // open audioInputStream to the clip 
            clipAlerte.open(audioInputStreamAlerte); 
            
            clipAlerte.start();

        }      
      
        // Method to play the audio 
        public void play()  
        { 
            //start the clip 
            clip.start(); 
              
        } 
          
        // Method to pause the audio 
        public void pause()  
        { 
            this.currentFrame = this.clip.getMicrosecondPosition(); 
            clip.stop(); 
        } 
          
        // Method to resume the audio 
        public void resumeAudio() throws UnsupportedAudioFileException, 
                                    IOException, LineUnavailableException  
        { 
            clip.close(); 
            resetAudioStream(); 
            clip.setMicrosecondPosition(currentFrame); 
            this.play(); 
        } 
          
        // Method to restart the audio 
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
          
        // Method to stop the audio 
        public void stop() throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
        { 
            currentFrame = 0L; 
            clip.stop(); 
            clip.close(); 
        } 
          
        // Method to jump over a specific part 
        public void jump(long c) throws UnsupportedAudioFileException, IOException, 
                                                            LineUnavailableException  
        { 
            if (c > 0 && c < clip.getMicrosecondLength())  
            { 
                clip.stop(); 
                clip.close(); 
                resetAudioStream(); 
                currentFrame = c; 
                clip.setMicrosecondPosition(c); 
                this.play(); 
            } 
        } 
          
        // Method to reset audio stream 
        public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
                                                LineUnavailableException  
        { 
           audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
            clip.open(audioInputStream); 
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
        } 
      
    } 

