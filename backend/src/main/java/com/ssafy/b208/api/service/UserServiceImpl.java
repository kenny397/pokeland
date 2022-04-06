package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.UserDto;
import com.ssafy.b208.api.dto.WalletDto;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import com.ssafy.b208.api.exception.ExistIdException;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
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
            throws MessagingException, UnsupportedEncodingException, ExistIdException {

        if (userRepository.findOptionalByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new ExistIdException(registerRequestDto.getEmail());
        } else {
            WalletDto walletDto = createWallet();
            String randomCode = RandomString.make(64);
            User user = User.builder()
                    .email(registerRequestDto.getEmail())
                    .nickname(registerRequestDto.getNickname())
                    .money(500L)
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .publicKey(walletDto.getPublicKey())
                    .privateKey(walletDto.getPrivateKey())
                    .verificationCode(randomCode)
                    .enabled(false)
                    .build();
            userRepository.save(user);
            sendVerificationEmail(user, siteURL);
        }
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String siteAddress = "gmail.com";
        String fromAddress = env.getProperty("spring.mail.username") + "@" + siteAddress;
        String senderName = "PokeLand";
        String subject = "회원가입 인증 안내 메일입니다.";
        String content = "<img src=\"https://ipfs.io/ipfs/QmQeC9C3ze4rmmG2wxdCYhoEPnGvoxp85whoQAHEvZd7Sf\" " +
                "style=\"margin: 0 auto; display: block;\" width=\"350\"/>" +
                "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400; text-align: center;\">" +
                "		<span style=\"color: #6A60A9; text-align: center;\">메일인증</span> 안내입니다." +
                "	</h1>\n"
                + "<h2 style=\"margin: 0; padding: 0 5px; font-size: 21px; font-weight: 400; text-align: center;\">"
                + "[[name]]님,<br>"
                + "아래 '인증완료' 버튼을 눌러 회원가입 인증을 진행해주세요.</h2> <br>"
                + "<h3 style=\"margin: 0; padding: 0 5px; font-size: 21px; font-weight: 400; text-align: center;\"><a href=\"[[URL]]\" target=\"_self\">인증완료</a></h3> <br>"
                + "<h3 style=\"margin: 0; padding: 0 5px; font-size: 21px; font-weight: 400; text-align: center;\">감사합니다.</h3>";

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
    @Transactional
    public UserDto giveBonus(String email) {
        Optional<User> userOptional = userRepository.findOptionalByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        user.setMoney(user.getMoney()+300);
        userRepository.save(user);
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .publicKey(user.getPublicKey())
                .Money(user.getMoney())
                .build();
        return userDto;
    }

    @Override
    public UserDto getUserByUserEmail(String email) {
        Optional<User> userOptional = userRepository.findOptionalByEmail(email);

        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .publicKey(user.getPublicKey())
                .privateKey(user.getPrivateKey())
                .Money(user.getMoney())
                .enabled(user.isEnabled())
                .build();

        return userDto;
    }

    @Override
    public UserDto getUserByUserNickname(String nickname) {
        Optional<User> userOptional = userRepository.findOptionalByNickname(nickname);
        if (userOptional.isEmpty()) {
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
