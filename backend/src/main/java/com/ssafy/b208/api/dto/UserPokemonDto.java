package com.ssafy.b208.api.dto;

import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class UserPokemonDto {
    Long tokenId;
    String ipfsUri;
    PokeDex pokemon;

    @Builder
    public UserPokemonDto(Long tokenId, String ipfsUri, PokeDex pokemon) {
        this.tokenId = tokenId;
        this.ipfsUri = ipfsUri;
        this.pokemon = pokemon;
    }
}
