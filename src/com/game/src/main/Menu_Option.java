package com.game.src.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Menu_Option {
	
	
	private static int Real1ButtonHeight = 300;
	private static int Real2ButtonHeight = 350;
	private static int menuButtonHeight = 400;
	private static int buttonWidth = 140;
	public static Rectangle menuButton = new Rectangle(Game.WIDTH/2 + 100 , menuButtonHeight,buttonWidth,40);
	public static Rectangle Real1Button = new Rectangle(Game.WIDTH/2 + 100 , Real1ButtonHeight,buttonWidth,40);
	public static Rectangle Real2Button = new Rectangle(Game.WIDTH/2 + 100 , Real2ButtonHeight,buttonWidth,40);
	private static int highlight;
	
	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;

		g.setFont(new Font("arial",Font.BOLD,50));
		g.setColor(Color.WHITE);
		
		g.drawString("OPTION", 30, 100);
		
		
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,15));
		
		g2d.draw(menuButton);
		g.drawString("MENU", menuButton.x+48, menuButton.y+28);
		
		g2d.draw(Real1Button);
		g.drawString("REAL MODE", Real1Button.x+26, Real1Button.y+28);
		
		g2d.draw(Real2Button);
		g.drawString("UNREAL MODE", Real2Button.x+16, Real2Button.y+28);
		
		
		//highlight
		if(highlight>0){
			int x = 0,y=0 ;
			if(highlight==1){
				x=menuButton.x;
				y=menuButton.y;
			}else if (highlight ==2) {
				x=Real1Button.x;
				y=Real1Button.y;
			}else if (highlight ==3) {
				x=Real2Button.x;
				y=Real2Button.y;
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
			
			if(my>=Real1ButtonHeight && my <=Real1ButtonHeight+40){
				
				highlight=2;
			}
			
			if(my>=Real2ButtonHeight && my <=Real2ButtonHeight+40){
				
				highlight=3;
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
				
				Game.State=Game.STATE.MENU;
			}
			
			if(my>=Real1ButtonHeight && my <=Real1ButtonHeight+40){
				
				PlayerStage.setRealistic_mode(true);
				Game.State=Game.STATE.MENU;
			}
			
			if(my>=Real2ButtonHeight && my <=Real2ButtonHeight+40){
				
				PlayerStage.setRealistic_mode(false);
				Game.State=Game.STATE.MENU;
			}
			
			
		}
		
	}
}
