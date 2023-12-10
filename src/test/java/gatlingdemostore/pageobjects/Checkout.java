package gatlingdemostore.pageobjects;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Checkout {

    public static ChainBuilder viewCart =
            doIf(session -> !session.getBoolean("customerLoggedIn"))
                    .then(exec(Customer.login))
                    .exec(
                            http("Load Cart Page")
                                    .get("/cart/view")
                                    .check(css("#grandTotal").isEL("$#{cartTotal}")));

    public static ChainBuilder completeCheckout =
            exec(
                    http("Checkout Cart")
                            .get("/cart/checkout")
                            .check(regex("Thanks for your order! See you soon!")));
}