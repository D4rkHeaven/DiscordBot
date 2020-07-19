package handlers;

import exceptions.InvalidParameterException;

/**
 * Interface for command handlers
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
