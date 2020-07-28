package bot.handlers;

import bot.commands.Command;
import bot.listeners.MessageListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Interface for command handlers
 */
public interface CommandHandler<T extends Command> {
    /**
     * Handler for commands
     * @param event - event received by bot
     * @param listener - current bot listener
     */
    T generateCommand(MessageReceivedEvent event, MessageListener listener);

    void execute(Command command);
}
