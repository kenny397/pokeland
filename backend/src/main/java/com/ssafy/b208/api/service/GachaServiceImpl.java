package com.ssafy.b208.api.service;

import com.ssafy.b208.api.db.repository.UserPokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GachaServiceImpl implements GachaService {
    private final UserPokemonRepository userPokemonRepository;








}
