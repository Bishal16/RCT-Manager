package dev.Mahathir.JwtSecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;
    @Column(nullable = true)
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
    )
    private Set<Permission> permissions;

}
