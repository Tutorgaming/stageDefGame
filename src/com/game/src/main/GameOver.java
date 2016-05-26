package com.game.src.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class GameOver extends Canvas {
	
	
	private static int menuButtonHeight = 300;
	private static int quitButtonHeight = 350;
	private static int buttonWidth = 140;
	public static Rectangle menuButton = new Rectangle(Game.WIDTH/2 + 100 , menuButtonHeight,buttonWidth,40);
	public static Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 100 , quitButtonHeight,buttonWidth,40);
	public static boolean requestFile=true;
	private static int highlight;
	
	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		

		g.setFont(new Font("arial",Font.BOLD,50));
		g.setColor(Color.RED);
		
		g.drawString("GAME OVER", Game.WIDTH/2+10, 100);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,20));
		
		g2d.draw(menuButton);
		g.drawString("MENU", menuButton.x+43, menuButton.y+28);
		
		g2d.draw(quitButton);
		g.drawString("EXIT", quitButton.x+50, quitButton.y+28);
		
		
		
		//highlight
		if(highlight>0){
			int x,y;
			if(highlight==1){
				x=menuButton.x;
				y=menuButton.y;
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
		
	}
	
	public static void mouseMoved(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		//HIT X BOUND OF MENU <------>
		if(mx>=Game.WIDTH/2 +100 && mx<=Game.WIDTH/2+100+buttonWidth){
			
			//HIT Y BOUND OF PLAY BUTTON
			if(my>=menuButtonHeight && my <=menuButtonHeight+40){
				
				highlight=1;
			}
			
			if(my>=quitButtonHeight && my <=quitButtonHeight+40){
				
				highlight=2;
			}
			
			
			
		}
	}
	
	public static void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("FUCK YOU BITHCASDIFJAIFJ");
		int mx = e.getX();
		int my = e.getY();
		
		//HIT X BOUND OF MENU <------>
		if(mx>=Game.WIDTH/2 +100 && mx<=Game.WIDTH/2+100+buttonWidth){
			
			if(my>=menuButtonHeight && my <=menuButtonHeight+40){
				Menu_HighScore.getFile();
				System.out.println("done");
				Game.State=Game.STATE.MENU;
			}
			
			if(my>=quitButtonHeight && my <=quitButtonHeight+40){
				
				System.exit(1);
			}
			
			
		}
		
	}

	public static void writeFile(){
		// TODO Auto-generated method stub
		try{
			int count=0;
			
			
			
			
			
			
			if(requestFile){
				
//			File config = new File("CONFIG.TXT");	
//			
//			out.println("[SCORE]"+PlayerStage.getScore());
//			out.println("[TIME]"+TimerThread.getTime().substring(11));
//			out.close();
			FileWriter fwriter = new FileWriter("CONFIG.TXT",true);	
			BufferedWriter writer = new BufferedWriter(fwriter);	
				
				if(count==0){
					writer.newLine();
					
				}else count=0;
				writer.append(""+PlayerStage.getScore());
				writer.newLine();
				writer.write(""+TimerThread.getTime().substring(4));
				
				writer.close();
				requestFile = false;
			}
		}catch(IOException e){
			
		}
	}	
}
