package com.bobo.tontinette.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

/**
 * @author Mamadou Bobo on 01/11/2023
 * @project Tontine
 */

@Entity(name = "role_tbl")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = {
                    @JoinColumn(name = "role_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "privilege_id")
            }
    )
    private Collection<Privilege> privileges;
}
