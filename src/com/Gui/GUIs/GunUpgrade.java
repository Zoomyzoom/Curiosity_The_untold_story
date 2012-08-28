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

public class GunUpgrade extends GUI {

	Texture rateoffire,damage,shotgun,rpg,sniper;

	public GunUpgrade() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/upgradeScreen.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			rpg = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/rpg.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			shotgun = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/shotgun.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			damage = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/damage.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		try {
			sniper = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/sniper.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		try {
			rateoffire = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/rate.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		

		this.setX(0);
		this.setY(0);
		this.setWidth(Game.WIDTH);
		this.setHeight(Game.HEIGHT);

		TextPeice[] tps = new TextPeice[6];
		tps[0] = new TextPeice("Rate Of Fire", 10, 100, true, false, 300, 29);
		tps[1] = new TextPeice("Damage", 10, 140, true, false, 300, 29);
		tps[2] = new TextPeice("Buy Shotgun", 10, 180, true, false, 300, 29);
		tps[3] = new TextPeice("Buy RPG", 10, 220, true, false, 300, 29);
		tps[4] = new TextPeice("Buy Sniper", 10, 260, true, false, 300, 29);
		tps[5] = new TextPeice("Back", 10, 320, true, false, 300, 29);
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
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Too many zombies? Buy this", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "upgrade and shoot faster!,", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "unleashing a blitz of bullets", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "killing those terrors!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 280, "Price 1500", true, false);
			if(Unlocked.rateOfFire){
				Render.drawText(Game.WIDTH / 2 + 10, 310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 1500) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);

			rateoffire.bind();
			
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
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Need to pack a bigger ", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "punch? Buy this to increase", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "the power of your weapons", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "Price 2000!", true, false);
			if(Unlocked.damage){
				Render.drawText(Game.WIDTH / 2 + 10, 290, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 2000) {
				Render.drawText(Game.WIDTH / 2 + 10, 290, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 290, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);

			damage.bind();
			
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
			Render.drawText(Game.WIDTH / 2 + 10, 150, "You know what's better than a gun?", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "A bigger gun, use this Shotgun", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "to pump pellets of doom into", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "unsuspecting zombies! and look cool!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 270, "Price 500!", true, false);
			if(Unlocked.shotgun){
				Render.drawText(Game.WIDTH / 2 + 10, 310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 500) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);

			shotgun.bind();
			
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
			Render.drawText(Game.WIDTH / 2 + 10, 150, "Do you like boom? I like boom.", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "Zombies don't like boom.", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "But we don't worry about that.", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "RPG = boom.", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 270, "Price 1500!", true, false);
			if(Unlocked.rpg){
				Render.drawText(Game.WIDTH / 2 + 10,310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 1500) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			}
			rpg.bind();
			
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
			Render.drawText(Game.WIDTH / 2 + 10, 150, "The only good kill is a collateral kill!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 180, "Go through multiple zombies with", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 210, "one powerful bullet!", true, false);
			Render.drawText(Game.WIDTH / 2 + 10, 240, "Price 3000!", true, false);
			if(Unlocked.sniper){
				Render.drawText(Game.WIDTH / 2 + 10, 310, "You already have this!", true, true);
			}else{
			if (StatsBase.kills > 3000) {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can Afford!", true, true);
			} else {
				Render.drawText(Game.WIDTH / 2 + 10, 310, "Can not afford", true, false);
			}
			}
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);

			sniper.bind();
			
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 190, 20);
			GL11.glTexCoord2d(0.5, 0);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 190 + 300, 20);
			GL11.glTexCoord2d(0.5, 0.5);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 190 + 300, 20 + 80);
			GL11.glTexCoord2d(0, 0.5);
			GL11.glVertex2d((Game.WIDTH / 4) * 3 - 190, 20 + 80);
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
			if(!Unlocked.rateOfFire){
			if (StatsBase.kills > 1500) {
				StatsBase.kills -= 1500;
				Unlocked.rateOfFire = true;
			}
			}
			break;
		case 1:
			if(!Unlocked.damage){
			if (StatsBase.kills > 2000) {
				StatsBase.kills -= 2000;
				Unlocked.damage = true;
			}
			}
			break;
		case 2:
			if(!Unlocked.shotgun){
			if (StatsBase.kills > 500) {
				StatsBase.kills -= 500;
				Unlocked.shotgun = true;
			}
			}
			break;
		case 3:
			if(!Unlocked.rpg){
			if (StatsBase.kills > 1500) {
				StatsBase.kills -= 1500;
				Unlocked.rpg = true;
			}
			}
			break;
		case 4:
			if(!Unlocked.sniper){
			if (StatsBase.kills > 3000) {
				StatsBase.kills -= 3000;
				Unlocked.sniper = true;
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
