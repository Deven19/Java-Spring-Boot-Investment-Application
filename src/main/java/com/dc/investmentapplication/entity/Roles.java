package com.dc.investmentapplication.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

 /*       @OneToOne(mappedBy = "role")
        @JsonBackReference
        private User user;*/

        @NonNull
        private String roleName;
        private String content;
        private LocalDateTime date;

}
