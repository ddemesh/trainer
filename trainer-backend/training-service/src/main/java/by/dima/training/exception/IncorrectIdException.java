package by.dima.training.exception;

public class IncorrectIdException extends RuntimeException {
    public IncorrectIdException() {
        super();
    }

    public IncorrectIdException(String message) {
        super(message);
    }

    public IncorrectIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
