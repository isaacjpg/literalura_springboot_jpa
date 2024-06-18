package com.alura.literalura.principal;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.services.GutendexApi;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {


    private Scanner teclado = new Scanner(System.in);

    private BookRepository bookRepository;
    private AuthorRepository authorrepository;

    public Principal(BookRepository bookRepository,AuthorRepository authorrepository) {
        this.bookRepository = bookRepository;
        this.authorrepository = authorrepository;
    }

    public void showMenu() {
        var opcion = -1;
        while(opcion!=0){
            System.out.println("1. Buscar y guardar libro");
            System.out.println("2. Listar libros guardados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("0. Salir");
            opcion = Integer.parseInt(teclado.nextLine());
            switch (opcion){
                case 1:
                    searchAndSaveBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    listLivingAuthorsByYear();
                    break;
                case 5:
                    listBookByLanguage();
                    break;
                case 0:
                    System.out.println(">> Saliendo...");
                    break;
                default:
                    System.out.println(">> Opción no válida");
            }
        }

    }

    private void listBookByLanguage() {
        System.out.println("Introduce el idioma: ");
        System.out.println("es - Español");
        System.out.println("en - Inglés");
        System.out.println("fr - Francés");
        System.out.println("pt - Portugués");
        var language = teclado.nextLine();
        var languageName = "";
        switch (language){
            case "es":
                languageName = "Español";
                break;
            case "en":
                languageName = "Inglés";
                break;
            case "fr":
                languageName = "Francés";
                break;
            case "pt":
                languageName = "Portugués";
                break;
            default:
                System.out.println(">> Idioma no válido");
                return;
        }
        List<Book> books = bookRepository.findByLanguages(language);
        if(books.isEmpty()){
            System.out.println(">> No se encontraron libros en el idioma " + languageName);
            return;
        }
        books.forEach(System.out::println);
    }

    private void listLivingAuthorsByYear() {
        System.out.println("Introduce el año: ");
        var year = Integer.parseInt(teclado.nextLine());
        List<Author> authors = authorrepository.findLivingAuthorsByYear(year);
        if(authors.isEmpty()){
            System.out.println("No se encontraron autores vivos en el año " + year);
            return;
        }
        authors.forEach(System.out::println);
    }

    private void listAllAuthors() {
        List<Author> authors = authorrepository.findAll();
        authors.forEach(System.out::println);
    }

    private void searchAndSaveBook(){
        System.out.println("Introduce el título del libro que deseas buscar: ");
        var titulo = teclado.nextLine();
        GutendexApi api = new GutendexApi();
        Book findedBook = api.getBook(titulo);
        System.out.println(">> Libro encontrado: " + findedBook);

        //Busca el libro en la base de datos
        Optional<Book> findedBookInDB = bookRepository.findById(findedBook.getId());
        if(findedBookInDB.isPresent()) {
            System.out.println(">> El libro ya se encuentra en la base de datos");
            System.out.println(findedBookInDB.get());
            return;
        }

        //Busca el autor en la base de datos
        Optional<Author> findedAuthor = authorrepository.findByNameAndBirthYearAndDeathYear(
                findedBook.getAuthor().getName(),
                findedBook.getAuthor().getBirthYear(),
                findedBook.getAuthor().getDeathYear());

        Author author;
        if(findedAuthor.isPresent()){
            author = findedAuthor.get();
        }else if(findedBook.getAuthor().getName() == null){
            return;
        }else{
            author = findedBook.getAuthor();
            authorrepository.save(author);
        }
        findedBook.setAuthor(author);
        bookRepository.save(findedBook);
    }

    private void listBooks(){
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

}
