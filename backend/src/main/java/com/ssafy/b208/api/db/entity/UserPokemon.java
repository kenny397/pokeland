package com.ssafy.b208.api.db.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "userPokemon")  // 테이블 이름 카멜 케이스?
public class UserPokemon extends BaseEntity{
    private Long tokenId;
    private String ipfsUri;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private PokeDex pokemon;


}
