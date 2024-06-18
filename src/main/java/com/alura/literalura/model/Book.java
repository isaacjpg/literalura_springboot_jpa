package com.alura.literalura.model;

import com.alura.literalura.services.ResponseAuthor;
import com.alura.literalura.services.ResponseBookData;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private Long Id;

    private String title;
    private Integer download_count;
    private String languages;

    @ManyToOne
    private Author author;

    public Book(){}

    public Book(ResponseBookData bookData) {
        this.Id = bookData.id();
        this.title = bookData.title();
        this.download_count = bookData.download_count();
        this.languages = bookData.languages().stream().findFirst().orElse("No se encontraron idiomas");
        Optional<ResponseAuthor> authorData = bookData.authors().stream().findFirst();
        if(authorData.isPresent()){
            this.author = new Author(
                    authorData.get()
            );
        }
        else {
            this.author = null;
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "----- LIBRO ------ " + '\n' +
                "Titulo: " + title + '\n' +
                "Autor: " + author.getName() + '\n' +
                "Idioma: "+ languages + '\n' +
                "Descargas: " + download_count + '\n' +
                "------------------"+ '\n';
    }
}
