package ru.gvozdilin.bibl.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gvozdilin.bibl.dao.UserDaoImpl;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.service.BooksService;
import ru.gvozdilin.bibl.service.MyUserPrincipal;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @Autowired
    public BooksService booksService;

    @Autowired
    public UserDaoImpl userDao;

    @GetMapping("/books")
    public String getAllBooks(Model model, HttpServletRequest request) throws NullPointerException {
        String sort = request.getParameter("type");
        String pageSizeString = request.getParameter("pageSize");
        String pageNumberString = request.getParameter("pageNumber");

        if (null == pageNumberString) {
            pageNumberString = "0";
        } else if (null == pageSizeString) {
            pageSizeString = "5";
        }

        int pageSize = 5;
        int pageNumber = 0;

        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        catch (Exception e) {

        }
        List<Books> books = booksService.getBooksSortedPaginated(sort, pageSize, pageNumber);
        model.addAttribute("leftJoinBooksUser", books);
        model.addAttribute("find", booksService.findAll());
        Long idOfUserForTake = ((MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        model.addAttribute("returnedBooks", booksService.takenBooks(idOfUserForTake));
        model.addAttribute("notReservedBooks", booksService.showNotReservedBooks());
        model.addAttribute("getUsernameByUserId", userDao.getUsernameByUserId(idOfUserForTake));


        return "books";
    }


    @PostMapping("/delete_book")
    public String delete_book(HttpServletRequest servletRequest) {

        int nameForUtility = Integer.parseInt(servletRequest.getParameter("deleteSelect"));
        System.out.println(nameForUtility);
        booksService.deleteBooks(nameForUtility);
        return "redirect:/books";
    }

    @PostMapping("/return_book")
    public String return_book(HttpServletRequest httpServletRequest, Model model){
    Long idOfBooksForReturn = Long.parseLong(httpServletRequest.getParameter("returnSelect"));
        System.out.println(idOfBooksForReturn);
        booksService.returnBooks(idOfBooksForReturn);
        return "redirect:/books";
    }


    @PostMapping("/edit_book")
    public String edit_book(HttpServletRequest servletRequest){
        servletRequest.getContextPath();
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
        Long idOfUserForTake = ((MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
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
