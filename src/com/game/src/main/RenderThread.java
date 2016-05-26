package com.game.src.main;

public class RenderThread implements Runnable {
	
	private Game game;
	
	public RenderThread(Game game){
		this.game = game;
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		final double ticks=60.0;
		double ns =1000000000/ticks;
		double delta =0;//limit fps
		int frames =0;
		long timer = System.currentTimeMillis();
		
		while(game.running){//game loop
			long now =System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			
			game.render();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println("Fps "+frames);

				frames=0;
			}
		}
		game.stop();
	}
}
