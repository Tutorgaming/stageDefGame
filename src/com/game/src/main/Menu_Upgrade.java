package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.game.src.weapon.Gun;

public class Menu_Upgrade {
	private double y=0;
	private double velY=0;
	private double accY=1;
	private boolean latched = false;
	private boolean hit=false;
	static String flag;
	static boolean showMsg=false;
	float alpha = 0f;
	private double y1,y2,y3;
	public void setLatch(boolean latch){
		latched=latch;
	}
	public void down(){
//		accY+=0.5;
		if(!hit){	
			velY+=accY;
			y+=velY;
		
			if(y>=200){
				hit=true;
				y=200;
				velY=10;
				
				
			}
		}else{
			velY-=accY;
			y+=velY;
			if(y<=190){
				y=190;
				velY=0;
			}
			
			
		}
		
		
		
	}
	
	public void up(){

//		accY+=0.5;
		hit=false;
		velY+=accY;
		y-=velY;
			if(y<=0){
				y=2;
				velY=0;
			}

		
		
	}
	public void tick(){
		
		if(latched){
			
			down();
			
		}
		else{
			up();	
			
			
			showMsg=false;
		}
		
	}
	
	
	public void render(Graphics g){
		Color temp = g.getColor();
		g.setFont(new Font("arial",Font.BOLD,50));
		g.setColor(Color.WHITE);	
		g.drawString("UPGRADE", 30,(int)(y-60));
		g.setFont(new Font("arial",Font.BOLD,20));
		if(showMsg)g.drawString("Not Enough Score!"+flag, 300,(int)(y-40));
		if(PlayerStage.getUpgradeCredits()>0)g.drawString("Avaliable!! = "+PlayerStage.getUpgradeCredits(), 300,(int)(y-60));
		
		
		fadein(g);
		
		g.setColor(temp);
	}

	public void fadein(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		String spk="";
		String spl="";
		String cdt="";
		if(latched){
			
			if(alpha<=1f){
				alpha+=0.0005f;
				if(alpha>=0.85f)alpha=0.85f;
				if(hit){
					y1++;
					if(y1>=150)y1=150;
					y2+=0.8;
					if(y2>=175)y2=175;
					y3+=0.6;
					if(y3>=200)y3=200;
					
					
					if(PlayerStage.getSpeakerDamage()==10)spk="Maxed (10)";
					else spk ="" +PlayerStage.getSpeakerDamage();
					
					if(PlayerStage.getSpotlightDamage()==8)spl="Maxed (8)";
					else spl =""+PlayerStage.getSpotlightDamage();
					
					if(Gun.COOLDOWN_TIME==100)cdt="Minnimized (100)";
					else cdt =Gun.COOLDOWN_TIME+"";
					
					g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		
					g2d.setFont(new Font("arial",Font.PLAIN,20));
					g2d.drawString("[Q]-SPEAKER(-5 score)   =  "+spk, 30,(int)(y1));
	
					g2d.drawString("[W]-SPOTLIGHT(-10 score)   =  "+spl, 30,(int)(y2));
	
					g2d.drawString("[E]-SPOTLIGHT_COOLDOWNTIME (-25 score)   =  "+cdt, 30,(int)(y3));
				}
			} 
		}else{
			y1-=0.2;
			if(y1<=0)y1=-16;
			y2-=0.3;
			if(y2<=-32)y2=-32;
			y3-=0.5;
			if(y3<=-64)y3=-64;
			
			alpha-=0.0005f;
			if(alpha<=0)alpha=0;
			
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
			g2d.setFont(new Font("arial",Font.PLAIN,20));
			g2d.drawString("[Q]-SPEAKER(-5 score)   =  "+spk, 30,(int)(y1));

			g2d.drawString("[W]-SPOTLIGHT(-10 score)   =  "+spl, 30,(int)(y2));

			g2d.drawString("[E]-SPOTLIGHT_COOLDOWNTIME (-25 score)   =  "+cdt, 30,(int)(y3));
			
		}
		
	}
	public static void setFlag(int i) {
		// TODO Auto-generated method stub
		flag = ""+i;
		showMsg=true;
	}
	
	
	
}
