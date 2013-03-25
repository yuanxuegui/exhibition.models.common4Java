package org.gui.stage.timeline.engine.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import cn.ac.sec.exhibition.domain.Area;
import cn.ac.sec.exhibition.domain.Exhibition;
import cn.ac.sec.exhibition.domain.ITimeTickListener;
import cn.ac.sec.exhibition.domain.ITimelineContext;
import cn.ac.sec.exhibition.domain.TimelineContext;
import cn.ac.sec.exhibition.engine.ITimelineEngine;
import cn.ac.sec.exhibition.engine.impl.DefaultTimeLineEnginel;
import cn.ac.sec.exhibition.parser.IExhibitionParser;
import cn.ac.sec.exhibition.parser.impl.JaxbXmlExhibitionParser;
import cn.ac.sec.exhibition.sender.SenderLocator;
import cn.ac.sec.exhibition.sender.impl.UDPSender;
import cn.ac.sec.exhibition.util.Constants;

public class DefaultTimeLineEngineTest {

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() throws FileNotFoundException {
		// 解析时间线
		String file = "D:/Work/Workspace4Eclipse/exhibition/exhibition.models.common4Java/src/main/resources/exhibition-1.2.xml";
		IExhibitionParser parser = new JaxbXmlExhibitionParser();
		Exhibition exhibition = parser.parse(file);
		Area area = exhibition.getArea().get(0);
		System.out.println(area.getTimeline().getProperty().get(1).getName());

		// 配置时间线引擎
		ITimelineContext ctx = new TimelineContext(area);
		ctx.mergeProperties(exhibition.getProperty());
		final ITimelineEngine engine = DefaultTimeLineEnginel.getInstance();
		engine.init(ctx);
		engine.addTimeTickListener(new ITimeTickListener() {
			@Override
			public void update(int timeTicked) {
				System.out.println("UI update timeTicked:" + timeTicked);
			}
		});

		// 注册发送器
		UDPSender sender = new UDPSender(
				exhibition.getPropertyValue(Constants.SERVER_IP_KEY),
				exhibition.getPropertyValue(Constants.SERVER_PORT_KEY));
		SenderLocator.register(sender);

		engine.start();

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPause() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

}
