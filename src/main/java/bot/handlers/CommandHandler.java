package bot.handlers;

import bot.TrainingBot;
import bot.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Interface for command handlers
 */
public interface CommandHandler<T extends Command> {
    /**
     * Handler for commands
     * @param event - event received by bot
     * @param bot - current bot
     */
    T generateCommand(MessageReceivedEvent event, TrainingBot bot);

    void execute(Command command);
}
