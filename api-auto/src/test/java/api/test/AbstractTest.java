package api.test;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;

@ExtendWith({ReportPortalExtension.class})
public abstract class AbstractTest {
    private static final Logger LOGGER = LogManager.getLogger(AbstractTest.class);
    protected static final String HOST = "http://localhost:8082/api";
    protected static final String ALL_USERS_ENDPOINT = "/all";
    protected static final String GET_USER_ENDPOINT = "/user/%s";
    protected static final String ADD_USER_ENDPOINT = "/add-user";
    protected static final String UPDATE_USER_ENDPOINT = "/update-user/%s";
    protected static final String DELETE_USER_ENDPOINT = "/delete-user/%s";
    protected static final String DELETE_ALL_USERS_ENDPOINT = "/delete-all-users";

    protected static String parseDataToJsonString(String name, String email) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new User(name, email));
    }

    protected static void deleteAllUsers() {
        LOGGER.info("Delete created users");
        given().when().delete(HOST + DELETE_ALL_USERS_ENDPOINT).thenReturn().body().print();
    }

    protected static Integer addUser(String name, String email) throws JsonProcessingException {
        LOGGER.info("Add user with data: name: {}, email: {}", name, email);
        return given()
                .baseUri(HOST).basePath(ADD_USER_ENDPOINT)
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(parseDataToJsonString(name, email))
                .when().post().then().extract().path("id");
    }
}
