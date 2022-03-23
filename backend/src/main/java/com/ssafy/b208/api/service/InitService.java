package com.ssafy.b208.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class InitService {

    // 로컬환경
//    String USER_ADDRESS = "0x2eDec1940401d493fb195f187a5218F38CbeC194";
//    String CONTRACT_ADDRESS = "0xb838Ed1DF2776D0c4f58C3edb19Bf39AE3891330";
//    String USER_PRIVATE_KEY = "0x1b6af4cf6708124364444d111118b41f0ca012942c8d7d06c50c24e29536ce09";

    // 싸피네트워크
    String USER_ADDRESS = "0x68014C18499FD5E3f64Ca968B577853f1Fe1495E";  // 내 메타마스크 지갑 주소
    String CONTRACT_ADDRESS = "0x046e4DAc0A20bc9977fd2373c793F812E2feEC64";  // 싸피네트워크, Ropsten에 배포한 스마트 컨트랙트 주소 (왜 같은지는 모르겠음)
    String USER_PRIVATE_KEY = "e45e4749bdb4e6a8deb1f752ba7b2072afca46f5e7b54bd2381904bf32d268cc";  // 내 메타마스크 지갑 개인키

    // Ropsten (infura)
    String infuraKey = "c6594cf9a10c429e9e0cb7a6add0c05f";

    String RECEIVER_ADDRESS = "0x68014C18499FD5E3f64Ca968B577853f1Fe1495E"; // 내 메타마스크 지갑 주소
    long TX_END_CHECK_DURATION = 5000;
    int TX_END_CHECK_RETRY = 3;

//    long CHAIN_ID = 31221;  // 싸피네트워크
    long CHAIN_ID = 3;  // Ropsten

    String amount = "10";

//    Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));  // 로컬
//    Web3j web3j = Web3j.build(new HttpService("http://20.196.209.2:8545"));  // 싸피네트워크
    Web3j web3j = Web3j.build(new HttpService("https://ropsten.infura.io/v3/" + infuraKey));  // Ropsten (infura)

    Credentials credential = Credentials.create(USER_PRIVATE_KEY);


    public void minting() throws Exception {

        // input parameters
        List<Type> params = new ArrayList<Type>();
        params.add(new Address(USER_ADDRESS));
        params.add(new Utf8String("12345667"));
//        params.add(new Uint256(new BigInteger("12345")));

        // output parameters
        List<TypeReference<?>> returnTypes = Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {});

        Function function = new Function(
                "create",
                params,
                returnTypes
        );

        String txData = FunctionEncoder.encode(function);

        TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(web3j, TX_END_CHECK_DURATION, TX_END_CHECK_RETRY);
        TransactionManager manager = new RawTransactionManager(web3j, credential);
//        TransactionManager manager = new RawTransactionManager(web3j, credential, CHAIN_ID, receiptProcessor);
        ContractGasProvider gasProvider = new DefaultGasProvider();

        String txHash = manager.sendTransaction(
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
//                gasProvider.getGasPrice("transfer"),
//                gasProvider.getGasLimit("transfer"),
                CONTRACT_ADDRESS,
                txData,
                BigInteger.ZERO
        ).getTransactionHash();

        System.out.println(txData);
        System.out.println();
        System.out.println(txHash);


        TransactionReceipt receipt = receiptProcessor.waitForTransactionReceipt(txHash);
        System.out.println(receipt.getStatus());



//        String txData = FunctionEncoder.encode(function);
//        org.web3j.protocol.core.methods.response.EthCall response = web3j.ethCall(
//                Transaction.createEthCallTransaction(USER_ADDRESS, CONTRACT_ADDRESS, txData),
//                DefaultBlockParameterName.LATEST).sendAsync().get();
//            )
//
//        List<Type> results = FunctionReturnDecoder.decode(
//                response.getValue(), function.getOutputParameters());
//
//        BigInteger balance = (BigInteger)results.get(0).getValue();
//            System.out.println(balance);

    }
}
