package com.game.src.controller;

import java.awt.Graphics;
import java.util.ArrayList;

import com.game.src.Audio.AudioPlayer;
import com.game.src.graphic.Textures;

import com.game.src.main.Game;
import com.game.src.weapon.Bullet;
import com.game.src.weapon.Gun;

public class BulletController {
	
	private ArrayList<Bullet> b =new ArrayList<>();
	
	private AudioPlayer adp;
	
	Bullet TempBullet;
	
	ArrayList<Graphics> g;
	
	Game game;
	Textures tex;
	public BulletController(Game game,ArrayList<Graphics> g,Textures tex,AudioPlayer adp){
		
		this.game=game;
		this.tex=tex;
		this.adp=adp;
		this.g=g;

		
	}
	public void tick(){
		for(int i=0;i<b.size();i++){
			TempBullet = b.get(i);
			if (TempBullet.getY()>Game.HEIGHT*Game.SCALE||TempBullet.getX()<0){ 
				
				removeBullet(TempBullet);
				
				g.remove(i);
				
			}
			TempBullet.tick();
		}
		
	}
	
	
	
	public void render(Graphics g,int i){
		try{
			if(b.size()!=0){
				TempBullet = b.get(i);
		
				TempBullet.render(g);
			}
		}catch(IndexOutOfBoundsException e){
			
		}
				
	}
	
	
	
	
	
	public void addBullet(Bullet shot){
		if(Gun.ready){
			b.add(shot);
			adp.play();
			Gun.setReady(false);
		}
		
		
	}
	public void removeBullet(Bullet shot){
		b.remove(shot);
	}
	
	public Bullet getBullet(int i){
		return this.b.get(i);
	}
	public int getBulletSize(){
		return this.b.size();
	}
	public void reset() {
		TempBullet=null;
		
	}
}
