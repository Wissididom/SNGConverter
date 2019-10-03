package converter.api.sng.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import converter.api.sng.SngFile;
import converter.api.sng.SngFileProperty;

public class SngTool {
	
	private static String readFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String inputLine = "", result = "";
		while ((inputLine = br.readLine()) != null) {
			if (result.length() < 1)
				result = inputLine;
			else
				result += "\n" + inputLine;
		}
		br.close();
		return result;
	}
	
	public static SngFile parseSng(File sngFile) throws IOException {
		String sngContent = readFile(sngFile).replace("\uFEFF", ""); // remove BOM
		return parseSng(sngContent);
	}
	
	public static SngFile parseSng(String sngContent) {
		SngFile sf = new SngFile();
		if (sngContent.startsWith("#")) {
			String[] lines = sngContent.split("\n");
			for (String line : lines)
				if (line.startsWith("#") && line.contains("="))
					sf.setProperty(line.substring("#".length(), line.indexOf("=")), line.substring(line.indexOf("=") + "=".length()));
			if (sngContent.contains("--"))
				sngContent = sngContent.substring(sngContent.indexOf("--") + "--".length());
			while (sngContent.startsWith("-"))
				sngContent = sngContent.substring(1);
			sngContent = sngContent.trim();
		}
		sf.setProperty("slides", sngContent);
		return sf;
	}
	
	public static void generateSng(SngFile properties, String sngFilePath) throws IOException {
		generateSng(properties, new File(sngFilePath));
	}
	
	public static void generateSng(SngFile properties, File sngFile) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sngFile)));
		bw.write(generateSng(properties));
		bw.flush();
		bw.close();
	}
	
	public static String generateSng(SngFile properties) {
		return properties.getAsSng();
	}
	
	public static String generateSng(SngFileProperty... properties) {
		return new SngFile(properties).getAsSng();
	}
	
	public static void main(String[] args) throws IOException {
		SngFile sf = parseSng(new File("/home/dominik/Dokumente/Wir sind keine Bots.sng"));
		System.out.println(generateSng(sf));
		System.out.println("--------------------------------------");
		System.out.println(generateSng(sf.getProperties()));
	}
}
