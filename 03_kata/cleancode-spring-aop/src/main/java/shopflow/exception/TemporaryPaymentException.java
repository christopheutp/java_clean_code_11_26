package shopflow.exception;

public class TemporaryPaymentException extends RuntimeException {
    public TemporaryPaymentException(String message) {
        super(message);
    }
}
