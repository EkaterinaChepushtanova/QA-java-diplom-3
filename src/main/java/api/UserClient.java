package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {

    public Response login(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post("api/auth/login");
    }

    public Response delete(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete("api/auth/user");
    }
}