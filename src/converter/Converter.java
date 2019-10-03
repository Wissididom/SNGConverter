package converter;

import java.io.File;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import converter.api.ConverterAPI;

public class Converter {
	
	public static void main(String[] args) throws IOException, BadLocationException {
		if (args.length == 3) {
			switch (args[0]) {
				case "pro6":
					ConverterAPI.writeFile(new File(args[2]), ConverterAPI.convertSngToPro6(new File(args[1])));
					break;
				case "sng":
					ConverterAPI.writeFile(new File(args[2]), ConverterAPI.convertPro6ToSng(new File(args[1])));
					break;
				default:
					System.err.println("Please only use pro6 or sng as targetformat!");
			}
		} else {
			System.err.println("Usage: java -jar SNGConverter.jar targetformat(pro6/sng) infile outfile!");
		}
	}
}
