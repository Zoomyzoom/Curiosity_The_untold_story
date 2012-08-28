package com.Game.Main;

import java.awt.Color;
import java.util.Random;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import com.Entity.Entities.Player;
import com.Evolution.Stats.StatsBase;
import com.Game.Unlocks.Unlocked;
import com.Gui.GUIs.GUINotifications;
import com.Particle.PointParticle;

public class Render {

	public static double transx = 0;
	public static double transy = 0;
	public static PointParticle[] particles = new PointParticle[1000];

	public static Texture sky;

	public static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0.6f, 0.3f, 0.0f, 1f);
		GL11.glPointSize(3f);

		GL11.glPushMatrix();
		GL11.glTranslated(transx, transy, 0);
		if (Logic.gameState == Logic.GameState.GAME || Logic.gameState == Logic.GameState.PAUSED) {
			GL11.glEnable(GL11.GL_BLEND);
			int x = -2500;
			sky.bind();
			for (int i = 0; i < 20; i++) {
				GL11.glPushMatrix();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2d(0, 0);
				GL11.glVertex2d(x, 0);
				GL11.glTexCoord2d(0.6091, 0);
				GL11.glVertex2d(x + 2500, 0);
				GL11.glTexCoord2d(0.6091, 0.8);
				GL11.glVertex2d(x + 2500, Game.HEIGHT);
				GL11.glTexCoord2d(0, 0.82);
				GL11.glVertex2d(x, Game.HEIGHT);
				GL11.glEnd();
				GL11.glPopMatrix();
				x += 2500;
			}
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			GL11.glDisable(GL11.GL_BLEND);
		}

		GL11.glPushMatrix();
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		if (Logic.gameState == Logic.GameState.GAME || Logic.gameState == Logic.GameState.PAUSED) {
			Game.world.render();
		}
		GL11.glPopMatrix();

		drawText((int) (3 - transx), (int) 10, StatsBase.kills + " xp!", true, false);

		Game.p.draw();
		GL11.glPopMatrix();

		if (Logic.gameState == Logic.GameState.UPGRADE) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.upgrade.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.PAUSED) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.pause.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.GUN_UPGRADE) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.gunUpgrade.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.ABOUT) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.about.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.STORY) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.story.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.MENU) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.menu.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.DEAD) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.death.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.CREDITS) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.credits.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.DIFFICULTY) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.difficulty.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.HOWTOPLAY) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.howtoplay.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.OPTIONS) {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			Game.options.draw();
			GL11.glPopMatrix();
		} else if (Logic.gameState == Logic.GameState.GAME) {
			{
				{
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);

						Game.p.health2.bind();
						GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2d(0, 0);
						GL11.glVertex2d(400 - 88, 20);
						GL11.glTexCoord2d(1, 0);
						GL11.glVertex2d(400 - 88 + 32, 20);
						GL11.glTexCoord2d(1, 1);
						GL11.glVertex2d(400 - 88 + 32, 52);
						GL11.glTexCoord2d(0, 1);
						GL11.glVertex2d(400 - 88, 52);
						GL11.glEnd();
						GL11.glPopMatrix();

					
						GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2d(0, 0);
						GL11.glVertex2d(400 - 16, 20);
						GL11.glTexCoord2d(1, 0);
						GL11.glVertex2d(400 - 16 + 32, 20);
						GL11.glTexCoord2d(1, 1);
						GL11.glVertex2d(400 - 16 + 32, 52);
						GL11.glTexCoord2d(0, 1);
						GL11.glVertex2d(400 - 16, 52);
						GL11.glEnd();
						GL11.glPopMatrix();
						
						GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2d(0, 0);
						GL11.glVertex2d(400 + 88 - 32, 20);
						GL11.glTexCoord2d(1, 0);
						GL11.glVertex2d(400 + 88, 20);
						GL11.glTexCoord2d(1, 1);
						GL11.glVertex2d(400 + 88, 52);
						GL11.glTexCoord2d(0, 1);
						GL11.glVertex2d(400 + 88 - 32, 52);
						GL11.glEnd();
						GL11.glPopMatrix();

					GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
				}
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				if (Game.p.healthpack > 0) {
					Game.p.health1.bind();
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2d(0, 0);
					GL11.glVertex2d(400 - 88, 20);
					GL11.glTexCoord2d(1, 0);
					GL11.glVertex2d(400 - 88 + 32, 20);
					GL11.glTexCoord2d(1, 1);
					GL11.glVertex2d(400 - 88 + 32, 52);
					GL11.glTexCoord2d(0, 1);
					GL11.glVertex2d(400 - 88, 52);
					GL11.glEnd();
					GL11.glPopMatrix();

				}
				if (Game.p.healthpack > 1) {
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2d(0, 0);
					GL11.glVertex2d(400 - 16, 20);
					GL11.glTexCoord2d(1, 0);
					GL11.glVertex2d(400 - 16 + 32, 20);
					GL11.glTexCoord2d(1, 1);
					GL11.glVertex2d(400 - 16 + 32, 52);
					GL11.glTexCoord2d(0, 1);
					GL11.glVertex2d(400 - 16, 52);
					GL11.glEnd();
					GL11.glPopMatrix();
				}
				if (Game.p.healthpack > 2) {
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2d(0, 0);
					GL11.glVertex2d(400 + 88 - 32, 20);
					GL11.glTexCoord2d(1, 0);
					GL11.glVertex2d(400 + 88, 20);
					GL11.glTexCoord2d(1, 1);
					GL11.glVertex2d(400 + 88, 52);
					GL11.glTexCoord2d(0, 1);
					GL11.glVertex2d(400 + 88 - 32, 52);
					GL11.glEnd();
					GL11.glPopMatrix();

				}
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}

			if (Logic.bomb != null) {
				Logic.bomb.draw();
			}
			if (Unlocked.DeathWave) {
				drawText(5, Game.HEIGHT - 22, "Death Wave", false, false);
				GL11.glColor3f(1, 0, 0);
				GL11.glRectd(75, Game.HEIGHT - 20, 75 + 150, Game.HEIGHT - 11);
				GL11.glColor3f(0, 0.1f, 0.4f);
				GL11.glRectd(75, Game.HEIGHT - 20, 75 + 150 - Logic.specialtimee, Game.HEIGHT - 11);
				GL11.glColor3f(1, 1, 1);
			}
			if (Unlocked.HellRain) {
				drawText(5, Game.HEIGHT - 42, "Hell Rain", false, false);
				GL11.glColor3f(1, 0, 0);
				GL11.glRectd(75, Game.HEIGHT - 40, 75 + 150, Game.HEIGHT - 31);
				GL11.glColor3f(0, 0.1f, 0.4f);
				GL11.glRectd(75, Game.HEIGHT - 40, 75 + 150 - Logic.specialtimeq, Game.HEIGHT - 31);
				GL11.glColor3f(1, 1, 1);
			}
			if (Unlocked.MegaBomb) {
				drawText(5, Game.HEIGHT - 62, "Mega Bomb", false, false);
				GL11.glColor3f(1, 0, 0);
				GL11.glRectd(75, Game.HEIGHT - 60, 75 + 150, Game.HEIGHT - 51);
				GL11.glColor3f(0, 0.1f, 0.4f);
				GL11.glRectd(75, Game.HEIGHT - 60, 75 + 150 - (Logic.specialtimex / 6.6666666666666666), Game.HEIGHT - 51);
				GL11.glColor3f(1, 1, 1);
			}
			// health
			GL11.glColor3f(1, 0, 0);
			GL11.glRectd(Game.WIDTH / 2 - 250, 10, Game.WIDTH / 2 + 250, 20);
			GL11.glColor3f(0, 0.4f, 0f);
			GL11.glRectd(Game.WIDTH / 2 - 250, 10, Game.WIDTH / 2 - 250 + Game.p.health, 20);
			GL11.glColor3f(1, 1, 1);

			String string = "ARRRGGHHH!";
			if (Game.p.equipedGun == Player.EquipedGun.MACHINE) {
				string = "Machine Gun";
				Render.drawText(Game.WIDTH - string.length() * 13, Game.HEIGHT - 28, string, true, false);
			}
			if (Game.p.equipedGun == Player.EquipedGun.SNIPER) {
				string = "Sniper";
				Render.drawText(Game.WIDTH - string.length() * 12, Game.HEIGHT - 28, string, true, false);
			}
			if (Game.p.equipedGun == Player.EquipedGun.RPG) {
				string = "Rocket Launcher";
				Render.drawText(Game.WIDTH - string.length() * 12, Game.HEIGHT - 28, string, true, false);
			}
			if (Game.p.equipedGun == Player.EquipedGun.SHOTGUN) {
				string = "Auto ShotGun";
				Render.drawText(Game.WIDTH - string.length() * 13, Game.HEIGHT - 28, string, true, false);
			}
			/*
			 * Render.drawText(2, 100, "Machinegun kills: " +
			 * StatsBase.machineGunKills, false, false); Render.drawText(2, 120,
			 * "Sniper kills: " + StatsBase.sniperKills, false, false);
			 * Render.drawText(2, 140, "RPG kills: " + StatsBase.rpgKills,
			 * false, false);
			 * 
			 * Render.drawText(2, 160, "Machinegun Damage: " +
			 * StatsBase.machineGunDamage, false, false); Render.drawText(2,
			 * 180, "Sniper Damage: " + StatsBase.sniperDamage, false, false);
			 * Render.drawText(2, 200, "RPG Damage: " + StatsBase.rpgDamage,
			 * false, false);
			 * 
			 * Render.drawText(2, 220, "Machinegun kills untill evolve: " +
			 * StatsBase.machineGunEvolve, false, false); Render.drawText(2,
			 * 240, "Sniper kills untill evolve: " + StatsBase.sniperEvolve,
			 * false, false); Render.drawText(2, 260,
			 * "RPG kills untill evolve: " + StatsBase.rpgEvolve, false, false);
			 */

			GUINotifications.draw();

		}

		for (int i = 0; i < particles.length; i++) {
			if (particles[i] != null) {
				particles[i].draw();
				particles[i].tick();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void drawText(int x, int y, String string, boolean big, boolean particles) {
		Random rand = new Random();

		if (big) {

			if (particles) {
				Float[] col = { 1f, 0.6f, 1f };
				for (int i = 0; i < string.length() / 15; i++) {
					double dx = 0.5 - rand.nextDouble();
					double dy = 0.5 - rand.nextDouble();
					int x1 = x + rand.nextInt((int) (string.length() * 10));
					int y1 = y + rand.nextInt(24);
					addPointParticle(new PointParticle(x1, y1, dx, dy, 50, col, false));
				}
			}
			GL11.glEnable(GL11.GL_BLEND);

			Game.font.drawString(x, y, string);
			GL11.glDisable(GL11.GL_BLEND);

		} else {
			if (particles) {
				Float[] col = { 1f, 0.3f, 1f };
				for (int i = 0; i < string.length() / 20; i++) {
					double dx = 0.5 - rand.nextDouble();
					double dy = 0.5 - rand.nextDouble();
					int x1 = x + rand.nextInt((int) (string.length() * 5));
					int y1 = y + rand.nextInt(12);
					addPointParticle(new PointParticle(x1, y1, dx, dy, 50, col, false));
				}
			}
			GL11.glEnable(GL11.GL_BLEND);
			Game.font2.drawString(x, y, string);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	public static void addPointParticle(PointParticle p) {
		boolean done = false;
		for (int i = 0; i < particles.length; i++) {
			if (!done) {
				if (particles[i] == null) {
					p.setId(i);
					p.notInWorld = true;
					particles[i] = p;
					done = true;
				}
			}
		}
	}
}
