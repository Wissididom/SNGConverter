package converter.api.pro6;

import java.util.ArrayList;
import java.util.List;

public class SlideGroup {
	private String color = "0 0 0 0";
	private String name = "";
	private String uuid = "";
	private List<Slide> slides = new ArrayList<Slide>();
	
	public SlideGroup() {}
	
	public SlideGroup(Slide... slides) {
		this(null, null, null, slides);
	}
	
	public SlideGroup(String color, Slide... slides) {
		this(color, null, null, slides);
	}
	
	public SlideGroup(String color, String name, Slide... slides) {
		this(color, name, null, slides);
	}
	
	public SlideGroup(String color, String name, String uuid, Slide... slides) {
		this.color = color;
		this.name = name;
		this.uuid = uuid;
		for (Slide slide : slides)
			this.slides.add(slide);
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	
	public Slide[] getSlides() {
		return this.slides.toArray(new Slide[this.slides.size()]);
	}
	
	public Slide getSlide(int slideIndex) {
		return this.slides.get(slideIndex);
	}
	
	public SlideGroup setSlide(int slideIndex, Slide slide) {
		this.slides.set(slideIndex, slide);
		return this;
	}
	
	public SlideGroup addSlide(Slide slide) {
		this.slides.add(slide);
		return this;
	}
	
	public int getSlideCount() {
		return this.slides.size();
	}
}
