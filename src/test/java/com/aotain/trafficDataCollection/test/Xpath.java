package com.aotain.trafficDataCollection.test;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.aotain.common.util.LoginInfoUtil;

public class Xpath {
	public static void main(String[] args) throws DocumentException{
		String serPath = LoginInfoUtil.class.getResource("/").getPath();
		serPath = serPath.replaceAll("%20", " ");
		File file = new File("E:/sysmenu.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		List<Element> list = doc.selectNodes("//*[@url!='']");
		System.out.println(list.get(1).selectNodes(".//*[@url!='']"));
		System.out.println(list.get(1).getPath().split("/").length);
		System.out.println(list.get(1).attribute("url"));
		System.out.println(list.get(1).attribute("name"));
	}
}
