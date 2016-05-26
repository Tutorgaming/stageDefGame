package com.game.src.weapon;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import com.game.src.graphic.Animator;
import com.game.src.graphic.Textures;
import com.game.src.main.PlayerStage;

public class Gun{
	
	public static final int DEFAULT_COOLDOWN_TIME = 60*6;
	private double x;
	private double y;
	private Point target;

	
	public static boolean ready=false;
	public static int COOLDOWN_TIME =60*6; 
	public static int cooldownTimer=0;
	
	private double rot = 0D;
	private AffineTransform at =new AffineTransform();
	
	private Animator anim;
	private boolean Trigger=true;
	//private BufferedImage gun;

	private Textures tex;
	private float alpha;
	
	
	
	public Gun(double x, double y, Textures tex){
		
		anim = new Animator(10, tex.gun[0], tex.gun[1],tex.gun[2],tex.gun[3]);
		
		this.x = x;
		this.y = y;
		this.tex =tex;
		
		at.translate(x,y);
	}
	
	public void tick(){
		
		if(Trigger)anim.animate();
		else anim = new Animator(10, tex.gun[0], tex.gun[1],tex.gun[2],tex.gun[3]); 
		
		
		
		if (target != null) {
			rot =-Math.atan2(target.y-y-32,x+16-target.x);
			rot	=Math.toDegrees(rot)-90;
			
		}
		//limiters
		if(rot>=-90){
			rot=-90;
		}
		if(rot<=-180){
			rot=-180;
		}
		//
		if(ready==false){
			cooldownTimer++;
			if(cooldownTimer>=COOLDOWN_TIME){
				cooldownTimer=0;
				ready=true;
				Trigger=false;
			}
		}
		
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		
		
		//if (target != null)g2d.rotate(Math.toRadians(rot),x+16, y+32);
		//g2d.drawImage(tex.gun,at,null);
		anim.drawAnim(g2d, rot, x, y, 16, 32);
		
		if(!ready){
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.1f));
			
			g2d.setStroke(new BasicStroke(3));
			
			
			int j=cooldownTimer*2;
			
			if(j>=320){
				alpha+=0.00001f;
				if(alpha>=0.95f)alpha=0.95f;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
				
				j=320;
			}
			
			
			g2d.setColor(Color.cyan);
			g2d.fillRect((int)(x+16-j/20),(int) y-cooldownTimer*2,j/10, cooldownTimer*2);
			g2d.setColor(Color.GREEN);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.1f));
			g2d.drawRect((int)(x+16-j/20),(int) y-cooldownTimer*2,(int)j/10, cooldownTimer*2);
			
			
		
		}else alpha=0f;

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
	}
	public void setTarget(Point target){
		this.target=target;
	}
	public double getRot(){
		return rot;
	}

	public void setTrigger(boolean t){
		Trigger=t;
	}
	
	public Point getTarget() {
		// TODO Auto-generated method stub
		return target;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y+16;
	}
	public static void setReady(Boolean r){
		ready = r;
	}

	public void reset() {
		setReady(false);
		// TODO Auto-generated method stub
		
	}
}
