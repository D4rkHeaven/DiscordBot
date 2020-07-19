package bot.handlers;

import bot.commands.About;
import bot.exceptions.InvalidParameterException;

public class AboutHandler implements CommandHandler {

    public AboutHandler(About about) {
    }

    @Override
    public String execute(String command) throws InvalidParameterException {
        return "Training bot";
    }
}
