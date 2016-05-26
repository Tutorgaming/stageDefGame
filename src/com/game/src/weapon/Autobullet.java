package com.game.src.weapon;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.src.graphic.Animator;
import com.game.src.graphic.Textures;
import com.game.src.main.PlayerStage;


public class Autobullet {

	private double x;
	private double y;
	
	private double velY=0;
	private double accY=0;
	
	private double velX=0;
	private double accX=0.05;
	private Textures tex;
	
	Animator anim ;
	private int damage;
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public Autobullet(Autogun gun,double x, double y,Textures tex){
		this.x = (int)x-40;
		this.y = (int)y;
		this.tex = tex;
		anim = new Animator(8, tex.abullet[0],tex.abullet[1],tex.abullet[2],tex.abullet[3]);
		this.damage = PlayerStage.getSpeakerDamage();
		
	}
	public Autobullet(Autogun gun,Textures tex){
		
	
		
		this.x = gun.getX()-40;
		this.y = gun.getY();
		this.tex = tex;
		this.damage = PlayerStage.getSpeakerDamage();
		anim = new Animator(4, tex.abullet[0],tex.abullet[1],tex.abullet[2],tex.abullet[3]);
	}
	
	
	
	public void tick(){
		//SPEED BULLET FROM RIGHT TO LEFT
		anim.animate();
		
		

		if(velX>=3.5)accX=0.7;

		velX+=accX;
		x-=velX;
	}
	
	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		
		
		//g2d.drawImage(tex.bullet[0], (int)x,(int)y, null);
		anim.drawAnim(g2d, this.x,this.y, 0);
	}
	public int getX(){
		return (int)this.x;
	}
}
