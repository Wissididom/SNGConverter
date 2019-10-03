package converter.api.pro6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Slide {
	private String rvXMLIvarName = null;
	private List<SlideProperty> properties = new ArrayList<SlideProperty>();
	private SlideElements elements = null;
	
	public Slide() {}
	
	public Slide(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public Slide(String rvXMLIvarName, SlideProperty... properties) {
		this.rvXMLIvarName = rvXMLIvarName;
		for (SlideProperty property : properties)
			this.properties.add(property);
		System.out.println(Arrays.toString(this.properties.toArray()));
	}
	
	public Slide(String rvXMLIvarName, SlideProperty[] properties, SlideElements elements) {
		this.rvXMLIvarName = rvXMLIvarName;
		for (SlideProperty property : properties)
			this.properties.add(property);
		this.elements = elements;
		System.out.println(Arrays.toString(this.properties.toArray()));
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public void setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public String[] getPropertyNames() {
		List<String> result = new ArrayList<String>();
		for (SlideProperty property : this.properties)
			result.add(property.getPropertyName());
		return result.toArray(new String[result.size()]);
	}
	
	public SlideProperty[] getProperties() {
		return this.properties.toArray(new SlideProperty[this.properties.size()]);
	}
	
	public Object getPropertyValue(String propertyName) {
		return this.internalGetProperty(propertyName).getPropertyValue();
	}
	
	public SlideProperty getProperty(String propertyName) {
		return this.internalGetProperty(propertyName);
	}
	
	private SlideProperty internalGetProperty(String propertyName) {
		for (SlideProperty property : this.properties)
			if (property.getPropertyName().equalsIgnoreCase(propertyName))
				return property;
		return null;
	}
	
	public Slide setProperty(String propertyName, Object propertyValue) {
		boolean found = false;
		for (int i = 0; i < this.properties.size(); i++) {
			if (this.properties.get(i).getPropertyName().equalsIgnoreCase(propertyName)) {
				found = true;
				this.properties.set(i, new SlideProperty(propertyName, propertyValue));
			}
		}
		if (!found)
			this.properties.add(new SlideProperty(propertyName, propertyValue));
		return this;
	}
	
	public boolean containsProperty(String propertyName) {
		for (SlideProperty property : this.properties)
			if (property.getPropertyName().equalsIgnoreCase(propertyName))
				return true;
		return false;
	}
	
	public Slide addElement(SlideElement element) {
		this.elements.addElement(element);
		System.out.println(Arrays.toString(this.properties.toArray()));
		return this;
	}
	
	public SlideElements getElements() {
		return this.elements;
	}
	
	public Slide setElements(SlideElements elements) {
		this.elements = elements;
		return this;
	}
	
	public HashMap<String, Object> getPropertiesAsHashMap() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (SlideProperty property : this.properties)
			result.put(property.getPropertyName(), property.getPropertyValue());
		return result;
	}
}
