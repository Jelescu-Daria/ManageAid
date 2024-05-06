package com.example.manageaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "modules",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EModule name;

    @Size(max = 100)
    private String displayName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "module_roles",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Module() {

    }

    public Module(EModule name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EModule getName() {
        return name;
    }

    public void setName(EModule name) {
        this.name = name;
    }

    public @Size(max = 100) String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@Size(max = 100) String displayName) {
        this.displayName = displayName;
    }
}