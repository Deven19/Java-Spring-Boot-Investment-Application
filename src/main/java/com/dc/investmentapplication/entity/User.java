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

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    //@UniqueEmail()
    private String email;

    @NotEmpty(message = "Password is required")
    //@ValidPassword(message = "Password must be at least 8 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character")
    private String password;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Roles role;

    public User(Long id, String name, String email, String password, String phoneNumber, Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}

