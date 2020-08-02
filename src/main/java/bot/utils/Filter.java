package bot.utils;

import bot.commands.Command;
import bot.exceptions.InvalidCommandException;
import bot.handlers.CommandHandler;
import bot.listeners.MessageListener;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Command filter
 */
@Slf4j
public class Filter {

    private static final Map<String, Class<? extends CommandHandler<? extends Command>>> handlerType = new HashMap<>();
    private Pattern pattern;
    private MessageListener listener;

    static {
        Arrays.stream(CommandType.values()).forEach(commandType -> handlerType.put(commandType.getCommandName(), commandType.getCommandHandler()));
    }

    public Filter(MessageListener listener) {
        this.listener = listener;
        pattern = Pattern.compile("^(!).*");
    }

    public boolean isCommand(String message) {
        return pattern.matcher(message).matches();
    }

    /**
     * Parses message to generate and execute commands
     *
     * @param event received message
     * @throws Exception if command didn't recognized
     */
    public void execute(MessageReceivedEvent event) throws Exception {
        String rawMessage = event.getMessage().getContentRaw().trim();
        log.info("Bot received message: {}", event.getMessage().toString());

        String coreCommand = rawMessage.split("\\s")[0].toLowerCase(Locale.ENGLISH);
        Class<? extends CommandHandler<? extends Command>> commandClass = handlerType.get(coreCommand);
        if (commandClass == null) {
            log.warn("Invalid command");
            throw new InvalidCommandException("Invalid command");
        }
        CommandHandler<? extends Command> commandHandler = commandClass.getDeclaredConstructor(MessageListener.class).newInstance(listener);
        Command command = commandHandler.generateCommand(event, listener);
        commandHandler.execute(command);
    }
}
