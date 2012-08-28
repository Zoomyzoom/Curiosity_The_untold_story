package com.Game.Main;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Point;

import com.Entity.Entities.Zombie;
import com.Entity.Entities.Bomb;
import com.Entity.Entities.Bullet;
import com.Evolution.Stats.StatsBase;
import com.Game.Unlocks.Unlocked;
import com.Gui.TextPeice;
import com.Gui.GUIs.GUINotifications;
import com.Particle.PointParticle;
import java.awt.Polygon;

public class Logic {

	private static boolean jumpKey;
	public static GameState gameState = GameState.STORY;

	public enum GameState {

		GAME, PAUSED, MENU, UPGRADE, OPTIONS, GUN_UPGRADE, ABOUT, DEAD, CREDITS, DIFFICULTY, HOWTOPLAY, STORY;
	}

	public static void logic() {

		Render.transx = -Game.p.x + Game.WIDTH / 2;
		//debugControls();
		if (gameState == GameState.GAME) {
			Game.world.tick();
			PlayerControls();
			Game.p.tick();
			if (bomb != null) {
				bomb.tick();
			}
			StatsBase.tick();
			GUINotifications.tick();
		} else if (gameState == GameState.PAUSED) {
			PausedControls();
		} else if (gameState == GameState.UPGRADE) {
			UpgradeControls();
		} else if (gameState == GameState.GUN_UPGRADE) {
			GunUpgradeControls();
		} else if (gameState == GameState.ABOUT) {
			AboutControls();
		} else if (gameState == GameState.MENU) {
			MenuControls();
		} else if (gameState == GameState.DEAD) {
			DeadControls();
		}else if (gameState == GameState.CREDITS) {
			CreditControls();
		}else if (gameState == GameState.DIFFICULTY) {
			DifficultyControls();
		}else if (gameState == GameState.HOWTOPLAY) {
			HowtoPlayControls();
		}else if (gameState == GameState.OPTIONS) {
			OptionsControls();
		}else if (gameState == GameState.STORY) {
			StoryControls();
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				gameState = GameState.MENU;
			}
		}

	}
	private static void StoryControls() {
		TextPeice[] tps = Game.story.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.story.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.story.action(i);
				}
			}
		}
	}
	
	private static void OptionsControls() {
		TextPeice[] tps = Game.options.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.options.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.options.action(i);
				}
			}
		}
	}
	
	private static void HowtoPlayControls() {
		TextPeice[] tps = Game.howtoplay.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.howtoplay.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.howtoplay.action(i);
				}
			}
		}
	}
	
	private static void DifficultyControls() {
		TextPeice[] tps = Game.difficulty.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.difficulty.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.difficulty.action(i);
				}
			}
		}
	}
	
	private static void CreditControls() {
		TextPeice[] tps = Game.credits.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.credits.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.credits.action(i);
				}
			}
		}
	}
	
	private static void DeadControls() {
		TextPeice[] tps = Game.death.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.death.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.death.action(i);
				}
			}
		}
	}

	private static void MenuControls() {
		TextPeice[] tps = Game.menu.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.menu.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.menu.action(i);
				}
			}
		}
	}

	private static void AboutControls() {
		TextPeice[] tps = Game.about.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.about.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.about.action(i);
				}
			}
		}
	}

	private static void GunUpgradeControls() {
		TextPeice[] tps = Game.gunUpgrade.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.gunUpgrade.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.gunUpgrade.action(i);
				}
			}
		}
	}

	private static void UpgradeControls() {
		TextPeice[] tps = Game.upgrade.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.upgrade.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.upgrade.action(i);
				}
			}
		}
	}

	private static void PausedControls() {
		TextPeice[] tps = Game.pause.getTps();
		for (int i = 0; i < tps.length; i++) {
			Polygon poly = new Polygon();
			poly.addPoint((int) tps[i].x, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth / 2, (int) tps[i].y);
			poly.addPoint((int) tps[i].x + tps[i].boxWidth, (int) tps[i].y - tps[i].boxHeight);
			poly.addPoint((int) tps[i].x, (int) tps[i].y - tps[i].boxHeight);

			if (poly.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY() - 29)) {
				Game.pause.selectionId = i;

				if (Mouse.isButtonDown(0)) {
					Game.pause.action(i);
				}
			}
		}
	}

	static long shoottime = System.currentTimeMillis();
	public static double specialtimee = 0;
	public static double specialtimeq = 0;
	public static double specialtimex = 0;
	public static Bomb bomb;

	static boolean scrolled = false;
	static boolean keyscrolled = false;
	static boolean rightClicked = false;

	private static void PlayerControls() {
		if(Mouse.isButtonDown(1)){
			if(!rightClicked){
			rightClicked = true;
			Game.p.usePack();
			}
		}else{
			rightClicked = false;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Logic.gameState=Logic.GameState.PAUSED;
			return;
		}
		
		double wheel = Mouse.getDWheel();
		if (!scrolled && (wheel > 0 || wheel < 0)) {
			scrolled = true;
			{
				if (wheel < 0) {
					Game.p.AdvanceGun();
				} else {
					Game.p.RegressGun();
				}
			}
		}
		if (!keyscrolled) {
			if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				keyscrolled = true;
				Game.p.AdvanceGun();
				return;
			} else {
				if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
					keyscrolled = true;
					Game.p.RegressGun();
					return;
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

		}
		if (wheel > 0 || wheel < 0) {
			scrolled = false;
		}

		if ((!Keyboard.isKeyDown(Keyboard.KEY_F)) && (!Keyboard.isKeyDown(Keyboard.KEY_R))) {
			keyscrolled = false;
		}

		Random rand = new Random();
		int delay = 0;
		if (Game.p.equipedGun == com.Entity.Entities.Player.EquipedGun.SNIPER) {
			delay = 400;
		} else if (Game.p.equipedGun == com.Entity.Entities.Player.EquipedGun.RPG) {
			delay = 50;
		}else if (Game.p.equipedGun == com.Entity.Entities.Player.EquipedGun.SHOTGUN) {
			delay = 300;
		}
		if (System.currentTimeMillis() - shoottime > delay) {
			shoottime = System.currentTimeMillis();
			if (Mouse.isButtonDown(0)) {
				Game.p.shoot();
			}
		}else if (Unlocked.rateOfFire&&(System.currentTimeMillis() - shoottime > delay*0.4)) {
			shoottime = System.currentTimeMillis();
			if (Mouse.isButtonDown(0)) {
				Game.p.shoot();
			}
		}
		if (specialtimee > 0) {
			specialtimee -= 0.3;
		}
		if (Unlocked.DeathWave) {
			if (specialtimee <= 0) {
				if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
					specialtimee = 150;
					for (int i = 0; i < 200; i++) {
						double deltaX = -(-Mouse.getX() + (Game.p.width / 2) - (-Game.p.x - Render.transx));
						double deltaY = (Game.HEIGHT - Mouse.getY() - (Game.p.height / 2)) - (Game.p.y + Render.transy);
						double angle = (Math.atan2(deltaY, deltaX)) + i * 0.9;
						deltaX = 8 * Math.cos(angle);
						deltaY = 8 * Math.sin(angle);
						Game.world.addBullet(new Bullet(Game.p.x + Game.p.width / 2 + deltaX * 3, Game.p.y + Game.p.height / 2 + deltaY * 5, deltaX, deltaY, 130));
					}
				}
			}
		}
		if (specialtimeq > 0) {
			specialtimeq -= 0.3;
		}
		if (Unlocked.HellRain) {
			if (specialtimeq <= 0) {
				if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
					specialtimeq = 150;
					for (int i = 0; i < 200; i++) {
						double deltaX = -(-Mouse.getX() + (Game.p.width / 2) - (-Game.p.x - Render.transx));
						double deltaY = (Game.HEIGHT - Mouse.getY() - (Game.p.height / 2)) - (Game.p.y + Render.transy);
						double angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) * 1.5);
						deltaX = (0.5 - rand.nextDouble()) * 7;
						deltaY = -12 + rand.nextDouble() * 4;
						Game.world.addBullet(new Bullet(Game.p.x + Game.p.width / 2 + deltaX * 3, Game.p.y + Game.p.height / 2 + deltaY * 5, deltaX, deltaY, 130 + rand.nextInt(30)));
					}
				}
			}
		}

		if (specialtimex > 0) {
			specialtimex -= 0.5;
		}
		if (Unlocked.MegaBomb) {
			if (specialtimex <= 0) {
				if (Keyboard.isKeyDown(Keyboard.KEY_X)) {

					specialtimex = 1000;
					bomb = new Bomb(Game.p.x + Game.p.width / 2 - 8, Game.p.y, 0.5 - rand.nextDouble(), -2);
				}
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			Game.p.setDx(Game.p.walkspeed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Game.p.setDx(-Game.p.walkspeed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)||Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (!jumpKey) {
				Game.p.jump();
				jumpKey = true;
			}
		} else {
			jumpKey = false;
		}
		if (Game.p.getX() < 555) {
			Game.p.setX(555);
		}
		if (Game.p.getX() > 29025) {
			Game.p.setX(29025);
		}
	}

	private static void debugControls() {
		Random rand = new Random();
		if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
			Game.world.respawnKitten(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			Game.p.setLoc((int) (Game.p.getX() + 80), 0);
			Game.p.setDy(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
			StatsBase.kills += 1000;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
			for (int i = 0; i < Game.world.zs.length; i++) {
				Game.world.respawnZombie(i);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
			Unlocked.rateOfFire = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
			Unlocked.sniper = true;
			Unlocked.shotgun = true;
			Unlocked.rpg = true;
			Unlocked.DeathWave = true;
			Unlocked.HellRain = true;
			Unlocked.MegaBomb = true;
			Unlocked.rateOfFire = true;
		}
	}
}
