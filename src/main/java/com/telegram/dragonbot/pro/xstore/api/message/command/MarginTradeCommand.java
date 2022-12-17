package com.telegram.dragonbot.pro.xstore.api.message.command;

import org.json.simple.JSONObject;
import com.telegram.dragonbot.pro.xstore.api.message.error.APICommandConstructionException;

public class MarginTradeCommand extends BaseCommand {

    public MarginTradeCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getMarginTrade";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{"symbol", "volume"};
    }
}