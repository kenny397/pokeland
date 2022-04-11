package com.ssafy.b208.api.db.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends BaseEntity {

    private String email;
    private String nickname;
    private String password;
    private String publicKey;
    private String privateKey;
    private Long money;
    private boolean enabled;  // 메일 인증 여부?
    private LocalDateTime createdDate;
    @Column(length = 64)
    private String verificationCode;


    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPokemon> userPokemonList = new ArrayList<>();

    @PrePersist
    public void createdAt(){
        this.createdDate = LocalDateTime.now();
    }
}
