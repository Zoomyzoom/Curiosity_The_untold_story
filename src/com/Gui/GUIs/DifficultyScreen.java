package com.Gui.GUIs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Game.Options.Options;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUI;
import com.Gui.TextPeice;
import com.Level.Main.World;

public class DifficultyScreen extends GUI {

	public DifficultyScreen() {
		try {
			backT1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/difficulty.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		TextPeice[] tps = new TextPeice[4];
		tps[0] = new TextPeice("Back", 355,500, true, true, 435, 29);
		tps[1] = new TextPeice("Nublet", 348,250, true, true, 445, 29);
		tps[2] = new TextPeice("Noob", 352,300, true, true, 441, 29);
		tps[3] = new TextPeice("Haxorz", 347,350, true, true, 448, 29);
		this.setTps(tps);
	}

	public void draw() {

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
	}

	public void action(int id) {
		switch (id) {
		case 0:
			Logic.gameState=Logic.GameState.MENU;
			break;
		case 1:
			Options.difficulty = 7;
			Logic.gameState=Logic.GameState.MENU;
			break;
		case 2:
			Options.difficulty = 15;
			Logic.gameState=Logic.GameState.MENU;
			break;
		case 3:
			Options.difficulty = 27;
			Logic.gameState=Logic.GameState.MENU;
			break;
		}
		Game.world = World.loadTestLevel();
	}
}
