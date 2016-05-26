package com.game.src.Audio;


import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.*;

public class AudioPlayer {
  
  private Clip clip;
  private boolean looping;
  public AudioPlayer(String s) {
    
    try {
    	System.out.println(s);
      System.out.println(getClass().getResourceAsStream(s));
      AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(s));
      AudioFormat baseFormat = ais.getFormat();
      AudioFormat decodeFormat = new AudioFormat(
        AudioFormat.Encoding.PCM_SIGNED,
        baseFormat.getSampleRate(),
        16,
        baseFormat.getChannels(),
        baseFormat.getChannels() * 2,
        baseFormat.getSampleRate(),
        false
      );
      AudioInputStream dais =
        AudioSystem.getAudioInputStream(
          decodeFormat, ais);
      clip = AudioSystem.getClip();
      clip.open(dais);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public AudioPlayer(String s,boolean setLoop) {
	    
	    try {
	    	System.out.println(s);
	      System.out.println(getClass().getResourceAsStream(s));
	      AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(s));
	      AudioFormat baseFormat = ais.getFormat();
	      AudioFormat decodeFormat = new AudioFormat(
	        AudioFormat.Encoding.PCM_SIGNED,
	        baseFormat.getSampleRate(),
	        16,
	        baseFormat.getChannels(),
	        baseFormat.getChannels() * 2,
	        baseFormat.getSampleRate(),
	        false
	      );
	      AudioInputStream dais =
	        AudioSystem.getAudioInputStream(
	          decodeFormat, ais);
	      clip = AudioSystem.getClip();
	      clip.open(dais);
	      clip.loop(Clip.LOOP_CONTINUOUSLY);
	      this.looping = true;
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	    
	  }

  
  
  public void play() {
	
    if(clip == null) return;
    stop();
    clip.setFramePosition(0);
    if(looping)clip.loop(Clip.LOOP_CONTINUOUSLY);
    clip.start();
  }
  

  
  public void stop() {
    if(clip.isRunning()) clip.stop();
  }
  
 public void setVol(int gain){
	 FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	 volume.setValue(gain);
	
 }
  public void close() {
    stop();
    clip.close();
  }
  
}















