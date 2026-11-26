package shopflow;

import org.junit.jupiter.api.*;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopFlowTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void reset() {
        RestAssured.port = port;
        given().post("/demo/reset").then().statusCode(200);
    }

    String create() {
        return given().header("X-User", "seller").contentType("application/json").body("{\"customerId\":\"C1\",\"productId\":\"P1\",\"quantity\":2}").post("/orders").then().statusCode(200).body("total", equalTo(4000)).extract().path("id");
    }

    String confirmed() {
        String id = create();
        action(id, "confirm", "seller", 200);
        return id;
    }

    void action(String id, String action, String user, int status) {
        given().header("X-User", user).post("/orders/" + id + "/" + action).then().statusCode(status);
    }

    void configure(int failures, boolean permanent) {
        given().contentType("application/json").body("{\"temporaryFailures\":" + failures + ",\"permanentFailure\":" + permanent + "}").post("/demo/provider").then().statusCode(200);
    }

    @Test
    void refusedDoesNotMutateOrAudit() {
        String id = create();
        action(id, "confirm", "guest", 403);
        given().get("/orders/" + id).then().body("status", equalTo("NEW"));
        given().get("/audit").then().body("size()", equalTo(1));
        given().get("/metrics").then().body("size()", equalTo(1));
        given().get("/stocks/P1").then().body(equalTo("10"));
    }

    @Test
    void cancellationRestoresStock() {
        String id = confirmed();
        given().get("/stocks/P1").then().body(equalTo("8"));
        action(id, "cancel", "seller", 200);
        given().get("/stocks/P1").then().body(equalTo("10"));
    }

    @Test
    void businessFailureAuditedAndMeasured() {
        String id = create();
        action(id, "pay", "finance", 400);
        given().get("/audit").then().body("find { it.operation == 'PAY' }.success", equalTo(false));
        given().get("/metrics").then().body("find { it.operation == 'PAY' }.thresholdMs", equalTo(800));
    }

    @Test
    void temporaryFailureRetriedAndAuditedOnce() {
        String id = confirmed();
        configure(2, false);
        action(id, "pay", "finance", 200);
        given().get("/demo/provider").then().body("attempts", equalTo(3), "effects", equalTo(1));
        given().get("/audit").then().body("findAll { it.operation == 'PAY' }.size()", equalTo(1), "find { it.operation == 'PAY' }.success", equalTo(true));
        given().get("/metrics").then().body("findAll { it.operation == 'PAY' }.size()", equalTo(1));
        action(id, "refund", "finance", 200);
        given().get("/orders/" + id).then().body("status", equalTo("REFUNDED"));
    }

    @Test
    void exhaustedRetriesLeaveOrderUnchanged() {
        String id = confirmed();
        configure(4, false);
        action(id, "pay", "finance", 503);
        given().get("/demo/provider").then().body("attempts", equalTo(3), "effects", equalTo(0));
        given().get("/orders/" + id).then().body("status", equalTo("CONFIRMED"));
        given().get("/audit").then().body("find { it.operation == 'PAY' }.success", equalTo(false));
    }

    @Test
    void definitiveFailureNotRetried() {
        String id = confirmed();
        configure(0, true);
        action(id, "pay", "finance", 422);
        given().get("/demo/provider").then().body("attempts", equalTo(1), "effects", equalTo(0));
    }

    @Test
    void stockAndProductPermissions() {
        given().header("X-User", "seller").post("/stocks/P1/restock/5").then().statusCode(403);
        given().header("X-User", "warehouse").post("/stocks/P1/restock/5").then().statusCode(200).body(equalTo("15"));
        given().header("X-User", "admin").put("/products/P1/price/3000").then().statusCode(200).body("price", equalTo(3000));
    }
}
