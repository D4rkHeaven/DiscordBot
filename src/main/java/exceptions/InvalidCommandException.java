package exceptions;

/**
 * This exception thrown when the user enters an invalid command
 */
public class InvalidCommandException extends TrainingBotException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
