package com.game.src.main;

public class TickThread implements Runnable{
	
	private Game game;
	
	public TickThread (Game game){
		this.game=game;
	}
	public void run(){
		game.init();
		
		long lastTime = System.nanoTime();
		final double ticks=60.0;
		double ns =1000000000/ticks;
		double delta =0;//limit fps
		int updates =0;
		long timer = System.currentTimeMillis();
		
		while(game.running){//game loop
			long now =System.nanoTime();
		
			delta+=(now-lastTime)/ns;
			lastTime=now;
			
				if(delta >=1){
					game.tick();
					updates++;
					delta--;
				}
				
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.print(updates+" Ticks");
				updates=0;
			}
			
		}
		
		game.stop();
	}
}
