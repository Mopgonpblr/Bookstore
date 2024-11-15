package com.andersen.bookstore.servlet;

import com.andersen.bookstore.controller.Controller;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        controller = (Controller) this.getServletConfig().getServletContext().getAttribute("controller");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookList = controller.getWebView().getOrderBookList();

        req.setAttribute("order", controller.getWebView().getBookstore().getCurrentOrder());

        req.setAttribute("orderBookList", bookList);

        req.getRequestDispatcher("/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("submit".equals(action)) {
            controller.getWebView().getBookstore().completeOrder();
        } else if ("cancel".equals(action)) {
            controller.getWebView().getBookstore().cancelOrder();
        }

        controller.getDataControl().saveLastState(controller.getWebView().getBookstore(), controller.getFilepath());
        controller.getDataControl().loadLastState(controller.getFilepath());
        resp.sendRedirect("/bookstore");
    }
}