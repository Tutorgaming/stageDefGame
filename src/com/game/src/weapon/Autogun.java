package com.game.src.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import com.game.src.graphic.Textures;
import com.game.src.main.PlayerStage;

public class Autogun {
	
	private double x;
	private double y;
	private Textures tex;
	public static boolean ready=true;
	private int cooldownTimer =0;
	private int COOLDOWN_TIME = 60*3;
	
	public Autogun(double x , double y , Textures tex){
		this.x = x;
		this.y = y;
		this.tex = tex;
		
	}
	
	
	public void tick(){
		
		if(ready==false){
			cooldownTimer++;
			if(cooldownTimer>=COOLDOWN_TIME){
				cooldownTimer=0;
				ready=true;
			}
		}
		
	}
	public static void resetAG(){
		ready=false;
	}
	
	
	
	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect((int)this.x, (int)this.y, 64, 64);
		
		
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY(){
		return (int)this.y;
	}
	
	
}
