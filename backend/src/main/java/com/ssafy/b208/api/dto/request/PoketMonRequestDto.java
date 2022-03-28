package com.ssafy.b208.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PoketMonRequestDto {
    String image;
    Long tokenId;
    String name;
    AttributesRequestDto[] attributes;
}
