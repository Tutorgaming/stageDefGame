package com.game.src.IO;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.game.src.main.Game;

public class MouseInput implements MouseMotionListener,MouseListener {
	
	Game game;
	
	public MouseInput(Game game){
		this.game=game;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		//game.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game.mouseMoved(e);
		//System.out.println(e.getPoint().toString());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		game.mousePressed(e);
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	
	
}
