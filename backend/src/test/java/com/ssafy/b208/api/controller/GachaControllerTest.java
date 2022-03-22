package com.ssafy.b208.api.controller;





import com.ssafy.b208.api.dto.response.BaseResponseBody;
import com.ssafy.b208.api.dto.request.WalletRequestDto;
import com.ssafy.b208.api.service.GachaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@SpringBootTest
@AutoConfigureMockMvc
class GachaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    GachaServiceImpl gachaService=new GachaServiceImpl();
    GachaController gachaController= new GachaController(gachaService);

    @Test
    public void 성공시_200상태코드를_반환된다(){
        //given
        WalletRequestDto walletDto=new WalletRequestDto();

        //when
        ResponseEntity<BaseResponseBody> responseEntity=gachaController.getNft(walletDto);
        BaseResponseBody responseBody=responseEntity.getBody();

        //then
        assertThat(responseBody.getStatusCode()).isEqualTo(200);
        assertThat(responseEntity).isInstanceOf(ResponseEntity.class);
    }

    @Test
    public void 실패시_에러를_발생한다() throws Exception {
        //given

        //when

        //then

    }

    @Test
    public void wallet아이디가_없으면_에러를_발생한다() throws Exception {
        //given

        //when

        //then

    }

    @Test
    void 가챠컨트롤러_테스트() throws Exception{
        mockMvc.perform(post("/api/v1/gacha").contentType(MediaType.APPLICATION_JSON).content("{ \"walletId\" : \"testId\" }")).andExpect(status().isOk());
    }
}
