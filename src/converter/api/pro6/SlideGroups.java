package converter.api.pro6;

import java.util.ArrayList;
import java.util.List;

public class SlideGroups {
	private String rvXMLIvarName = null;
	private List<SlideGroup> groups = new ArrayList<SlideGroup>();
	
	public SlideGroups(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public SlideGroups(String rvXMLIvarName, SlideGroup... groups) {
		this.rvXMLIvarName = rvXMLIvarName;
		for (SlideGroup group : groups)
			this.groups.add(group);
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public void setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
	}
	
	public SlideGroup[] getGroups() {
		return this.groups.toArray(new SlideGroup[this.groups.size()]);
	}
	
	public SlideGroups setGroups(SlideGroup... groups) {
		for (SlideGroup group : groups)
			this.groups.add(group);
		return this;
	}
	
	public SlideGroups addGroup(SlideGroup group) {
		this.groups.add(group);
		return this;
	}
	
	public SlideGroup getGroup(int groupIndex) {
		return this.groups.get(groupIndex);
	}
	
	public SlideGroups setGroup(int groupIndex, SlideGroup slide) {
		this.groups.set(groupIndex, slide);
		return this;
	}
	
	public int getGroupCount() {
		return this.groups.size();
	}
}
