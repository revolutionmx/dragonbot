package com.telegram.dragonbot.pro.xstore.api.message.command;

import org.json.simple.JSONObject;
import com.telegram.dragonbot.pro.xstore.api.message.error.APICommandConstructionException;

public class IbHistoryCommand extends BaseCommand {

    public IbHistoryCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getIbsHistory";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{"start", "end"};
    }
}