package com.game.src.graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Animator{
	
	private BufferedImage img0,img1,img2,img3,img4,img5,img6,img7,currentImg;
	private int speed;
	private int count =0;
	private int frames;
	private int timer=0;
	
	public Animator(int speed,BufferedImage img0,BufferedImage img1){
		this.speed=speed;
		this.img0=img0;
		this.img1=img1;
		frames=2;
		nextFrame();
	}
	public Animator(int speed,BufferedImage img0,BufferedImage img1,BufferedImage img2){
		this.speed=speed;
		this.img0=img0;
		this.img1=img1;
		this.img2=img2;
		frames=3;
		nextFrame();
	}
	public Animator(int speed,BufferedImage img0,BufferedImage img1,BufferedImage img2,BufferedImage img3){
		this.speed=speed;
		this.img0=img0;
		this.img1=img1;
		this.img2=img2;
		this.img3=img3;
		frames=4;
		nextFrame();
	}
	
	public Animator(int speed, BufferedImage bufferedImage,
			BufferedImage bufferedImage2, BufferedImage bufferedImage3,
			BufferedImage bufferedImage4, BufferedImage bufferedImage5,
			BufferedImage bufferedImage6, BufferedImage bufferedImage7,
			BufferedImage bufferedImage8) {
		this.speed=speed;
		this.img0=bufferedImage;
		this.img1=bufferedImage2;
		this.img2=bufferedImage3;
		this.img3=bufferedImage4;
		this.img4=bufferedImage5;
		this.img5=bufferedImage6;
		this.img6=bufferedImage7;
		this.img7=bufferedImage8;
		frames=8;
		nextFrame();
	}
	public void animate(){
		timer++;

		if(timer>speed){
			timer=0;
			nextFrame();
		}
	}
	
	private void nextFrame(){
		switch(frames){
		case 2:
			if(count==0) currentImg=img0;
			if(count==1) currentImg=img1;
			count++;
			if(count>=frames){
				count=0;
			
			}
			break;
		case 3:
			if(count==0) currentImg=img0;
			if(count==1) currentImg=img1;
			if(count==2) currentImg=img2;
			count++;
			if(count>=frames){
				count=0;
			}
			break;
		case 4:
			if(count==0) currentImg=img0;
			if(count==1) currentImg=img1;
			if(count==2) currentImg=img2;
			if(count==3) currentImg=img3;
			count++;
			if(count>=frames){
				count=0;
						
			}
			break;
		case 8:
			if(count==0) currentImg=img0;
			if(count==1) currentImg=img1;
			if(count==2) currentImg=img2;
			if(count==3) currentImg=img3;
			if(count==4) currentImg=img4;
			if(count==5) currentImg=img5;
			if(count==6) currentImg=img6;
			if(count==7) currentImg=img7;
			count++;
			if(count>=frames){
				count=0;
			}
			break;
			
		}
		
	}
	
	public void drawAnim(Graphics2D g,double x,double y,int offset){
		g.drawImage(currentImg, (int)x-offset, (int)y, null);
	}
	public void drawAnim(Graphics2D g,double rot,double x,double y,int pivotX,int pivotY){
		g.rotate(Math.toRadians(rot),(int)x+pivotX,(int)y+pivotY);
		g.drawImage(currentImg, (int)x,(int)y, null);
	}
	public void drawAnim(Graphics g,double x,double y,double width,double height, Object obj){
		g.drawImage(currentImg,(int)x,(int)y,(int)width,(int)height,(ImageObserver) obj);
	}
	
	


}
