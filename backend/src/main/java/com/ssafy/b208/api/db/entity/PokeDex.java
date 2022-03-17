package com.ssafy.b208.api.db.entity;

import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PokeDex extends BaseEntity{
    private String name;
    private String type;
    private Double height;
    private String category;
    private String gender;
    private Double weight;
    private String abilities;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pokemon")
    private List<UserPokemon> userPokemonList = new ArrayList<>();
}
