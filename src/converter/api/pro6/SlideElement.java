package converter.api.pro6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlideElement {
	private String rvXMLIvarName = null;
	private String elementName = null;
	private SlideShadow shadow = null;
	private String displayName = null;
	private double lineFillVerticalOffset = -1;
	private String source = null;
	private boolean drawingShadow = false;
	private NSString string = null;
	private int lineBackgroundType = -1;
	private String fillColor = null;
	private int revealType = -1;
	private boolean drawLineBackground = false;
	private boolean drawingFill = false;
	private int typeID = -1;
	private RVRect3D rect3d = null;
	private String uuid = null;
	private boolean locked = false;
	private boolean persistent = false;
	private double additionalLineFillHeight = -1;
	private boolean textSourceRemoveLineReturnsOption = false;
	private double displayDelay = -1;
	private double rotation = -1;
	private boolean adjustsHeightToFit = false;
	private boolean fromTemplate = false;
	private boolean useAllCaps = false;
	private boolean drawingStroke = false;
	private Dictionary dictionary = null;
	private double opacity = -1;
	private double bezelRadius = -1;
	private int verticalAlignment = -1;
	
	public SlideElement() {}
	
	public SlideElement(String rvXMLIvarName, String elementName, SlideShadow shadow, String displayName, double lineFillVerticalOffset, String source, boolean drawingShadow, NSString string, int lineBackgroundType, String fillColor, int revealType, boolean drawLineBackground, boolean drawingFill, int typeID, RVRect3D rect3d, String uuid, boolean locked, boolean persistent, double additionalLineFillHeight, boolean textSourceRemoveLineReturnsOption, double displayDelay, double rotation, boolean adjustsHeightToFit, boolean fromTemplate, boolean useAllCaps, boolean drawingStroke, Dictionary dictionary, double opacity, double bezelRadius, int verticalAlignment) {
		this.rvXMLIvarName = rvXMLIvarName;
		this.elementName = elementName;
		this.shadow = shadow;
		this.displayName = displayName;
		this.lineFillVerticalOffset = lineFillVerticalOffset;
		this.source = source;
		this.drawingShadow = drawingShadow;
		this.string = string;
		this.lineBackgroundType = lineBackgroundType;
		this.fillColor = fillColor;
		this.revealType = revealType;
		this.drawLineBackground = drawLineBackground;
		this.drawingFill = drawingFill;
		this.typeID = typeID;
		this.rect3d = rect3d;
		this.uuid = uuid;
		this.locked = locked;
		this.persistent = persistent;
		this.additionalLineFillHeight = additionalLineFillHeight;
		this.textSourceRemoveLineReturnsOption = textSourceRemoveLineReturnsOption;
		this.displayDelay = displayDelay;
		this.rotation = rotation;
		this.adjustsHeightToFit = adjustsHeightToFit;
		this.fromTemplate = fromTemplate;
		this.useAllCaps = useAllCaps;
		this.drawingStroke = drawingStroke;
		this.dictionary = dictionary;
		this.opacity = opacity;
		this.bezelRadius = bezelRadius;
		this.verticalAlignment = verticalAlignment;
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public void setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public String getElementName() {
		return this.elementName;
	}
	
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	public SlideShadow getShadow() {
		return this.shadow;
	}
	
	public void setShadow(SlideShadow shadow) {
		this.shadow = shadow;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public double getLineFillVerticalOffset() {
		return this.lineFillVerticalOffset;
	}
	
	public void setLineFillVerticalOffset(double lineFillVerticalOffset) {
		this.lineFillVerticalOffset = lineFillVerticalOffset;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public boolean isDrawingShadow() {
		return this.drawingShadow;
	}
	
	public void setDrawingShadow(boolean drawingShadow) {
		this.drawingShadow = drawingShadow;
	}
	
	public NSString getString() {
		return this.string;
	}
	
	public void setString(NSString string) {
		this.string = string;
	}
	
	public int getLineBackgroundType() {
		return this.lineBackgroundType;
	}
	
	public void setLineBackgroundType(int lineBackgroundType) {
		this.lineBackgroundType = lineBackgroundType;
	}
	
	public String getFillColor() {
		return this.fillColor;
	}
	
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	
	public int getRevealType() {
		return this.revealType;
	}
	
	public void setRevealType(int revealType) {
		this.revealType = revealType;
	}
	
	public boolean isDrawLineBackground() {
		return this.drawLineBackground;
	}
	
	public void setDrawLineBackground(boolean drawLineBackground) {
		this.drawLineBackground = drawLineBackground;
	}
	
	public boolean isDrawingFill() {
		return this.drawingFill;
	}
	
	public void setDrawingFill(boolean drawingFill) {
		this.drawingFill = drawingFill;
	}
	
	public int getTypeID() {
		return this.typeID;
	}
	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public RVRect3D getRect3d() {
		return this.rect3d;
	}
	
	public void setRect3d(RVRect3D rect3d) {
		this.rect3d = rect3d;
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	
	public boolean isLocked() {
		return this.locked;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isPersistent() {
		return this.persistent;
	}
	
	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}
	
	public double getAdditionalLineFillHeight() {
		return this.additionalLineFillHeight;
	}
	
	public void setAdditionalLineFillHeight(double additionalLineFillHeight) {
		this.additionalLineFillHeight = additionalLineFillHeight;
	}
	
	public boolean isTextSourceRemoveLineReturnsOption() {
		return this.textSourceRemoveLineReturnsOption;
	}
	
	public void setTextSourceRemoveLineReturnsOption(boolean textSourceRemoveLineReturnsOption) {
		this.textSourceRemoveLineReturnsOption = textSourceRemoveLineReturnsOption;
	}
	
	public double getDisplayDelay() {
		return this.displayDelay;
	}
	
	public void setDisplayDelay(double displayDelay) {
		this.displayDelay = displayDelay;
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public boolean isAdjustsHeightToFit() {
		return this.adjustsHeightToFit;
	}
	
	public void setAdjustsHeightToFit(boolean adjustsHeightToFit) {
		this.adjustsHeightToFit = adjustsHeightToFit;
	}
	
	public boolean isFromTemplate() {
		return this.fromTemplate;
	}
	
	public void setFromTemplate(boolean fromTemplate) {
		this.fromTemplate = fromTemplate;
	}
	
	public boolean isUseAllCaps() {
		return this.useAllCaps;
	}
	
	public void setUseAllCaps(boolean useAllCaps) {
		this.useAllCaps = useAllCaps;
	}
	
	public boolean isDrawingStroke() {
		return this.drawingStroke;
	}
	
	public void setDrawingStroke(boolean drawingStroke) {
		this.drawingStroke = drawingStroke;
	}
	
	public Dictionary getDictionary() {
		return this.dictionary;
	}
	
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public double getOpacity() {
		return this.opacity;
	}
	
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
	
	public double getBezelRadius() {
		return this.bezelRadius;
	}
	
	public void setBezelRadius(double bezelRadius) {
		this.bezelRadius = bezelRadius;
	}
	
	public int getVerticalAlignment() {
		return this.verticalAlignment;
	}
	
	public void setVerticalAlignment(int verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}
	
	public Object getPropertyValue(String propertyName) {
		return this.internalGetProperty(propertyName).getValue();
	}
	
	public HashMap.Entry<String, Object> getProperty(String propertyName) {
		return this.internalGetProperty(propertyName);
	}
	
	private HashMap.Entry<String, Object> internalGetProperty(String propertyName) {
		Set<Map.Entry<String, Object>> properties = this.internalGetProperties().entrySet();
		for (HashMap.Entry<String, Object> property : properties)
			if (property.getKey().equalsIgnoreCase(propertyName))
				return property;
		return null;
	}
	
	public String[] getPropertyNames() {
		List<String> result = new ArrayList<String>();
		Set<Map.Entry<String, Object>> properties = this.internalGetProperties().entrySet();
		for (HashMap.Entry<String, Object> property : properties)
			result.add(property.getKey());
		return result.toArray(new String[result.size()]);
	}
	
	public HashMap<String, Object> getProperties() {
		return this.internalGetProperties();
	}
	
	private HashMap<String, Object> internalGetProperties() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("rvXMLIvarName", this.rvXMLIvarName);
		// result.put("elementName", this.elementName);
		result.put("shadow", this.shadow);
		result.put("displayName", this.displayName);
		result.put("lineFillVerticalOffset", this.lineFillVerticalOffset);
		result.put("source", this.source);
		result.put("drawingShadow", this.drawingShadow);
		result.put("string", this.string);
		result.put("lineBackgroundType", this.lineBackgroundType);
		result.put("fillColor", this.fillColor);
		result.put("revealType", this.revealType);
		result.put("drawLineBackground", this.drawLineBackground);
		result.put("drawingFill", this.drawingFill);
		result.put("typeID", this.typeID);
		result.put("RVRect3D", this.rect3d);
		result.put("UUID", this.uuid);
		result.put("locked", this.locked);
		result.put("persistent", this.persistent);
		result.put("additionalLineFillHeight", this.additionalLineFillHeight);
		result.put("textSourceRemoveLineReturnsOption", this.textSourceRemoveLineReturnsOption);
		result.put("displayDelay", this.displayDelay);
		result.put("rotation", this.rotation);
		result.put("adjustsHeightToFit", this.adjustsHeightToFit);
		result.put("fromTemplate", this.fromTemplate);
		result.put("useAllCaps", this.useAllCaps);
		result.put("drawingStroke", this.drawingStroke);
		result.put("dictionary", this.dictionary);
		result.put("opacity", this.opacity);
		result.put("bezelRadius", this.bezelRadius);
		result.put("verticalAlignment", this.verticalAlignment);
		return result;
	}
}
