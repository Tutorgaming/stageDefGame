package com.game.src.controller;

import java.awt.Graphics;
import java.util.ArrayList;

import com.game.src.graphic.Textures;
import com.game.src.main.Game;
import com.game.src.weapon.Autobullet;
import com.game.src.weapon.Autogun;

public class AutobulletController{

	private ArrayList<Autobullet> ab =new ArrayList<>();
	
	Autobullet TempAuto;
	Game game;
	Textures tex;
	public AutobulletController(Game game,Textures tex,Autogun autogun){
		this.game=game;
		this.tex=tex;
		
	}
	
	
		
	public void tick(){


		try{
		for(int i=0;i<ab.size();i++){
			TempAuto = ab.get(i);
			if (TempAuto.getX()<0){ 
				removeBullet(TempAuto);

			}
			TempAuto.tick();
		}
		}catch (NullPointerException e){}
		
	}
	
	
	
	public void render(Graphics g){
		for(int i=0;i<ab.size();i++){
			TempAuto = ab.get(i);
			
			TempAuto.render(g);
		}
				
	}
	
	public void addBullet(Autobullet shot){

			ab.add(shot);	

		
		
	}
	public void removeBullet(Autobullet shot){
		ab.remove(shot);
	}
	
	public Autobullet getBullet(int i){
		return this.ab.get(i);
	}
	public int getBulletSize(){
		return this.ab.size();
	}
	
	
		

}
