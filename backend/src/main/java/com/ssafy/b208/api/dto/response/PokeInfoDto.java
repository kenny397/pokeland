package com.ssafy.b208.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PokeInfoDto {
    long id;
    String name;
    String type;
    String height;
    String category;
    String gender;
    String weight;
    String abilities;
}
