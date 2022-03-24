package com.ssafy.b208.api.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;

import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Ethereum;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Strings;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;


@Slf4j
@Service
public class InitService {

    private String ssafyContract="0x66A1227347C7A62d35d9302E1cc937f02fA9c350";//0x66A1227347C7A62d35d9302E1cc937f02fA9c350 //0xD6d4765F0E2D0c326C2D9b7E54641d4CB242Efbe
    private String localContract="";
    private String account1="0xe2c479177Ee6bBbD35046f508B89534931DB1E75";
    private String account1pwd= "";
    private String account2="0x7584AD33C517EfFf0Fa10663a49154cB31721C3a";
    private String account2pwd="0x8d22a0aa9c43da157ebc24bc7d70c26d198381e042ab93434757752e3f0ee8e5";
    private String from = "0x7584AD33C517EfFf0Fa10663a49154cB31721C3a";
    private String contract = ssafyContract;//0xBF5C8587bF6936412c69e9346B7014d367621Ef6
    private String pwd = "0x4d44a3ae3ab73187d1c0cd504c40fb63ce6d24cf08e4a0beca6be54d7430ff4e";
    long CHAIN_ID = 31221;
    private Web3j web3j2 = Web3j.build(new HttpService("http://20.196.209.2:8545")); // default server : http://20.196.209.2:8545

    private Admin web3j = Admin.build(new HttpService("http://20.196.209.2:8545")); // default server : http://20.196.209.2:8545
    private Credentials credential = Credentials.create(pwd);





    public void minting()throws Exception{
        System.out.println(web3j.ethAccounts().sendAsync().get().getAccounts());
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(account1,account1pwd).sendAsync().get();
        if (personalUnlockAccount.accountUnlocked()) {
            List<Type> params = new ArrayList<Type>();
            params.add(new Address(account2));
            params.add(new Utf8String("asd"));
            List<TypeReference<?>> returnTypes = Collections.<TypeReference<?>>emptyList();
            Function function = new Function("transfer",
                    params, returnTypes);

            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(account1, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            System.out.println(nonce);
            Transaction transaction = Transaction.createFunctionCallTransaction(account1, nonce,
                    Transaction.DEFAULT_GAS,
                    null, contract,
                    FunctionEncoder.encode(function));

            EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).sendAsync().get();
            log.info(transaction.getFrom());
            log.info(transaction.getChainId());
            log.info(transaction.getTo());
            log.info(transaction.getValue());
            log.info(transaction.getData());
            Transaction transaction1 = new Transaction(account1, nonce, Transaction.DEFAULT_GAS, null, contract, null, FunctionEncoder.encode(function), 31221L, null, null);
            EthSendTransaction ethSendTransaction1 = web3j.ethSendTransaction(transaction).sendAsync().get();

            log.info(transaction1.getFrom());
            log.info(transaction1.getChainId());
            log.info(transaction1.getTo());
            log.info(transaction1.getValue());

            String transactionHash = ethSendTransaction.getTransactionHash();
            String transactionHash1 = ethSendTransaction1.getTransactionHash();

            System.out.println(transactionHash1);
            System.out.println(transactionHash);
        }
    }

    public void minting2()throws Exception{


            List<Type> params = new ArrayList<Type>();
            params.add(new Address(account1));
            params.add(new Utf8String("asd"));
            List<TypeReference<?>> returnTypes = Collections.<TypeReference<?>>emptyList();
            Function function = new Function("create",
                    params, returnTypes);
            System.out.println(web3j2.web3ClientVersion().send().getWeb3ClientVersion());
            System.out.println(web3j2.ethAccounts().sendAsync().get().getAccounts());
            System.out.println(web3j2.ethGetBalance(account1, DefaultBlockParameter.valueOf("latest")).sendAsync().get().getBalance());

        String txData = FunctionEncoder.encode(function);

        TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(web3j2, 5000, 3);
        TransactionManager manager = new RawTransactionManager(web3j2, credential, CHAIN_ID, receiptProcessor);
        ContractGasProvider gasProvider = new DefaultGasProvider();

        String txHash = manager.sendTransaction(
                new BigInteger("0"),
                new BigInteger("0"),
                contract,
                txData,
                BigInteger.ZERO
        ).getTransactionHash();
        System.out.println(txHash);
        TransactionReceipt receipt = receiptProcessor.waitForTransactionReceipt(txHash);
        System.out.println(receipt.getStatus());


    }


    public void createWallet(){
        String seed = UUID.randomUUID().toString();
        System.out.println(seed);
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();

            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

            String privKey = privateKeyInDec.toString(16);

            WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);

            String address = "0x" + aWallet.getAddress();

            System.out.println("address: " + address);
            System.out.println("private key: " + privKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }












    public ResponseEntity<String> upLoadIpfs() {
        File imageFile = new File("C:/Users/multicampus/Documents/erd.PNG");
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(imageFile));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String key="42b11f87d5278e127356";
        String secretKey="c449127f65594fdbb9491fb2d9554d3c084c7459464aa15cde64aaec240a77f3";

        headers.set("pinata_api_key",key);
        headers.set("pinata_secret_api_key",secretKey);
        HttpEntity<MultiValueMap> entity =new HttpEntity<>(bodyMap,headers);
        log.info("header= {}",headers.get("pinata_api_key"));
        log.info("htto= {}",entity);
        String url= "https://api.pinata.cloud/pinning/pinFileToIPFS";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String>response = restTemplate.postForEntity(url,entity,String.class);
        log.info("response= {}",response);
        return response;
    }
}
