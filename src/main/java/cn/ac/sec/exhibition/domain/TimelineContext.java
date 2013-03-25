package cn.ac.sec.exhibition.domain;

import java.util.ArrayList;
import java.util.List;

public class TimelineContext implements ITimelineContext {
	private Area area;
	private List<Property> properties;

	public TimelineContext(Area area) {
		this.area = area;
	}

	@Override
	public void mergeProperties(List<Property> properties) {
		if(this.properties == null)
			this.properties = new ArrayList<Property>();
		
		this.properties.addAll(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ac.sec.exhibition.domain.ITimelineContext#getArea()
	 */
	@Override
	public Area getArea() {
		return area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.ac.sec.exhibition.domain.ITimelineContext#getTimeline()
	 */
	@Override
	public Timeline getTimeline() {
		return area.timeline;
	}

	@Override
	public String getPropertyValue(String name) {
		if (name == null || "".equals(name))
			return null;
		for (Property prop : properties) {
			if (prop.getName().equals(name))
				return prop.getValue();
		}
		return null;
	}
}
