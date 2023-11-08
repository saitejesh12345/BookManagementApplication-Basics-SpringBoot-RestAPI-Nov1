package com.example.bookManagement.controller;

import com.example.bookManagement.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class BookController {
  //  map id --> book we can use map for storage Database

    Map<Integer, Book> data = new HashMap<>(); //temp database

    @PostMapping("/add-Book")
    public String addBook(@RequestBody Book book){ //json-format
        data.put(book.getBookId(),book);
        //System.out.println(data.get(book.getBookId()));
        return "Book with id:" + book.getBookId()+" "+"added successfully";
    }

    @PostMapping("/addBook")
    public Book addBooktoShelf(@RequestBody Book book){ //json-format
        data.put(book.getBookId(),book);
        //System.out.println(data.get(book.getBookId()));
        return book;
    }
    @PostMapping("/add-fill-Book")
    public String addBookByFilling(@RequestParam int bookId,@RequestParam String title,
                                   @RequestParam String author, @RequestParam int pages){ //json-format
        Book book = new Book(bookId,title,author,pages);
        data.put(book.getBookId(),book);
        //System.out.println(data.get(book.getBookId()));
        return "Book with id:" + book.getBookId()+" "+"added successfully";
    }

    @GetMapping("/find-book/{id}") //    ? id = 1
    public Book findBook(@RequestParam int id){
      //  if(data.containsKey(id))
        return  data.get(id);

    }

    @GetMapping("/findbook/{id}") //   findbook/1
    public Book findBookById(@PathVariable("id") int id){
        return  data.get(id);
    }


    //if i dont want to update author name we can keeo optional
    //and we can keep if condition.
    @PutMapping("/updated-book")
    public String updateBook(@PathVariable int id,@RequestParam String title,
                             @RequestParam(required = false) String author,@RequestParam int pages){
        Book book = data.get(id);
        if(author!= null){
            book.setAuthor(author);
        }
        book.setTitle(title);
        book.setPages(pages);
        data.put(id,book);
        return "book updated";
    }


//    @PutMapping("/updated-book")
//    public String updateBookMarkDeafult(@RequestParam int id,@RequestParam(defaultValue = "default",required = false) String title,
//                             @RequestParam String author,@RequestParam int pages){
//        Book book = data.get(id);
//        if(Objects.nonNull(title)){
//            book.setTitle(title);
//        }
//        book.setTitle(title);
//        book.setAuthor(author);
//        book.setPages(pages);
//        data.put(id,book);
//        return "book updated";
//    }


//    @PutMapping("/updated-book")
//    public String updateBookMark(@RequestParam int id,@RequestParam   String title,
//                             @RequestParam String author,@RequestParam int pages){
//        Book book = data.get(id);
//        book.setTitle(title);
//        book.setAuthor(author);
//        book.setPages(pages);
//        data.put(id,book);
//        return "book updated";
//    }


    @DeleteMapping("/remove-book/{id}")
    public String  deleteBook(@PathVariable int id){
        data.remove(id);
       return "Book deleted Succesfull";
    }
}