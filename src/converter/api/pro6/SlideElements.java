package converter.api.pro6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlideElements {
	private String rvXMLIvarName = null;
	private List<SlideElement> elements = new ArrayList<SlideElement>();
	
	public SlideElements() {}
	
	public SlideElements(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public SlideElements(String rvXMLIvarName, SlideElement... elements) {
		this.rvXMLIvarName = rvXMLIvarName;
		for (SlideElement element : elements)
			this.elements.add(element);
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public void setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public SlideElement[] getElements() {
		return this.elements.toArray(new SlideElement[this.elements.size()]);
	}
	
	public SlideElement getElement(String elementName) {
		for (SlideElement element : this.elements)
			if (element.getElementName().equalsIgnoreCase(elementName))
				return element;
		return null;
	}
	
	public SlideElements setElement(String elementName, SlideElement element) {
		boolean found = false;
		for (int i = 0; i < this.elements.size(); i++) {
			if (this.elements.get(i).getElementName().equalsIgnoreCase(elementName)) {
				found = true;
				this.elements.set(i, element);
			}
		}
		if (!found)
			this.elements.add(element);
		return this;
	}
	
	public SlideElements addElement(SlideElement element) {
		this.elements.add(element);
		return this;
	}
	
	public boolean containsElement(String elementName) {
		for (SlideElement element : this.elements)
			if (element.getElementName().equalsIgnoreCase(elementName))
				return true;
		return false;
	}
	
	public HashMap<String, SlideElement> getElementsAsHashMap() {
		HashMap<String, SlideElement> result = new HashMap<String, SlideElement>();
		for (SlideElement element : this.elements)
			result.put(element.getElementName(), element);
		return result;
	}
}
