package cn.ac.sec.exhibition.domain;

import cn.ac.sec.exhibition.util.Constants;

public class CommandContext implements ICommandContext {
	private ITimelineContext timelineContext;
	private Command command;

	public CommandContext(ITimelineContext timelineContext, Command command) {
		this.timelineContext = timelineContext;
		this.command = command;
	}

	@Override
	public String buildCommand() {
		String deviceId = command.getDeviceId();
		String operationName = command.getOperationName();
		Device device = timelineContext.getArea().getItemByKey(deviceId);
		Operation operation = device.getItemByKey(operationName);
		String commandContent = operation.getCommand();
		commandContent = commandContent.replace("{" + Constants.CMD_DEVICE_ID
				+ "}", device.getId());
		for (Parameter parameter : operation.getParameter()) {
			String name = parameter.getName();
			String value = command.getPropertyValue(name);
			if (name != null && value != null)
				commandContent = commandContent
						.replace("{" + name + "}", value);
		}
		return commandContent;
	}

}
