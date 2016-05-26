package com.game.src.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.game.src.Audio.AudioPlayer;
import com.game.src.character.Enemy;
import com.game.src.character.Musician;
import com.game.src.controller.EnemyController;
import com.game.src.graphic.Animator;
import com.game.src.graphic.Textures;
import com.game.src.weapon.Gun;

public class PlayerStage {
		
		private static int MAX_HEALTH=100;
		private static int MIN_HEALTH=0;
		
		//private static int MAX_LEVEL=3;
		//private static int MIN_LEVEL=1;
		
		public static int stage=1;
		private static int score; //USE AS MONEY TO UPGRADE INSTEAD
		//public static int level=MIN_LEVEL;
		private static int speakerDamage = 1;
		private static int spotlightDamage = 3;
		
		private static boolean upgradeReady=false;
		
		private static boolean StageDisplay=false;
		private int drawCount=0;
		
		private static int upgradeCredits;
		public static int getUpgradeCredits() {
			return upgradeCredits;
		}

		public static void setUpgradeCredits(int upgradeCredits) {
			PlayerStage.upgradeCredits = upgradeCredits;
		}

		private static int lastEnemyCount=0;
		
		
		private static boolean realistic_mode = false;
	
		public static boolean player_alive =true;
		private static int health;
		
		private Musician guitar;
		private Musician bass;
		private Musician drum;
		private Game game;
		private Textures tex;
		private LinkedList<Enemy> enemies;
		private Font f;
		
		private AudioPlayer fail;
		private Animator animG,animR,fxG,fxR;
		
		public PlayerStage(Game game,Textures tex,LinkedList<Enemy> enemies,AudioPlayer adp){
			this.game=game;
			this.tex=tex;
			this.enemies=enemies;
			f = new Font("Tahoma",Font.PLAIN,15*Game.SCALE);
			health=MAX_HEALTH;
			//level=MIN_LEVEL;
			this.fail=adp;
			
			guitar =new Musician(game,tex,1);
			bass =new Musician(game,tex,2);
			drum =new Musician(game,tex,3);
		
			
			animG=new Animator(10,tex.lightGoff,tex.lightGon);
			animR=new Animator(10,tex.lightRon,tex.lightRoff);
			fxG=new Animator(10,null,tex.flareG);
			fxR=new Animator(10,tex.flareR,null);
		}
		
		public static boolean isRealistic_mode() {
			return realistic_mode;
		}

		public static void setRealistic_mode(boolean realistic_mode) {
			PlayerStage.realistic_mode = realistic_mode;
		}

		
		public static void increasePlayerScore(int pts){
			score+=pts;
		}
		public static void upStage(){
			stage++;
			increaseHealth(10);
			StageDisplay=true;
			
			
		}
		public static void increaseHealth(int h){
			health+=h;
		}
		
		public static void lastEnemyCount(){
			lastEnemyCount++;
			if(lastEnemyCount==4){
				upgradeCredits++;
				lastEnemyCount=0;
			}
			
		}
		public static void upgrade(int n){
			if(upgradeReady){
				if(n==1){
					if(score>=5){
						
					speakerDamage++;
					if(speakerDamage>=10){
						speakerDamage=10;
						return;
					}
					score=score-5;
					upgradeCredits--;
					}else{
						Menu_Upgrade.setFlag(5);
					}
				}else if(n==2){
					if(score>=10){
					spotlightDamage++;
					if(spotlightDamage>=8){
						spotlightDamage=8;
						return;
					}
					score=score-10;
					upgradeCredits--;
					}else{
						Menu_Upgrade.setFlag(10);
					}
				}else if(n==3){
					if(score>=25){
					Gun.COOLDOWN_TIME-=5;
					if(Gun.COOLDOWN_TIME<=100){
						Gun.COOLDOWN_TIME=100;
						return;
					}
					score=score-25;
					upgradeCredits--;
					}else{
						Menu_Upgrade.setFlag(25);
					}
				}
				System.out.println(n+" upgrade completed.");
				n=0;
				//upgradeCredits--;
				if(upgradeCredits<=0)upgradeCredits=0;
			}	
		}
		
