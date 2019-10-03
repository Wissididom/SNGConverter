package converter.api.pro6;

public class SlideShadow {
	private String rvXMLIvarName = null;
	private String content = null;
	
	public SlideShadow() {}
	
	public SlideShadow(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public SlideShadow(String rvXMLIvarName, String content) {
		this.rvXMLIvarName = rvXMLIvarName;
		this.content = content;
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public void setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
