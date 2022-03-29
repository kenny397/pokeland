package com.ssafy.b208.api.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
public class UserPokemon extends BaseEntity{
    private Long tokenId;
    private String ipfsMetaUri;
    private String ipfsImageUri;
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private PokeDex pokemon;

}
