package cn.ac.sec.exhibition.domain;

import java.util.List;

public interface ITimelineContext extends IPropertyFinder {

	public abstract void mergeProperties(List<Property> properties);
	
	public abstract Area getArea();

	public abstract Timeline getTimeline();

}