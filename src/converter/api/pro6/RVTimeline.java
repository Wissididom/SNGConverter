package converter.api.pro6;

import org.json.JSONArray;
import org.json.JSONObject;

public class RVTimeline {
	private double duration = -1;
	private String[] rvXMLIvarNames = null;
	private boolean loop = false;
	private int selectedMediaTrackIndex = -1;
	private double timeOffset = -1;
	private String rvXMLIvarName = null;
	private double playBackRate = -1;
	
	public RVTimeline() {}
	
	public RVTimeline(JSONObject obj) {
		if (obj.has("duration"))
			this.duration = obj.getInt("duration");
		if (obj.has("array")) {
			JSONArray array = obj.getJSONArray("array");
			int length = array.length();
			this.rvXMLIvarNames = new String[length];
			for (int i = 0; i < length; i++) {
				JSONObject temp = array.getJSONObject(i);
				if (temp.has("rvXMLIvarName"))
					this.rvXMLIvarNames[i] = temp.getString("rvXMLIvarName");
			}
		}
		if (obj.has("loop"))
			this.loop = obj.getBoolean("loop");
		if (obj.has("selectedMediaTrackIndex"))
			this.selectedMediaTrackIndex = obj.getInt("selectedMediaTrackIndex");
		if (obj.has("timeOffset"))
			this.timeOffset = obj.getDouble("timeOffset");
		if (obj.has("rvXMLIvarName"))
			this.rvXMLIvarName = obj.getString("rvXMLIvarName");
		if (obj.has("playBackRate"))
			this.playBackRate = obj.getDouble("playBackRate");
	}
	
	public double getDuration() {
		return this.duration;
	}
	
	public RVTimeline setDuration(double duration) {
		this.duration = duration;
		return this;
	}
	
	public String[] getRvXMLIvarNames() {
		return this.rvXMLIvarNames;
	}
	
	public RVTimeline setRvXMLIvarNames(String... rvXMLIvarNames) {
		this.rvXMLIvarNames = rvXMLIvarNames;
		return this;
	}
	
	public boolean isLoop() {
		return this.loop;
	}
	
	public RVTimeline setLoop(boolean loop) {
		this.loop = loop;
		return this;
	}
	
	public double getSelectedMediaTrackIndex() {
		return this.selectedMediaTrackIndex;
	}
	
	public RVTimeline setSelectedMediaTrackIndex(int selectedMediaTrackIndex) {
		this.selectedMediaTrackIndex = selectedMediaTrackIndex;
		return this;
	}
	
	public double getTimeOffset() {
		return this.timeOffset;
	}
	
	public RVTimeline setTimeOffset(double timeOffset) {
		this.timeOffset = timeOffset;
		return this;
	}
	
	public String getRvXMLIvarName() {
		return this.rvXMLIvarName;
	}
	
	public RVTimeline setRvXMLIvarName(String rvXMLIvarName) {
		this.rvXMLIvarName = rvXMLIvarName;
		return this;
	}
	
	public double getPlayBackRate() {
		return this.playBackRate;
	}
	
	public RVTimeline setPlayBackRate(double playBackRate) {
		this.playBackRate = playBackRate;
		return this;
	}
}
