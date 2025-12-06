package api.test;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsProperties;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestsProperties {

    Faker fake;
    User userPayload;

    private static final Logger logger = LogManager.getLogger(UserTestsProperties.class);

    @BeforeClass
    public void setup() {
        logger.info("=================User Model Test Suite Started=====================");
        logger.info("===== Initializing Test Data =====");

        fake = new Faker();
        userPayload = new User();

        userPayload.setId(fake.number().numberBetween(1, 9999));     // FIXED
        userPayload.setUsername("user" + fake.number().digits(5));   // FIXED
        userPayload.setFirstName(fake.name().firstName());
        userPayload.setLastName(fake.name().lastName());
        userPayload.setEmail(fake.internet().emailAddress());
        userPayload.setPassword(fake.internet().password(5, 10));
        userPayload.setPhone(fake.phoneNumber().cellPhone());

        logger.info("Generated Test User Details:-");
        logger.info("ID: " + userPayload.getId());
        logger.info("Username: " + userPayload.getUsername());
        logger.info("Email: " + userPayload.getEmail());
        logger.info("====================================");
    }

    // ========================================================
    // Create User  
    // ========================================================
    @Test(priority = 1)
    public void testPostUser() throws InterruptedException {

        logger.info("===== Starting Create User Test =====");
        logger.info("Sending POST request to create user...");

        Response res = UserEndPointsProperties.createUser(userPayload);
        res.then().log().all();

        logger.info("POST Response Code: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200, "User creation failed!");

        logger.info("User successfully created in PetStore API.");
        logger.info("Waiting 1 second for data persistence...");

        Thread.sleep(1000);

        logger.info("===== Create User Test Completed =====");
    }

    // ========================================================
    // Get User
    // ========================================================
    @Test(priority = 2)
    public void testGetUserByName() {

        logger.info("===== Starting Get User Test =====");
        logger.info("Fetching User by username: " + userPayload.getUsername());

        Response res = UserEndPointsProperties.getUser(this.userPayload.getUsername());
        res.then().log().all();

        logger.info("GET Response Code: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200, "Failed to fetch user!");

        logger.info("User fetch successful.");
        logger.info("===== Get User Test Completed =====");
    }

    // ========================================================
    // Update User
    // ========================================================
    @Test(priority = 3)
    public void testUpdateUserByName() {

        logger.info("===== Starting Update User Test =====");
        logger.info("Updating user details...");

        // Update user details
        userPayload.setFirstName(fake.name().firstName());
        userPayload.setLastName(fake.name().lastName());
        userPayload.setEmail(fake.internet().emailAddress());

        Response res = UserEndPointsProperties.updateUser(this.userPayload.getUsername(), userPayload);
        res.then().log().all();

        logger.info("PUT Response Code: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200, "User update failed!");

        logger.info("User updated successfully. Validating updated data...");

        // Validate updated data
        Response responseAfterUpdate = UserEndPointsProperties.getUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200, "Update verification failed!");

        logger.info("Updated user verified successfully.");
        logger.info("===== Update User Test Completed =====");
    }

    // ========================================================
    // Delete User
    // ========================================================
    @Test(priority = 4)
    public void testDeleteUserByName() {

        logger.info("===== Starting Delete User Test =====");
        logger.info("Deleting user: " + userPayload.getUsername());

        Response res = UserEndPointsProperties.deleteUser(this.userPayload.getUsername());
        res.then().log().all();

        logger.info("DELETE Response Code: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200, "User deletion failed!");

        logger.info("User deleted successfully.");
        logger.info("===== Delete User Test Completed =====");
        logger.info("=================User Test Suite Finished=====================");
    }
}
