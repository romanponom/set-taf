package api.test;

import com.epam.reportportal.junit5.ReportPortalExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.test.AbstractTest.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith({ReportPortalExtension.class})
public class SpringBootRestTest {

    private static final Logger LOGGER = LogManager.getLogger(SpringBootRestTest.class);

    @DisplayName("Add user")
    @ParameterizedTest
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    public void addUserTest(String name, String email) throws JsonProcessingException {
        LOGGER.info("Test data: name: {}, email {}", name, email);
        given()
                .baseUri(HOST).basePath(ADD_USER_ENDPOINT)
                .accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(parseDataToJsonString(name, email))
                .when().post()
                .then().assertThat().contentType(ContentType.JSON)
                .and().statusCode(201)
                .and().body("name", equalTo(name)).body("email", equalTo(email));

        deleteAllUsers();
    }

    @DisplayName("Get all users")
    @ParameterizedTest
    @CsvFileSource(resources = "/one_user.csv", numLinesToSkip = 1)
    public void getAllUsersTest(String name, String email) throws JsonProcessingException {
        deleteAllUsers();
        addUser(name, email);

        LOGGER.info("Get all users");
        given()
                .baseUri(HOST).basePath(ADD_USER_ENDPOINT)
                .when()
                .get()
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON);
    }

    @DisplayName("Get user")
    @ParameterizedTest
    @CsvFileSource(resources = "/one_user.csv", numLinesToSkip = 1)
    public void getUser(String name, String email) throws JsonProcessingException {
        deleteAllUsers();
        Integer id = addUser(name, email);

        LOGGER.info("Get user by id: {}", id);
        given()
                .baseUri(HOST).basePath(String.format(GET_USER_ENDPOINT, id))
                .when()
                .get()
                .then().assertThat().contentType(ContentType.JSON)
                .and().statusCode(200)
                .and().body("name", equalTo(name)).body("email", equalTo(email)).body("id", equalTo(id));

        deleteAllUsers();
    }

    @DisplayName("Delete user")
    @Test
    public void deleteUser() {

    }
    @DisplayName("Update user")
    @ParameterizedTest
    @CsvFileSource(resources = "/one_user.csv", numLinesToSkip = 1)
    public void updateUser() {

    }

    @DisplayName("Delete all users")
    @ParameterizedTest
    @CsvFileSource(resources = "/one_user.csv", numLinesToSkip = 1)
    public void deleteAllUsersTest(String name, String email) throws JsonProcessingException {
        addUser(name, email);
        given()
                .when().delete(HOST + DELETE_ALL_USERS_ENDPOINT)
                .then().assertThat().statusCode(200).contentType(ContentType.JSON);
    }
}
