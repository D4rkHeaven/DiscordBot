package exceptions;

/**
 * This exception thrown when the user enters an invalid parameter
 */
public class InvalidParameterException extends TrainingBotException {
    public InvalidParameterException(String message) {
        super(message);
    }
}
