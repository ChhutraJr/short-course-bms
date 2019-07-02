package com.example.topic03pp.controllers.restcontrollers;


import com.example.topic03pp.models.Book;
import com.example.topic03pp.services.BookService;
import com.example.topic03pp.services.UploadService;
import com.example.topic03pp.utilities.Pagination;
import com.example.topic03pp.utilities.filters.BookFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/book")
@RestController
@ApiResponses({
        @ApiResponse(code = 404, message = "Not Found Custom")
})
@Api(description = "Book Rest Controller Class Custom")
public class BookRestController {

    private BookService bookService;

    @Autowired
    private UploadService uploadService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "Filter Book by Book Title and Cate Id")
    @GetMapping("/filter")
    public List<Book> getAll(BookFilter bookFilter) {
        return this.bookService.getFilter(bookFilter);
    }


    @GetMapping("/test1")
    public String test() {
        return "fragments/header :: fr-header";
    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFileAjax(MultipartFile file) {
        String filename = this.uploadService.upload(file);
        Map<String, Object> response = new HashMap<>();
        response.put("name", filename);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllBook() {
        Map<String, Object> response = new HashMap<>();
        List<Book> books = this.bookService.getAll();

        if (books == null) {
            response.put("message", "Book Not Found!");
            response.put("status", false);
            response.put("record_count", 0);
        } else {
            response.put("message", "Book Found!");
            response.put("status", true);
            response.put("books", books);
            response.put("record_count", books.size());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/multiple")
    public ResponseEntity<Map<String, Object>> creates(@RequestBody List<Book> books) {
        Map<String, Object> response = new HashMap<>();

        boolean status = this.bookService.creates(books);
        if (!status) {
            response.put("message", "Failed!!!");
            response.put("status", false);
        } else {
            response.put("message", "OK Looking good!");
            response.put("status", true);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> getBookFilterPagination(BookFilter bookFilter, Pagination pagination) {

        if (bookFilter == null) {
            System.out.println("meme");
        }
        System.out.println(bookFilter);


        if (true) {
            System.out.println("hermes");
            pagination.setTotalCount(this.bookService.countFilter(bookFilter));
        } else {
            Integer totalBookRecord = this.bookService.count();
            System.out.println("gucci");
            pagination.setTotalCount(totalBookRecord);

        }

        System.out.println(pagination);


        Map<String, Object> response = new HashMap<>();
        List<Book> books = this.bookService.getBookFilterPagination(bookFilter, pagination);

        if (books == null || books.size() == 0) {
            response.put("status", false);
            response.put("message", "Book Not Found!!!");
            response.put("paginate", pagination);
            response.put("data", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", true);
            response.put("message", "Book Found!!!");
            response.put("paginate", pagination);
            response.put("data", books);

            return ResponseEntity.ok(response);
        }
    }

}
