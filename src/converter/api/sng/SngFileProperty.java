package converter.api.sng;

public class SngFileProperty {
	private String propertyName;
	private String propertyValue;
	
	public SngFileProperty(String name, String value) {
		this.propertyName = name;
		this.propertyValue = value;
	}
	
	public String getPropertyName() {
		return this.propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getPropertyValue() {
		return this.propertyValue;
	}
	
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
}
