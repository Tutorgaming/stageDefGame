package com.game.src.weapon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.game.src.graphic.Animator;
import com.game.src.graphic.Textures;
import com.game.src.main.PlayerStage;

public class Bullet {
	
	private double x;
	private double y;
	public static int radius = 50;
	private double initY;
	private double velY=0;
	private double accY=0.05;
	
	private double velX=1;
	private double accX=0;
	
	private Animator anim;
	
	private int damage;
	
	private double rot=0D;
	private double  initRot=0D;
	private double tempInitRot=0D;

	private Textures tex;
	
	public Bullet(Gun gun,double x, double y,Textures tex){
		this.tex=tex;
		this.anim=new Animator(15, tex.bullet[0], tex.bullet[1],tex.bullet[2],tex.bullet[3]);
		this.rot=gun.getRot();
		this.damage = PlayerStage.getSpotlightDamage();
		initRot=Math.atan2(gun.getTarget().y-42, 616-gun.getTarget().x);
		//Limiters
		if(initRot<=0)initRot=0;
		if(initRot>=90)initRot=90;
		//
		this.x =x-50*Math.cos(initRot);
		this.y=y+50*Math.sin(initRot);
		initY=this.y;

		
		tempInitRot=initRot;
		
		
		
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void tick(){
		
		anim.animate();
		
		if(accY<=0.05&&accY>0.02)accY=-0.01;
		else if(accY>=0.01)accY=1;
		else accY+=0.0005;
		velY+=accY;
		y-=velY;
		
	
	}
	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D)g;

		//g2d.rotate(Math.toRadians(rot), x+16, y+16);
		//g2d.drawImage(tex.bullet, (int)x,(int)y, null);
		anim.drawAnim(g2d, rot, x, y,16,16);
		

		rot=0D;	
		initRot=0D;
	}
	
	public double getX(){
		double Rx=x-36+y*Math.cos(tempInitRot);
		//System.out.println("x = " +(int)Rx);
		return Rx;
	}
	public double getY(){
		double Ry=85-(-tempInitRot+y)*Math.sin(tempInitRot);
		//System.out.println("y = "+(int)Ry);
		return Ry;
	}
}
