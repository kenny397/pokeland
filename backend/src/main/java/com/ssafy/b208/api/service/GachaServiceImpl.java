package com.ssafy.b208.api.service;

import com.ssafy.b208.api.dto.request.WalletRequestDto;
import com.ssafy.b208.api.exception.AddressNotFoundException;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

@Service
public class GachaServiceImpl implements GachaService {
    private String contract="0xe2798e7585BD0c48e82d9b1569bBa5aE092345a3";
    private String pwd="test";
    private Admin web3j=null;

    public GachaServiceImpl(){
        web3j = Admin.build(new HttpService("http://localhost:8545"));
    }

    @Override
    public void getNft(WalletRequestDto wallet) throws Exception {
        String from = wallet.getWalletId();
        Function function = new Function("create",
                Arrays.asList(new Address(from)),
                Arrays.asList(new TypeReference<Uint256>() {}));
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,pwd).send();
        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                    from, DefaultBlockParameterName.LATEST).sendAsync().get();

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            //3. Transaction값 제작
            Transaction transaction = Transaction.createFunctionCallTransaction(from, nonce,
                    Transaction.DEFAULT_GAS,
                    null, contract,
                    FunctionEncoder.encode(function));

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




}
