package com.ssafy.b208.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NfpListDto {
    List<NfpDetailDto> nfpList;
}
