package com.usermanagement.assignment.repository;/*
* @created 05/03/2025 - 14:07
* @project assignment
& @author Swati Madaan
Repository class for User Management
*/

import com.usermanagement.assignment.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserInformation, Long> {
}
