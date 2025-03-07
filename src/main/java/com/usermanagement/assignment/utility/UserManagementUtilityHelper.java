package com.usermanagement.assignment.utility;/*
* @created 06/03/2025 - 14:50
* @project assignment
& @author Swati Madaan
*/

import com.usermanagement.assignment.dto.UserManagementInputRequest;
import com.usermanagement.assignment.dto.UserManagementResponse;
import com.usermanagement.assignment.entity.UserAddress;
import com.usermanagement.assignment.entity.UserEmail;
import com.usermanagement.assignment.entity.UserInformation;
import com.usermanagement.assignment.entity.UserPhone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for helper methods in the application
 */
public class UserManagementUtilityHelper {
    /**
     * @param userResp
     * @param user
     * @return
     * helper method for converting UserInformation object to UserManagementResponse obj
     */
    public static UserManagementResponse cnvrtPhnInfoToRespObject(UserManagementResponse userResp, UserInformation user){
        userResp.setFirstName(user.getFirstName());
        userResp.setLastName(user.getLastName());
        userResp.setTitle(user.getTitle());
        userResp.setDob(user.getDob().toString());
        if(user.getUserAddressesList().size() > 0){
            userResp.setUserAddresses(user.getUserAddressesList());
        }
        if(user.getPhoneNumbers().size() > 0){
            userResp.setPhoneNumbers(user.getPhoneNumbers());
        }
        if(user.getEmailList().size() > 0){
            userResp.setEmailList(user.getEmailList());
        }
        return userResp;
    }

    public static UserInformation populateUserInformationObject(UserManagementInputRequest req, UserInformation user) throws Exception{
        if(req.getDob() != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            user.setDob(formatter.parse(req.getDob()));
        }
        if(req.getTitle() != null) {
            user.setTitle(req.getTitle());
        }
        if(req.getFirstName() != null) {
            user.setFirstName(req.getFirstName());
        }
        if(req.getLastName() != null) {
            user.setLastName(req.getLastName());
        }
        if(req.getUserAddresses().size() > 0) {
            List<UserAddress> userAddrList = new ArrayList<>();
            for(UserAddress addr : req.getUserAddresses()){
                if (addr != null) {
                    UserAddress addrObj = new UserAddress();
                    addrObj.setUserInformation(user);
                    if(addr.getAddressLineOne() != null){
                        addrObj.setAddressLineOne(addr.getAddressLineOne());
                    }
                    if(addr.getAddressLineTwo() != null){
                        addrObj.setAddressLineTwo(addr.getAddressLineTwo());
                    }
                    if(addr.getCity() != null){
                        addrObj.setCity(addr.getCity());
                    }
                    if(addr.getCounty() != null){
                        addrObj.setCounty(addr.getCounty());
                    }
                    if(addr.getPostcode() != null){
                        addrObj.setPostcode(addr.getPostcode());
                    }
                    if(addr.getAddressType() != null){
                        addrObj.setAddressType(addr.getAddressType());
                    }
                    userAddrList.add(addrObj);
                }
            }
            user.setUserAddressesList(userAddrList);
        }
        if(req.getEmailList().size() > 0){
            List<UserEmail> emailList = new ArrayList<>();
            for(UserEmail emailObj : req.getEmailList()){
                if (emailObj != null) {
                    UserEmail email = new UserEmail();
                    email.setUserInformation(user);
                    if(emailObj.getEmailAddress() != null){
                        email.setEmailAddress(emailObj.getEmailAddress());
                    }
                    emailList.add(email);
                }
            }
            user.setEmailList(emailList);
        } else{
            throw new UserManagementCRUDException("Required field email Id not present");
        }
        if(req.getPhoneNumbers().size() > 0){
            List<UserPhone> phoneList = new ArrayList<>();
            for(UserPhone phone : req.getPhoneNumbers()){
                if (phone != null) {
                    UserPhone phoneObj = new UserPhone();
                    phoneObj.setUserInformation(user);
                    if(phone.getPhoneNumber() != null) {
                        phoneObj.setPhoneNumber(phone.getPhoneNumber());
                    }
                    if(phone.getPhoneType() != null) {
                        phoneObj.setPhoneType(phone.getPhoneType());
                    }
                    phoneList.add(phoneObj);
                }
            }
            user.setPhoneNumbers(phoneList);
        }
        return user;
    }

