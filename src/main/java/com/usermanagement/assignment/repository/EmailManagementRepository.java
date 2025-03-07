package com.usermanagement.assignment.repository;/*
* @created 06/03/2025 - 14:07
* @project assignment
& @author Swati Madaan
*/

import com.usermanagement.assignment.entity.UserEmail;
import com.usermanagement.assignment.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailManagementRepository extends JpaRepository<UserEmail, Long> {
    List<UserEmail> findByEmailAddress(String emailAddress);
}
