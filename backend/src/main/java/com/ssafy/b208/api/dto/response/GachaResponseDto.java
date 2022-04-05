package com.ssafy.b208.api.dto.response;

import com.ssafy.b208.api.db.entity.PokeDex;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GachaResponseDto {
    String tokenId;
    String pokeDexId;
    String ipfsMetaUri;
    String ipfsImageUri;
    String grade;
}
