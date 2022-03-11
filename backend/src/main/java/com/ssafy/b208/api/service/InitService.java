package com.ssafy.b208.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


@Slf4j
@Service
public class InitService {

    public ResponseEntity<String> upLoadIpfs(){
        File imageFile = new File("C:/Users/multicampus/Documents/erd.PNG");
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(imageFile));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        String key="42b11f87d5278e127356";
        String secretKey="c449127f65594fdbb9491fb2d9554d3c084c7459464aa15cde64aaec240a77f3";

        headers.set("pinata_api_key",key);
        headers.set("pinata_secret_api_key",secretKey);
        HttpEntity<MultiValueMap> entity =new HttpEntity<>(bodyMap,headers);
        /*
        ClassPathResource resource = new ClassPathResource("C:/Users/multicampus/Documents/카카오톡 받은 파일/erd.jpg");
        InputStream inputStream = resource.getInputStream();
        File tempImage = File.createTempFile("temp",".jpg");
        try{
            FileUtils.copyInputStreamToFile(inputStream,tempImage);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        */

        log.info("header= {}",headers.get("pinata_api_key"));
        log.info("htto= {}",entity);

        String url= "https://api.pinata.cloud/pinning/pinFileToIPFS";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String>response = restTemplate.postForEntity(url,entity,String.class);
        log.info("response= {}",response);
        return response;
    }

   
}
