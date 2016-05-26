package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.game.src.Audio.AudioPlayer;
import com.game.src.IO.KeyboardInput;
import com.game.src.IO.MouseInput;
import com.game.src.controller.AutobulletController;
import com.game.src.controller.BulletController;
import com.game.src.controller.EnemyController;
import com.game.src.graphic.Animator;
import com.game.src.graphic.BufferedImageLoader;
import com.game.src.graphic.Explosion;
import com.game.src.graphic.Textures;
import com.game.src.weapon.Autobullet;
import com.game.src.weapon.Autogun;
import com.game.src.weapon.Bullet;
import com.game.src.weapon.Gun;


@SuppressWarnings("serial")
public class Game extends Canvas {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE =2;
	public final String TITLE = "Stage Defense Game";
	public static AudioPlayer bgm = new AudioPlayer("/bgm.mp3",true);
	public boolean running = false;
	public boolean isPaused=false;
	private Thread ticker, renderer,timer;
	private TickThread tick=new TickThread(this);
	private RenderThread render=new RenderThread(this);
	private TimerThread time = new TimerThread();
	
	//private Thread AutoThread;
	private BufferStrategy bs;
	
	private AudioPlayer strum = new AudioPlayer("/laser.mp3");
	private AudioPlayer failed = new AudioPlayer("/failed.mp3");
	
	
	
	private BufferedImage img=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet=null;
	
	
	
	public boolean dropDown = false;
	
	
	
	
	
	
	private Gun g1;
	private Autogun ag1;
	private PlayerStage p;
	private Explosion exp;
	
	private AutobulletController ab;
	private BulletController c;
	private EnemyController e;
	private ArrayList<Graphics> bullets = new ArrayList<>();
	

	private Textures tex;
	private Main_Menu menu;
	private GameOver over;
	private Menu_Upgrade upgrade;
	private Menu_Option option;
	private Menu_Tutorial tutorial;
	private BufferedImage bg[]=new BufferedImage[8];
	private Animator Bganim;
	private BufferedImage Tutor;
	public static enum STATE{
		MENU,GAME,TUTORIAL,OPTION,HIGHSCORE,COUNTDOWN,OVER
	};
	
