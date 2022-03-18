package com.ssafy.b208.api.db.entity;

import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {

    private String account;
    private LocalDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPokemon> pokemonList = new ArrayList<>();

    @PrePersist
    public void createdAt(){
        this.createdDate = LocalDateTime.now();
    }
}
