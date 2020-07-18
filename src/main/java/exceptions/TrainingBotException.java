package exceptions;

/**
 * The superclass of training bot exceptions
 */
public class TrainingBotException extends RuntimeException {
    public TrainingBotException(String message) {
        super(message);
    }
}
