package com.ssafy.b208.api.service;

import com.ssafy.b208.api.contract.SsafyNFT;
import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.utils.NetworkConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

@Service
public class NftService {
    @Autowired
    private Environment env;
    private String adminAddress;
    private String nftContractAddress;
    private String moneyTokenAddress;
    private String voucherTokenAddress;
    private String purchaseVoucherAddress;

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
        }else{
        }
    }

  /*  public void check(UserDto userDto) throws Exception{
        NetworkConnector nc =new NetworkConnector(userDto.getPrivateKey());
        SsafyNFT nft = SsafyNFT.load(
                "0x73535084843e37a59B56c9fa1E12E13dbd5681c4",
                nc.getWeb3j(),
                nc.getManager(),
                nc.getGasProvider()
        );
        String answer2=nft.tokenURI(new BigInteger("2")).send();
        String answer=nft.balanceOf("0x79A96922007a3bC5eC83c922148C0826D05995e6").send().toString();
        System.out.println("뭐징"+answer+" 2"+answer2);
    }*/
}
