package com.ssafy.b208.api.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Slf4j
@Service
public class InitService {

    private String from = "0x79A96922007a3bC5eC83c922148C0826D05995e6";
    private String contract = "0x6C927304104cdaa5a8b3691E0ADE8a3ded41a333";//0xBF5C8587bF6936412c69e9346B7014d367621Ef6
    private String pwd = "4d44a3ae3ab73187d1c0cd504c40fb63ce6d24cf08e4a0beca6be54d7430ff4e";
    long CHAIN_ID = 31221;
    private Web3j web3j = Web3j.build(new HttpService("http://20.196.209.2:8545")); // default server : http://localhost:8545
    private Credentials credential = Credentials.create(pwd);


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

    public void minting()throws Exception{
        List<Type> params = new ArrayList<Type>();
        params.add(new Address("0x79A96922007a3bC5eC83c922148C0826D05995e6"));
        params.add(new Uint256(new BigInteger("15")));
        List<TypeReference<?>> returnTypes = Collections.<TypeReference<?>>emptyList();
        Function function = new Function("transfer",
            params,returnTypes);
        System.out.println(web3j.web3ClientVersion().send().getWeb3ClientVersion());
       // PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,pwd).send();
      //  if (personalUnlockAccount.accountUnlocked()) { // unlock 일때
        String txData=FunctionEncoder.encode(function);
        TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(web3j, 5000L, 3);
        TransactionManager manager = new RawTransactionManager(web3j, credential);
        ContractGasProvider gasProvider = new DefaultGasProvider();

        String txHash = manager.sendTransaction(
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                contract,
                txData,
                BigInteger.ZERO
        ).getTransactionHash();
        System.out.println(txData);
        System.out.println(txHash);
        TransactionReceipt receipt = receiptProcessor.waitForTransactionReceipt(txHash);
        System.out.println(receipt.getStatus());

       /* BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            System.out.println("nonce "+ FunctionEncoder.encode(function));
            //3. Transaction값 제작
            Transaction transaction = Transaction.createFunctionCallTransaction(from, nonce,
                    Transaction.DEFAULT_GAS,
                    null, contract,
                    FunctionEncoder.encode(function));
            System.out.println(transaction.getData());

            // 4. ethereum Call &
            EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(transaction).send();

            // transaction에 대한 transaction Hash값 얻기.
            String transactionHash = ethSendTransaction.getTransactionHash();
            Thread.sleep(5000);
            System.out.println("tra = " + transactionHash);
            TransactionReceipt receipt = getReceipt(transactionHash);
            System.out.println("receipt = " + receipt);
           // System.out.println("log"+receipt.getLogs().get(0).getTopics().get(0));*/

       // }
     //  else
     //  {

     //  }
    }

    public TransactionReceipt getReceipt(String transactionHash) throws IOException {

        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();

        if(transactionReceipt.getTransactionReceipt().isPresent())
        {
            System.out.println("transactionReceipt.getResult().getContractAddress() = " +
                    transactionReceipt.getResult());
        }
        else
        {
            System.out.println("transaction complete not yet");
        }

        return transactionReceipt.getResult();
    }
    /*public void test(String address) throws Exception{
        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("balanceOf",
                Arrays.asList(new Address(address)),
                Arrays.asList(new TypeReference<Uint256>() {}));
        //PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            //2. transaction 제작
            Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                    FunctionEncoder.encode(function));

            //3. ethereum 호출후 결과 가져오기
            EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            BigInteger bal=web3j.ethGetBalance(address,DefaultBlockParameterName.LATEST).send().getBalance();

            //4. 결과값 decode
            List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                    function.getOutputParameters());

            System.out.println("ethCall.getResult1() = " + decode.get(0).getTypeAsString());
            System.out.println("ethCall.getResult2() = " + ethCall.getResult());
            System.out.println("ethCall.getResult3() = " + ethCall.getRawResponse());
            System.out.println("getValue = " + decode.get(0).getValue());
            System.out.println("ebal = " +bal);
            System.out.println("getType = " + decode.get(0).getTypeAsString());

        }
        else
        {

        }
    }*/


}
