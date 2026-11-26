package shopflow.model;

public final class Models {
    private Models() {
    }

    public enum Status {NEW, CONFIRMED, CANCELLED, PAID, REFUNDED}

    public record Product(String id, String name, int price) {
    }

    public record Customer(String id, String name) {
    }

    public record Order(String id, String customerId, String productId, int quantity, int total, Status status) {
        public Order withStatus(Status next) {
            return new Order(id, customerId, productId, quantity, total, next);
        }
    }

    public record CreateOrder(String customerId, String productId, int quantity) {
    }

    public record Audit(String user, String operation, String timestamp, boolean success) {
    }

    public record Metric(String operation, long durationMs, long thresholdMs, boolean exceeded) {
    }

    public record PaymentConfig(int temporaryFailures, boolean permanentFailure) {
    }
}
