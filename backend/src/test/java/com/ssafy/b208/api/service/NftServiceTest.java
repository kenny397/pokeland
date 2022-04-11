package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NftServiceTest {
    @Autowired
    NftService nftService;
    @Test
    public void 테스트fsd() throws Exception {
        //given
        //UserDto userDto = new UserDto();
        //userDto.setPrivateKey("0x4d44a3ae3ab73187d1c0cd504c40fb63ce6d24cf08e4a0beca6be54d7430ff4e");
        //nftService.minting(userDto);
        //nftService.check(userDto);

    }
}