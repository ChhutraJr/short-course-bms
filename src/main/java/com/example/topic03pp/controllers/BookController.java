package com.example.topic03pp.controllers;

import com.example.topic03pp.utilities.Paginate;
import com.example.topic03pp.utilities.filters.BookFilter;
import com.example.topic03pp.models.Book;
import com.example.topic03pp.models.Category;
import com.example.topic03pp.services.BookService;
import com.example.topic03pp.services.CategoryService;
import com.example.topic03pp.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    private UploadService uploadService;

    public BookController(BookService bookService, UploadService uploadService) {
        this.bookService = bookService;
        this.uploadService = uploadService;
    }

    //    @GetMapping("/book")
    @RequestMapping(method = RequestMethod.GET, value = {"/book"})
    public String allBook(ModelMap model, BookFilter bookFilter) {

        System.out.println(bookFilter);

        List<Book> bookList = this.bookService.getFilter(bookFilter);

        model.addAttribute("books", bookList);
        model.addAttribute("filter", new BookFilter());

        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);

        System.out.println(categories);

        return "book/all-book";
    }

    //    @GetMapping("/book")
    @RequestMapping(method = RequestMethod.GET, value = {"/book/paginate"})
    public String allBookPaginate(ModelMap model, BookFilter bookFilter, Paginate paginate) {

        System.out.println(bookFilter);

        List<Book> bookList = this.bookService.getBookFilterPagination(bookFilter, paginate);

        if (bookFilter.getCateId() != null || bookFilter.getBookTitle() != null ){
            System.out.println("meme");
            paginate.setTotalCount(this.bookService.countFilter(bookFilter));
        }

        else {
            int totalBookRecord = this.bookService.count();
            paginate.setTotalCount(totalBookRecord);
        }
//        paginate.setLimit(5);
        System.out.println(paginate);

//        paginate.setTotalCount(bookList.size());

        System.out.println(bookList);

        model.addAttribute("books", bookList);
        model.addAttribute("filter", bookFilter);
        model.addAttribute("paginate", paginate);

        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);

        return "book/all-book";
    }


    @GetMapping("/view/{id}")
    public String viewDetail(@PathVariable("id") Integer id, Model model) {

        System.out.println("ID: " + id);

        Book book = this.bookService.findOne(id);

        model.addAttribute("book", book);

        return "book/view-detail";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, ModelMap modelMap) {

        Book book = this.bookService.findOne(id);

        modelMap.addAttribute("isNew", false);
        modelMap.addAttribute("book", book);

        List<Category> categories = this.categoryService.getAll();
        modelMap.addAttribute("categories", categories);


        return "book/add-book";
    }


    @PostMapping("update/submit")
    public String updateSubmit(@Valid Book book, BindingResult bindingResult, Model model, MultipartFile file) {
        System.out.println(book);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isNew", false);
            model.addAttribute("categories", this.categoryService.getAll());
            return "book/add-book";
        }

        String filename = this.uploadService.upload(file);

        if (filename != null)
            book.setThumbnail(filename);

        this.bookService.update(book);

        return "redirect:/book";
    }


    @GetMapping("remove/{id}")
    public String remove(@PathVariable Integer id) {
        this.bookService.remove(id);

        return "redirect:/book";
    }


    @GetMapping("/count")
    @ResponseBody
    public Map<String, Object> count() {
        Map<String, Object> response = new HashMap<>();

        response.put("record_count", this.bookService.count());
        response.put("status", true);

        return response;
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("isNew", true);

        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);

        System.out.println(categories);

        return "book/add-book";
    }


    @PostMapping("/create/submit")
    public String createSubmit(@Valid Book book, BindingResult bindingResult, MultipartFile file, Model model) {
        System.out.println(book);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isNew", true);

            List<Category> categories = this.categoryService.getAll();
            model.addAttribute("categories", categories);

            return "book/add-book";
        }

        String filename = this.uploadService.upload(file, "pp/");

        if (filename != null)
            book.setThumbnail(filename);

        this.bookService.create(book);

        System.out.println(book);

        return "redirect:/book";
    }


    @GetMapping("/test-multiple-upload")
    public String testMultipleUpload() {
        return "book/test-multiple-upload";
    }


    @PostMapping("/test-multiple-upload/submit")
    public String uploadSubmit(@RequestParam("file") List<MultipartFile> files) {

        if (files.isEmpty()) {
            System.out.println("nothing");
        }


        files.forEach(file -> {
            System.out.println(file.getOriginalFilename());
        });


        return "";
    }


}
