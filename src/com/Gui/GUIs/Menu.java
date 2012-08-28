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

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

public class Menu extends GUI {

	int frame = 1;
	public Menu() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/menu/menu.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			backT2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/menu/menu2.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			backT3 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/menu/menu3.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			backT4 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/menu/menu4.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		this.setX(0);
		this.setY(0);
		this.setWidth(Game.WIDTH);
		this.setHeight(Game.HEIGHT);

		TextPeice[] tps = new TextPeice[7];
		tps[0] = new TextPeice("Play", 10, 180, true, false, 200, 29);
		tps[1] = new TextPeice("Select Difficulty", 10, 220, true, false, 200, 29);
		tps[2] = new TextPeice("How To Play", 10, 260, true, false, 200, 29);
		tps[3] = new TextPeice("Options", 10, 300, true, false, 200, 29);
		tps[4] = new TextPeice("About", 10, 340, true, false, 200, 29);
		tps[5] = new TextPeice("Quit", 10, 420, true, false, 200, 29);
		tps[6] = new TextPeice("Credits", 10, 380, true, false, 200, 29);
		setTps(tps);
	}
	long animate = System.currentTimeMillis();
	
	public void draw() {
		if(System.currentTimeMillis()-animate>200){
			animate = System.currentTimeMillis();
			frame++;
		}
		if(frame>4){
			frame = 1;
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

		switch (selectionId) {

		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		}
		
		this.drawBasic();
	}

	public void action(int id) {
		switch (id) {
		case 0:
			Game.loadingScreen();
			Game.p = new Player(300*50, -20);
			StatsBase.kills = 0;
			StatsBase.kittyKills = 0;
			StatsBase.init();
			Unlocked.DoubleJump=false;
			Unlocked.MaxHealth = false;
			Unlocked.DeathWave = false;
			Unlocked.HellRain = false;
			Unlocked.MegaBomb = false;
			Unlocked.rpg = false;
			Unlocked.sniper = false;
			Unlocked.rateOfFire = false;
			Unlocked.shotgun = false;
			Unlocked.HotBullets = false;
			Unlocked.damage = false;
			Unlocked.blah = false;
			Unlocked.MaxHealth = false;
			Game.setupWorld();
			if(Options.music){
				if(Options.difficulty==15){
					Game.song1.loop(1f, 0.5f);
				}
				if(Options.difficulty==8){
					Game.song2.loop(1f, 0.5f);
				}
				if(Options.difficulty==27){
					Game.song3.loop(1f, 0.5f);
				}
			}
			Logic.gameState=Logic.GameState.GAME;
			break;
		case 1:
			Logic.gameState=Logic.GameState.DIFFICULTY;
			break;
		case 2:
			Logic.gameState=Logic.GameState.HOWTOPLAY;
			break;
		case 3:
			Logic.gameState=Logic.GameState.OPTIONS;
			break;
		case 4:
			Logic.gameState=Logic.GameState.ABOUT;
			break;
		case 5:
			AL.destroy();
			Display.destroy();
			System.exit(0);
			break;
		case 6:
			Logic.gameState=Logic.GameState.CREDITS;
			break;
		}
	}
}
