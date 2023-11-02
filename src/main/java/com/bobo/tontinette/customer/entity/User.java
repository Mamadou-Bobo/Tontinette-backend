package com.bobo.tontinette.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author Mamadou Bobo on 31/10/2023
 * @project Tontine
 */

@Entity(name = "user_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Column(name = "user_email")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private LocalDateTime createdAt;

    private boolean isAccountEnabled;

    private boolean isCredentialsExpired;

    private boolean isAccountExpired;

    private boolean isAccountLocked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Collection<Role> roles;

    public User(String firstName,
                String lastName,
                String password,
                String phoneNumber,
                String email,
                Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
        this.createdAt = LocalDateTime.now();
    }
}
