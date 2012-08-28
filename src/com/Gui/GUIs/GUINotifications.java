package com.Gui.GUIs;

import com.Game.Main.Game;
import com.Game.Main.Render;
import com.Gui.GUI;

public class GUINotifications extends GUI{

	static String mes1 = "";
	static String mes2 = null;
	static int time = 130;
	static int xoffs, xoffs2;
	
	static boolean border = true;
	
	
	public static void setMessage(String ms1,String ms2,int xofs,int xofs2,boolean hasBorder){
		mes1 = ms1;
		mes2 = ms2;
		xoffs = xofs;
		xoffs2 = xofs2;
		time=130;
		border = hasBorder;
	}
	
	
	
	public static void draw(){
		if(border){
			
		}
		if(mes1!=null){
		Render.drawText(Game.WIDTH/2-(mes1.length()*5)+xoffs, 100, mes1, true, true);
		}
		if(mes2!=null){
			Render.drawText(Game.WIDTH/2-(mes2.length()*5)+xoffs2, 135, mes2, true, true);
		}
	}
	
	public static void tick(){
		if(mes1!=null){
			time--;
			if(time<0){
				mes1=null;
				mes2=null;
				time=130;
			}
		}
	}
}
