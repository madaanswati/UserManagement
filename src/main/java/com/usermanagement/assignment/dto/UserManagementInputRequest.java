package com.usermanagement.assignment.dto;/*
* @created 05/03/2025 - 13:56
* @project assignment
& @author Swati Madaan
* Class for storing UserManagement Request details
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.usermanagement.assignment.entity.UserAddress;
import com.usermanagement.assignment.entity.UserEmail;
import com.usermanagement.assignment.entity.UserPhone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementInputRequest {
    /**
     * firstName
     */
    private String firstName;
    /**
     * lastName
     */
    private String lastName;
    /**
     * title
     */
    private String title;
    /**
     * Date of Birth
     */
    private String dob;
    /**
     * List of addresses of the user
     * Re-used entity class for User Address. Another DTO object may be created as well
     */
    private List<UserAddress> userAddresses;
    /**
     * List of email addresses for the user
     * Re-used entity class for User Email. Another DTO object may be created as well
     */
    private @NotNull List<UserEmail> emailList;

    /**
     * List of phone numbers for the user
     * Re-used entity class for User Phone Number. Another DTO object may be created as well
     */
    private List<UserPhone> phoneNumbers;
}
