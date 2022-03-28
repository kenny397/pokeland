package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.UserPokemonDto;
import com.ssafy.b208.api.dto.WalletDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void register(UserRequestDto registerRequestDto) {
        //회원가입이 된경우

        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setMoney(500L);
        user.setMail("x");
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        WalletDto walletDto = createWallet();
        user.setPublicKey(walletDto.getPublicKey());
        user.setPrivateKey(walletDto.getPrivateKey());
        Optional<User> users = Optional.ofNullable(userRepository.findUserByEmail(registerRequestDto.getEmail())
                .orElseGet(() -> userRepository.save(user)));

        //회원가입이 안된경우
    }

    @Override
    public UserDto getUserByUserEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        UserDto userDto = new UserDto();
        //빌더 사용해보기
        return userDto;
    }

//    @Override
//    public User getNft(String email) {
//        User user = userRepository.findUserByEmail(email).get();
//        Optional<UserPokemon> userPokemon = userPokemonRepository.findUserPokemonByUser(user);
//        UserPokemonDto userPokemonDto = new UserPokemonDto();
//        // 빌더
//    }


    public WalletDto createWallet() {
        String seed = UUID.randomUUID().toString();
        System.out.println(seed);
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
