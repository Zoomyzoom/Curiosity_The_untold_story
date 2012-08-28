package com.Entity.Entities;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.Entity.LivingEntity;
import com.Evolution.Stats.StatsBase;
import com.Game.Main.Game;
import com.Particle.PointParticle;

public class Kitten extends LivingEntity {

    Rectangle rect = new Rectangle();
    Texture tex1,tex2;

    public Kitten(double x, double y, double width, double height) {
        Random rand = new Random();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walkspeed = this.walkspeed - rand.nextDouble();
        rect.setBounds((int) x, (int) y, (int) width, (int) height);
        
        try {
			tex1 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/kitty/wkitty/1.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
        try {
			tex2 = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/textures/kitty/wkitty/2.png")));
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
        
    }
    
    int frame = 1;
    @Override
    public void draw() {
        GL11.glColor3f(1, 0, 0);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x, y - 5);
        GL11.glVertex2d(x + width, y - 5);
        GL11.glVertex2d(x + width, y - 3);
        GL11.glVertex2d(x, y - 3);
        GL11.glEnd();
        GL11.glColor3f(1, 1, 1);

        if(health>=0){
            GL11.glColor3f(0, 1, 0);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x, y - 5);
        GL11.glVertex2d(x + (width/fullhealth)*health, y - 5);
        GL11.glVertex2d(x + (width/fullhealth)*health, y - 3);
        GL11.glVertex2d(x, y - 3);
        GL11.glEnd();
        GL11.glColor3f(1, 1, 1);
        }

        GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		if (frame == 1) {
			tex1.bind();
		} else if (frame == 2) {
			tex2.bind();
		}
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1, 1, 1);
		if(dx<0){
			GL11.glTexCoord2f(0.8f, 0);
			GL11.glVertex2d(x, y);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2d(x + width, y);
			GL11.glTexCoord2f(0f, 0.7f);
			GL11.glVertex2d(x + width, y + height);
			GL11.glTexCoord2f(0.8f, 0.7f);
			GL11.glVertex2d(x, y + height);
			GL11.glEnd();
		}else{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2d(x, y);
			GL11.glTexCoord2f(0.8f, 0);
			GL11.glVertex2d(x + width, y);
			GL11.glTexCoord2f(0.8f, 0.7f);
			GL11.glVertex2d(x + width, y + height);
			GL11.glTexCoord2f(0, 0.7f);
			GL11.glVertex2d(x, y + height);
			GL11.glEnd();
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,0);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
    }
    long animate = System.currentTimeMillis();
    @Override
    public void tick() {
    	if (System.currentTimeMillis() - animate > 400) {
			animate = System.currentTimeMillis();
			frame++;
			if (frame >= 3) {
				frame = 1;
			}
		}
        if (Math.abs(dx) > 20) {
            Game.world.respawnKitten(id);
        }
        if (health < 0) {
            Game.world.respawnKitten(id);
            
            for(int i = 0;i<70;i++){
				Random rand = new Random();
				Float[] col = {0.4f+rand.nextFloat()/4,0.4f+rand.nextFloat()/4,0.4f+rand.nextFloat()/4};
				double ddx = rand.nextDouble()/2;
				double ddy = rand.nextDouble()/2;
				Game.world.addPointParticle(new PointParticle(x+rand.nextInt((int) width), y+rand.nextInt((int) height), ddy, ddy, 20+rand.nextInt(30), col, false));
			}
        }
        dy += 0.2;
        this.WalkToPlayer();
        this.moveCollide(dx, dy);
        rect.setBounds((int) x, (int) y, (int) width, (int) height);
        Bullet[] bs = Game.world.bs;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] != null) {
                if (bs[i].rect.intersects(rect)) {
                    health-=StatsBase.machineGunDamage;
                    StatsBase.machineGunEvolve--;
                    Game.world.bs[i] = null;
                }
            }
        }
        SniperBullet[] sbs = Game.world.sbs;
        for (int i = 0; i < sbs.length; i++) {
            if (sbs[i] != null) {
                if (sbs[i].rect.intersects(rect)) {
                    health-=StatsBase.sniperDamage;
                    StatsBase.sniperEvolve--;
                }
            }
        }
        ShotgunBullet[]sgbs = Game.world.sgbs;
		for (int i = 0; i < sgbs.length; i++) {
			if (sgbs[i] != null) {
				if (sgbs[i].rect.intersects(rect)) {
					health -= StatsBase.shotgunDamage;
					StatsBase.shotgunEvolve--;
				}
			}
		}
		ExplosiveBullet[] ebs = Game.world.ebs;
		for (int i = 0; i < ebs.length; i++) {
			if (ebs[i] != null) {
				if (ebs[i].rect.intersects(rect)) {
					health -= StatsBase.rpgDamage;
					
					Game.world.ebs[i].explode();
				}
			}
		}
    }
}
