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
        name = "mainServlet",
        description = "Main page servlet",
        urlPatterns = {""}
)
public class MainServlet extends HttpServlet {

    private Controller controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        controller = new Controller();
        controller.run();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletConfig().getServletContext().setAttribute("controller", controller);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}