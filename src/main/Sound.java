package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    public boolean isMuted = false;

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/c418ariamath.wav");
        soundURL[1] = getClass().getResource("/sound/grass.WAV");
    }

    public void setFile(int i) throws Exception {
        try {
            AudioInputStream au = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(au);
        } catch (Exception e){
            throw new Exception();
        }
    }

    public void play(){
        if (!isMuted)
            clip.start();
    }

    public void loop(){
        if (!isMuted)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        if (!isMuted)
            clip.stop();
    }
}
