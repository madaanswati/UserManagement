package com.usermanagement.assignment.entity;/*
* @created 05/03/2025 - 18:36
* @project assignment
& @author Swati Madaan
*class for storing user email
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "USER_EMAIL")
@Data
public class UserEmail {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * user Id associated with email address
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userEmailRelationId")
    private UserInformation userInformation;
    /**
     * emailAddress
     */
    @Column
    private String emailAddress;
}
