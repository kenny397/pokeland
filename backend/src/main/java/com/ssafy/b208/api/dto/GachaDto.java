package com.ssafy.b208.api.dto;

import com.ssafy.b208.api.db.entity.PokeDex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GachaDto {
    String tokenId;
    PokeDex pokeDex;
    String ipfsMetaUri;
    String ipfsImageUri;
    String grade;
}
