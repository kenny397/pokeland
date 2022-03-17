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
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Strings;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Slf4j
@Service
public class InitService {

    private String from = "0xDF547496C8880cC654155F594Dda189165C3AF15";
    private String contract = "0xCfCdf694956D7b121D6d0f1D1B5bb29278271b19";//0xBF5C8587bF6936412c69e9346B7014d367621Ef6
    private String pwd = "0xfb353a7ff8ad8fcd9f0cd1a8f3726747fe2dfd61a2a8bc5b23186467e7a0dee3";

    private Admin web3j = null;

    public InitService()
    {
        web3j = Admin.build(new HttpService("http://localhost:8545")); // default server : http://localhost:8545
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

    public void minting(String ipfsHash)throws IOException, ExecutionException, InterruptedException{
        Function function = new Function("create",
                Arrays.asList(new Utf8String(ipfsHash)),
                Arrays.asList(new TypeReference<Uint256>() {}));
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,pwd).send();
        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    from, DefaultBlockParameterName.LATEST).sendAsync().get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
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
           // System.out.println("log"+receipt.getLogs().get(0).getTopics().get(0));

        }
        else
        {

        }
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
    public void test(String address) throws Exception{
        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("balanceOf",
                Arrays.asList(new Address(address)),
                Arrays.asList(new TypeReference<Uint256>() {}));
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

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
    }


}
