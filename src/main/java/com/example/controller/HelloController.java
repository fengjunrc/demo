package com.example.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by fj on 2017/4/12.
 */
@RestController
public class HelloController {


    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String sayHello(){
        System.out.println("hello world"+bookAuthor+"bookName="+bookName);
        return  "bookAuthor="+bookAuthor+",bookName="+bookName;
    }
}
