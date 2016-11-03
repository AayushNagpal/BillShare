package com.billshare.tests;

import com.billshare.Application;
import com.billshare.controllers.UserController;
import com.billshare.dto.UserDTO;
import com.billshare.services.UserService;
import com.billshare.utils.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.hamcrest.Matcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Chad on 10/31/2016.
 * http://javaninja.net/2014/02/testing-spring-mvc-rest-controllers/
 * http://www.captaindebug.com/2013/07/getting-started-with-springs-mvc-test_17.html
 * http://blog.trifork.com/2012/12/11/properly-testing-spring-mvc-controllers/
 * https://myshittycode.com/2013/10/23/how-to-unit-test-spring-mvc-controller/
 * https://myshittycode.com/2014/01/16/mockmvc-mockito-epic-tests/
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestUserController {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private UserDTO userDTO = new UserDTO();

    @Before
    public void setup() {
        userDTO.setDeviceId("1");
        userDTO.setCurrency("USD");
        userDTO.setName("Chad");
        userDTO.setTimeZone("America/Chicago");
        userDTO.setLangugeCode("EN");
        userDTO.setPassword("12345");
        userDTO.setEmailId("test@example.com");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    //The test currently throws an error. It's a problem with the setup of the test itself.
    @Test
    public void testRegister_NoError() throws Exception {
        when(userService.register(userDTO)).thenReturn(new ResponseStatus());

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(view().name("ok"));
    }
}