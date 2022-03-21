package com.ssafy.b208.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class userPokemon extends baseEntity {
    private String token_id;

    @ManyToOne
    private users account_id;

    @ManyToOne
    private pokedex pokedex_id;

    private String ipfs_uri;

    private String background_color;
}
