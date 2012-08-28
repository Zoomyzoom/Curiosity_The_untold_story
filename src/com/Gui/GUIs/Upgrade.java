package com.Gui.GUIs;

import com.Evolution.Stats.StatsBase;
import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUI;
import com.Gui.TextPeice;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Upgrade extends GUI {

	Texture health;
	Texture hellrain;
	Texture bomb;
	Texture deathwave;
	Texture guns;
	Texture djump;
	

	public Upgrade() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/upgradeScreen.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			health = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/health.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			hellrain = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/cloud.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			bomb = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/bomb.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			deathwave = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/deathwave.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			guns = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/guns.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			djump = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/djump.png")));
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
		tps[0] = new TextPeice("Double Jump", 10, 100, true, false, 300, 29);
		tps[1] = new TextPeice("Armor Upgrade", 10, 140, true, false, 300, 29);
		tps[2] = new TextPeice("Hell Rain", 10, 180, true, false, 300, 29);
		tps[3] = new TextPeice("Death Wave", 10, 220, true, false, 300, 29);
		tps[4] = new TextPeice("Mega Bomb", 10, 260, true, false, 300, 29);
		tps[5] = new TextPeice("Back", 10, 320, true, false, 300, 29);
		tps[6] = new TextPeice("Gun Upgrades", 10, 60, true, false, 300, 29);
		setTps(tps);
	}

	public void draw() {
		if (backT1 != null) {
			GL11.glColor3f(1f, 1f, 1f);
			backT1.bind();
		}
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
			Render.drawText(Game.WIDTH / 2 + 10, 150, "The ability to press 'Jump' again", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "after jumping! This allows you to", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "pass over zombies more safely.", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 250, "Price 200", true, false);
			if(Unlocked.DoubleJump){
				Render.drawText(Game.WIDTH / 2 + 10, 290, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 200) {
				Render.drawText(Game.WIDTH / 2 + 10, 290, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 290, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			djump.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20 + 128);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20 + 128);
			GL11.glEnd();
			GL11.glPopMatrix();
			break;
		case 1:
			Render.drawText(Game.WIDTH / 2 + 10, 150, "So you like health? Well, Have", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "better armor! thats right,", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "take half damage!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "Price 1000!", true, false);
			if(Unlocked.MaxHealth){
				Render.drawText(Game.WIDTH / 2 + 10, 290, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 1000) {
				Render.drawText(Game.WIDTH / 2 + 10, 290, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 290, "Can not afford", true, false);
			}
			}
			
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			health.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20 + 128);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20 + 128);
			GL11.glEnd();
			GL11.glPopMatrix();
			break;
		case 2:
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Need somthing to get you out", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "of tight situations? try the", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "'Death Wave'! fire a wall of", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "bullets to murderfy the enemy!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 270, "Price 700!", true, false);
			if(Unlocked.DeathWave){
				Render.drawText(Game.WIDTH / 2 + 10, 310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 700) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			deathwave.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20 + 128);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20 + 128);
			GL11.glEnd();
			GL11.glPopMatrix();
			break;
		case 3:
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Tired of killing zombies by", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "hand? Then 'Hell Rain' is", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "what you need! rain pain", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "from above, then do it again!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 270, "Price 900!", true, false);
			if(Unlocked.HellRain){
				Render.drawText(Game.WIDTH / 2 + 10, 310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 900) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			hellrain.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20 + 128);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20 + 128);
			GL11.glEnd();
			GL11.glPopMatrix();
			break;
		case 4:
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Kaboom? Yes. Kaboom! wipe", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "out the zombies whilst", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "knocking satilites out", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "of orbit!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 270, "Price 4000!", true, false);
			if(Unlocked.MegaBomb){
				Render.drawText(Game.WIDTH / 2 + 10, 310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 4000) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			bomb.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20 + 128);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20 + 128);
			GL11.glEnd();
			GL11.glPopMatrix();
			break;
		case 6:
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Here you can upgrade your", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "guns to speed up the killing", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "of the undead and other enemys", true, false);
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			guns.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84 + 128, 20 + 128);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 84, 20 + 128);
			GL11.glEnd();
			GL11.glPopMatrix();
			break;
		}
		Render.drawText(10, 10, "Upgrades", true, false);
		this.drawBasic();
	}

	public void action(int id) {
		switch (id) {
		case 0:
			if(!Unlocked.DoubleJump){
			if (StatsBase.kills > 200) {
				StatsBase.kills -= 200;
				Unlocked.DoubleJump = true;
			}
			}
			break;
		case 1:
			if(!Unlocked.MaxHealth){
			if (StatsBase.kills > 1000) {
				StatsBase.kills -= 1000;
				Unlocked.MaxHealth = true;
			}
			}
			break;
		case 2:
			if(!Unlocked.DeathWave){
			if (StatsBase.kills > 700) {
				StatsBase.kills -= 700;
				Unlocked.DeathWave = true;
			}
			}
			break;
		case 3:
			if(!Unlocked.HellRain){
			if (StatsBase.kills > 900) {
				StatsBase.kills -= 900;
				Unlocked.HellRain = true;
			}
			}
			break;
		case 4:
			if(!Unlocked.MegaBomb){
			if (StatsBase.kills > 4000) {
				StatsBase.kills -= 4000;
				Unlocked.MegaBomb = true;
			}
			}
			break;
		case 5:
			Logic.gameState=Logic.GameState.PAUSED;
			break;
		case 6:
			Logic.gameState=Logic.GameState.GUN_UPGRADE;
			break;
		}
	}
}
