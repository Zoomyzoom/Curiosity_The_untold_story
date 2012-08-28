/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gui;

import com.Game.Main.Render;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author William
 */
public class TextPeice {
    String string;
    public double x,y;
    boolean big;
    public boolean particles;
    public int boxWidth, boxHeight;
    
    public TextPeice(String string,double x,double y,boolean big,boolean particles,int boxWidth,int boxHeight){
        this.x = x;
        this.y = y;
        this.string = string;
        this.big = big;
        this.particles = particles;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
    }
    
    public void draw(boolean selected){
        if(selected){
            GL11.glColor3f(1f, 0.5f, 00f);
        }else{
            GL11.glColor3f(0.5f, 0.35f, 0.0f);
        }
        GL11.glRectd(x, y, boxWidth, boxHeight+y);
        Render.drawText((int)x+15, (int)y, string, big, particles);
        GL11.glColor3f(1, 1, 1);
    }
}
