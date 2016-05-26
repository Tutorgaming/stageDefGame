package com.game.src.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.text.Highlighter.Highlight;



public class Main_Menu extends Canvas {
	
	private static int playButtonHeight = 150;
	private static int tutorialButtonHeight = 200;
	private static int optionButtonHeight = 250;
	private static int highscoreButtonHeight = 300;
	private static int quitButtonHeight = 350;
	private static int buttonWidth = 140;
	public static Rectangle playButton = new Rectangle(Game.WIDTH/2 + 100 , playButtonHeight,buttonWidth,40);
	public static Rectangle tutorialButton = new Rectangle(Game.WIDTH/2 + 100 , tutorialButtonHeight,buttonWidth,40);
	public static Rectangle optionButton = new Rectangle(Game.WIDTH/2 + 100 , optionButtonHeight,buttonWidth,40);
	public static Rectangle highscoreButton = new Rectangle(Game.WIDTH/2 + 100 , highscoreButtonHeight,buttonWidth,40);
	public static Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 100 , quitButtonHeight,buttonWidth,40);
	
	
	private static int highlight,clicked;
	
	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		

		g.setFont(new Font("arial",Font.BOLD,50));
		g.setColor(Color.WHITE);
		
		g.drawString("STAGE DEFENSE GAME", 30, 100);
		
		
		g.setFont(new Font("arial",Font.BOLD,20));
		
		g2d.draw(playButton);
		
		g.drawString("PLAY",playButton.x+45,playButton.y+28);
		g2d.draw(tutorialButton);
		g.drawString("TUTORIAL",tutorialButton.x+20,tutorialButton.y+28);
		g2d.draw(optionButton);
		g.drawString("OPTION",optionButton.x+30,optionButton.y+28);
		g2d.draw(highscoreButton);
		g.drawString("HIGHSCORE",highscoreButton.x+11,highscoreButton.y+28);
		g2d.draw(quitButton);
		g.drawString("QUIT",quitButton.x+45,quitButton.y+28);
		
		
		//highlight
		if(highlight>0){
			int x,y;
			if(highlight==1){
				x=playButton.x;
				y=playButton.y;
			}else if(highlight==2){
				x=tutorialButton.x;
				y=tutorialButton.y;
				
			}else if(highlight==3){
				x=optionButton.x;
				y=optionButton.y;
			}else if(highlight==4){
				x=highscoreButton.x;
				y=highscoreButton.y;
				
			}else {
				x=quitButton.x;
				y=quitButton.y;
			}
				
		
			g2d.setColor(Color.CYAN);
			g2d.setStroke(new BasicStroke(4));
			g2d.drawRect(x, y, buttonWidth, 40);
		
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(1));
	
		}
		if(clicked>0){
			int x,y;
			if(clicked==1){
				x=playButton.x;
				y=playButton.y;
			}else if(clicked==2){
				x=tutorialButton.x;
				y=tutorialButton.y;
				
			}else if(clicked==3){
				x=optionButton.x;
				y=optionButton.y;
			}else if(clicked==4){
				x=highscoreButton.x;
				y=highscoreButton.y;
				
			}else {
				x=quitButton.x;
				y=quitButton.y;
			}
			
			
			
			
			
			g2d.setColor(Color.GREEN);
			g2d.setStroke(new BasicStroke(4));
			g2d.drawRect(x, y, buttonWidth, 40);
			
			highlight=0;
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(1));
			
		}
		
		
		
	}
	
	
		
		

	
	public static void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		//HIT X BOUND OF MENU <------>
		if(mx>=Game.WIDTH/2 +100 && mx<=Game.WIDTH/2+100+buttonWidth){
			clicked=0;
			//HIT Y BOUND OF PLAY BUTTON
			if(my>=playButtonHeight && my <=playButtonHeight+40){
				
				highlight=1;
			}
			
			if(my>=tutorialButtonHeight && my <=tutorialButtonHeight+40){

				highlight=2;
			}
			if(my>=optionButtonHeight && my <=optionButtonHeight+40){
				highlight=3;
			}
			if(my>=highscoreButtonHeight && my <=highscoreButtonHeight+40){
				highlight=4;
			}
			if(my>=quitButtonHeight && my <=quitButtonHeight+40){
				highlight=5;
			}
			
		}
	}
	
	public static void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		int mx = e.getX();
		int my = e.getY();
		
		//HIT X BOUND OF MENU <------>
		if(mx>=Game.WIDTH/2 +100 && mx<=Game.WIDTH/2+100+buttonWidth){
			
			//HIT Y BOUND OF PLAY BUTTON
			if(my>=playButtonHeight && my <=playButtonHeight+40){
				clicked=1;
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e1) {
//					
//					e1.printStackTrace();
//				}
				Game.State = Game.STATE.GAME;
				
			}
			
			if(my>=tutorialButtonHeight && my <=tutorialButtonHeight+40){
				clicked=2;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				Game.State = Game.STATE.TUTORIAL;
				
			}
			if(my>=optionButtonHeight && my <=optionButtonHeight+40){
				clicked=3;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				Game.State = Game.STATE.OPTION;
			}
			if(my>=highscoreButtonHeight && my <=highscoreButtonHeight+40){
				
				clicked=4;
				Menu_HighScore.getFile();
				System.out.println("done");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				Game.State = Game.STATE.HIGHSCORE;
			}
			if(my>=quitButtonHeight && my <=quitButtonHeight+40){
				
				clicked=5;
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				System.exit(1);
			}
			
		}
		
		
		
		
		
	}
}
