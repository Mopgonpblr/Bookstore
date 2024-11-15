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
        name = "ShowOrdersServlet",
        description = "Show orders list",
        urlPatterns = {"/orders"}
)

public class ShowOrdersServlet extends HttpServlet {

    private Controller controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        controller = (Controller) this.getServletConfig().getServletContext().getAttribute("controller");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", controller.getWebView().getOrdersList());
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}