package com.telegram.dragonbot.pro.xstore.api.message.command;

import org.json.simple.JSONObject;
import com.telegram.dragonbot.pro.xstore.api.message.error.APICommandConstructionException;

public class StepRulesCommand extends BaseCommand {

    public StepRulesCommand() throws APICommandConstructionException{
        super(new JSONObject());
    }

    @Override
    public String getCommandName() {
        return "getStepRules";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{};
    }
}