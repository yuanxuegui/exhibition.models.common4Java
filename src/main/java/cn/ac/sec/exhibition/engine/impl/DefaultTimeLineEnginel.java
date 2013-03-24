package cn.ac.sec.exhibition.engine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import cn.ac.sec.exhibition.domain.Command;
import cn.ac.sec.exhibition.domain.CommandContext;
import cn.ac.sec.exhibition.domain.ICommandContext;
import cn.ac.sec.exhibition.domain.ITimeTickListener;
import cn.ac.sec.exhibition.domain.ITimelineContext;
import cn.ac.sec.exhibition.domain.TimePoint;
import cn.ac.sec.exhibition.domain.Timeline;
import cn.ac.sec.exhibition.engine.ITimelineEngine;
import cn.ac.sec.exhibition.util.Constants;

public class DefaultTimeLineEnginel implements ITimelineEngine {
	private static ITimelineEngine instance = new DefaultTimeLineEnginel();
	private ITimelineContext timelineContext;
	private Timeline timeline;
	public static final String DEFAULT_TIMER_NAME = "DEFAULT_TIMER";
	private Timer timer = new Timer(DEFAULT_TIMER_NAME);
	private long period;
	private long max;
	private int timeTicked;
	private List<ITimeTickListener> timeTickListenerList = new ArrayList<ITimeTickListener>();
	
	private void queryAndExecuteCommands()
	{
		TimePoint timePoint = timeline.getItemByKey(timeTicked);
        if (timePoint != null)
        {
            List<Command> commands = timePoint.getCommand();
            if (commands != null && commands.size() > 0)
            {
                ICommandContext cmdCtx;
                for (Command cmd : commands)
                {
                    cmdCtx = new CommandContext(timelineContext, cmd);
                    cmd.execute(cmdCtx);
                }
            }
        }
	}
	
	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			System.out.println("timer tick:" + timeTicked);
			updateTimeTickListener();
			queryAndExecuteCommands();
			timeTicked++;
			if(timeTicked > max)
				timer.cancel();
		}
	};

	private DefaultTimeLineEnginel() {
	}

	public static ITimelineEngine getInstance() {
		return instance;
	}

	public void init(ITimelineContext timelineContext) {
		this.timelineContext = timelineContext;
		this.timeline = timelineContext.getTimeline();
		if (timeline == null) {
			System.out.println("please init timeLine Engine first");
			return;
		}
		
		
		String unit = timeline.getPropertyValue(Constants.TIME_UNIT_KEY);
		if (Constants.TIME_UNIT_MS.equals(unit)) {
			period = 1;
		} else if (Constants.TIME_UNIT_S.equals(unit)) {
			period = 1000;
		} else if (Constants.TIME_UNIT_M.equals(unit)) {
			period = 60000;
		}
		System.out.println(timeline.getPropertyValue(Constants.TIME_MAX_KEY));
		max = Long.valueOf(timeline.getPropertyValue(Constants.TIME_MAX_KEY));
	}

	public void start() {
		System.out.println("DefaultTimeLineEngine start");
		timer.schedule(timerTask, 0, period);
	}

	public void pause() {
		timer.cancel();
	}

	public void stop() {
		timer.cancel();
		timeTicked = 0;
		System.out.println("DefaultTimeLineEngine shop");
	}
	
	public void go(int timePointValue)
	{
		timeTicked = timePointValue;
	}

	@Override
	public void addTimeTickListener(ITimeTickListener timeTickListener) {
		this.timeTickListenerList.add(timeTickListener);
	}
	
	@Override
	public void removeTimeTickListener(ITimeTickListener timeTickListener) {
		this.timeTickListenerList.remove(timeTickListener);
	}
	
	private void updateTimeTickListener() {
		for(ITimeTickListener listener : timeTickListenerList)
			listener.update(timeTicked);
	}
}
