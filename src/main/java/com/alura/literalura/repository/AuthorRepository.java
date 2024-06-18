package com.alura.literalura.repository;

import com.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndBirthYearAndDeathYear(String name, Integer birthYear, Integer deathYear);

    @Query("SELECT a FROM Author a WHERE a.deathYear >= :year AND a.birthYear <= :year")
    List<Author> findLivingAuthorsByYear(int year);
}
