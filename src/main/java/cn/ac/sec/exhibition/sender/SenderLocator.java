package cn.ac.sec.exhibition.sender;

public class SenderLocator {
	public static ISender sender;

	public static void register(ISender sender) {
		SenderLocator.sender = sender;
	}

	public static ISender lookup() {
		return SenderLocator.sender;
	}
}
