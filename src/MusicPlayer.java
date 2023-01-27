import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class MusicPlayer {
	
	void playMusic (String musicLocation)
	{
		try
		{
			File musicPath = new File(musicLocation);
			
			if (musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
				executor.schedule(() -> clip.stop(), 3, TimeUnit.SECONDS);
			}
			else
			{
				System.out.println("Can't find file");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