	public static STATE State = STATE.MENU;

	
	public void init(){
		
		requestFocus();
		BufferedImageLoader ldr=new BufferedImageLoader();
		
		try{
			spriteSheet = ldr.loadImage("/sprite_sheet.png");
			Tutor = ldr.loadImage("/TUTORIAL.png");
			
			bg[0] = ldr.loadImage("/Bg1.png");
			bg[1] = ldr.loadImage("/Bg2.png");
			bg[2] = ldr.loadImage("/Bg3.png");
			bg[3] = ldr.loadImage("/Bg4.png");
			bg[4] = ldr.loadImage("/Bg5.png");
			bg[5] = ldr.loadImage("/Bg6.png");
			bg[6] = ldr.loadImage("/Bg7.png");
			bg[7] = ldr.loadImage("/Bg8.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		addMouseMotionListener(new MouseInput(this));
		addMouseListener(new MouseInput(this));
		addKeyListener(new KeyboardInput(this));
		Bganim=new Animator(10, bg[0], bg[1],bg[2],bg[3],bg[4],bg[5],bg[6],bg[7]);
		tex =new Textures(this);
		ag1 = new Autogun(570,(Game.HEIGHT-56)*Game.SCALE,tex);
		
		menu = new Main_Menu();
		over = new GameOver();
		upgrade = new Menu_Upgrade();
		option = new Menu_Option();
		tutorial=new Menu_Tutorial();
		
		g1= new Gun(300*Game.SCALE,5*Game.SCALE,tex);
		c = new BulletController(this,bullets,tex,strum);
		ab = new AutobulletController(this,tex,ag1);
		e = new EnemyController(this,c,ab,bullets,tex);
		p = new PlayerStage(this, tex, e.getE(),failed);
		
		exp = new Explosion(e.getE(), tex);
		
		
		
		
		
	}
	
	public synchronized void start(){
		
		if(running) return;
		
		running = true;
		ticker =new Thread(tick);
		renderer=new Thread(render);
		timer = new Thread(time);
		
		ticker.start();
		timer.start();
		renderer.start();
		
		
		
	}
	public synchronized void stop(){
		if(!running)return;
		
		running=false;
		
		try {
			renderer.join();
			ticker.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	
	public void tick(){
		if(State==STATE.MENU){
			Bganim.animate();
			PlayerStage.reset();
			c.reset();
			e.reset();
			g1.reset();
			time.reset();
		}
		else if(State==STATE.GAME){
			Bganim.animate();
			if(!PlayerStage.player_alive)State=STATE.OVER;
			if(!isPaused){
				time.setTimer(true);
				p.tick();
				ag1.tick();
		
				g1.tick();
				c.tick();
				e.tick();
				if(Autogun.ready){
					ab.addBullet(new Autobullet(ag1, tex));
					Autogun.resetAG();
				}
				ab.tick();
				upgrade.tick();
				exp.tick();
			}	
		}
		else if(State==STATE.OVER){
			if(GameOver.requestFile){
			GameOver.writeFile();
			}
		}else if(State==STATE.HIGHSCORE){
			
		}
		
	}
	
	public void render(){
		bs =this.getBufferStrategy();		
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g =bs.getDrawGraphics();
		Graphics gun=bs.getDrawGraphics();
		Graphics upgradeMenu=bs.getDrawGraphics();
		
		//g.drawImage(bg[0], 0, 0, getWidth(), getHeight(), this);
		
		//Graphics bullets=bs.getDrawGraphics();
		/////drawHERE!!////////
			try{
				Bganim.drawAnim(g,0,0,getWidth(),getHeight(),this);
				if(State==STATE.GAME){
					g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
					Bganim.drawAnim(g,0,0,getWidth(),getHeight(),this);
					
					
					p.render(g);
					g1.render(gun);
					//ag1.render(g);
					
					
		
		
					for(int i=0;i<bullets.size();i++){

						c.render(bullets.get(i),i);
					}
		
					e.render(g);	
					ab.render(g);
					exp.render(g);
					
					upgrade.render(upgradeMenu);
					time.render(g);
				}else if(State == STATE.MENU){
					
					menu.render(g);
					
				}else if(State==STATE.OVER){
					over.render(g);
				}else if(State==STATE.TUTORIAL){
					g.drawImage(Tutor, 0, 0, getWidth(), getHeight(), this);
					tutorial.render(g);
				}else if(State==STATE.OPTION){
					option.render(g);
				}else if(State==STATE.HIGHSCORE){
					Menu_HighScore.render(g);
				}
				
				
				}catch(NullPointerException e){
					
				}catch(IOException e){
					
				}
			
			
		if(isPaused){
			Font fnt =new Font("aerial", Font.BOLD, 50);
			Color temp = g.getColor();
			g.setColor(Color.GREEN);
			g.setFont(fnt);
			g.drawString("PAUSED", (WIDTH/2-45)*SCALE, HEIGHT*SCALE/2);
			
			g.setFont(new Font("aerial", Font.BOLD, 20));
			g.drawString("Please ESC to Continue", (WIDTH/2-45)*SCALE, HEIGHT*SCALE/2+25);
			g.setColor(temp);
		}
	
		
		/////drawDONE!!///////
		
		g.dispose();
		gun.dispose();
		
		bs.show();
	}
	
	public void mouseMoved(MouseEvent e){
		if(State==STATE.GAME){
			Point cursor=e.getPoint();
			g1.setTarget(cursor);
		}
		else if (State==STATE.MENU){
			menu.mouseMoved(e);
		}else if(State==STATE.OVER){
			over.mouseMoved(e);
		}else if(State==STATE.OPTION){
			option.mouseMoved(e);
		}
	}
	public void mouseClicked(MouseEvent e){
	}
	public void mousePressed(MouseEvent e){
		if(State==STATE.GAME){
			if(!isPaused){
				g1.setTrigger(true);
		
				if(p.player_alive){
					bullets.add(bs.getDrawGraphics());
					c.addBullet(new Bullet(g1,g1.getX(),g1.getY(), tex));
				}
			}	
		}else if(State==STATE.MENU){
			menu.mousePressed(e);
		}else if(State==STATE.OVER){
			over.mousePressed(e);
		}else if(State==STATE.TUTORIAL){
			Menu_Tutorial.mousePressed(e);
		}else if(State==STATE.OPTION){
			Menu_Option.mousePressed(e);
		}else if(State==STATE.HIGHSCORE){
			Menu_HighScore.mousePressed(e);
		}
	}
	public void mouseDragged(MouseEvent e){
		
	}
	
	
	
	//KBLISTENERS
	
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		
		
		if(State==STATE.GAME){
			if(key==KeyEvent.VK_ESCAPE){
				if(!isPaused){					
					isPaused=true;
					timer.suspend();
					
				}else{
					isPaused=false;
					timer.resume();
					
				}
					
			}
			
			else if(key==KeyEvent.VK_CONTROL){
				
				
				
				dropDown=true;
				
				upgrade.setLatch(true);

				
			}else if(key==KeyEvent.VK_Q&&dropDown){
				PlayerStage.upgrade(1);//speakerDamage++
			}else if(key==KeyEvent.VK_W&&dropDown){
				PlayerStage.upgrade(2);//spotlightDamage++
			}else if(key==KeyEvent.VK_E&&dropDown){
				PlayerStage.upgrade(3);//cooldown
			}
			
		}	
		
	}
	public void keyReleased(KeyEvent e){
		
		int key=e.getKeyCode();
		
		if(State==STATE.GAME){
			
			if(key==KeyEvent.VK_CONTROL){
				dropDown=false;
				upgrade.setLatch(false);
			}
			
			
		}
	}
	
	
	
	public static void main(String[]args) throws IOException{
		
		
		Game game = new Game();
		
		BufferedImageLoader ldr=new BufferedImageLoader();
	
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Image image = ldr.loadImage("/crosshair22.png"); 
		Point hotSpot = new Point(20,15);  
		Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "CROSSHAIR");
		
		
		Game.bgm.play();
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	
		JFrame frame =new JFrame(game.TITLE);
		
		
		frame.setCursor(cursor);
		frame.setIconImage(image);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		
		
	
		game.start();
	}
	
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
}
