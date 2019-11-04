package converter.api;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.mozilla.universalchardet.ReaderFactory;

import converter.api.pro6.Dictionary;
import converter.api.pro6.NSColor;
import converter.api.pro6.NSNumber;
import converter.api.pro6.NSString;
import converter.api.pro6.Pro6File;
import converter.api.pro6.RTFTools;
import converter.api.pro6.RVRect3D;
import converter.api.pro6.RVTimeline;
import converter.api.pro6.Slide;
import converter.api.pro6.SlideElement;
import converter.api.pro6.SlideElements;
import converter.api.pro6.SlideGroup;
import converter.api.pro6.SlideGroups;
import converter.api.pro6.SlideShadow;
import converter.api.pro6.parser.Pro6Tool;
import converter.api.sng.SngFile;
import converter.api.sng.parser.SngTool;

public class ConverterAPI {
	
	public static final String[] AVAILABLE_VERSE_ORDER_ENTRIES = {
		"Unbekannt",
		"Unbenannt",
		"Unknown",
		"Intro",
		"Vers",
		"Verse",
		"Strophe",
		"Pre-Bridge",
		"Bridge",
		"Misc",
		"Pre-Refrain",
		"Refrain",
		"Pre-Chorus",
		"Chorus",
		"Pre-Coda",
		"Zwischenspiel",
		"Instrumental",
		"Interlude",
		"Coda",
		"Ending",
		"Outro",
		"Teil",
		"Part",
		"Chor",
		"Solo"
	};
	public static final String UNKNOWN = "98 176 255 255";
	public static final String INTRO_CODA_ENDING_OUTRO = "0 128 64 255";
	public static final String VERSE_PART = "0 128 255 255";
	public static final String PRES = "170 85 255 255";
	public static final String BRIDGE_MISC_INSTRUMENTAL_INTERLUDE = "204 0 0 255";
	public static final String REFRAIN_CHORUS = "128 0 255 255";
	public static final String CHOR_SOLO = "170 176 255 255";
	
	public static String getCurrentDateTime() {
		// String time = Instant.now().atZone(ZoneId.of("Europe/Berlin")).toString();
		 String time = Instant.now().atZone(ZoneId.systemDefault()).toString();
		return time.substring(0, time.indexOf('['));
	}
	
	public static String readFile(File file) throws IOException {
		// BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = ReaderFactory.createBufferedReader(file);
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
	
	public static void writeFile(File file, String content) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		bw.write(content);
		bw.flush();
		bw.close();
	}
	
	public static String convertPro6ToSng(File pro6File) throws IOException, BadLocationException {
		return ConverterAPI.convertPro6ToSng(ConverterAPI.readFile(pro6File));
	}
	
	public static String convertPro6ToSng(String pro6Xml) throws IOException, BadLocationException {
		return ConverterAPI.convertPro6ToSng(Pro6Tool.parsePro6(pro6Xml)).getAsSng();
	}
	
	public static SngFile convertPro6ToSng(Pro6File pro6File) throws IOException, BadLocationException {
		SngFile sf = new SngFile();
		if (pro6File.containsProperty("CCLISongTitle"))
			sf.setProperty("Title", (String) pro6File.getProperty("CCLISongTitle").getPropertyValue());
		if (pro6File.containsProperty("CCLICopyrightYear")) {
			if (pro6File.containsProperty("CCLIPublisher"))
				sf.setProperty("(c)", ((String) pro6File.getProperty("CCLICopyrightYear").getPropertyValue() + " " + (String) pro6File.getProperty("CCLIPublisher").getPropertyValue()));
			else
				sf.setProperty("(c)", (String) pro6File.getProperty("CCLICopyrightYear").getPropertyValue());
		} else {
			if (pro6File.containsProperty("CCLIPublisher"))
				sf.setProperty("(c)", (String) pro6File.getProperty("CCLIPublisher").getPropertyValue());
		}
		if (pro6File.containsProperty("CCLISongNumber"))
			sf.setProperty("CCLI", pro6File.getProperty("CCLISongNumber").getPropertyValue().toString());
		if (pro6File.containsProperty("CCLIAuthor"))
			sf.setProperty("Author", pro6File.getProperty("CCLIAuthor").getPropertyValue().toString());
		String slides = "";
		SlideGroups groups = pro6File.getGroups();
		for (SlideGroup group : groups.getGroups()) {
			for (int i = 0; i < group.getSlideCount(); i++) {
				for (SlideElement element : group.getSlide(i).getElements().getElements()) {
					NSString string = element.getString();
					if (!string.isDecoded())
						string.decodeContent();
					if (slides.length() > 0)
						slides += "---\n";
					String name = group.getName();
					if (name != null && name.length() > 0)
						slides += name + "\n";
					slides += RTFTools.convertRtfToPlainText(string.getContent());
				} 
			}
		}
		sf.setProperty("slides", slides);
		return sf;
	}
	
