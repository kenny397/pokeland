package com.ssafy.b208.api.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PokeDex {
    @Id
    private long id;
    private String name;
    private String type;
    private String height;
    private String category;
    private String gender;
    private String weight;
    private String abilities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pokemon")
    private List<UserPokemon> userPokemonList = new ArrayList<>();
}
