package com.fescotech.apps.entryonline.web.kaptcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.google.code.kaptcha.Producer;
/**
 * 简单验证码生成工具
 * @author paladin
 */
public class DefaultKaptchaProducer implements Producer {
	@Override
	public BufferedImage createImage(String sRand) {
		if(sRand==null || sRand.trim().equals(""))
			return null;
		int width=60, height=20;
		BufferedImage	image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		g.setColor(getRandColor(160,200));

		Random random = new Random();
		for (int i=0;i<155;i++){
			 int x = random.nextInt(width);
			 int y = random.nextInt(height);
			 int xl = random.nextInt(12);
			 int yl = random.nextInt(12);
			 g.drawLine(x,y,x+xl,y+yl);
		}

		char[] randChars=sRand.trim().toCharArray();
		for (int i=0;i<randChars.length;i++){
		    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
		    g.drawString(String.valueOf(randChars[i]),13*i+6,16);
		}
		g.dispose();
		return image;
	}

	@Override
	public String createText() {
		Random random = new Random();
		String sRand="";
		for (int i=0;i<4;i++){
		    String rand=String.valueOf(random.nextInt(10));
		    sRand+=rand;
		}
		return sRand;
	}
	private Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
     }
}
