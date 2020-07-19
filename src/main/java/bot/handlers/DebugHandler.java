package bot.handlers;

import bot.TrainingBot;
import bot.exceptions.InvalidCommandException;
import bot.exceptions.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugHandler implements CommandHandler {

    private TrainingBot bot;

    public DebugHandler(TrainingBot bot) {
        this.bot = bot;
    }

    @Override
    public String execute(String command) throws InvalidCommandException {
        if (command.contains("on")) {
            bot.setDebugMode(true);
            log.info("Debug mod enabled");
            return "Debug mod enabled";
        } else if (command.contains("off")) {
            bot.setDebugMode(false);
            log.info("Debug mod disabled");
            return "Debug mod disabled";
        } else {
            log.warn("Invalid command parameter");
            throw new InvalidParameterException("Invalid command parameter");
        }
    }
}