    public static UserInformation populateUpdateUserInformationObject(UserManagementInputRequest req, UserInformation user) throws Exception{
        if (req.getDob() != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            user.setDob(formatter.parse(req.getDob()));
        }
        if (req.getTitle() != null) {
            user.setTitle(req.getTitle());
        }
        if (req.getFirstName() != null) {
            user.setFirstName(req.getFirstName());
        }
        if (req.getLastName() != null) {
            user.setLastName(req.getLastName());
        }
        if (req.getUserAddresses().size() > 0) {
            for(int i =0; i < user.getUserAddressesList().size(); i++){
                UserAddress userAdd = user.getUserAddressesList().get(i);
                if (req.getUserAddresses().get(i).getAddressLineOne() != null && !userAdd.getAddressLineOne().equalsIgnoreCase(req.getUserAddresses().get(i).getAddressLineOne())) {
                    userAdd.setAddressLineOne(req.getUserAddresses().get(i).getAddressLineOne());
                }
                if (req.getUserAddresses().get(i).getAddressLineTwo() != null && !userAdd.getAddressLineTwo().equalsIgnoreCase(req.getUserAddresses().get(i).getAddressLineTwo())) {
                    userAdd.setAddressLineTwo(req.getUserAddresses().get(i).getAddressLineTwo());
                }
                if (req.getUserAddresses().get(i).getCity() != null && !userAdd.getCity().equalsIgnoreCase(req.getUserAddresses().get(i).getCity())) {
                    userAdd.setCity(req.getUserAddresses().get(i).getCity());
                }
                if (req.getUserAddresses().get(i).getCounty() != null && !userAdd.getCounty().equalsIgnoreCase(req.getUserAddresses().get(i).getCounty())) {
                    userAdd.setCounty(req.getUserAddresses().get(i).getCounty());
                }
                if (req.getUserAddresses().get(i).getPostcode() != null && !userAdd.getPostcode().equalsIgnoreCase(req.getUserAddresses().get(i).getPostcode())) {
                    userAdd.setPostcode(req.getUserAddresses().get(i).getPostcode());
                }
                if (req.getUserAddresses().get(i).getAddressType() != null && !userAdd.getAddressType().equalsIgnoreCase(req.getUserAddresses().get(i).getAddressType())) {
                    userAdd.setAddressType(req.getUserAddresses().get(i).getAddressType());
                }
                user.getUserAddressesList().set(i, userAdd);
            }
        }
        if (req.getEmailList().size() > 0) {
            for(int i = 0; i < user.getEmailList().size(); i++) {
                UserEmail email = user.getEmailList().get(i);
                if(req.getEmailList().get(i).getEmailAddress() != null && !email.getEmailAddress().equalsIgnoreCase(req.getEmailList().get(i).getEmailAddress())){
                    email.setEmailAddress(req.getEmailList().get(i).getEmailAddress());
                }
                user.getEmailList().set(i, email);
            }
        }
        if (req.getPhoneNumbers().size() > 0) {
            for(int i = 0; i < user.getPhoneNumbers().size(); i++) {
                UserPhone phone  = user.getPhoneNumbers().get(i);
                if(req.getPhoneNumbers().get(i).getPhoneNumber() != null && !(phone.getPhoneNumber()).equalsIgnoreCase(req.getPhoneNumbers().get(i).getPhoneNumber())) {
                    phone.setPhoneNumber(req.getPhoneNumbers().get(i).getPhoneNumber());
                }
                if(req.getPhoneNumbers().get(i).getPhoneType() != null && !(phone.getPhoneType()).equalsIgnoreCase(req.getPhoneNumbers().get(i).getPhoneType())) {
                    phone.setPhoneType(req.getPhoneNumbers().get(i).getPhoneType());
                }
                user.getPhoneNumbers().set(i, phone);
            }
        }
        return user;
    }
}
