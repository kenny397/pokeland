package com.ssafy.b208.api.service;

import com.ssafy.b208.api.contract.SsafyNFT;
import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.utils.NetworkConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class NftService {
    @Autowired
    private Environment env;
    private String adminAddress;
    private String nftContractAddress;


    @PostConstruct
    public void setupTokenContract() throws InterruptedException {
        adminAddress = env.getProperty("admin.address");
        nftContractAddress= env.getProperty("ca.nftContract");
    }
    public void minting(User user, String tokenUri) throws Exception{
        NetworkConnector nc =new NetworkConnector(user.getPrivateKey());
        SsafyNFT nft = SsafyNFT.load(
                nftContractAddress,
                nc.getWeb3j(),
                nc.getManager(),
                nc.getGasProvider()
        );
        TransactionReceipt receipt = nft.create(
                user.getPublicKey(),
                tokenUri
        ).send();
        if(receipt.isStatusOK()){
            log.info(receipt.toString());
        }else{
        }
    }
}
