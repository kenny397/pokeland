package com.ssafy.b208.api.service;

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
class InitServiceTest {
    @Autowired
    InitService initService;

    @Test
    public void Test() throws Exception{
        initService.minting("ipfs://QmY72pGHXwT3y6cDjAvkqVKZKggdCA8Nmsx8jT2wr9FtDs");
        initService.test("0xDF547496C8880cC654155F594Dda189165C3AF15");

    }
}