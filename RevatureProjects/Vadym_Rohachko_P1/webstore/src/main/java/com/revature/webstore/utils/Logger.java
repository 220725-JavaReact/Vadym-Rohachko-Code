package com.revature.webstore.utils;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Logger {
	// Create the instance of Logger
	private static Logger single_instance_of_logger = null;

	// Create the private constructor to prevent from creating the object
	private Logger() {
	}

	// Create the method that will return the same object every time it is called
	public static Logger getInstance() {
		if (single_instance_of_logger == null)
			// Create the object
			single_instance_of_logger = new Logger();
		return single_instance_of_logger;
	}

	public static void log(LogLevel level, String mess) {
		Util util = new Util(level, mess);
		writeToFile(util.toString());
	}

	private static void writeToFile(String message) {
		try (FileWriter write = new FileWriter("D:\\java\\java_my_repo\\Vadym-Rohachko-Code\\RevatureProjects\\Vadym_Rohachko_P1\\webstore\\onlinestore.log", true)) {
			write.append(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Utility class to create the string for writeToFile method (String toString()
	// is overridden)
	private static class Util {
		LogLevel logLevel;
		String formattedDateTime;
		String message;

		public Util(LogLevel loglevel, String message) {
			super();
			this.logLevel = loglevel;
			this.message = message;

			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.now();
			this.formattedDateTime = dateTime.format(format);
		}

		@Override
		public String toString() {
			return formattedDateTime + " " + logLevel + " " + message + "\n";
		}
	}

	public enum LogLevel {
		info, debug, verbose, warning, fatal, error
	}

	private static String getResourcePath() {
		try {
			URI resourcePathFile = System.class.getResource("/RESOURCE_PATH").toURI();
			String resourcePath = Files.readAllLines(Paths.get(resourcePathFile)).get(0);
			URI rootURI = new File("").toURI();
			URI resourceURI = new File(resourcePath).toURI();
			URI relativeResourceURI = rootURI.relativize(resourceURI);
			return relativeResourceURI.getPath();
		} catch (Exception e) {
			return null;
		}
	}
}
