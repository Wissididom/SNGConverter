package converter.api.pro6.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.text.BadLocationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import converter.api.pro6.Dictionary;
import converter.api.pro6.FileProperty;
import converter.api.pro6.NS;
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

public class Pro6Tool {
	
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
	
	public static Pro6File parsePro6(File pro6File) throws IOException {
		String pro6Content = readFile(pro6File).replace("\uFEFF", ""); // remove BOM
		return parsePro6(pro6Content);
	}
	
	public static Pro6File parsePro6(String pro6Content) {
		Pro6File pf = new Pro6File();
		JSONObject obj = XML.toJSONObject(pro6Content);
		if (obj.has("RVPresentationDocument"))
			obj = obj.getJSONObject("RVPresentationDocument");
		String[] properties = {
			"notes",
			"CCLIArtistCredits",
			"CCLIPublisher",
			"buildNumber",
			"uuid",
			"lastDateUsed",
			"resourcesDirectory",
			"CCLICopyrightYear",
			"CCLIDisplay",
			"CCLISongTitle",
			"chordChartPath",
			"CCLISongNumber",
			"usedCount",
			"height",
			"backgroundColor",
			"os",
			"docType",
			"selectedArrangementID",
			"versionNumber",
			"width",
			"drawingBackgroundColor",
			"CCLIAuthor",
			"category"
		};
		for (String property : properties)
			if (obj.has(property))
				pf.setProperty(property, obj.get(property));
		if (obj.has("RVTimeline"))
			pf.setProperty("RVTimeline", new RVTimeline(obj.getJSONObject("RVTimeline")));
		if (obj.has("array")) {
			JSONArray array = obj.getJSONArray("array");
			int arrayLength = array.length();
			for (int i = 0; i < arrayLength; i++) {
				JSONObject o = array.getJSONObject(i);
				String rvXMLIvarName = null;
				if (o.has("rvXMLIvarName"))
					rvXMLIvarName = o.getString("rvXMLIvarName");
				SlideGroups s = new SlideGroups(rvXMLIvarName);
				JSONArray slideGroups = null;
				try {
					slideGroups = o.getJSONArray("RVSlideGrouping");
				} catch (JSONException ex) {
					continue;
				}
				int slideGroupsLength = slideGroups.length();
				for (int j = 0; j < slideGroupsLength; j++) {
					JSONObject slideGroupObject = slideGroups.getJSONObject(j);
					SlideGroup p = new SlideGroup();
					if (slideGroupObject.has("color"))
						p.setColor(slideGroupObject.getString("color"));
					if (slideGroupObject.has("name"))
						p.setName(slideGroupObject.getString("name"));
					if (slideGroupObject.has("uuid"))
						p.setUUID(slideGroupObject.getString("uuid"));
					else if (slideGroupObject.has("UUID"))
						p.setUUID(slideGroupObject.getString("UUID"));
					if (slideGroupObject.has("array")) {
						JSONObject slidesObject = slideGroupObject.getJSONObject("array");
						Slide ps = new Slide();
						if (slidesObject.has("rvXMLIvarName"))
							ps.setRvXMLIvarName(slidesObject.getString("rvXMLIvarName"));
						if (slidesObject.has("RVDisplaySlide")) {
							JSONArray slidesArray = null;
							int slidesLength = -1;
							JSONObject slide = null;
							try {
								slidesArray = slidesObject.getJSONArray("RVDisplaySlide");
								slidesLength = slidesArray.length();
							} catch (JSONException ex) {
								slide = slidesObject.getJSONObject("RVDisplaySlide");
								slidesLength = 1;
							}
							for (int k = 0; k < slidesLength; k++) {
								if (slidesLength > 1)
									slide = slidesArray.getJSONObject(k);
								properties = new String[] {
									"highlightColor",
									"backgroundColor",
									"notes",
									"socialItemCount",
									"hotKey",
									"chordChartPath",
									"drawingBackgroundColor",
									"label",
									"UUID",
									"enabled"
								};
								for (String property : properties)
									if (slide.has(property))
										ps.setProperty(property, slide.get(property));
								if (slide.has("array")) {
									JSONArray a = slide.getJSONArray("array");
									int length = a.length();
									SlideElements pse = new SlideElements();
									for (int l = 0; l < length; l++) {
										JSONObject object = a.getJSONObject(l);
										if (object.length() < 2) {
											pse.setRvXMLIvarName(object.getString("rvXMLIvarName"));
											continue;
										}
										SlideElement elem = new SlideElement();
										if (object.has("rvXMLIvarName"))
											elem.setRvXMLIvarName(object.getString("rvXMLIvarName"));
										String[] names = JSONObject.getNames(object);
										for (String name : names) {
											if (name.equals("rvXMLIvarName"))
												continue;
											JSONObject element = object.getJSONObject(name);
											elem.setElementName(name);
											if (element.has("rvXMLIvarName"))
												elem.setRvXMLIvarName(element.getString("rvXMLIvarName"));
											if (element.has("shadow")) {
												SlideShadow pss = new SlideShadow();
												JSONObject temp = element.getJSONObject("shadow");
												if (temp.has("rvXMLIvarName"))
													pss.setRvXMLIvarName(temp.getString("rvXMLIvarName"));
												if (temp.has("content"))
													pss.setContent(temp.getString("content"));
												elem.setShadow(pss);
											}
											if (element.has("displayName"))
												elem.setDisplayName(element.getString("displayName"));
											if (element.has("lineFillVerticalOffset"))
												elem.setLineFillVerticalOffset(element.getDouble("lineFillVerticalOffset"));
											if (element.has("source"))
												elem.setSource(element.getString("source"));
											if (element.has("drawingShadow"))
												elem.setDrawingShadow(element.getBoolean("drawingShadow"));
											if (element.has("NSString")) {
												JSONObject temp = element.getJSONObject("NSString");
												NSString string = new NSString();
												if (temp.has("rvXMLIvarName"))
													string.setRvXMLIvarName(temp.getString("rvXMLIvarName"));
												if (temp.has("content"))
													string.setContent(temp.getString("content"));
												elem.setString(string);
											}
											if (element.has("lineBackgroundType"))
												elem.setLineBackgroundType(element.getInt("lineBackgroundType"));
											if (element.has("fillColor"))
												elem.setFillColor(element.getString("fillColor"));
											if (element.has("revealType"))
												elem.setRevealType(element.getInt("revealType"));
											if (element.has("drawLineBackground"))
												elem.setDrawLineBackground(element.getBoolean("drawLineBackground"));
											if (element.has("drawingFill"))
												elem.setDrawingFill(element.getBoolean("drawingFill"));
											if (element.has("typeID"))
												elem.setTypeID(element.getInt("typeID"));
											if (element.has("RVRect3D")) {
												JSONObject temp = element.getJSONObject("RVRect3D");
												RVRect3D rr3d = new RVRect3D();
												if (temp.has("rvXMLIvarName"))
													rr3d.setRvXMLIvarName(temp.getString("rvXMLIvarName"));
												if (temp.has("content"))
													rr3d.setContent(temp.getString("content"));
												elem.setRect3d(rr3d);
											}
											if (element.has("UUID"))
												elem.setUUID(element.getString("UUID"));
											else if (element.has("uuid"))
												elem.setUUID(element.getString("uuid"));
											if (element.has("locked"))
												elem.setLocked(element.getBoolean("locked"));
											if (element.has("persistent"))
												elem.setPersistent(element.getBoolean("persistent"));
											if (element.has("additionalLineFillHeight"))
												elem.setAdditionalLineFillHeight(element.getDouble("additionalLineFillHeight"));
											if (element.has("textSourceRemoveLineReturnsOption"))
												elem.setTextSourceRemoveLineReturnsOption(element.getBoolean("textSourceRemoveLineReturnsOption"));
											if (element.has("displayDelay"))
												elem.setDisplayDelay(element.getDouble("displayDelay"));
											if (element.has("rotation"))
												elem.setRotation(element.getDouble("rotation"));
											if (element.has("adjustsHeightToFit"))
												elem.setAdjustsHeightToFit(element.getBoolean("adjustsHeightToFit"));
											if (element.has("fromTemplate"))
												elem.setFromTemplate(element.getBoolean("fromTemplate"));
											if (element.has("useAllCaps"))
												elem.setUseAllCaps(element.getBoolean("useAllCaps"));
											if (element.has("drawingStroke"))
												elem.setDrawingStroke(element.getBoolean("drawingStroke"));
											if (element.has("dictionary")) {
												JSONObject temp = element.getJSONObject("dictionary");
												Dictionary dic = new Dictionary();
												if (temp.has("NSColor")) {
													JSONObject temp2 = temp.getJSONObject("NSColor");
													NSColor col = new NSColor();
													if (temp2.has("rvXMLDictionaryKey"))
														col.setRvXMLDictionaryKey(temp2.getString("rvXMLDictionaryKey"));
													if (temp2.has("content"))
														col.setContent(temp2.getString("content"));
													dic.addEntry(col);
												}
												if (temp.has("NSNumber")) {
													JSONObject temp2 = temp.getJSONObject("NSNumber");
													NSNumber num = new NSNumber();
													if (temp2.has("rvXMLDictionaryKey"))
														num.setRvXMLDictionaryKey(temp2.getString("rvXMLDictionaryKey"));
													if (temp2.has("content"))
														num.setContent(temp2.getDouble("content"));
													dic.addEntry(num);
												}
												if (temp.has("rvXMLIvarName"))
													dic.setRvXMLIvarName(temp.getString("rvXMLIvarName"));
												elem.setDictionary(dic);
											}
											if (element.has("opacity"))
												elem.setOpacity(element.getDouble("opacity"));
											if (element.has("bezelRadius"))
												elem.setBezelRadius(element.getDouble("bezelRadius"));
											if (element.has("verticalAlignment"))
												elem.setVerticalAlignment(element.getInt("verticalAlignment"));
											pse.addElement(elem);
										}
										ps.setElements(pse);
									}
								}
								p.addSlide(ps);
							}
						}
					}
					s.addGroup(p);
				}
				pf.setGroups(s);
			}
		}
		return pf;
	}
	
