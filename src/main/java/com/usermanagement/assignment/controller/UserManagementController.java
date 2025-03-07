package com.usermanagement.assignment.controller;

import com.usermanagement.assignment.dto.SearchProfileRequest;
import com.usermanagement.assignment.dto.UserManagementInputRequest;
import com.usermanagement.assignment.dto.UserManagementResponse;
import com.usermanagement.assignment.service.UserManagementService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * @created 04/03/2025 - 15:54
 * @project assignment
 * @author Swati Madaan
 * Rest Controller for Profile retrieval application
 */

@RestController
@RequestMapping(path = "/users")
public class UserManagementController {
    /**
     * User Management Service
     */
    @Autowired
    private UserManagementService userManagementService;


    /**
     * @param userInformation
     * Method for creating a new user profile
     * @return
     */
    @PostMapping("/createUserProfile/")
    public ResponseEntity<UserManagementResponse> createUserProfile(@RequestBody UserManagementInputRequest userInformation) {
           return ResponseEntity.ok(userManagementService.createUser(userInformation));
    }

    /**
     * @param userId
     * @return
     * Method for getting details of the user
     */
    @GetMapping("/readUserProfile/{userId}")
    public ResponseEntity<UserManagementResponse> readUserProfile(@PathVariable @NotNull Long userId) {
        return ResponseEntity.ok(userManagementService.readUserProfile(userId));
    }

    /**
     * @param userEmail
     * @return
     * Method for getting details of the user using email address
     */
    @GetMapping("/readUserProfileByEmail/{userEmail}")
    public ResponseEntity<UserManagementResponse> readUserProfileByEmail(@PathVariable @NotNull String userEmail) {
        return ResponseEntity.ok(userManagementService.readUserProfileByEmail(userEmail));
    }
    /**
     * @param userId
     * @return
     * method for deleting the user
     */
    @DeleteMapping("/deleteUserProfile/{userId}")
    public ResponseEntity<UserManagementResponse> deleteUserProfile(@PathVariable @NotNull Long userId) {
        return ResponseEntity.ok(userManagementService.deleteUserProfile(userId));
    }

    /**
     * @param userEmail
     * @return
     * method for deleting the user
     */
    @DeleteMapping("/deleteUserProfileByEmail/{userEmail}")
    public ResponseEntity<UserManagementResponse> deleteUserProfileByEmail(@PathVariable @NotNull String userEmail) {
        return ResponseEntity.ok(userManagementService.deleteUserProfileByEmail(userEmail));
    }
    /**
     * @param userId
     * @return
     * method for updating User details
     */
    @PostMapping("/updateUserProfile/{userId}")
    public ResponseEntity<UserManagementResponse> updateUserProfile(@RequestBody UserManagementInputRequest userInformation, @PathVariable @NotNull Long userId) {
        return ResponseEntity.ok(userManagementService.updateUserProfile(userInformation, userId));
    }

    /**
     * @param searchProfileRequest
     * @return
     * method for user profile with email address or user Id
     */
    @PostMapping("/searchUserProfile/")
    public ResponseEntity<UserManagementResponse> searchUserProfile(@RequestBody SearchProfileRequest searchProfileRequest) {
        if(searchProfileRequest.getEmailAddress() != null) {
            return ResponseEntity.ok(userManagementService.searchUserProfileByEmail(searchProfileRequest));
        }  else if(searchProfileRequest.getUserId() != null){
            return ResponseEntity.ok(userManagementService.searchUserProfileByUserId(searchProfileRequest));
        } else{
            return ResponseEntity.ok(new UserManagementResponse());
        }
    }

}

