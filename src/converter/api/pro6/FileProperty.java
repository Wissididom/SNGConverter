package converter.api.pro6;

public class FileProperty {
	private String propertyName = null;
	private Object propertyValue = null;
	
	public FileProperty() {}
	
	public FileProperty(String propertyName, Object propertyValue) {
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}
	
	public String getPropertyName() {
		return this.propertyName;
	}
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public Object getPropertyValue() {
		return this.propertyValue;
	}
	
	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}
}
