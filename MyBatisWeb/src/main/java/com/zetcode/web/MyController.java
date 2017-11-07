package com.zetcode.web;

import com.zetcode.bean.Book;
import com.zetcode.service.BooksService;
import com.zetcode.service.IBooksService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyController", urlPatterns = {"/mycontroller"})
public class MyController extends HttpServlet {

    private static final String ACTION_KEY = "action";
    private static final String SHOW_MY_BOOKS_ACTION = "showMyBooks";

    private static final String UNKNOWN_VIEW = "unknown.jsp";
    private static final String SHOW_MY_BOOKS_VIEW = "showMyBooks.jsp";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter(ACTION_KEY);
        String page = "";

        if (SHOW_MY_BOOKS_ACTION.equals(action)) {

            IBooksService bookService = new BooksService();
            List<Book> books = bookService.getAllBooks();

            request.setAttribute("mybooks", books);

            page = SHOW_MY_BOOKS_VIEW;
        } else {
            page = UNKNOWN_VIEW;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }
}
