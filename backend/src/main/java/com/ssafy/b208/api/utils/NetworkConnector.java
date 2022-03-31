package com.ssafy.b208.api.utils;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;

import java.math.BigInteger;

public class NetworkConnector {

    private Web3j web3j;
    private Credentials credential;
    private ContractGasProvider gasProvider;
    private FastRawTransactionManager manager;

    private final long  CHAINID                 = 31221;
    private final long  TX_END_CHECK_DURATION   = 3000;
    private final int   TX_END_CHECK_RETRY      = 3;

    public NetworkConnector(String privateKey) {
        this.web3j = Web3j.build(new HttpService("http://20.196.209.2:8545"));
        this.credential = Credentials.create(privateKey);
        this.gasProvider = new StaticGasProvider(new BigInteger("0"),new BigInteger("210000"));
        this.manager = new FastRawTransactionManager(web3j, credential, CHAINID, new PollingTransactionReceiptProcessor(web3j, TX_END_CHECK_DURATION, TX_END_CHECK_RETRY));
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public Credentials getCredentials() {
        return credential;
    }

    public FastRawTransactionManager getManager() {
        return manager;
    }

    public ContractGasProvider getGasProvider() {
        return gasProvider;
    }

    public void setCredentials(String privateKey) {
        this.credential = Credentials.create(privateKey);
    }

}
