package com.uniamerica.unijobsbackend.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "usuarios")
@NamedEntityGraph(name = "graph.user",
        attributeNodes = @NamedAttributeNode(value = "roles", subgraph = "subgraph.roles"),
        subgraphs = @NamedSubgraph(name = "subgraph.roles",
                attributeNodes = @NamedAttributeNode("privileges"))
)
public class Usuario implements Serializable {

    public Usuario(Usuario usuario) {
        this(usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getCelular(),
                usuario.getRa(),
                usuario.getTipoUsuario(),
                usuario.getRoles());
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private String senha;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    private String celular;

    @NotNull
    private String ra;

    @Transient
    private TipoUsuario tipoUsuario;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles;
}
