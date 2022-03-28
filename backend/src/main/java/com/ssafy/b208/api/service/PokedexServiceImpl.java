package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.entity.PokeDex;
import com.ssafy.b208.api.db.entity.User;
import com.ssafy.b208.api.db.repository.PokeDexRepository;
import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import com.ssafy.b208.api.db.repository.UserRepository;
import com.ssafy.b208.api.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PokedexServiceImpl implements PokedexService {
    @Autowired
    private UserPokemonRepository userPokemonRepository;
    @Autowired
    private PokeDexRepository pokeDexRepository;
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public List<Object[]> getPokemonList(UserRequestDto userRequestDto) {
//        String email = userRequestDto.getEmail();
//        Optional<User> optional = userRepository.findUserByEmail(email);
//        User user;
//        if (optional.isPresent()) {
//            user = optional.get();
//        } else {
//            user = new User();
//        }
//        List<Object[]> pokemonList = userPokemonRepository.findPokemonList(user);
//        System.out.println(pokemonList);
//        return pokemonList;
//    }
    @Override
    public List<Long> getPokemonList(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
//        Optional<User> optional = userRepository.findUserByEmail(email);
//        User user;
//        if (optional.isPresent()) {
//            user = optional.get();
//        } else {
//            user = new User();
//        }
        User user = Optional.ofNullable(userRepository.findUserByEmail(email).get())
                .orElseGet(() -> new User());
        List<Long> pokemonList = Optional.ofNullable(userPokemonRepository.findPokemonList2(user.getId()).get())
                .orElseGet(() -> new ArrayList<>());
        System.out.println(pokemonList);
        return pokemonList;
    }

    @Override
    public PokeDex getPokeInfo(Long id) {
        PokeDex pokeDex = Optional.ofNullable(pokeDexRepository.findPokeDexById(id).get())
                .orElseGet(() -> new PokeDex());
        return pokeDex;
    }

}
