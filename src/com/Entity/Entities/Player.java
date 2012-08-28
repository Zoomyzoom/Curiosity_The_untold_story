package com.Entity.Entities;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.Entity.LivingEntity;
import com.Game.Main.Game;
import com.Game.Main.Logic;
import com.Game.Main.Render;
import com.Game.Unlocks.Unlocked;
import com.Level.Main.World;
import com.Particle.PointParticle;

public class Player extends LivingEntity {

	public boolean jumped = false;
	public boolean jumped2 = false;
	Rectangle rect = new Rectangle();

	Texture still1, still2, still3, still4;
	Texture machineGun, sniper, rpg, shotgun;
	public Texture health1;
	public Texture health2;

	public Player(double x, double y) {
		this.health = 500;
		this.fullhealth = 500;
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 80;
		try {
			health1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/health/mini healthpack.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			health2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/gui/health/g-mini healthpack.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		try {
			shotgun = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/guns/autoshotgun.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			rpg = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/guns/rpg.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			sniper = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/guns/sniper.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			machineGun = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/items/guns/machineGun.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			still1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/player/still/1.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			still2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/player/still/2.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			still3 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/player/still/3.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		try {
			still4 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/player/still/4.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		rect.setBounds((int) x, (int) y, (int) width, (int) height);
	}

	public void shoot() {
		Random rand = new Random();
		Float[] col = { 0.5f, 0.5f, 0.5f };

		double Speed = 10;

		double deltaX = -(-Mouse.getX() + (width / 2) - (-x - Render.transx));
		double deltaY = (Game.HEIGHT - Mouse.getY() - (height / 2)) - (y + Render.transy);
		double angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) / 10);
		deltaX = Speed * Math.cos(angle);
		deltaY = Speed * Math.sin(angle);
		if (equipedGun == EquipedGun.RPG) {
			Game.world.addExplosiveBullet(new ExplosiveBullet(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 3, deltaX, deltaY, 130));
		} else if (equipedGun == EquipedGun.MACHINE) {
			if(Unlocked.rateOfFire&&rand.nextBoolean()){
				Game.world.addBullet(new Bullet(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 3, deltaX, deltaY, 130));
			}
			Game.world.addBullet(new Bullet(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 3, deltaX, deltaY, 130));
		} else if (equipedGun == EquipedGun.SNIPER) {
			Game.world.addSniperBullet(new SniperBullet(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 4.2, deltaX * 1.4, deltaY * 1.4, 100));
		} else if (equipedGun == EquipedGun.SHOTGUN) {
			
			
			for(int i = 0;i<5;i++){
				angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) / 5);
				deltaX = (Speed+ ((0.5 - rand.nextDouble()) / 5)) * Math.cos(angle);
				deltaY = (Speed+ ((0.5 - rand.nextDouble()) / 5)) * Math.sin(angle);
				
				Game.world.addShotgunBullet(new ShotgunBullet(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 3, deltaX, deltaY, 20+rand.nextInt(10)));
			}
		
		
		}
		angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) / 6);
		deltaX = Speed * Math.cos(angle);
		deltaY = Speed * Math.sin(angle);
		Game.world.addPointParticle(new PointParticle(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 5, deltaX / 2, deltaY / 2, 10 + rand.nextInt(10), col, false));
		angle = (Math.atan2(deltaY, deltaX)) + ((0.5 - rand.nextDouble()) / 6);
		deltaX = Speed * Math.cos(angle);
		deltaY = Speed * Math.sin(angle);
		Game.world.addPointParticle(new PointParticle(x + width / 2 + deltaX * 3, y + height / 2 + deltaY * 5, deltaX / 2, deltaY / 2, 10 + rand.nextInt(10), col, false));
	}

	int frame = 1;

	@Override
	public void draw() {
		if(health>this.fullhealth){
			health=fullhealth;
		}
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		if (frame == 1) {
			still1.bind();
		} else if (frame == 2) {
			still2.bind();
		} else if (frame == 3) {
			still3.bind();
		} else if (frame == 4) {
			still4.bind();
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1, 1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2f(0.8f, 0);
		GL11.glVertex2d(x + width, y);
		GL11.glTexCoord2f(0.8f, 0.8f);
		GL11.glVertex2d(x + width, y + height);
		GL11.glTexCoord2f(0, 0.8f);
		GL11.glVertex2d(x, y + height);
		GL11.glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDisable(GL11.GL_BLEND);
		double deltaX = -(-Mouse.getX() + (width / 2) - (-x - Render.transx));
		double deltaY = (Game.HEIGHT - Mouse.getY() - (height / 2)) - (y + Render.transy);
		double angle = (Math.atan2(deltaY, deltaX));

		double rotAngle = (angle * 180 / Math.PI);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		if (equipedGun == EquipedGun.MACHINE) {
			machineGun.bind();
		} else if (equipedGun == EquipedGun.SNIPER) {
			sniper.bind();
		} else if (equipedGun == EquipedGun.RPG) {
			rpg.bind();
		} else if (equipedGun == EquipedGun.SHOTGUN) {
			shotgun.bind();
		}

		GL11.glTranslatef((float) (x + width / 2), (float) (y + height / 2), 0);
		GL11.glRotatef((float) rotAngle, 0, 0, 1);
		GL11.glTranslatef((float) (-x - width / 2), (float) (-y - height / 2), 0);

		if (rotAngle > -92 && rotAngle < 92) {
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d(x - 10, y + 30);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d(x + width + 10, y + 30);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d(x + width + 10, y + height - 30);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d(x - 10, y + height - 30);
			GL11.glEnd();
			GL11.glPopMatrix();
		} else if (rotAngle > 0 && rotAngle < 92) {
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d(x - 10, y + 30);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d(x + width + 10, y + 30);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d(x + width + 10, y + height - 30);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d(x - 10, y + height - 30);
			GL11.glEnd();
			GL11.glPopMatrix();
		} else {
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d(x - 10, y + 30);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d(x + width + 10, y + 30);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d(x + width + 10, y + height - 30);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d(x - 10, y + height - 30);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDisable(GL11.GL_BLEND);
		

		
	}

	long animate = System.currentTimeMillis();

	public EquipedGun equipedGun = EquipedGun.MACHINE;
	public int healthpack = 0;

	public static enum EquipedGun {
		MACHINE, RPG, SNIPER, SHOTGUN,
	}

	@Override
	public void tick() {
		if(healthpack>3){
			healthpack=2;
		}
		if (health < 0) {
			Game.p = new Player(300, 0);
			Game.world = World.loadTestLevel();
			Logic.gameState = Logic.GameState.DEAD;
		}
		if (System.currentTimeMillis() - animate > 90) {
			animate = System.currentTimeMillis();
			frame++;
			if (frame >= 5) {
				frame = 1;
			}
		}
		dy += 0.2;
		if (this.moveCollide(dx, dy)) {
			jumped = false;
			jumped2 = false;
		}
		for (int i = 0; i < Game.world.hps.length; i++) {
			if (Game.world.hps[i] != null) {
				if (rect.intersects(Game.world.hps[i].rect)) {
					Game.world.hps[i]=null;
					healthpack++;
					if(healthpack>3){
						healthpack=3;
					}
				}
			}
		}
		rect.setBounds((int) x, (int) y, (int) width, (int) height);
		for (int i = 0; i < Game.world.ks.length; i++) {
			if (Game.world.ks[i] != null) {
				if (rect.intersects(Game.world.ks[i].rect)) {
					if(!Unlocked.MaxHealth){
						health--;
						System.out.println("OUCH!");
					}else{
						health-=0.5;
						System.out.println("HALF OUCH!");
					}
				}
			}
		}
		for (int i = 0; i < Game.world.zs.length; i++) {
			if (Game.world.zs[i] != null) {
				if (rect.intersects(Game.world.zs[i].rect)) {
					if(!Unlocked.MaxHealth){
						health--;
						System.out.println("OUCH!");
					}else{
						health-=0.5;
						System.out.println("HALF OUCH!");
					}
				}
			}
		}
	}

	public void jump() {
		if (!jumped) {
			jumped = true;
			this.dy = -6;
		} else {
			if (Unlocked.DoubleJump) {
				if (!jumped2) {
					jumped2 = true;
					this.dy = -6;
					Float[] col = { 0.5f, 0.5f, 0.5f };
					Random rand = new Random();
					for (int i = 0; i < 30; i++) {
						Game.world.addPointParticle(new PointParticle(x + (width / 30) * i, y + height, 0.5 - rand.nextDouble(), rand.nextDouble(), 30 + rand.nextInt(20), col, false));
					}
				}
			}
		}
	}

	public void RegressGun() {
		if (equipedGun == EquipedGun.SHOTGUN) {
			if (Unlocked.rpg) {
				equipedGun = EquipedGun.RPG;
				return;
			} else if (Unlocked.sniper) {
				equipedGun = EquipedGun.SNIPER;
				return;
			} else {
				equipedGun = EquipedGun.MACHINE;
				return;
			}
		}
		if (equipedGun == EquipedGun.MACHINE) {
			if (Unlocked.shotgun) {
				equipedGun = EquipedGun.SHOTGUN;
				return;
			} else if (Unlocked.rpg) {
				equipedGun = EquipedGun.RPG;
				return;
			} else if (Unlocked.sniper) {
				equipedGun = EquipedGun.SNIPER;
				return;
			}
		}
		if (equipedGun == EquipedGun.RPG) {
			if (Unlocked.sniper) {
				equipedGun = EquipedGun.SNIPER;
				return;
			} else {
				equipedGun = EquipedGun.MACHINE;
				return;
			}
		}
		if (equipedGun == EquipedGun.SNIPER) {
			equipedGun = EquipedGun.MACHINE;
			return;
		}

	}

	public void AdvanceGun() {
		if (equipedGun == EquipedGun.RPG) {
			if (Unlocked.shotgun) {
				equipedGun = EquipedGun.SHOTGUN;
				return;
			} else {
				equipedGun = EquipedGun.MACHINE;
				return;
			}
		}
		if (equipedGun == EquipedGun.SNIPER) {
			if (Unlocked.rpg) {
				equipedGun = EquipedGun.RPG;
				return;
			} else if (Unlocked.shotgun) {
				equipedGun = EquipedGun.SHOTGUN;
				return;
			} else {
				equipedGun = EquipedGun.MACHINE;
				return;
			}
		}
		if (equipedGun == EquipedGun.MACHINE) {
			if (Unlocked.sniper) {
				equipedGun = EquipedGun.SNIPER;
				return;
			} else if (Unlocked.rpg) {
				equipedGun = EquipedGun.RPG;
				return;
			} else if (Unlocked.shotgun) {
				equipedGun = EquipedGun.SHOTGUN;
				return;
			}
		}
		if (equipedGun == EquipedGun.SHOTGUN) {
				equipedGun = EquipedGun.MACHINE;
				return;
		}
	}

	public void usePack() {
		if(healthpack>0){
			healthpack--;
			health+=100;
		}
	}

}
