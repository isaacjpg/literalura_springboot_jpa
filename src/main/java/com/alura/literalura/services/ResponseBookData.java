package com.alura.literalura.services;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseBookData(
        @JsonAlias("id")
        Long id,
        @JsonAlias("title")
        String title,
        @JsonAlias("download_count")
         Integer download_count,
        @JsonAlias("authors")
        List<ResponseAuthor> authors,
        @JsonAlias("languages")
        List<String> languages
){


}
