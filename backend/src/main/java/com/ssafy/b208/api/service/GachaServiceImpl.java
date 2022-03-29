package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.entity.UserPokemon;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.response.GachaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GachaServiceImpl implements GachaService {
    private final UserPokemonRepository userPokemonRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public GachaResponseDto gacha(String email) {
        User user=userRepository.findUserByEmail(email).get();
        GachaResponseDto gachaResponseDto=new GachaResponseDto();
        if(user.getMoney()>=100){
            user.setMoney(user.getMoney()-100L);
            List<UserPokemon> userPokemonList=userPokemonRepository.findUserPokemonByUserIsNull();
            int randomNumber=(int)(Math.random()*userPokemonList.size());

            UserPokemon userPokemon=userPokemonList.get(randomNumber);
            System.out.println(randomNumber);
            userPokemon.setUser(user);
            gachaResponseDto.setGrade(userPokemon.getGrade());
            gachaResponseDto.setIpfsImageUri(userPokemon.getIpfsImageUri());
            gachaResponseDto.setIpfsMetaUri(userPokemon.getIpfsMetaUri());
            gachaResponseDto.setPokeDexId(""+userPokemon.getPokemon().getId());
            userPokemonRepository.save(userPokemon);

        }

        return gachaResponseDto;
    }
}
