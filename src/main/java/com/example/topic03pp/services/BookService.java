package com.example.topic03pp.services;

import com.example.topic03pp.utilities.Paginate;
import com.example.topic03pp.utilities.Pagination;
import com.example.topic03pp.utilities.filters.BookFilter;
import com.example.topic03pp.models.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    List<Book> getFilter(BookFilter bookFilter);

    Book findOne(Integer id);

    boolean update(Book book);

    boolean remove(Integer id);

    Integer count();


    boolean create(Book book);

    boolean creates(List<Book> books);


    // all about pagination

    Integer countFilter(BookFilter bookFilter);

    List<Book> getBookFilterPagination(BookFilter bookFilter, Pagination pagination);

    List<Book> getBookFilterPagination(BookFilter bookFilter, Paginate paginate);
}
