package kz.bitlab.academy.news.servlets;

import kz.bitlab.academy.news.service.NewsService;
import kz.bitlab.academy.users.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteNews")
public class DeleteNews extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");

        if (currentUser.getRoleId() != 1) {
            throw new RuntimeException("Permissions denied!!!");
        }

        Long id = Long.parseLong(req.getParameter("id"));
        NewsService.delete(id);
        resp.sendRedirect("/");
    }
}
