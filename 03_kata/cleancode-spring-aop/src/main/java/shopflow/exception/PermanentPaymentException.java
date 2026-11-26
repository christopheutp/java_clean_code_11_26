package shopflow.exception;

public class PermanentPaymentException extends RuntimeException {
    public PermanentPaymentException(String message) {
        super(message);
    }
}
