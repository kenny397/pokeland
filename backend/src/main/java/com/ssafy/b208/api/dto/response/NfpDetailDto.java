package com.ssafy.b208.api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class NfpDetailDto {
    Long tokenId;
    Long pokedexId;
    String ipfsMetaUri;
    String ipfsImageUri;
    String grade;
}
