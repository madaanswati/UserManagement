package com.usermanagement.assignment.utility;/*
* @created 04/03/2025 - 17:03
* @project assignment
& @author Swati Madaan
*Custom exception created for User management application
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserManagementCRUDException extends Exception{
    private static final long serialVersionUID = 1L;

    public UserManagementCRUDException(String msg) {
        super(msg);
    }
}
