package com.telegram.dragonbot.pro.xstore.api.message.response;

import com.telegram.dragonbot.pro.xstore.api.message.error.APIReplyParseException;

public class PingResponse extends BaseResponse {

    public PingResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
    }

	@Override
	public String toString() {
		return "PingResponse ["+ super.getStatus() + "]";
	}
}