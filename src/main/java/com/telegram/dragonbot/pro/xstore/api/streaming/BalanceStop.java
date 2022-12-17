package com.telegram.dragonbot.pro.xstore.api.streaming;

public class BalanceStop extends StreamingCommandRecord {

	@Override
	protected String getCommand() {
		return "stopBalance";
	}
}