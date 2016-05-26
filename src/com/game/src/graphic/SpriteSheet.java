package com.game.src.graphic;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage img;
	
	public SpriteSheet(BufferedImage img){
		this.img=img;
	}
	
	public BufferedImage pickImg(int col, int row,int width, int height){
		
		BufferedImage temp_img=img.getSubimage(col*32-32, row*32-32, width, height);
		return temp_img;
	}
}
