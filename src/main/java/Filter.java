import commands.About;
import exceptions.InvalidCommandException;
import handlers.*;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Command filter
 */
@Slf4j
public class Filter {

    private HashMap<String, CommandType> commandType;
    private HashMap<CommandType, CommandHandler> handlerType;
    private Pattern pattern;
    TrainingBot bot;

    public Filter(TrainingBot trainingBot) {
        this.bot = trainingBot;
        pattern = Pattern.compile("^(!).*");
        commandType = new HashMap<>();
        handlerType = new HashMap<>();

        commandType.put("!about", CommandType.ABOUT);
        commandType.put("!debug", CommandType.DEBUG);
        commandType.put("!help", CommandType.HELP);
        commandType.put("!profile", CommandType.PROFILE);
        handlerType.put(CommandType.ABOUT, new AboutHandler(new About()));
        handlerType.put(CommandType.DEBUG, new DebugHandler());
        handlerType.put(CommandType.HELP, new HelpHandler());
        handlerType.put(CommandType.PROFILE, new ProfileHandler());
    }

    public boolean isCommand(String message) {
        return pattern.matcher(message).matches();
    }

    public String execute(MessageReceivedEvent event) throws InvalidCommandException {
        String rawMessage = event.getMessage().getContentRaw().trim();
        log.info(event.getMessage().toString());
        Scanner scanner = new Scanner(rawMessage);
        CommandType commandType = this.commandType.get(scanner.next());
        if (commandType == null) {
            throw new InvalidCommandException("Invalid command");
        }
        return handlerType.get(commandType).execute(rawMessage);
    }
}
