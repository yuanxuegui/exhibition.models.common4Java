package cn.ac.sec.exhibition.engine;

import cn.ac.sec.exhibition.domain.ITimeTickListener;
import cn.ac.sec.exhibition.domain.ITimelineContext;

public interface ITimelineEngine {
	void init(ITimelineContext timelineContext);
	void start();
	void pause();
	void stop();
	void go(int timePointValue);
	void addTimeTickListener(ITimeTickListener timeTickListener);
	void removeTimeTickListener(ITimeTickListener timeTickListener);
}
