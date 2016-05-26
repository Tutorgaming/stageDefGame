package com.game.src.character;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.src.graphic.Animator;
import com.game.src.graphic.Textures;
import com.game.src.main.Game;

public class Musician {
	
	private Game game;
	private Textures tex;
	Animator anim;
	public Musician(Game game, Textures tex,int select) {
		// TODO Auto-generated constructor stub
		this.game=game;
		this.tex=tex;
		if(select==1){
			anim = new Animator(10, tex.guitar[0], tex.guitar[1], tex.guitar[2]);
		}else if(select==2){
			anim = new Animator(10, tex.bass[0], tex.bass[1], tex.bass[2]);
			
		}else if(select==3){
			anim= new Animator(5,tex.drum[0],tex.drum[1],tex.drum[2]);
		}
	}
	public void tick(){
		anim.animate();
		
	}
	public void render(Graphics g,int x,int y){
		Graphics2D g2d=(Graphics2D) g;
		
		anim.drawAnim(g2d, x, y, 0);
	}

}
