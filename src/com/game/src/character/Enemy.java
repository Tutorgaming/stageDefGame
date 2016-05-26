package com.game.src.character;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.game.src.controller.AutobulletController;
import com.game.src.controller.BulletController;
import com.game.src.graphic.Animator;
import com.game.src.graphic.Textures;
import com.game.src.main.PlayerStage;
import com.game.src.weapon.Autogun;
import com.game.src.weapon.Gun;

public class Enemy {

	private double x,y;
	private double velX=0.5;
	private Textures tex;
	private int lifepoint=8;
	private int damage=1;
	private boolean alive =true;
	private boolean hit_stage1=false;
	public boolean isHit=false;
	private int drawCount=0;
	private Animator anim,animDefault,animhitStage,explosion;

	private AffineTransform af =new AffineTransform();
	
	
	
	ArrayList<Graphics> g;
	
	public Enemy(double x, double y,double velX,ArrayList<Graphics> g,Textures tex){
		this.x=x;
		this.y=y;
		if(velX>=0.5)this.velX=velX;
		//this.velX=30;
		this.tex = tex;
		this.g = g;
		
		animDefault=new Animator((int)(10/this.velX), tex.enemy[0],tex.enemy[1],tex.enemy[2]);
		animhitStage=new Animator(8,tex.enemy[3],tex.enemy[4]);
		anim=animDefault;
		explosion = new Animator(3, tex.explosion[0], tex.explosion[1],tex.explosion[2],tex.explosion[3]);
		
	}
	
	public void tick(){
		if(alive){
			if(isHit){
				drawCount++;
				if(drawCount>=3){
					if(lifepoint<=0)alive = false;
					isHit=false;
					drawCount=0;
				}
			}

			if(x<340)anim=animDefault;
				
			
			anim.animate();
			explosion.animate();

			if(x<=-64)this.x=-64;
			this.x+=velX;
		
			//CHECK IF ENEMY HIT CONDITION ( DIRECTION INVERSE )
		if((int)this.x>=350){
			lifepoint--;
			this.hit_stage1 = true;
			
			velX=-velX;
			anim=animhitStage;
			
		}
		//CHECK THE ENEMY's HITTING THE STAGE (FOR SET THE DIRECTION INVERSE)
		if(hit_stage1 && (int)this.x<=250){
			velX=-velX;
			hit_stage1=false;
			
		}
		if(!isHit&&lifepoint<=0)alive = false; //LAST HIT AND TURN TO THE DEATH
		}
		
		
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void hit(BulletController c){
		
		for(int i =0;i<c.getBulletSize();i++){
		
			if(c.getBullet(i).getX() >= this.x && 
					c.getBullet(i).getX() <= this.x + 50 && 
					c.getBullet(i).getY() >= this.y+15 && 
					c.getBullet(i).getY() <= this.y + 50){
				
				isHit=true;
				lifepoint-=c.getBullet(i).getDamage(); //DAMAGE IS FROM PLAYERSTAGE.SPOTLIGHTDAMAGE
				if(lifepoint<=0){
					lifepoint=0;
				}
				c.removeBullet(c.getBullet(i));

				this.x=this.x-33;//KNOCKBACK!!!!
				
				g.remove(i);
			}	
		}	
	}
	
	public void hit(AutobulletController ac){
		
		for(int i =0;i<ac.getBulletSize();i++){
		
			if(ac.getBullet(i).getX() >= this.x && 
					ac.getBullet(i).getX() <= this.x + 50 ){
				
						lifepoint-=ac.getBullet(i).getDamage();
						if(lifepoint<=0){
							lifepoint=0;
						}
						ac.removeBullet(ac.getBullet(i));
						this.x=this.x-30;//KNOCKBACK!!!!
			}
		}	
	}
	public void hitRadius(int radius, double x, double y,int damage){
		if(this.x>=x-radius&&this.x<=x+radius
				&&this.y>=y+15&&this.y<=y+50){
			
			isHit=true;			
			lifepoint-=(damage-1);
			if(lifepoint<=0){
				lifepoint=0;
			}
			this.x=this.x-30;//KNOCKBACK!!!!

		}
	}
	
	public boolean hitStage(){ //WHEN HIT => PERFORM THIS ACTION

		if((int)this.x>=350){		
			return true;
		}
		
		return false;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	
	public void render (Graphics g){
		//GET THE LAST RENDEREE 's COLOR
		Color temp = g.getColor();
		Graphics2D g2d=(Graphics2D)g;
		anim.drawAnim(g2d, x, y, 0);
		g.setColor(Color.WHITE);
		

		//FOR LIFE STATUS (NOT SHOWING IS REALISTIC MODE OF PLAYER STAGE)
		if(!PlayerStage.isRealistic_mode()){
			
			
			//LIFE NUMBER
			if(lifepoint==1)g.setColor(Color.RED);
			g.setFont(new Font("Tahoma",Font.BOLD,15));
			g.drawString(""+lifepoint, (int)x+32, (int)y-15);
			
			
			//LIFE BAR	
			g2d.setStroke(new BasicStroke(10));
				for(int k = 0 ; k < lifepoint*5 ; k+=5){
				g2d.setColor(Color.GREEN);
				if(lifepoint==1)g.setColor(Color.RED);
				g2d.drawLine((int)x+18, (int)y, (int)x+18+k , (int)(y));
			}
		}
		
		//RETURN THE LAST RENDEREE 's COLOR
				g.setColor(temp);
	}
}