	public static String convertSngToPro6(File sngFile) throws IOException, BadLocationException {
		return ConverterAPI.convertSngToPro6(ConverterAPI.readFile(sngFile));
	}
	
	public static String convertSngToPro6(String sngContent) throws IOException, BadLocationException {
		return ConverterAPI.convertSngToPro6(SngTool.parseSng(sngContent)).getAsPro6();
	}
	
	public static Pro6File convertSngToPro6(SngFile sngFile) throws IOException, BadLocationException {
		Pro6File pf = new Pro6File();
		if (sngFile.containsProperty("Title"))
			pf.setProperty("CCLISongTitle", sngFile.getProperty("Title").getPropertyValue());
		if (sngFile.containsProperty("(c)")) {
			String c = sngFile.getProperty("(c)").getPropertyValue().replace("©", "").trim();
			if (Character.isDigit(c.charAt(0)) && Character.isDigit(c.charAt(1)) && Character.isDigit(c.charAt(2)) && Character.isDigit(c.charAt(3))) {
				pf.setProperty("CCLICopyrightYear", c.substring(0, 4));
				c = c.substring(4).trim();
			}
			pf.setProperty("CCLIPublisher", c);
		}
		if (sngFile.containsProperty("CCLI"))
			pf.setProperty("CCLISongNumber", sngFile.getProperty("CCLI").getPropertyValue());
		if (sngFile.containsProperty("Author"))
			pf.setProperty("CCLIAuthor", sngFile.getProperty("Author").getPropertyValue());
		pf.setProperty("buildNumber", 16245);
		pf.setProperty("uuid", UUID.randomUUID().toString().toUpperCase());
		pf.setProperty("lastDateUsed", ConverterAPI.getCurrentDateTime());
		pf.setProperty("CCLIDisplay", true);
		pf.setProperty("usedCount", 0);
		pf.setProperty("height", 1080);
		pf.setProperty("backgroundColor", "0 0 0 0");
		pf.setProperty("os", 2);
		pf.setProperty("docType", 0);
		pf.setProperty("versionNumber", 600);
		pf.setProperty("width", 1920);
		pf.setProperty("drawingBackgroundColor", false);
		RVTimeline rvt = new RVTimeline();
		rvt.setDuration(0.0D);
		rvt.setLoop(false);
		rvt.setPlayBackRate(1.0D);
		rvt.setRvXMLIvarName("timeline");
		rvt.setSelectedMediaTrackIndex(0);
		rvt.setTimeOffset(0.0D);
		rvt.setRvXMLIvarNames("timeCues", "mediaTracks");
		pf.setProperty("RVTimeline", rvt);
		SlideGroups groups = new SlideGroups("groups");
		if (!sngFile.containsProperty("slides"))
			return pf;
		String[] slides = sngFile.getProperty("slides").getPropertyValue().replace("\r\n", "\n").replace('\r', '\n').trim().split("\n---\n");
		SlideGroup group = new SlideGroup();
		for (int i = 0; i < slides.length; i++) {
			Slide slideObj = new Slide("slides");
			boolean hasName = false;
			if (ConverterAPI.startsWithOneInArrayIgnoreCase(slides[i], ConverterAPI.AVAILABLE_VERSE_ORDER_ENTRIES)) {
				group.setName(slides[i].split("\n")[0]);
				group.setColor(ConverterAPI.getColor(slides[i].split("(?: |\n)")[0]));
				group.setUUID(UUID.randomUUID().toString().toUpperCase());
				slides[i] = slides[i].substring(slides[i].indexOf('\n') + 1);
				hasName = true;
			}
			MutableAttributeSet mas = new SimpleAttributeSet();
			StyleConstants.setFontFamily(mas, "Helvetica");
			StyleConstants.setFontSize(mas, 200);
			StyleConstants.setForeground(mas, Color.WHITE);
			StyleConstants.setBackground(mas, Color.BLACK);
			NSString string = new NSString("RTFData", RTFTools.convertPlainTextToRtf(slides[i], mas)).encodeContent();
			Dictionary dic = new Dictionary("stroke");
			dic.addEntry(new NSColor("RVShapeElementStrokeColor", "0 0 0 0"));
			dic.addEntry(new NSNumber("RVShapeElementStrokeWidth", 1.0D));
			SlideElement element = new SlideElement("displayElements", "RVTextElement", new SlideShadow("shadow", "0.000000|0 0 0 0.3333333432674408|{4.0000002002606152, -3.9999995259110506}"), "TextElement", 0.0D, "", false, string, 0, "0 0 0 0", 0, false, false, 0, new RVRect3D("position", "{192 108 0 1536 864}"), UUID.randomUUID().toString(), false, false, 0.0D, false, 0.0D, 0.0D, false, true, false, false, dic, 1.0D, 0.0D, 1);
			slideObj.setElements(new SlideElements("cues", element));
			slideObj.setProperty("highlightColor", "0 0 0 0");
			slideObj.setProperty("backgroundColor", "0 0 0 1");
			slideObj.setProperty("socialItemCount", 1);
			slideObj.setProperty("drawingBackgroundColor", false);
			slideObj.setProperty("UUID", UUID.randomUUID().toString().toUpperCase());
			slideObj.setProperty("enabled", true);
			group.addSlide(slideObj);
			if (hasName) {
				groups.addGroup(group);
				group = new SlideGroup();
			}
		}
		if (groups.getGroupCount() < 1)
			groups.addGroup(group);
		pf.setGroups(groups);
		return pf;
	}
	
