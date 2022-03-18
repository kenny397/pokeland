package com.ssafy.b208.api.db.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class UserPokemon extends BaseEntity{
    private Long tokenId;
    private String ipfsUri;
    private String backgroundColor;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private PokeDex pokemon;


}
