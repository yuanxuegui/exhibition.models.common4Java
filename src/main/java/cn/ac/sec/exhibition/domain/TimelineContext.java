package cn.ac.sec.exhibition.domain;

public class TimelineContext implements ITimelineContext {
	private Area area;

	public TimelineContext(Area area) {
		this.area = area;
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
}