	public static void generatePro6(Pro6File properties, String pro6FilePath) throws IOException {
		generatePro6(properties, new File(pro6FilePath));
	}
	
	public static void generatePro6(Pro6File properties, File pro6File) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pro6File)));
		bw.write(generatePro6(properties));
		bw.flush();
		bw.close();
	}
	
	public static String generatePro6(Pro6File pro6File) {
		StringBuilder result = new StringBuilder("<RVPresentationDocument");
		for (String property : pro6File.getPropertyNames()) {
			if (property.equalsIgnoreCase("RVTimeline"))
				continue;
			if (pro6File.containsProperty(property))
				result.append(" ").append(property).append("=\"").append(pro6File.getPropertyValue(property)).append("\"");
		}
		result.append('>');
		FileProperty fp = pro6File.getProperty("RVTimeline");
		if (fp != null) {
			RVTimeline rvt = (RVTimeline) fp.getPropertyValue();
			if (rvt != null) {
				result.append("<RVTimeline duration=\"");
				result.append(rvt.getDuration());
				result.append("\" loop=\"");
				result.append(rvt.isLoop());
				result.append("\" playBackRate=\"");
				result.append(rvt.getPlayBackRate());
				result.append("\" rvXMLIvarName=\"");
				result.append(rvt.getRvXMLIvarName());
				result.append("\" selectedMediaTrackIndex=\"");
				result.append(rvt.getSelectedMediaTrackIndex());
				result.append("\" timeOffset=\"");
				result.append(rvt.getTimeOffset());
				result.append("\">");
				for (String s : rvt.getRvXMLIvarNames())
					result.append("<array rvXMLIvarName=\"").append(s).append("\"></array>");
				result.append("</RVTimeline>");
				SlideGroups groups = pro6File.getGroups();
				result.append("<array rvXMLIvarName=\"").append(groups.getRvXMLIvarName()).append("\">");
				for (SlideGroup group : groups.getGroups()) {
					result.append("<RVSlideGrouping color=\"");
					result.append(group.getColor());
					result.append("\" name=\"");
					result.append(group.getName());
					result.append("\" UUID=\"");
					result.append(group.getUUID());
					result.append("\">");
					int slideCount = group.getSlideCount();
					for (int i = 0; i < slideCount; i++) {
						Slide slide = group.getSlide(i);
						if (i == 0)
							result.append("<array rvXMLIvarName=\"").append(slide.getRvXMLIvarName()).append("\">");
						result.append("<RVDisplaySlide");
						String[] propertyNames = slide.getPropertyNames();
						for (String propertyName : propertyNames)
							result.append(" ").append(propertyName).append("=\"").append(slide.getPropertyValue(propertyName)).append("\"");
						SlideElements pse = slide.getElements();
						for (SlideElement elem : pse.getElements()) {
							result.append("><array rvXMLIvarName=\"").append(pse.getRvXMLIvarName()).append("\"></array>");
							result.append("<array rvXMLIvarName=\"").append(elem.getRvXMLIvarName()).append("\"><").append(elem.getElementName());
							propertyNames = elem.getPropertyNames();
							for (String propertyName : propertyNames) {
								if (propertyName.equalsIgnoreCase("rvXMLIvarName") ||
									propertyName.equalsIgnoreCase("string") ||
									propertyName.equalsIgnoreCase("RVRect3D") ||
									propertyName.equalsIgnoreCase("shadow") ||
									propertyName.equalsIgnoreCase("dictionary") ||
									propertyName.equalsIgnoreCase("NSColor") ||
									propertyName.equalsIgnoreCase("NSNumber"))
									continue;
								result.append(" ").append(propertyName).append("=\"").append(elem.getPropertyValue(propertyName)).append("\"");
							}
							result.append('>');
							RVRect3D rect3d = elem.getRect3d();
							if (rect3d != null) {
								result.append("<RVRect3D rvXMLIvarName=\"").append(rect3d.getRvXMLIvarName()).append("\">");
								result.append(rect3d.getContent()).append("</RVRect3D>");
							}
							SlideShadow shadow = elem.getShadow();
							if (shadow != null) {
								result.append("<shadow rvXMLIvarName=\"").append(shadow.getRvXMLIvarName()).append("\">");
								result.append(shadow.getContent()).append("</shadow>");
							}
							Dictionary dictionary = elem.getDictionary();
							if (dictionary != null) {
								result.append("<dictionary rvXMLIvarName=\"").append(dictionary.getRvXMLIvarName()).append("\">");
								for (NS ns : dictionary.getEntries()) {
									if (ns instanceof NSColor) {
										NSColor color = (NSColor) ns;
										result.append("<NSColor rvXMLDictionaryKey=\"").append(color.getRvXMLDictionaryKey()).append("\">");
										result.append(color.getContent()).append("</NSColor>");
									}
									if (ns instanceof NSNumber) {
										NSNumber number = (NSNumber) ns;
										result.append("<NSNumber rvXMLDictionaryKey=\"").append(number.getRvXMLDictionaryKey());
										result.append("\" hint=\"float\">").append(number.getContent()).append("</NSNumber>");
									}
									if (ns instanceof NSString) {
										NSString string = (NSString) ns;
										result.append("<NSString rvXMLIvarName=\"").append(string.getRvXMLIvarName()).append("\">");
										result.append(string.getContent()).append("</NSString>");
									}
								}
								result.append("</dictionary>");
							}
							NSString string = elem.getString();
							if (string != null) {
								result.append("<NSString rvXMLIvarName=\"").append(string.getRvXMLIvarName()).append("\">");
								if (string.isDecoded())
									result.append(string.encodeContent().getContent());
								else
									result.append(string.getContent());
								result.append("</NSString>");
							}
						}
						result.append("</RVTextElement></array></RVDisplaySlide>");
					}
					result.append("</array></RVSlideGrouping>");
				}
			}
		}
		// TODO: load arrangements from variable
		result.append("</array><array rvXMLIvarName=\"arrangements\"></array></RVPresentationDocument>");
		return result.toString();
	}
	
	public static String getRtfAsText(String rtf) {
//		try {
//			StringTextConverter stc = new StringTextConverter();
//			stc.convert(new RtfStringSource(rtf));
//			return stc.getText();
//		} catch (IOException ex) {
//			return null;
//		}
		try {
			return RTFTools.convertRtfToPlainText(rtf);
		} catch (IOException | BadLocationException e) {
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Pro6File pf = parsePro6(new File("/home/dominik/eclipse-workspace/Pro6Parser/src/files/example/Groß und herrlich.pro6"));
		String generated = Pro6Tool.generatePro6(pf);
		System.out.println(XML.toJSONObject(readFile(new File("/home/dominik/eclipse-workspace/Pro6Parser/src/files/example/Groß und herrlich.pro6"))).toString(4));
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(XML.toJSONObject(generated).toString(4));
	}
}
