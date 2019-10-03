package converter.api.pro6;

public class NSNumber extends NS {
	private String rvXMLDictionaryKey = null;
	private double content = -1;
	
	public NSNumber() {}
	
	public NSNumber(String rvXMLDictionaryKey, double content) {
		this.rvXMLDictionaryKey = rvXMLDictionaryKey;
		this.content = content;
	}
	
	public String getRvXMLDictionaryKey() {
		return this.rvXMLDictionaryKey;
	}
	
	public void setRvXMLDictionaryKey(String rvXMLDictionaryKey) {
		this.rvXMLDictionaryKey = rvXMLDictionaryKey;
	}
	
	public double getContent() {
		return this.content;
	}
	
	public void setContent(double content) {
		this.content = content;
	}
}
