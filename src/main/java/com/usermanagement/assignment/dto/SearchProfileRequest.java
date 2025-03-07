package com.usermanagement.assignment.dto;/*
* @created 06/03/2025 - 14:07
* @project assignment
& @author Swati Madaan
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchProfileRequest {
    /**
     * userId of the profile to be searched
     */
    private Long userId;
    /**
     * emailAddress to be searched
     */
    private String emailAddress;
}