		public void tick(){
			if(player_alive){

				animG.animate();
				animR.animate();
				fxG.animate();
				fxR.animate();
				
				
				guitar.tick();
				bass.tick();
				drum.tick();
				
				if(upgradeCredits>0)upgradeReady=true;
				else upgradeReady=false;
				
				Game.bgm.setVol(0);
				if(health>=100)health=100;
				for(int i=0;i<enemies.size();i++){
			
					if(enemies.get(i).hitStage()) {
					
						health-=enemies.get(i).getDamage();
					}
				}
				if(health<=0){
					health=0;
					Game.bgm.setVol(-5);
					fail.play();
					player_alive=false;
				}
			}	
		}
		
		public void render(Graphics g){
			Graphics2D g2d=(Graphics2D) g;
			if(!Gun.ready){
				g.drawImage(tex.lightGoff, 415,10,null);
				g.drawImage(tex.lightRoff, 447,10,null);
				g.drawImage(tex.lightGoff, 479,10,null);
				g.drawImage(tex.lightRoff, 511,10,null);
				g.drawImage(tex.lightGoff, 543,10,null);
				g.drawImage(tex.lightRoff, 575,10,null);
			}else{
				animG.drawAnim(g2d, 415,10, 0);
				fxG.drawAnim(g2d, 415,20, 0);				
				
				animR.drawAnim(g2d, 447,10, 0);
				fxR.drawAnim(g2d, 447,20, 0);
				
				animG.drawAnim(g2d, 479,10, 0);
				fxG.drawAnim(g2d, 479,20, 0);
				
				animR.drawAnim(g2d, 511,10, 0);
				fxR.drawAnim(g2d, 511,20, 0);
				
				animG.drawAnim(g2d, 543,10, 0);
				fxG.drawAnim(g2d, 543,20, 0);
				
				animR.drawAnim(g2d, 575,10, 0);
				fxR.drawAnim(g2d, 575,20, 0);
				
			}
			
			drum.render(g2d, 550, (Game.HEIGHT-93)*Game.SCALE);
			bass.render(g2d, 485, (Game.HEIGHT-98)*Game.SCALE);
			guitar.render(g2d, 430, (Game.HEIGHT-95)*Game.SCALE);
			
			
			//speaker
			g.drawImage(tex.speaker, 550, (Game.HEIGHT-60)*Game.SCALE, null);
			
			//PLAYER's HEALTH STATUS
			g2d.setFont(new Font("aerial",Font.PLAIN,10*game.SCALE));
			g2d.setColor(Color.white);			
			g2d.drawString("Health "+health,(int) (game.WIDTH-90)*game.SCALE, (int) (game.HEIGHT-10)*game.SCALE);
			
			//PLAYER's WAVE STAGE STATUS
			g2d.setFont(new Font("aerial",Font.PLAIN,10*game.SCALE));
			g2d.setColor(Color.white);			
			g2d.drawString("Stage "+stage+"-"+EnemyController.getWave(),(int) (game.WIDTH-200)*game.SCALE, (int) (game.HEIGHT-10)*game.SCALE);
			
			
			//SCORE RENDERING
			g.setColor(Color.WHITE);
			g.setFont(new Font("aerial",Font.PLAIN,20));
			g.drawString("SCORE "+score+"", 50, 30);
			
			
			//Stage Display
			if(StageDisplay){
				drawCount++;
				if(drawCount>=1000){
					StageDisplay=false;
					drawCount=0;
				}
				g.drawString("STAGE  "+stage+" +10 HP", Game.WIDTH/2+100, Game.HEIGHT/2);
				
			}
			
			
			
			
			//GUN's COOLDOWN RENDERING
			g2d.setStroke(new BasicStroke(10));
				g2d.setColor(Color.GREEN);
				
				int k = (Gun.cooldownTimer/4) ;
				int full = (Gun.COOLDOWN_TIME/4);
				if(Gun.ready)k=full;
				
				g2d.setColor(Color.WHITE);
				g2d.drawOval((int) (game.WIDTH-315)*game.SCALE, (int) (game.HEIGHT-210)*game.SCALE,40,40);
				
				g2d.setColor(new Color(255-(k*2),k*2,0));
				
				g2d.fillOval((int) (game.WIDTH-315)*game.SCALE, (int) (game.HEIGHT-210)*game.SCALE,40,40);
				
				
				
				//WHITE FRAME
				g2d.setColor(Color.WHITE);
				g2d.setStroke(new BasicStroke(4));
				g2d.drawLine((int) (game.WIDTH-295)*game.SCALE, (int) (game.HEIGHT-197)*game.SCALE , 
						(int) (game.WIDTH-295+k+2 )*game.SCALE , (int) (game.HEIGHT-197)*game.SCALE);
				g2d.drawLine((int) (game.WIDTH-295)*game.SCALE, (int) (game.HEIGHT-203)*game.SCALE , 
						(int) (game.WIDTH-295+k+2 )*game.SCALE , (int) (game.HEIGHT-203)*game.SCALE);
				
				g2d.drawLine((int) (game.WIDTH-295+k+3)*game.SCALE, (int) (game.HEIGHT-197)*game.SCALE , 
						(int) (game.WIDTH-295+k+3 )*game.SCALE , (int) (game.HEIGHT-203)*game.SCALE);
				
				
				
				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(new Color(255-(k*2),k*2,0));
				g2d.drawLine((int) (game.WIDTH-295)*game.SCALE, (int) (game.HEIGHT-200)*game.SCALE , 
						(int) (game.WIDTH-295+k )*game.SCALE , (int) (game.HEIGHT-200)*game.SCALE);
				
				
				g2d.setFont(new Font("Tahoma",Font.PLAIN,6*game.SCALE));
				String statusgun = Gun.cooldownTimer+"/"+Gun.COOLDOWN_TIME;
				if(Gun.ready){
					g2d.setColor(Color.white);
					statusgun = "SPOTLIGHT IS READY!";
				}else{
					g2d.setColor(Color.GREEN);
				}
				
				g2d.drawString(statusgun, ((game.WIDTH-300)*game.SCALE)+((int) (game.WIDTH-300+k ))/2, (int) (game.HEIGHT-190)*game.SCALE );
			
				
				//upgradeReady!
				if(upgradeReady){
					g.setColor(Color.ORANGE);
					g.drawString("UPGRADE(s) is ready!",(Game.WIDTH/2+50)*Game.SCALE, Game.HEIGHT/2);
					g.drawString(" Press[CTRL] to activate upgrade panel.",(Game.WIDTH/2+20)*Game.SCALE, (Game.HEIGHT/2-50)*Game.SCALE);
					
				}
				
			//GAME OVER STATE	
//			if(!player_alive){
//				g2d.setFont(f);
//				g2d.setColor(Color.RED);
//				g2d.drawString("Game Over",(int) (game.WIDTH/2-15)*game.SCALE, (int) (game.HEIGHT/2)*game.SCALE);
//				
//			}
			
			
			

		}

		public static void reset() {
			player_alive=true;
			stage=1;
			score=0;
			StageDisplay=false;
			
			upgradeCredits=0;
			upgradeReady=false;
			lastEnemyCount=0;
			health=MAX_HEALTH;
			//level=MIN_LEVEL;
			speakerDamage = 1;
			spotlightDamage = 3;
			Gun.COOLDOWN_TIME = Gun.DEFAULT_COOLDOWN_TIME;
			TimerThread.reset();
			GameOver.requestFile = true;
			
			
		}

		public static int getSpeakerDamage() {
			return speakerDamage;
		}

		public static void setSpeakerDamage(int speakerDamage) {
			PlayerStage.speakerDamage = speakerDamage;
		}

		public static int getSpotlightDamage() {
			return spotlightDamage;
		}

		public static void setSpotlightDamage(int spotlightDamage) {
			PlayerStage.spotlightDamage = spotlightDamage;
		}

		public static int getScore() {
			// TODO Auto-generated method stub
			return score;
		}

		
		
}
