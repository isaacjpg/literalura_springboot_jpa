package com.alura.literalura.services;

import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

public class GutendexApi {

    DataConvertion convertor = new DataConvertion();

    final String baseUrl = "https://gutendex.com/books/";


    public Book getBook(String search) {
        String url = baseUrl + "?search=" + search.replace(" ","+");
        String json = apiData(url);
        System.out.println(json);
        List<ResponseBookData> responseBookData = convertor.converData(json, ReponseApiData.class).responseBookData();
        Optional<ResponseBookData> findedBook = responseBookData.stream().findFirst();
        if(findedBook.isPresent()){

            String language = findedBook.get().languages().stream().findFirst().orElse("No se encontraron idiomas");
            Optional<ResponseAuthor> author = findedBook.get().authors().stream().findFirst();
            if(author.isPresent()){
                System.out.println(author.get().name());
            }
            else {
                System.out.println("No se encontraron autores");
            }
            System.out.println(language);

            return new Book(findedBook.get());

        }else{
            System.out.println("No se encontraron resultados");
            return null;
        }
    }

    private String apiData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }







}
