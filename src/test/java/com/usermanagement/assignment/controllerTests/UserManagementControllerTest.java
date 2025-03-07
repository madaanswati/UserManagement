package com.usermanagement.assignment.controllerTests;/*
* @created 07/03/2025 - 04:47
* @project assignment
& @author Swati Madaan
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.assignment.controller.UserManagementController;
import com.usermanagement.assignment.dto.UserManagementInputRequest;
import com.usermanagement.assignment.dto.UserManagementResponse;
import com.usermanagement.assignment.service.UserManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.io.File;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserManagementController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserManagementControllerTest {
    /**
     * mockMVC
     */
    @Autowired
    private MockMvc mockMVC;
    /**
     * userManagementService
     */
    @MockitoBean
    private UserManagementService userManagementService;
    /**
     * searchProfileRequest
     */
    private UserManagementInputRequest userMgmtInputRequest;
    /**
     * objectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @throws Exception
     * Test for creating user profile
     */
    @Test
    public void createUserProfileTest() throws Exception {
        try {
            userMgmtInputRequest = objectMapper.readValue(new File("/Volumes/SwatiWorkspace/intellijworkspace/assignment/src/test/resources/CreateUserProfile.json"), UserManagementInputRequest.class);
            UserManagementResponse resp  = objectMapper.readValue(new File("/Volumes/SwatiWorkspace/intellijworkspace/assignment/src/test/resources/CreateUserProfileResponse.json"), UserManagementResponse.class);
            given(userManagementService.createUser(ArgumentMatchers.any())).willReturn(resp);
            MockHttpServletResponse response = mockMVC.perform(post("/users/createUserProfile/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userMgmtInputRequest))).andReturn().getResponse();
            assertEquals(response.getStatus(), 200);
            assertEquals(response.getContentAsString(), objectMapper.writeValueAsString(resp));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}



