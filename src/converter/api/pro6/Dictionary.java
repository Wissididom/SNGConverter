package converter.api.pro6;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	private String rvXMLIvarName = null;
	private List<NS> entries = new ArrayList<NS>();
	
	public Dictionary() {}
	
	public Dictionary(String rvXMLIvarName, NS... entries) {
		this.rvXMLIvarName = rvXMLIvarName;
		for (NS entry : entries)
			this.entries.add(entry);
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public void setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public NS[] getEntries() {
		return this.entries.toArray(new NS[this.entries.size()]);
	}
	
	public void setEntries(NS... entries) {
		this.entries.clear();
		for (NS entry : entries)
			this.entries.add(entry);
	}
	
	public void addEntry(NS entry) {
		this.entries.add(entry);
	}
	
	public void removeEntry(NS entry) {
		this.entries.remove(entry);
	}
	
	public boolean containsEntry(NS entry) {
		return this.entries.contains(entry);
	}
}
