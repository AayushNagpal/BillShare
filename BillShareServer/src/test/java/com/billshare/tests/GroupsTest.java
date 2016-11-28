package com.billshare.tests;

import com.billshare.domain.Groups;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

public class GroupsTest {

    Groups groups = new Groups();

    @Test
    public void testSetAmount(){
        BigDecimal amount = new BigDecimal(540);
        groups.setAmount(amount);
        assertEquals(groups.getAmount(), amount);
    }

    @Test
    public void testSetID(){
        Integer id = 123;
        groups.setId(id);
        assertEquals(groups.getId(), id);
    }

    @Test
    public void testSetName(){
        groups.setName("John");
        assertEquals(groups.getName(), "John");
    }

    @Test
    public void testSetAdminId(){
        Integer id = 123;
        groups.setAdminId(id);
        assertEquals(groups.getAdminId(), id);
    }

}
