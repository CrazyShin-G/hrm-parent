package com.erocraft.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityUtils {
	private static Properties p = new Properties();
	static {
		p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
		p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
	}

	public static String getContentByTemplate(Object model, String templateFilePathAndName){
		try {
			Velocity.init(p);
			Template template = Velocity.getTemplate(templateFilePathAndName);
			VelocityContext context = new VelocityContext();
			context.put("model", model);
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			String retContent = writer.toString();
			writer.close();
			return retContent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void staticByTemplate(Object model, String templateFilePathAndName, String targetFilePathAndName) {
		try {
			Velocity.init(p);
			Template template = Velocity.getTemplate(templateFilePathAndName);
			
			VelocityContext context = new VelocityContext();
			context.put("model", model);
			FileOutputStream fos = new FileOutputStream(targetFilePathAndName);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			template.merge(context, writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void staticBySimple(Object content, String targetFilePathAndName) {
		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		String template = "${content}";
		VelocityContext context = new VelocityContext();
		context.put("content", content);
		StringWriter writer = new StringWriter();
		ve.evaluate(context, writer, "", template);
		try {
			FileWriter fileWriter = new FileWriter(new File(targetFilePathAndName));
			fileWriter.write(writer.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
