package com.billshare.tests;

import com.billshare.constants.Status;
import com.billshare.domain.Friend;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

public class FriendTest {

    Friend friend = new Friend();

    @Test
    public void testSetGroupId(){
        Integer id = 123;
        friend.setGroupId(id);
        assertEquals(friend.getGroupId(), id);
    }

    @Test
    public void testSetUserId(){
        Integer id = 123;
        friend.setUserId(id);
        assertEquals(friend.getUserId(), id);
    }

    @Test
    public void testSetId(){
        Integer id = 123;
        friend.setId(id);
        assertEquals(friend.getId(), id);
    }

    @Test
    public void testSetStatus(){
        friend.setStatus(com.billshare.utils.Status.ACCEPTED);
        assertEquals(friend.getStatus(), com.billshare.utils.Status.ACCEPTED);
    }

}
