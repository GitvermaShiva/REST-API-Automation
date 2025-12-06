package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsProperties {

    static ResourceBundle getURL(){  //Gets URLs from routes.properties
        ResourceBundle routes=ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload){

        String post_url=getURL().getString("post_url");

        Response res=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(post_url);

        return res;
    }

    public static Response getUser(String username){

        String get_url=getURL().getString("get_url");

        Response res=given()
                .pathParam("username",username)

                .when()
                .get(get_url);

        return res;
    }

    public static Response updateUser(String username, User payload){

        String put_url=getURL().getString("put_url");

        Response res=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)

                .when()
                .put(put_url);

        return res;
    }

    public static Response deleteUser(String username){

        String delete_url=getURL().getString("delete_url");

        Response res=given()
                .pathParam("username",username)

                .when()
                .delete(delete_url);

        return res;
    }
}
