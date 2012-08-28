package com.Gui;

import com.Game.Main.Render;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public abstract class GUI {

    public int x, y, width, height;
    public int selectionId = 0;
    public TextPeice[] tps = new TextPeice[30];
    public String title = "";
    public Texture backT1;
    public Texture backT2;
    public Texture backT3;
    public Texture backT4;

    public void drawBasic() {
        GL11.glColor3f(0.2f, 0.5f, 0.2f);

        //GL11.glRectd(x + width / 2 - title.length() * 8 - 4, y + 5, x + width / 2 - title.length() * 8 + width / 4, y + 31);
        Render.drawText(x + width / 2 - title.length() * 8, y + 5, title, true, false);
        for (int i = 0; i < tps.length; i++) {
            if (tps[i] != null) {
                if (i == selectionId) {
                    tps[i].draw(true);
                } else {
                    tps[i].draw(false);
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height2) {
        height = height2;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width2) {
        width = width2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x2) {
        x = x2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y2) {
        y = y2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TextPeice[] getTps() {
        return tps;
    }

    public void setTps(TextPeice[] ts) {
        tps = ts;
    }

    public int getSelectionId() {
        return selectionId;
    }
}
