package com.ssafy.b208.api.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    private String email;
    private String nickname;
    private String password;
    private String publicKey;
    private String privateKey;
    private Long money;
    private String mail;  // 메일 인증 여부?
    private LocalDateTime createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPokemon> userPokemonList = new ArrayList<>();

    @PrePersist
    public void createdAt(){
        this.createdDate = LocalDateTime.now();
    }
}
