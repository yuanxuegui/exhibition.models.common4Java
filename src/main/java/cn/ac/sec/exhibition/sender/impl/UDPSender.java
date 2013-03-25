package cn.ac.sec.exhibition.sender.impl;


import cn.ac.sec.exhibition.sender.ISender;

public class UDPSender implements ISender {

	public UDPSender(String ip, String port) {
		System.out.println("init UDPSender: ip:" + ip + ", port:" + port);
	}

	@Override
	public void send(String message) {
		System.out.println("send: " + message);
	}

}
