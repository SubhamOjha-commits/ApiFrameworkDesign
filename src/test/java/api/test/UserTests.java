package api.test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.number().numberBetween(1, 10000));
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(8, 16));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(0);
        logger = LogManager.getLogger(UserTests.class);

    }
    @Test(priority = 1)
    public void testPostUser() {
        logger.info("**********Creating User**********");
       Response response= UserEndPoint.createUser(userPayload);
       logger.info("**********User Created**********");
       response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void getUserByName(){
        logger.info("**********Reading User**********");
        Response response=UserEndPoint.readUser(this.userPayload.getUsername());
        logger.info("**********User Read**********");
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void updateUserByName(){
        logger.info("**********Updating User**********");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response=UserEndPoint.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();
        logger.info("**********User Updated**********");
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**********Verifying User Update**********");
        Response responseAfterUpdate=UserEndPoint.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
    }

    @Test(priority = 4)
    public void deleteUserByName(){
        Response response=UserEndPoint.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
