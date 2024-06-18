package com.alura.literalura.services;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReponseApiData(
        @JsonAlias("results")
        List<ResponseBookData> responseBookData

){
}
