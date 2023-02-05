package com.comp301.eso_rotation.model;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SimpleAudioPlayer {

  // to store current position
  Long currentFrame;
  Clip clip;

  // current status of clip
  String status;

  AudioInputStream audioInputStream;
  private File file;

  // constructor to initialize streams and clip
  public SimpleAudioPlayer(String filePath)
      throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    // create AudioInputStream object
    audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
    this.file = new File(filePath).getAbsoluteFile();
    // create clip reference
    clip = AudioSystem.getClip();
  }

  public static void main(String[] args) {}

  // Method to stop the audio
  public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    currentFrame = 0L;
    clip.stop();
    clip.close();
  }

  // Method to reset audio stream
  public void resetAudioStream()
      throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
    clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start(); // This plays the audio
    LineListener listener = new LineListener() {
          public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
              clip.close();
//              System.out.println("clip was closed");
              return;
            }
          }
        };
    clip.addLineListener(listener);
    }
  }