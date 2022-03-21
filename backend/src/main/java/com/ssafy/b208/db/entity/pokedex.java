package com.ssafy.b208.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class pokedex extends baseEntity {
    private String name;
    private String type;
    private int height;
    private String category;
    private String gender;
    private int weight;
    private String abilities;
}
