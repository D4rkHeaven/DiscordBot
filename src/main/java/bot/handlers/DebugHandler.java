package bot.handlers;

import bot.commands.Command;
import bot.commands.Debug;
import bot.exceptions.InvalidParameterException;
import bot.listeners.MessageListener;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

@Slf4j
public class DebugHandler implements CommandHandler<Debug> {

    MessageListener listener;

    public DebugHandler(MessageListener listener) {
        this.listener = listener;
    }

    @Override
    public Debug generateCommand(MessageReceivedEvent message, MessageListener listener) {
        String[] commandArgs = Arrays.
                stream(message.getMessage()
                        .getContentRaw()
                        .split(" "))
                .skip(1)
                .toArray(String[]::new);

        Debug debugCommand = new Debug();
        debugCommand.setTargetChannel(message.getChannel());
        EmbedBuilder embed = new EmbedBuilder();

        if (commandArgs[0].equals("on")) {
            listener.setDebugMode(true);
            debugCommand.setMode(true);
            debugCommand.setAnswer(embed.setDescription("Debug mod enabled").build());
            log.info("Debug mod enabled");
            return debugCommand;
        } else if (commandArgs[0].equals("off")) {
            listener.setDebugMode(false);
            debugCommand.setMode(false);
            debugCommand.setAnswer(embed.setDescription("Debug mod disabled").build());
            log.info("Debug mod disabled");
            return debugCommand;
        } else {
            log.warn("Invalid command parameter");
            throw new InvalidParameterException("Invalid command parameter");
        }
    }

    @Override
    public void execute(Command command) {
        command.getTargetChannel().sendMessage(command.getAnswer()).submit();
    }
}
