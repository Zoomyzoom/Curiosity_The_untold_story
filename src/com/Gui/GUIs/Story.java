package com.Gui.GUIs;

import com.Entity.Entities.Player;
import com.Evolution.Stats.StatsBase;
import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Options.Options;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUI;
import com.Gui.TextPeice;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

public class Story extends GUI {

	int frame = 1;
	public Story() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/story/1.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			backT2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/story/2.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			backT3 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/story/3.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			backT4 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/story/4.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		this.setX(0);
		this.setY(0);
		this.setWidth(Game.WIDTH);
		this.setHeight(Game.HEIGHT);

		TextPeice[] tps = new TextPeice[1];
		tps[0] = new TextPeice("Continue", 338, 550, true, false, 462, 29);
		setTps(tps);
	}
	long animate = System.currentTimeMillis();
	
	public void draw() {
			
		if(frame>4){
			frame = 1;
			Logic.gameState=Logic.GameState.MENU;
		}
		if(frame==1){
			backT1.bind();
		}else if(frame==2){
			backT2.bind();
		}else if(frame==3){
			backT3.bind();
		}else if(frame==4){
			backT4.bind();
		}
			
		GL11.glColor3f(1f, 1f, 1f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(0.782, 0);
		GL11.glVertex2d(x + width, y);
		GL11.glTexCoord2d(0.782, 0.58);
		GL11.glVertex2d(x + width, y + height);
		GL11.glTexCoord2d(0, 0.58);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();
		this.drawBasic();
		
		if (!Mouse.isButtonDown(0)) {
			clicked = false;
		}
	}
	boolean clicked = true;
	public void action(int id) {
		if (!clicked) {
		switch (id) {
		case 0:
			frame++;
			clicked = true;
			break;
		}
	}
	}
}
