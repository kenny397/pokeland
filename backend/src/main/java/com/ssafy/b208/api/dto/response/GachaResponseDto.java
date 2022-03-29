package com.ssafy.b208.api.dto.response;

import com.ssafy.b208.api.db.entity.PokeDex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GachaResponseDto {
    String tokenId;
    String pokeDexId;
    String ipfsMetaUri;
    String ipfsImageUri;
    String grade;
}
