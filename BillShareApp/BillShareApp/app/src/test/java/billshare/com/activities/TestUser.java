package billshare.com.activities;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import billshare.com.model.User;

/**
 * Created by Chad on 10/11/2016.
 */

public class TestUser {
    /**
     * To work on unit tests, switch the Test Artifact in the Build Variants view.
     */
    private User testUser1;
    private User testUser2;

    public TestUser(){
        testUser1 = new User(0, "Chad", "504-123-4567", "chad@chadmwest.com", "USD", "America/Chicago", "en", "1", "badpassword");
        testUser2 = new User();
    }

    //Testing get methods
    @Test
    public void id_isCorrect() throws Exception {
        assertEquals((int)testUser1.getId(), 0);
    }

    @Test
    public void name_isCorrect() throws Exception {
        assertEquals(testUser1.getName(), "Chad");
    }

    @Test
    public void mobileNo_isCorrect() throws Exception {
        assertEquals(testUser1.getMobileNo(), "504-123-4567");
    }

    @Test
    public void emailId_isCorrect() throws Exception {
        assertEquals(testUser1.getEmailId(), "chad@chadmwest.com");
    }

    @Test
    public void currency_isCorrect() throws Exception {
        assertEquals(testUser1.getCurrency(), "USD");
    }

    @Test
    public void timeZone_isCorrect() throws Exception {
        assertEquals(testUser1.getTimeZone(), "America/Chicago");
    }

    @Test
    public void languageCode_isCorrect() throws Exception {
        assertEquals(testUser1.getLangugeCode(), "en");
    }

    @Test
    public void deviceId_isCorrect() throws Exception {
        assertEquals(testUser1.getDeviceId(), "1");
    }

    @Test
    public void password_isCorrect() throws Exception {
        assertEquals(testUser1.getPassword(), "badpassword");
    }


    //Testing Set Methods
    @Test
    public void set_id_isCorrect() throws Exception {
        testUser2.setId(0);
        assertEquals((int)testUser2.getId(), 0);
    }

    @Test
    public void set_name_isCorrect() throws Exception {
        testUser2.setName("Chad");
        assertEquals(testUser1.getName(), "Chad");
    }

    @Test
    public void set_mobileNo_isCorrect() throws Exception {
        testUser2.setMobileNo("504-123-4567");
        assertEquals(testUser1.getMobileNo(), "504-123-4567");
    }

    @Test
    public void set_emailId_isCorrect() throws Exception {
        testUser2.setEmailId("chad@chadmwest.com");
        assertEquals(testUser1.getEmailId(), "chad@chadmwest.com");
    }

    @Test
    public void set_currency_isCorrect() throws Exception {
        testUser2.setCurrency("USD");
        assertEquals(testUser1.getCurrency(), "USD");
    }

    @Test
    public void set_timeZone_isCorrect() throws Exception {
        testUser2.setTimeZone("America/Chicago");
        assertEquals(testUser1.getTimeZone(), "America/Chicago");
    }

    @Test
    public void set_languageCode_isCorrect() throws Exception {
        testUser2.setLangugeCode("en");
        assertEquals(testUser1.getLangugeCode(), "en");
    }

    @Test
    public void set_deviceId_isCorrect() throws Exception {
        testUser2.setDeviceId("1");
        assertEquals(testUser1.getDeviceId(), "1");
    }

    @Test
    public void set_password_isCorrect() throws Exception {
        testUser2.setPassword("badpassword");
        assertEquals(testUser1.getPassword(), "badpassword");
    }

}
