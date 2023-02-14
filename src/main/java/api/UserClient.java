package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {

    public static final String END_POINT = "api/auth/";

    public Response create(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(END_POINT + "register");
    }

    public Response login(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(END_POINT + "login");
    }

    public Response delete(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(END_POINT + "user");
    }
}