package kz.bitlab.academy.users.servlet;

import kz.bitlab.academy.users.entity.UserEntity;
import kz.bitlab.academy.users.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("currentUser", session.getAttribute("currentUser"));

        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        String fullName = req.getParameter("fullName");
        HttpSession session = req.getSession();
        UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");

        String redirect = UserService.update(currentUser, oldPassword, password, rePassword, fullName);

        if (!redirect.isBlank()) {
            resp.sendRedirect("/profile?" + redirect);
            return;
        }

        currentUser.setPassword(password);
        currentUser.setFullName(fullName);
        session.setAttribute("currentUser", currentUser);
        resp.sendRedirect("/profile");
    }
}
