package com.example.elasticsearch.repo;


import com.example.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findAllByAuthorName(String name);

    List<Book> findAllByName(String name);

    List<Book> findAll();

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"author.name\": \"?0\"}}]}}")
    @Query("{\"bool\":{\"should\":[{\"wildcard\":{\"name\":\"?0\"}},{\"wildcard\":{\"authorName\":\"?0\"}},{\"wildcard\":{\"genre\":\"?0\"}}]}}")
    List<Book> findBookUsingCustomQuery(String query);
}
