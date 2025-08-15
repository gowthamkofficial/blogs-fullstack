package com.offcl.blogs_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserRequestDto {

    @NotBlank(message = "First name is required!")
    @Size(min = 3, max = 30, message = "First name should have at least 3 to 30 characters!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    @Size(min = 1, max = 30, message = "Last name should have at least 1 to 30 characters!")
    private String lastName;

    @NotBlank(message = "Mobile number is required!")
    @Pattern(regexp = "^[0-9]{10,12}$", message = "Mobile number must be 10 to 12 digits")
    private String mobileNumber;

    @Email(message = "Provide a valid email address")
    @NotBlank(message = "Email address is required")
    @Size(max = 50, message = "Email address should not exceed 50 characters")
    private String emailAddress;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address should not exceed 255 characters")
    private String address;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;
}
