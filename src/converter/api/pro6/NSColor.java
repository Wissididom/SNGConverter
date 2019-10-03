package converter.api.pro6;

public class NSColor extends NS {
	private String rvXMLDictionaryKey = null;
	private String content = null;
	
	public NSColor() {}
	
	public NSColor(String rvXMLDictionaryKey, String content) {
		this.rvXMLDictionaryKey = rvXMLDictionaryKey;
		this.content = content;
	}
	
	public String getRvXMLDictionaryKey() {
		return this.rvXMLDictionaryKey;
	}
	
	public void setRvXMLDictionaryKey(String rvXMLDictionaryKey) {
		this.rvXMLDictionaryKey = rvXMLDictionaryKey;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
