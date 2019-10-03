package converter.api.pro6;

import java.util.Base64;

public class NSString extends NS {
	private String rvXMLIvarName = null;
	private String content = null;
	private boolean decoded = false;
	
	public NSString() {}
	
	public NSString(String rvXMLIvarName, String content) {
		this(rvXMLIvarName, content, false);
	}
	
	public NSString(String rvXMLIvarName, String content, boolean autoDecode) {
		this.rvXMLIvarName = rvXMLIvarName;
		this.content = content;
		if (autoDecode)
			this.decodeContent();
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
	
	public boolean isDecoded() {
		return this.decoded;
	}
	
	public NSString decodeContent() {
		this.content = new String(Base64.getDecoder().decode(this.content));
		this.decoded = true;
		return this;
	}
	
	public NSString encodeContent() {
		this.content = Base64.getEncoder().encodeToString(this.content.getBytes());
		this.decoded = false;
		return this;
	}
}
