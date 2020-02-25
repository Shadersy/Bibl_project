package ru.gvozdilin.bibl.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gvozdilin.bibl.service.BooksService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @Autowired
public BooksService booksService;

    @GetMapping("/books")
    public String getAllBooks(Model model, HttpServletRequest request) throws NullPointerException{
//

        String sort = request.getParameter("type");
        System.out.println(sort);

        if(sort==null){
            model.addAttribute("books", booksService.findAll());
        }
        else
        if(sort.equals("sortByAuthor")){
           model.addAttribute("books", booksService.sortByAuthor());
            System.out.println("kek2");
        }
        else
            if(sort.equals("sortByName"))
        {
            System.out.println("kek1");
            model.addAttribute("books", booksService.sortByName());
        }
        return "books";
    }







    @PostMapping("/delete_book")
    public String delete_book(HttpServletRequest servletRequest){


        int nameForUtility = Integer.parseInt(servletRequest.getParameter("deleteSelect"));
        String buttonDelete = servletRequest.getParameter("delete");
        System.out.println(nameForUtility);
         booksService.deleteBooks(nameForUtility);

    return "redirect:/books";
    }





    @PostMapping("add_book")
    public String add_book(HttpServletRequest servletRequest){
        String name = servletRequest.getParameter("name");
                String author = servletRequest.getParameter("author");

                booksService.addBooks(name, author);
return "redirect:/books";
    }





}
