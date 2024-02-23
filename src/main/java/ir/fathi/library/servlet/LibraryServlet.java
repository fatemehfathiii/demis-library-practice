package ir.fathi.library.servlet;

import ir.fathi.library.service.BookService;
import ir.fathi.library.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "books", urlPatterns = "/books")
public class LibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        req.setAttribute("bookList", bookService.getBooks());
        req.setAttribute("counter",1);
        req.getRequestDispatcher("/WEB-INF/views/bookList").forward(req, resp);
    }
}
