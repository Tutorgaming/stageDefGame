package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Menu_HighScore {
	static Scanner in;
	static ArrayList<Integer> score=new ArrayList<>();
	static ArrayList<String> timeuse=new ArrayList<>();
	
	public static void getFile(){
		try{
		in = new Scanner(new File("CONFIG.TXT"));
		String temp="";
		in.nextLine();
		while(in.hasNext()){
			temp=in.nextLine();
			if(!score.contains(Integer.parseInt(temp)))score.add(Integer.parseInt(temp));
			
			temp=in.nextLine();
			if(!score.contains(temp))timeuse.add(temp);
			
			
		
			
		}
		in.close();
		}catch (IOException e){
			
		}catch (NumberFormatException e){}
		catch(NoSuchElementException e){}
		bubbleSort();
	}
	
	private static void bubbleSort() {
    	int tmp = 0;
    	String t="";
    	for(int i=0; i < score.size(); i++){
            for(int j=1; j < (score.size()-i); j++){
            	if (score.get(j-1) > score.get(j)) {
            		tmp = score.get(j);
            		t=timeuse.get(j);
            		
            		score.set(j, score.get(j-1)) ;
            		timeuse.set(j, timeuse.get(j-1)) ;
            		
            		score.set(j-1, tmp);
            		timeuse.set(j-1, t);
            	}    
            }
    	}
    }
	
	
	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		try{
			g.setFont(new Font("arial",Font.BOLD,50));
			g.setColor(Color.ORANGE);
			g.drawString("HIGH SCORE !", 30, 100);
		
			g.setFont(new Font("arial",Font.BOLD,15));
			g.setColor(Color.WHITE);
			
			for(int j=0;j<5;j++){
				g.drawString((j+1)+".) SCORE = "+score.get(score.size()-1-j), 30, 150+50*j);
		
				
				//g.drawString("TIME USE = "+timeuse.get(timeuse.size()-1-j), 30, 170+50*j);
			}	
		}catch (ArrayIndexOutOfBoundsException e){}
	}

	public static void mousePressed(MouseEvent e) {
		Game.State = Game.STATE.MENU;
		
	}
}
