package ru.gvozdilin.bibl.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gvozdilin.bibl.dao.BooksDaoImpl;
import ru.gvozdilin.bibl.dao.UserDaoImpl;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.service.BooksService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @Autowired
    public BooksService booksService;

    @Autowired
    public UserDaoImpl userDao;

//
//    @Autowired
//    MyUserDetailsService myUserDetailsService;


    @RequestMapping(value="/viewemp/{pageid}")
    public String edit(@PathVariable int pageid, Model m){
        int total = 5;
        if(pageid==1){ }
        else{
            pageid=(pageid-1)*total+1;
        }
        System.out.println(pageid);
        List<Books> list = booksService.getEmployeesByPage(pageid, total);
    return "viewemp";
    }




    @GetMapping("/books")
    public String getAllBooks(Model model, Model model2, HttpServletRequest request) throws NullPointerException {

        String sort = request.getParameter("type");
        System.out.println(sort);

        if (sort == null) {
            model.addAttribute("books", booksService.findAll());
        } else if (sort.equals("sortByAuthor")) {
            model.addAttribute("books", booksService.sortByAuthor());
            System.out.println("kek2");
        } else if (sort.equals("sortByName")) {
            System.out.println("kek1");
            model.addAttribute("books", booksService.sortByName());
        }

        model2.addAttribute("users", userDao.findAll());


        return "books";
    }


    @PostMapping("/delete_book")
    public String delete_book(HttpServletRequest servletRequest) {


        int nameForUtility = Integer.parseInt(servletRequest.getParameter("deleteSelect"));
        String buttonDelete = servletRequest.getParameter("delete");
        System.out.println(nameForUtility);
        booksService.deleteBooks(nameForUtility);

        return "redirect:/books";
    }


    @PostMapping("/edit_book")
    public String edit_book(HttpServletRequest servletRequest){
        int idForEdit = Integer.parseInt(servletRequest.getParameter("editSelect"));
        System.out.println(idForEdit);

        String nameOfBook = servletRequest.getParameter("nameOfBook");
        System.out.println(nameOfBook);

        booksService.editBooks(nameOfBook,idForEdit);
        return "redirect:/books";
    }



    @PostMapping("/take_book")
    public String take_book(HttpServletRequest servletRequest){
        int idOfBooksForTake = Integer.parseInt(servletRequest.getParameter("take_book"));
        System.out.println(idOfBooksForTake);

        int idOfUserForTake = Integer.parseInt(servletRequest.getParameter("choose_user"));
        System.out.println(idOfUserForTake);

        booksService.takeBooks(idOfBooksForTake, idOfUserForTake);
        return "redirect:/books";
    }



    @PostMapping("add_book")
    public String add_book(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String author = servletRequest.getParameter("author");

        booksService.addBooks(name, author);
        return "redirect:/books";
    }




}
