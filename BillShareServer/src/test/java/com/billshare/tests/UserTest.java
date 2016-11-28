package com.billshare.tests;

import com.billshare.constants.Status;
import com.billshare.domain.User;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

public class UserTest {

    User user = new User();

    @Test
    public void testSetPassword(){
        user.setPassword("pass123");
        assertEquals(user.getPassword(), "pass123");
    }

    @Test
    public void testSetId(){
        Long id = new Long(123);
        user.setId(id);
        assertEquals(user.getId(), id);
    }

    @Test
    public void testSetName(){
        user.setName("John");
        assertEquals(user.getName(), "John");
    }

    @Test
    public void testSetMobileNumber(){
        String number = "504-123-4567";
        user.setMobileNo(number);
        assertEquals(user.getMobileNo(), number);
    }

    @Test
    public void testSetEmailId(){
        String email = "test@test.com";
        user.setEmailId(email);
        assertEquals(user.getEmailId(), email);
    }

    @Test
    public void testSetCurrency(){
        String curreny = "USD";
        user.setCurrency(curreny);
        assertEquals(user.getCurrency(), "USD");
    }

    @Test
    public void testSetTimeZone(){
        String time = "America/Chicago";
        user.setTimeZone(time);
        assertEquals(user.getTimeZone(), "America/Chicago");
    }

    @Test
    public void testSetLanguageCode(){
        String lang = "EN-US";
        user.setLangugeCode(lang);
        assertEquals(user.getLangugeCode(), "EN-US");
    }

    @Test
    public void testSetDeviceID(){
        String deviceID = "Moto5";
        user.setDeviceId(deviceID);
        assertEquals(user.getDeviceId(), "Moto5");
    }

}
