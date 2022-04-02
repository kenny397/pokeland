package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.WalletDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.exception.ExistIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;


import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(UserRequestDto registerRequestDto) {
        //회원가입이 된경우
        Optional<User> optionalUser=userRepository.findUserByEmail(registerRequestDto.getEmail());
        System.out.println(optionalUser.isPresent());
        if(optionalUser.get()==null){
            throw new ExistIdException(registerRequestDto.getEmail());
        }else{
            WalletDto walletDto = createWallet();
            User user = User.builder().nickname(registerRequestDto.getNickname())
                    .email(registerRequestDto.getEmail())
                    .money(500L)
                    .mail("x")
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .publicKey(walletDto.getPublicKey())
                    .privateKey(walletDto.getPrivateKey())
                    .build();
            userRepository.save(user);
        }



        //회원가입이 안된경우
    }

    @Override
    public UserDto getUserByUserEmail(String email) {
        Optional<User> userOptional=userRepository.findUserByEmail(email);
        if(!userOptional.isPresent()){
            return null;
        }
        User user = userOptional.get();
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .publicKey(user.getPublicKey())
                .privateKey(user.getPrivateKey())
                .money(user.getMoney())
                .build();

        return userDto;
    }
    @Override
    public UserDto getUserByUserNickname(String nickname) {
        Optional<User> userOptional=userRepository.findUserByNickname(nickname);
        if(!userOptional.isPresent()){
            return null;
        }

        User user = userOptional.get();
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .publicKey(user.getPublicKey())
                .build();
        return userDto;
    }



    public WalletDto createWallet() {
        String seed = UUID.randomUUID().toString();
        WalletDto walletDto = new WalletDto();
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
            String privKey = "0x" + privateKeyInDec.toString(16);
            WalletFile aWallet = Wallet.createLight(seed, ecKeyPair);
            String address = "0x" + aWallet.getAddress();


            walletDto.setPublicKey(address);
            walletDto.setPrivateKey(privKey);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return walletDto;
    }




}
