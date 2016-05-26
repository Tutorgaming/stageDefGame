package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.game.src.graphic.BufferedImageLoader;

public class Menu_Tutorial {
	private static BufferedImageLoader ldr;

	
	public static void render(Graphics g) throws IOException{
		Graphics2D g2d = (Graphics2D)g;
		
		
		g.setFont(new Font("arial",Font.BOLD,50));
		g.setColor(Color.ORANGE);
		
		g.drawString("TUTORIAL", 30, (Game.HEIGHT-30)*Game.SCALE);
		
	}

	public static void mousePressed(MouseEvent e) {
		Game.State = Game.STATE.MENU;
		
	}
}