	public static boolean stringArrayContains(String needle, String... haystack) {
		for (String entry : haystack)
			if (entry.equals(needle))
				return true;
		return false;
	}
	
	public static boolean stringArrayContainsIgnoreCase(String needle, String... haystack) {
		for (String entry : haystack)
			if (entry.equalsIgnoreCase(needle))
				return true;
		return false;
	}
	
	public static boolean startsWithOneInArray(String haystack, String... needles) {
		for (String needle : needles)
			if (haystack.startsWith(needle))
				return true;
		return false;
	}
	
	public static boolean startsWithOneInArrayIgnoreCase(String haystack, String... needles) {
		for (String needle : needles)
			if (startsWithIgnoreCase(haystack, needle))
				return true;
		return false;
	}
	
	public static boolean startsWithIgnoreCase(String haystack, String needle) {
		return haystack.toLowerCase().startsWith(needle.toLowerCase());
	}
	
	public static String getColor(String name) {
		if (name.equalsIgnoreCase("Unbekannt") || name.equalsIgnoreCase("Unbenannt") || name.equalsIgnoreCase("Unknown"))
			return ConverterAPI.UNKNOWN;
		if (name.equalsIgnoreCase("Intro") || name.equalsIgnoreCase("Coda") || name.equalsIgnoreCase("Ending") || name.equalsIgnoreCase("Outro"))
			return ConverterAPI.INTRO_CODA_ENDING_OUTRO;
		if (name.equalsIgnoreCase("Vers") || name.equalsIgnoreCase("Verse") || name.equalsIgnoreCase("Strophe") || name.equalsIgnoreCase("Teil") || name.equalsIgnoreCase("Part"))
			return ConverterAPI.VERSE_PART;
		if (name.equalsIgnoreCase("Pre-Bridge") || name.equalsIgnoreCase("Pre-Refrain") || name.equalsIgnoreCase("Pre-Chorus") || name.equalsIgnoreCase("Pre-Coda"))
			return ConverterAPI.PRES;
		if (name.equalsIgnoreCase("Bridge") || name.equalsIgnoreCase("Misc") || name.equalsIgnoreCase("Zwischenspiel") || name.equalsIgnoreCase("Instrumental") || name.equalsIgnoreCase("Interlude"))
			return ConverterAPI.BRIDGE_MISC_INSTRUMENTAL_INTERLUDE;
		if (name.equalsIgnoreCase("Refrain") || name.equalsIgnoreCase("Chorus"))
			return ConverterAPI.REFRAIN_CHORUS;
		if (name.equalsIgnoreCase("Chor") || name.equalsIgnoreCase("Solo"))
			return ConverterAPI.CHOR_SOLO;
		return "0 0 0 0";
	}
}
