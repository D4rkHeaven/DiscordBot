package handlers;

import exceptions.InvalidCommandException;

/**
 * Interface for command handlers
 */
public interface CommandHandler {
    /**
     * Handle input command
     * @param command from user
     * @return reply for user
     * @throws InvalidCommandException when user inputs invalid command
     */
    String execute(String command) throws InvalidCommandException;
}
