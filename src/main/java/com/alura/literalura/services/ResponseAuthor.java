package com.alura.literalura.services;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ResponseAuthor(
        @JsonAlias("name")
        String name,
        @JsonAlias("birth_year")
        Integer birth_year,
        @JsonAlias("death_year")
        Integer death_year) {
}
