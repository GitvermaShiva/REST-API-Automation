package api.testCases;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.utilities.DataProviders;

public class TS01_UserTestsDataDrivenTests {

    @Test(priority = 1, dataProvider = "UserDataFromExcel", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fName, String lName, String eml, String ph) throws InterruptedException {

        User userPayload=new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(eml);
        userPayload.setPhone(ph);

        Response res = UserEndPoints.createUser(userPayload);
        res.then().log().all();

        Assert.assertEquals(res.getStatusCode(), 200);
        Thread.sleep(1000); // FIX: Allow Petstore to store user
    }

}
