package com.alura.literalura.model;

import com.alura.literalura.services.ResponseAuthor;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Book> books;

    public Author(){}


    public Author(ResponseAuthor authordata) {
        this.name = authordata.name();
        this.birthYear = authordata.birth_year();
        this.deathYear = authordata.death_year();
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {

        List<String> bookList = books.stream().map(Book::getTitle).toList();

        return "----- AUTOR ------ " + '\n' +
                "Nombre: " + name + '\n' +
                "Año de Nacimiento: " + birthYear + '\n' +
                "Año de Fallecimiento: "+ deathYear + '\n' +
                "Libros: " + bookList + '\n' +
                "------------------"+ '\n';
    }
}
