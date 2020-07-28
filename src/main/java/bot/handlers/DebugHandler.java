package bot.handlers;

import bot.TrainingBot;
import bot.commands.Command;
import bot.commands.Debug;
import bot.exceptions.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

@Slf4j
public class DebugHandler implements CommandHandler<Debug> {

    TrainingBot bot;

    public DebugHandler(TrainingBot bot) {
        this.bot = bot;
    }

    @Override
    public Debug generateCommand(MessageReceivedEvent message, TrainingBot bot) {
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
            bot.setDebugMode(true);
            debugCommand.setMode(true);
            debugCommand.setAnswer(embed.setDescription("Debug mod enabled").build());
            log.info("Debug mod enabled");
            return debugCommand;
        } else if (commandArgs[0].equals("off")) {
            bot.setDebugMode(false);
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
