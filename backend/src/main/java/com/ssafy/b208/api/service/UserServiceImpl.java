package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.WalletDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final Environment env;

    @Transactional
    @Override
    public void register(UserRequestDto registerRequestDto, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        //회원가입이 된경우

        User user = new User();
        user.setNickname(registerRequestDto.getNickname());
        user.setEmail(registerRequestDto.getEmail());
        user.setMoney(500L);
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        if (userRepository.findUserByEmail(registerRequestDto.getEmail()).isPresent()) {
            return;
        } else {
            WalletDto walletDto = createWallet();
            user.setPublicKey(walletDto.getPublicKey());
            user.setPrivateKey(walletDto.getPrivateKey());

            String randomCode = RandomString.make(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            userRepository.save(user);
            sendVerificationEmail(user, siteURL);
        }

        //회원가입이 안된경우
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String siteAddress = "gmail.com";
        String fromAddress = env.getProperty("spring.mail.username") + "@" + siteAddress;
        String senderName = "PokeLand";
        String subject = "Please verify your registration";
        String content = "[[name]]님,<br>"
                + "아래 인증 버튼을 눌러 회원가입 인증을 진행해주세요 :<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">인증완료</a></h3>"
                + "감사합니다,<br>"
                + senderName;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getNickname());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    @Transactional
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        System.out.println(user.getEmail());
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }

    }

    @Override
    public UserDto getUserByUserEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPublicKey(user.getPublicKey());
        userDto.setMoney(user.getMoney());
        userDto.setPrivateKey(user.getPrivateKey());
        userDto.setEnabled(user.isEnabled());
        //빌더 사용해보기
        return userDto;
    }

    @Override
    public UserDto getUserByUserNickname(String nickname) {
        Optional<User> userOptional = userRepository.findUserByNickname(nickname);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPublicKey(user.getPublicKey());
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
