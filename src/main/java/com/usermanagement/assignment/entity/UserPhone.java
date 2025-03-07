package com.usermanagement.assignment.entity;/*
* @created 05/03/2025 - 18:36
* @project assignment
& @author Swati Madaan
Class for storing User Phone
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USER_PHONE")
@Data
public class UserPhone {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * user Id associated with the phone Number
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userPhoneRelationId")
    private UserInformation userInformation;
    /**
     * phoneType
     */
    @Column
    private String phoneType;
    /**
     * phoneNumber
     */
    @Column
    private String phoneNumber;
}
