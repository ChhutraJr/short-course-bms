package com.example.topic03pp.services.impl;

import com.example.topic03pp.models.Book;
import com.example.topic03pp.repositories.BookRepository;
import com.example.topic03pp.services.BookService;
import com.example.topic03pp.utilities.Paginate;
import com.example.topic03pp.utilities.Pagination;
import com.example.topic03pp.utilities.filters.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {

//        Code to do with business

        return this.bookRepository.getAll();
    }


    @Override
    public List<Book> getFilter(BookFilter bookFilter) {
        return this.bookRepository.getFilter(bookFilter);
    }

    @Override
    public Book findOne(Integer id) {

        return this.bookRepository.findOne(id);
    }

    @Override
    public boolean update(Book book) {
        return this.bookRepository.update(book);
    }

    @Override
    public boolean remove(Integer id) {
        return this.bookRepository.remove(id);
    }

    @Override
    public Integer count() {
        return this.bookRepository.count();
    }


    @Override
    public boolean create(Book book) {
        return this.bookRepository.create(book);
    }

    @Override
    public boolean creates(List<Book> books) {
        return this.bookRepository.creates(books);
    }


    @Override
    public Integer countFilter(BookFilter bookFilter) {
        return this.bookRepository.countFilter(bookFilter);
    }
    @Override
    public List<Book> getBookFilterPagination(BookFilter bookFilter, Pagination pagination) {
        return this.bookRepository.getBookFilterPagination(bookFilter, pagination);
    }

    @Override
    public List<Book> getBookFilterPagination(BookFilter bookFilter, Paginate paginate) {

        return this.bookRepository.getBookFilterPaginate(bookFilter, paginate);
    }
}
