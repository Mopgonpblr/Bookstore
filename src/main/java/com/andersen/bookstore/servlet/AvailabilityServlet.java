package com.andersen.bookstore.servlet;

import com.andersen.bookstore.controller.Controller;
import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Bookstore;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "availabilityServlet",
        description = "Change book availability",
        urlPatterns = {"/availability"}
)
public class AvailabilityServlet extends HttpServlet {

    private Controller controller;
    private Bookstore bookstore;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        controller = (Controller) this.getServletConfig().getServletContext().getAttribute("controller");
        bookstore = controller.getWebView().getBookstore();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder bookList = new StringBuilder();
        for (Book book : bookstore.getBooks()) {
            bookList.append("<br>").append(book);
        }

        req.setAttribute("bookList", bookList.toString());
        req.getRequestDispatcher("availability.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("action");

        int bookNumber = Integer.parseInt(req.getParameter("bookNumber"));

        String isAvailable = req.getParameter("isAvailable");

        if ("submit".equals(action)) {
            if ("yes".equals(isAvailable)) {
                bookstore.setBookAvailability(bookNumber, true);
            } else if ("no".equals(isAvailable)) {
                bookstore.setBookAvailability(bookNumber, false);
            }
        }

        resp.sendRedirect("/bookstore");
    }
}
