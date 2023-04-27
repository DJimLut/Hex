package main.LogicalComponents;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SFX {

    String filePath;

    //Plays button click sound effect
    public void playButtonClick() {
        filePath = "audio_files\\button_click.wav";
        File file = new File(filePath);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    //Plays tile click sound effect
    public void playTileClick() {
        filePath = "audio_files\\tile_click.wav";
        File file = new File(filePath);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    //Plays win sound effect
    public void playWin() {
        filePath = "audio_files\\win.wav";
        File file = new File(filePath);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    //Plays lose sound effect
    public void playLose() {
        filePath = "audio_files\\lose.wav";
        File file = new File(filePath);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void playWrong() {
        filePath = "audio_files\\wrong.wav";
        File file = new File(filePath);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
