package com.example.elasticsearch.service;

import com.example.elasticsearch.dto.BookDTO;
import com.example.elasticsearch.entity.Book;
import com.example.elasticsearch.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<BookDTO> getBooks(){
        List<Book> bookList = bookRepository.findAll();
        return convertToDtoList(bookList);
    }

    public void saveBook(BookDTO bookDTO){
        Book book = new Book();
        bookRepository.save(convertToEntity(bookDTO, book));
    }

    public BookDTO findBookById(String bookId){
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            return convertToDto(book);
        }
        return null;
    }

    public List<BookDTO> searchBook(String query){
        List<Book> bookList = bookRepository.findBookUsingCustomQuery(query+"*");
        return convertToDtoList(bookList);
    }

    public void updateBook(BookDTO bookDTO){
        Optional<Book> optionalBook = bookRepository.findById(bookDTO.getId());
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            bookRepository.save(convertToEntity(bookDTO, book));
        }
    }

    public void deleteBook(String bookId){
        bookRepository.deleteById(bookId);
    }

    private List<BookDTO> convertToDtoList(List<Book> bookList){
        List<BookDTO> books = new ArrayList<>();
        bookList.forEach(book -> books.add(convertToDto(book)));
        return books;
    }

    private BookDTO convertToDto(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthorName(book.getAuthorName());
        bookDTO.setGenre(book.getGenre());
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO, Book book){
        book.setName(bookDTO.getName());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setGenre(bookDTO.getGenre());
        return book;
    }


}
