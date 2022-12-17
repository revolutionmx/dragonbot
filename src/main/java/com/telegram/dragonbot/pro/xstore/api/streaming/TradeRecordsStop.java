package com.telegram.dragonbot.pro.xstore.api.streaming;

public class TradeRecordsStop extends StreamingCommandRecord {

	@Override
	protected String getCommand() {
		return "stopTrades";
	}
}