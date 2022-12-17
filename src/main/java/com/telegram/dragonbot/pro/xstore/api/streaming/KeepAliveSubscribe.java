package com.telegram.dragonbot.pro.xstore.api.streaming;

public class KeepAliveSubscribe extends RecordSubscribe {

    public KeepAliveSubscribe(String streamSessionId) {
    	super(streamSessionId);
    }

	@Override
	public String getCommand() {
		return "getKeepAlive";
	}
}