package bot.handlers;

import bot.exceptions.InvalidParameterException;

/**
 * Interface for command bot.handlers
 */
public interface CommandHandler {
    /**
     * Handle input command
     * @param command from user
     * @return reply for user
     * @throws InvalidParameterException when command contains invalid parameter
     */
    String execute(String command) throws InvalidParameterException;
}
