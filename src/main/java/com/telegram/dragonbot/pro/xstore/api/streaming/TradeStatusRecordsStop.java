package com.telegram.dragonbot.pro.xstore.api.streaming;

public class TradeStatusRecordsStop extends StreamingCommandRecord {

	@Override
	protected String getCommand() {
		return "stopTradeStatus";
	}
}