package com.ssafy.b208.api.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {

    private String account;
    private String password;
    @Column(name = "publicKey")  // DB 상에서 컬럼 이름 camel case로 할건지?
    private String publicKey;
    @Column(name = "privateKey")
    private String privateKey;
    private Double money;  // 정수? 실수?
    private String mail;  // 메일 인증 여부?
    private LocalDateTime createdTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPokemon> userPokemonList = new ArrayList<>();

    @PrePersist
    public void createdAt(){
        this.createdTime = LocalDateTime.now();
    }
}
