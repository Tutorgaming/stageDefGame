package com.game.src.graphic;

import java.awt.image.BufferedImage;

import com.game.src.main.Game;

public class Textures {
	public BufferedImage guitar[]=new BufferedImage[3];
	public BufferedImage bass[]=new BufferedImage[3];
	public BufferedImage drum[]=new BufferedImage[3];
	public BufferedImage speaker;
	
	
	public BufferedImage explosion[]=new BufferedImage[4];
	public BufferedImage[]gun=new BufferedImage[4];
	public BufferedImage[]bullet=new BufferedImage[4];
	public BufferedImage[]abullet=new BufferedImage[4];
	public BufferedImage[]enemy=new BufferedImage[5];
	public BufferedImage lightRoff,lightRon,lightGoff,lightGon;
	public BufferedImage flareR,flareG;
	private SpriteSheet ss =null;
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	private void getTextures(){
		gun[0]=ss.pickImg(1, 1, 32, 32);
		gun[1]=ss.pickImg(1, 2, 32, 32);
		gun[2]=ss.pickImg(1, 3, 32, 32);
		gun[3]=ss.pickImg(1, 4, 32, 32);
		
		bullet[0]=ss.pickImg(2, 1, 32, 32);
		bullet[1]=ss.pickImg(2, 2, 32, 32);
		bullet[2]=ss.pickImg(2, 3, 32, 32);
		bullet[3]=ss.pickImg(2, 4, 32, 32);
		
		abullet[0]=ss.pickImg(3, 1, 32, 32);
		abullet[1]=ss.pickImg(3, 2, 32, 32);
		abullet[2]=ss.pickImg(3, 3, 32, 32);
		abullet[3]=ss.pickImg(3, 4, 32, 32);
		
		enemy[0]=ss.pickImg(7, 1, 64, 64);
		enemy[1]=ss.pickImg(7, 3, 64, 64);
		enemy[2]=ss.pickImg(7, 5, 64, 64);
		enemy[3]=ss.pickImg(7, 7, 64, 64);
		enemy[4]=ss.pickImg(7, 9, 64, 64);
		
		explosion[0] = ss.pickImg(1, 5, 96, 96);
		explosion[1] = ss.pickImg(1, 8, 96, 96);
		explosion[2] = ss.pickImg(1, 11, 96, 96);
		explosion[3] = ss.pickImg(1, 14, 96, 96);
		
		lightRoff=ss.pickImg(4,1,32,32);
		lightRon=ss.pickImg(4,2,32,32);
		lightGoff=ss.pickImg(4,3,32,32);
		lightGon=ss.pickImg(4,4,32,32);
		
		flareR=ss.pickImg(6, 1, 32, 7*32);
		flareG=ss.pickImg(5, 1, 32, 7*32);
		
		guitar[0]=ss.pickImg(9, 1,64, 64);
		guitar[1]=ss.pickImg(9, 3,64, 64);
		guitar[2]=ss.pickImg(9, 5,64, 64);
		bass[0]=ss.pickImg(11, 1,64, 64);
		bass[1]=ss.pickImg(11, 3,64, 64);
		bass[2]=ss.pickImg(11, 5,64, 64);
		drum[0]=ss.pickImg(13, 1,64, 64);
		drum[1]=ss.pickImg(13, 3,64, 64);
		drum[2]=ss.pickImg(13, 5,64, 64);
		
		speaker=ss.pickImg(15,1, 64, 64);
		
		
	}
}
