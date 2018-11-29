package com.aotain.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * 验证码生成器类，可生成数字、大写、小写字母及三者混合类型的验证码。 支持自定义验证码字符数量； 支持自定义验证码图片的大小； 支持自定义需排除的特殊字符；
 * 支持自定义干扰线的数量； 支持自定义验证码图文颜色
 * 
 * @author shiyz
 * @version 1.0
 */
public class SecurityImage {
	/**
	 * 生成验证码图片
	 * 
	 * @param securityCode
	 *            验证码字符
	 * @return BufferedImage 图片
	 */
	public static BufferedImage createImage(String securityCode, int width, int height) {
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		Random random = new Random();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		// 设置字体
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		g.setFont(font);
		// 画边框
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width - 1, height - 1);

		g.setColor(Color.GRAY);
		// 随机产生干扰线
		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(2);
			int y1 = random.nextInt(2);
			g.drawLine(x, y, x + x1, y + y1);
		}
/*		// 数字字母集合
		char[] numbersAndLettersStore = ("1234567890").toCharArray();
		String randomCode = "";*/
		int red = 0, green = 0, blue = 0;
		for(int i = 0; i < securityCode.length();i++){
			red = random.nextInt(63);
			green = random.nextInt(63);
			blue = random.nextInt(127);
			g.setColor(new Color(red, green, blue));
            g.drawString(String.valueOf(securityCode.charAt(i)), 12 * i + 7, 16);
       }
/*		for (int i = 0; i < 4; i++) {
			red = random.nextInt(63);
			green = random.nextInt(63);
			blue = random.nextInt(127);
			g.setColor(new Color(red, green, blue));
			String tem = String.valueOf(numbersAndLettersStore[random
					.nextInt(10)]);
			randomCode += tem;
			g.drawString(tem, 12 * i + 7, 16);
		}*/
		g.dispose();
		return buffImg;
	}

	/**
	 * 返回验证码图片的流格式
	 * 
	 * @param securityCode
	 *            验证码
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @return ByteArrayInputStream 图片流
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode, int width, int height) {
		BufferedImage image = createImage(securityCode, width, height);
		return convertImageToStream(image);
	}

	/**
	 * 将BufferedImage转换成ByteArrayInputStream
	 * 
	 * @param image
	 *            图片
	 * @return ByteArrayInputStream 流
	 */
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		byte[] bts = bos.toByteArray();
		inputStream = new ByteArrayInputStream(bts);
		
		return inputStream;
	}
}
