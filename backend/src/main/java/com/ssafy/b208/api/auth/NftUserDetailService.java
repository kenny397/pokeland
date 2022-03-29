package com.ssafy.b208.api.auth;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NftUserDetailService implements UserDetailsService {

    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isPresent()) {
            NftUserDetail userDetails = new NftUserDetail(user.get());
            return userDetails;
        }
        return null;
    }
}
