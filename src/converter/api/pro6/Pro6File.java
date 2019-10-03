package converter.api.pro6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import converter.api.pro6.parser.Pro6Tool;

public class Pro6File {
	private SlideGroups groups = null;
	private List<FileProperty> properties = new ArrayList<FileProperty>();
	
	public Pro6File() {}
	
	public Pro6File(SlideGroups groups, FileProperty... properties) {
		this.groups = groups;
		for (FileProperty property : properties)
			this.properties.add(property);
	}
	
	public SlideGroups getGroups() {
		return this.groups;
	}
	
	public Pro6File setGroups(SlideGroups groups) {
		this.groups = groups;
		return this;
	}
	
	public FileProperty[] getProperties() {
		return this.properties.toArray(new FileProperty[this.properties.size()]);
	}
	
	public String[] getPropertyNames() {
		List<String> result = new ArrayList<String>();
		for (FileProperty property : this.properties)
			result.add(property.getPropertyName());
		return result.toArray(new String[result.size()]);
	}
	
	public Object getPropertyValue(String propertyName) {
		FileProperty fp = this.internalGetProperty(propertyName);
		if (fp == null)
			return "";
		return fp.getPropertyValue();
	}
	
	public FileProperty getProperty(String propertyName) {
		return this.internalGetProperty(propertyName);
	}
	
	private FileProperty internalGetProperty(String propertyName) {
		for (FileProperty property : this.properties)
			if (property.getPropertyName().equalsIgnoreCase(propertyName))
				return property;
		return null;
	}
	
	public Pro6File setProperty(String propertyName, Object propertyValue) {
		boolean found = false;
		for (int i = 0; i < this.properties.size(); i++) {
			if (this.properties.get(i).getPropertyName().equalsIgnoreCase(propertyName)) {
				found = true;
				this.properties.set(i, new FileProperty(propertyName, propertyValue));
			}
		}
		if (!found)
			this.properties.add(new FileProperty(propertyName, propertyValue));
		return this;
	}
	
	public boolean containsProperty(String propertyName) {
		for (FileProperty property : this.properties)
			if (property.getPropertyName().equalsIgnoreCase(propertyName))
				return true;
		return false;
	}
	
	public HashMap<String, Object> getPropertiesAsHashMap() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (FileProperty property : this.properties)
			result.put(property.getPropertyName(), property.getPropertyValue());
		return result;
	}
	
	public String getAsPro6() {
		return Pro6Tool.generatePro6(this);
	}
	
	public void printAsPro6() {
		System.out.println(this.getAsPro6());
	}
	
	@Override
	public String toString() {
		return this.getAsPro6();
	}
}
