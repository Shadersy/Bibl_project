package ru.gvozdilin.bibl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gvozdilin.bibl.entity.Books;
import ru.gvozdilin.bibl.service.BooksService;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PaginationBooks {

    @Autowired
    public BooksService booksService;

    @GetMapping("/api/get-books")
    public String booksPaginationAction(HttpServletRequest request)
    {
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

        List<String> booksJson = new ArrayList<>();

        for (Books book: books ) {
            String bookJson = "{" +
                    "\"id\":" + book.getId() + "," +
                    "\"name\":\"" + book.getName() + "\"," +
                    "\"author\":\"" + book.getAuthor() + "\"," +
                    "\"username\":\"" + book.getUsername() + "\"}";
            booksJson.add(bookJson);
        }

        return "[" + String.join(",", booksJson) + "]";
    }

}
