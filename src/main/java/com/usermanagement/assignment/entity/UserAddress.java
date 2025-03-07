package com.usermanagement.assignment.entity;/*
* @created 05/03/2025 - 17:08
* @project assignment
& @author Swati Madaan
*Class for storing user address
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USER_ADDRESS")
@Data
public class UserAddress {
    /**
     * addressId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    /**
     * userId associated with user address
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userAddressRelationId")
    private UserInformation userInformation;
    /**
     * addressType
     */
    private String addressType;
    /**
     * addressLineOne
     */
    private String addressLineOne;
    /**
     * addressLineTwo
     */
    private String addressLineTwo;
    /**
     * city
     */
    private String city;
    /**
     * county
     */
    private String county;
    /**
     * postcode
     */
    private String postcode;
}
