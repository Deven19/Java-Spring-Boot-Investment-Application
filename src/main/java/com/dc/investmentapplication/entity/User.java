package com.dc.investmentapplication.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.dc.investmentapplication.validation.ValidPassword;
import com.dc.investmentapplication.validation.UniqueEmail;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Password is required")
    //@ValidPassword(message = "Password must be at least 8 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character")
    private String password;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    //@UniqueEmail()
    private String email;

    @Column(unique = true)
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @Column(nullable = false, updatable = false)
    private String createdAt;

    private String updatedAt;

}

