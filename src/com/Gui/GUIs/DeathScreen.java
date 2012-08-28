package com.Gui.GUIs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import com.Evolution.Stats.StatsBase;
import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUI;
import com.Gui.TextPeice;

public class DeathScreen extends GUI {

	public DeathScreen() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/dead.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		TextPeice[] tps = new TextPeice[1];
		tps[0] = new TextPeice("Continue", 316, Game.HEIGHT-50, true, true, 446, 29);
		this.setTps(tps);
	}

	public void draw() {
		Game.song1.stop();
		Game.song2.stop();
		Game.song3.stop();
		if (backT1 != null) {
			GL11.glColor3f(1f, 1f, 1f);
			backT1.bind();
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0.782, 0);
		GL11.glVertex2d(Game.WIDTH, 0);
		GL11.glTexCoord2d(0.782, 0.58);
		GL11.glVertex2d(Game.WIDTH, Game.HEIGHT);
		GL11.glTexCoord2d(0, 0.58);
		GL11.glVertex2d(0, Game.HEIGHT);
		GL11.glEnd();
		this.drawBasic();
		Render.drawText(300, 200, "Kills: " + (StatsBase.machineGunKills+ StatsBase.sniperKills+ StatsBase.rpgKills+ StatsBase.shotgunKills), true, true);
		Render.drawText(300, 240, "Kills with Machine Gun: " + StatsBase.machineGunKills, true, true);
		Render.drawText(300, 280, "Kills with a Sniper: " + StatsBase.sniperKills, true, true);
		Render.drawText(300, 320, "Kills with an RPG: " + StatsBase.rpgKills, true, true);
		Render.drawText(300, 360, "Kills with an Shotgun: " + StatsBase.shotgunKills, true, true);
		Render.drawText(300, 400, "DeadKittens: " + StatsBase.kittyKills, true, true);
	}

	public void action(int id) {
		switch (id) {
		case 0:
			Logic.gameState=Logic.GameState.MENU;
			break;
		}
	}
}
