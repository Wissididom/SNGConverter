package converter.api.sng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SngFile {
	private List<SngFileProperty> properties = new ArrayList<SngFileProperty>();
	
	public SngFile() {}
	
	public SngFile(SngFileProperty... properties) {
		for (SngFileProperty property : properties)
			this.properties.add(property);
	}
	
	public SngFileProperty[] getProperties() {
		return this.properties.toArray(new SngFileProperty[this.properties.size()]);
	}
	
	public SngFileProperty getProperty(String propertyName) {
		for (SngFileProperty property : this.properties)
			if (property.getPropertyName().equalsIgnoreCase(propertyName))
				return property;
		return null;
	}
	
	public SngFile setProperty(String propertyName, String propertyValue) {
		boolean found = false;
		for (int i = 0; i < this.properties.size(); i++) {
			if (this.properties.get(i).getPropertyName().equalsIgnoreCase(propertyName)) {
				found = true;
				this.properties.set(i, new SngFileProperty(propertyName, propertyValue));
				break;
			}
		}
		if (!found)
			this.properties.add(new SngFileProperty(propertyName, propertyValue));
		return this;
	}
	
	public boolean containsProperty(String propertyName) {
		for (SngFileProperty property : this.properties)
			if (property.getPropertyName().equalsIgnoreCase(propertyName))
				return true;
		return false;
	}
	
	public HashMap<String, String> getPropertiesAsHashMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		for (SngFileProperty property : this.properties)
			result.put(property.getPropertyName(), property.getPropertyValue());
		return result;
	}
	
	public String getAsSng() {
		String slides = "", result = "";
		for (SngFileProperty property : this.properties) {
			if (property.getPropertyName().equalsIgnoreCase("slides"))
				slides = property.getPropertyValue();
			else
				result += "#" + property.getPropertyName() + "=" + property.getPropertyValue() + "\n";
		}
		if (result.length() < 1)
			result = slides;
		else
			result += "---\n" + slides;
		return result.trim();
	}
	
	public void printAsSng() {
		System.out.println(this.getAsSng());
	}
}
