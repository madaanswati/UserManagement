package com.usermanagement.assignment.service;/*
* @created 04/03/2025 - 16:41
* @project assignment
& @author Swati Madaan
* User Management Service class
*/

import com.usermanagement.assignment.repository.EmailManagementRepository;
import com.usermanagement.assignment.utility.UserManagementCRUDException;
import com.usermanagement.assignment.dto.SearchProfileRequest;
import com.usermanagement.assignment.dto.UserManagementInputRequest;
import com.usermanagement.assignment.dto.UserManagementResponse;
import com.usermanagement.assignment.entity.UserAddress;
import com.usermanagement.assignment.entity.UserEmail;
import com.usermanagement.assignment.entity.UserInformation;
import com.usermanagement.assignment.entity.UserPhone;
import com.usermanagement.assignment.repository.UserManagementRepository;
import com.usermanagement.assignment.utility.UserManagementConstants;
import com.usermanagement.assignment.utility.UserManagementUtilityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserManagementRepository userRepo;

    @Autowired
    private EmailManagementRepository emailRepo;

    /**
     * @param req method for creating a new user profile
     * @return
     */
    public UserManagementResponse createUser(UserManagementInputRequest req) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            UserInformation user = new UserInformation();
            user = UserManagementUtilityHelper.populateUserInformationObject(req, user);
            UserInformation userInfoResult = userRepo.save(user);
            if (userInfoResult.getUserId() != null) {
                resp.setStatusCode(200);
                resp.setMessage(UserManagementConstants.USER_CREATION_SUCCESS_MSG);
            }
        } catch (Exception ex) {
            resp.setStatusCode(500);
            resp.setError(ex.getMessage());
        }
        return resp;
    }

    /**
     * @param userId
     * @return method for reading an existing user profile
     */
    public UserManagementResponse readUserProfile(Long userId) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            Optional<UserInformation> userInfo = userRepo.findById(userId);
            if (userInfo.isPresent()) {
                resp.setStatusCode(200);
                resp.setMessage(UserManagementConstants.RETRIEVE_USER_SUCCESS_MSG);
                resp = UserManagementUtilityHelper.cnvrtPhnInfoToRespObject(resp, userInfo.get());
            }
        } catch (Exception ex) {
            resp.setStatusCode(500);
            resp.setError(ex.getMessage());
        }
        return resp;
    }

    /**
     * @param userEmail
     * @return method for reading an existing user profile
     */
    public UserManagementResponse readUserProfileByEmail(String userEmail) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            UserEmail email = emailRepo.findByEmailAddress(userEmail).get(0);
            if (email != null && email.getId() != null && email.getUserInformation() != null && email.getUserInformation().getUserId() != null) {
                Optional<UserInformation> userInfo = userRepo.findById(email.getUserInformation().getUserId());
                if (userInfo.isPresent()) {
                    resp.setStatusCode(200);
                    resp.setMessage(UserManagementConstants.RETRIEVE_USER_SUCCESS_MSG);
                    resp = UserManagementUtilityHelper.cnvrtPhnInfoToRespObject(resp, userInfo.get());
                }
            }
        } catch (Exception ex) {
            resp.setStatusCode(500);
            resp.setError(ex.getMessage());
        }
        return resp;
    }

    /**
     * @param userId
     * @return method for delete an existing user profile
     */
    public UserManagementResponse deleteUserProfile(Long userId) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            userRepo.deleteById(userId);
            resp.setStatusCode(200);
            resp.setMessage(UserManagementConstants.DELETE_USER_SUCCESS_MSG);
        } catch (Exception ex) {
            resp.setStatusCode(500);
            resp.setError(ex.getMessage());
        }
        return resp;
    }

    /**
     * @param userEmail
     * @return method for delete an existing user profile using email address
     */
    public UserManagementResponse deleteUserProfileByEmail(String userEmail) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            UserEmail email = emailRepo.findByEmailAddress(userEmail).get(0);
            if (email != null && email.getId() != null && email.getUserInformation() != null && email.getUserInformation().getUserId() != null) {
                userRepo.deleteById(email.getUserInformation().getUserId());
                resp.setStatusCode(200);
                resp.setMessage(UserManagementConstants.DELETE_USER_SUCCESS_MSG);
            }
        } catch (Exception ex) {
            resp.setStatusCode(500);
            resp.setError(ex.getMessage());
        }
        return resp;
    }

    /**
     * @param userId
     * @return method for updating an existing user profile
     * Criteria API can also be used here
     */
    public UserManagementResponse updateUserProfile(UserManagementInputRequest req, Long userId) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            if (userRepo.existsById(userId)) {
                Optional<UserInformation> userInfo = userRepo.findById(userId);
                if (userInfo.isPresent()) {
                    UserInformation user = userInfo.get();
                    user = UserManagementUtilityHelper.populateUpdateUserInformationObject(req, user);
                    UserInformation userInfoResult = userRepo.save(user);
                    if (userInfoResult.getUserId() != null) {
                        resp.setStatusCode(200);
                        resp.setMessage(UserManagementConstants.UPDATE_USER_SUCCESS_MSG);
                    }
                }
            }
        } catch (Exception ex) {
            resp.setStatusCode(500);
            resp.setError(ex.getMessage());
        }
        return resp;
    }

    /**
     * @param req
     * @return search profile with given email address
     */
    public UserManagementResponse searchUserProfileByEmail(SearchProfileRequest req) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            UserEmail email = emailRepo.findByEmailAddress(req.getEmailAddress()).get(0);
            if (email != null && email.getId() != null && email.getUserInformation() != null && email.getUserInformation().getUserId() != null) {
                Optional<UserInformation> user = userRepo.findById(email.getUserInformation().getUserId());
                if (user.isPresent() && user.get().getUserId() != null) {
                    resp = UserManagementUtilityHelper.cnvrtPhnInfoToRespObject(resp, user.get());
                    resp.setStatusCode(200);
                    resp.setMessage(UserManagementConstants.SEARCH_USER_SUCCESS_MSG);
                }
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setMessage(UserManagementConstants.SEARCH_USER_ERROR_MSG + e.getMessage());
        }
        return resp;
    }

    /**
     * @param req
     * @return search profile with given User Id
     */
    public UserManagementResponse searchUserProfileByUserId(SearchProfileRequest req) {
        UserManagementResponse resp = new UserManagementResponse();
        try {
            Optional<UserInformation> user = userRepo.findById(req.getUserId());
            if (user.isPresent() && user.get().getUserId() != null) {
                resp = UserManagementUtilityHelper.cnvrtPhnInfoToRespObject(resp, user.get());
                resp.setStatusCode(200);
                resp.setMessage(UserManagementConstants.SEARCH_USER_SUCCESS_MSG);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setMessage(UserManagementConstants.SEARCH_USER_ERROR_MSG + e.getMessage());
        }
        return resp;
    }
}
