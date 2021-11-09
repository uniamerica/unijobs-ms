package com.uniamerica.unijobsbackend.models;

import com.uniamerica.unijobsbackend.models.enums.RoleName;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> users;

    @ManyToMany
    @JoinTable(
            name = "roles_provileges",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_privilege")

    )
    private Collection<Privilege> privileges;


}
