package com.alura.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvertion implements IDataConvertion{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T converData(String json, Class<T> clase) {
        try{
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
