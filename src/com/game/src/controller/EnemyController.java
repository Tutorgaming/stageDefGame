package com.game.src.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.LinkedList;

import com.game.src.character.Enemy;
import com.game.src.graphic.Textures;

import com.game.src.main.Game;
import com.game.src.main.PlayerStage;
import com.game.src.weapon.Bullet;

public class EnemyController {
	
	private LinkedList<Enemy> e =new LinkedList<>();  //ENEMY's LINKED LIST
	private BulletController c;
	private AutobulletController ac;
	Enemy TempEnemy;
	Game game;
	ArrayList<Graphics> g;
	Textures tex;
	private static int wave=1;
	private int stage;
	
	
	

	private Graphics gmain;
	
	
	private int COOLDOWN_TIME=60*3;
	private int cooldowntimer=0;
	
	public EnemyController(Game game,BulletController c,AutobulletController ac,ArrayList<Graphics> g,Textures tex){
		this.game=game;
		this.tex=tex;
		this.c = c;
		this.ac = ac;
		this.g=g;
		
//		//SPAWNING ENEMY
//		for(int x=0; x<Game.WIDTH*Game.SCALE;x+=64){
//			addEnemy(new Enemy(x+64-Game.WIDTH*Game.SCALE,(Game.HEIGHT-64)*Game.SCALE,g,tex));
//		}
	}
	
	public void setGmain(Graphics g){
		System.out.println("gumjai");
		this.gmain=g;
	}
	
	
	
	public static int getWave() {
		return wave-1;
	}


	public void setWave(int wave) {
		this.wave = wave;
	}


	public int getStage() {
		return stage;
	}


	public void setStage(int stage) {
		this.stage = stage;
	}


	public LinkedList<Enemy> getE() {
		return e;
	}
	public void setE(LinkedList<Enemy> e) {
		this.e = e;
	}
	
	public boolean haveEnemy(){
		if(e.size()==0)return true;
		else return false;
	}
	
	public void tick(){
		
		//ADDING ENEMY
		if(e.size()<=0){
			if(cooldowntimer>=COOLDOWN_TIME){
				cooldowntimer=0;
				if(wave>3){
					PlayerStage.upStage();
					wave=1;
				}
			
				for(int j=0 ; j<PlayerStage.stage*wave ;j++){
//					for(int j=0 ; j<20 ;j++){

					addEnemy(new Enemy((-64-(64*j*Math.random()+32))*Game.SCALE,(Game.HEIGHT-64)*Game.SCALE,1.5*Math.random(),g,tex));
		
				}
				
				wave++;
				
				
			}else cooldowntimer++;
			
		}	
		
		hit_radius(c);
		for(int i=0;i<e.size();i++){
			TempEnemy = e.get(i);
			
			TempEnemy.hit(ac); //HIT BY AUTOGUN ?
			TempEnemy.hit(c); //HIT BY USER's GUN ?
			
			
			if(!TempEnemy.getAlive()){ //IF ONE ENEMY DIES INCREASE SCORE !
				removeEnemy(TempEnemy); //AND REMOVE IT
				PlayerStage.increasePlayerScore(2);
				PlayerStage.lastEnemyCount();
			}
			TempEnemy.tick();
		}
		
	}
	
	
	public void hit_radius(BulletController c){
		for(int j=0;j<c.getBulletSize();j++){	
			for(int i=0;i<e.size();i++){
				TempEnemy = e.get(i);
				TempEnemy.hitRadius(Bullet.radius, c.getBullet(j).getX(), c.getBullet(j).getY(), c.getBullet(j).getDamage());
			}
		}
	}
	
	public void render(Graphics g){
		for(int j=0;j<e.size();j++){
			TempEnemy = e.get(j);
			 TempEnemy.render(g);
		}
		
	}
	public void addEnemy(Enemy enemy){
		e.add(enemy);

	}
	public void removeEnemy(Enemy enemy){
		e.remove(enemy);
	}

	public void reset() {
		e.removeAll(e);
		wave = 1 ;
	}
}	
