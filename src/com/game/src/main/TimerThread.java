package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class TimerThread extends Thread {

	private Graphics showTime;
	private static boolean isTiming;
	private static int seconds;
	public static String text;
	public TimerThread(){
		this.showTime = showTime;
		this.isTiming = true;
		this.seconds = 0;
	}
	
	public void render(Graphics showTime){
		Color temp = showTime.getColor();
		showTime.setColor(Color.YELLOW);
		showTime.setFont(new Font("aerial",Font.PLAIN,15));
		showTime.drawString(text, 100, (Game.HEIGHT-10)*Game.SCALE);
		showTime.setColor(temp);
	}
	public static void setTimer(boolean set){
		isTiming = set;
	}
	public static void reset(){
		seconds = 0;
		isTiming = true;
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("RUN TIMMMMMMMMMMMMMMMMMMMMMERRRRRRRRR");
		while(isTiming){
			Date date = new Date(seconds * 1000);
			SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
			text = "TIME "+sdf.format(date);
			//showTime.drawString(text, 50, 50);
			//System.out.println("TIMEISIIIIII="+seconds);
			
			seconds++;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopTiming(){
		this.isTiming = false;
//		
//		Date date = new Date(seconds * 1000);
//		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		showTime.drawString(text, 50, 50);
	}

	public static String getTime() {
		// TODO Auto-generated method stub
		return text+"";
	}


		
}
