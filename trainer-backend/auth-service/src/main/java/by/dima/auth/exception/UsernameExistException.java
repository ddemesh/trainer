package by.dima.auth.exception;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException() {
    }

    public UsernameExistException(String message) {
        super(message);
    }

    public UsernameExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
