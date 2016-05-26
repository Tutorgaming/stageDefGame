package com.game.src.graphic;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import com.game.src.character.Enemy;

public class Explosion extends Canvas{
	
	private LinkedList<Enemy> e=new LinkedList<>();
	private LinkedList<Point> p=new LinkedList<>();
	
	private int drawCount;
	private Textures tex;
	private Animator anim;
	public Explosion(LinkedList<Enemy> e,Textures tex){
		this.e=e;
		this.tex=tex;
		anim= new Animator(3, tex.explosion[0], tex.explosion[1],tex.explosion[2],tex.explosion[3]);
	}
	
	
	public void tick(){
		anim.animate();
		for(int i=0;i<e.size();i++){
			if(e.get(i).isHit){
				Point Temppoint=new Point((int) e.get(i).getX(),(int) e.get(i).getY()-15);
				p.add(Temppoint);
				
			}
		}
		if(drawCount<=10){
			
			drawCount++;
		
		}else{
			for(int i=0;i<p.size();i++){
				p.remove(i);
			}
			
			drawCount=0;
		}	
		
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		
			for(int i=0;i<p.size();i++){
				anim.drawAnim(g2d, p.get(i).x,p.get(i).y,0);
			}
		
		
			
		
		
		
		
		
	}
}
