package kz.bitlab.academy.news.servlets;

import kz.bitlab.academy.categories.service.CategoryService;
import kz.bitlab.academy.news.service.NewsService;
import kz.bitlab.academy.users.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("newsList", NewsService.findAll());
        req.setAttribute("categories", CategoryService.findAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");

        if (currentUser.getRoleId() != 1) {
            throw new RuntimeException("Permissions denied!!!");
        }

        Long categoryId = Long.parseLong(req.getParameter("categoryId"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        NewsService.create(categoryId, title, content);
        resp.sendRedirect("/");
    }
}
