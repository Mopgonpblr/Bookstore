package com.andersen.bookstore.servlet;

import com.andersen.bookstore.controller.Controller;
import com.andersen.bookstore.controller.DataControl;
import com.andersen.bookstore.model.Bookstore;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "newOrderServlet",
        description = "Opens new oder",
        urlPatterns = {"/orders/new"}
)

public class NewOrderServlet extends HttpServlet {

    private Controller controller;
    private Bookstore bookstore;
    private String filepath;
    private DataControl dataControl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        controller = (Controller) this.getServletConfig().getServletContext().getAttribute("controller");
        dataControl = controller.getDataControl();
        bookstore = controller.getWebView().getBookstore();
        filepath = controller.getFilepath();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookList = controller.getWebView().getOrderBookList();

        req.setAttribute("order", bookstore.getCurrentOrder());

        req.setAttribute("orderBookList", bookList);

        req.getRequestDispatcher("/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("action");

        if ("submit".equals(action)) {
            bookstore.completeOrder();
        } else if ("cancel".equals(action)) {
            bookstore.cancelOrder();
        }

        dataControl.saveLastState(bookstore, filepath);
        dataControl.loadLastState(filepath);
        resp.sendRedirect("/bookstore");
    }
}